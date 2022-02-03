package com.nft.signdoc.repository

import com.google.gson.Gson
import com.nft.signdoc.extensions.getMimeType
import com.nft.signdoc.extensions.safeCall
import com.nft.signdoc.extensions.safeCallWithHttpError
import com.nft.signdoc.data.localcontact.ContactSource
import com.nft.signdoc.data.preference.SharePrefs
import com.nft.signdoc.model.Contact
import com.nft.signdoc.model.nft.toDomainModel
import com.nft.signdoc.model.toDomain
import com.nft.signdoc.model.toDomainModel
import com.nft.signdoc.model.transaction.TransactionDirection
import com.nft.signdoc.model.transaction.toDomainModel
import com.nft.signdoc.data.networks.*
import com.nft.signdoc.data.networks.request.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class Repository(
    private val api: Api,
    private val transactionApi: TransactionApi,
    private val contactApi: ContactApi,
    private val nftApi: NFTApi,
    private val userApi: UserApi,
    private val loginApi: LoginApi,
    private val sharePrefs: SharePrefs,
    private val localContact: ContactSource
) {

    fun isLoggedIn() = sharePrefs.accessToken.isNotEmpty()

    suspend fun getContacts() = safeCall {
        val dtoContacts = contactApi.getContacts(sharePrefs.userId)
        dtoContacts.data.mapNotNull { it.toDomainModel() }
    }

    suspend fun getTransactions() = safeCall {
        val dtoTransactions = transactionApi.getTransaction(sharePrefs.userId)
        dtoTransactions.data.map { it.toDomainModel() }
    }

    suspend fun getSentTransactions() = safeCall {
        val dtoTransactions = transactionApi.getTransaction(sharePrefs.userId)
        dtoTransactions.data
            .map { it.toDomainModel() }
            .filter { it.direction == TransactionDirection.Outgoing }
    }

    suspend fun getRecvTransactions() = safeCall {
        val dtoTransactions = transactionApi.getTransaction(sharePrefs.userId)
        dtoTransactions.data
            .map { it.toDomainModel() }
            .filter { it.direction == TransactionDirection.Incoming }
    }

    suspend fun getRecentTransactions() = safeCall {
        val dtoTransactions = transactionApi.getTransaction(sharePrefs.userId).data.take(20)
        dtoTransactions.map { it.toDomainModel() }
    }

    suspend fun getAllNFTCollection() = safeCall {
        val dtoNft = nftApi.getAllNFTCollections(sharePrefs.userId)
        dtoNft.data.filter { it.parentId == null }.map { it.toDomainModel() }
    }

    suspend fun createUser(
        name: String,
        walletId: String,
        phone: String,
        email: String,
        claimNFTID: String? = null
    ) =
        safeCallWithHttpError {
            val request = DtoUserCreateNFTRequest(
                fullName = name,
                walletName = walletId,
                phone = phone,
                email = email,
                nftID = claimNFTID
            )
            val dtoResponse = userApi.createUser(request).apply {
                sharePrefs.userId = userInfo.id
                sharePrefs.userName = userInfo.fullName ?: ""
                sharePrefs.walletName = walletId
                sharePrefs.accessToken = accessToken
                sharePrefs.idToken = idToken
                sharePrefs.refreshToken = refreshToken
                sharePrefs.userInfo = Gson().toJson(userInfo)
            }
            dtoResponse.userInfo.toDomain()
        }

    suspend fun login(walletName: String) =
        safeCallWithHttpError {
            val request = DtoLoginRequest(walletName = walletName)
            val dtoResponse = loginApi.login(request).apply {
                sharePrefs.loginType = type
                sharePrefs.walletName = walletName
            }
            dtoResponse
        }

    suspend fun verifyLogin(walletName: String, nonce: String) =
        safeCallWithHttpError {
            val request = DtoLoginRequest(
                walletName = walletName,
                nonce = nonce
            )
            val dtoResponse = loginApi.verifyLogin(request).apply {
                sharePrefs.userId = userInfo.id
                sharePrefs.userName = userInfo.fullName ?: ""
                sharePrefs.walletName = walletName
                sharePrefs.accessToken = accessToken
                sharePrefs.idToken = idToken
                sharePrefs.refreshToken = refreshToken
                sharePrefs.userInfo = Gson().toJson(userInfo)
            }
        }


    suspend fun sendTransaction(request: DtoSendTransactionRequest) = safeCall {
        val response = transactionApi.sendTransaction(request)
        response.isSuccessful
    }

    suspend fun createNft(nftCreateRequest: NftCreateRequest) = safeCallWithHttpError {
        val filePart = MultipartBody.Part.createFormData(
            "file",
            nftCreateRequest.file.name,
            nftCreateRequest.file.asRequestBody(
                nftCreateRequest.file.getMimeType().toMediaTypeOrNull()
            )
        )
        val nftInfoJsonString = Gson().toJson(nftCreateRequest.nftInformation)
        val dtoResponse = nftApi.createNft(nftInfoJsonString.toRequestBody(), filePart)
        dtoResponse.message
    }

    suspend fun postLocalContact(contacts: List<Contact>) = safeCall {
        val request = localContact.getAllContactWithEmail(sharePrefs.userId)
        contactApi.importContact(contacts)
    }

    suspend fun postAddLocalContact(contacts: Contact) = safeCall {
        contactApi.addContact(contacts.apply { owner_id = sharePrefs.userId })
    }

    suspend fun getLocalContact() = safeCall {
        localContact.getAllContactWithEmail(sharePrefs.userId)

    }

    suspend fun getNFTDetails(nftId: String) = safeCall {
        val nftDetailsResponse = nftApi.getNFTDetails(nftId)
        nftDetailsResponse.data.toDomainModel()
    }

    suspend fun claimNFT(nftId: String) = safeCallWithHttpError {
        val climNftResponse = nftApi.claimNFT(nftId, ClimNFTRequest(sharePrefs.userId))
        climNftResponse.message
    }

    suspend fun getAllDocuments() = safeCallWithHttpError {
        val allDocumentsResponse = nftApi.getAllDocuments("qqyhIUfU02qvW3TAoGNKX-x")
        allDocumentsResponse.data
    }

    suspend fun getUserProfile(userId: String) = safeCall {
        val dtoResponse = api.getUserProfile(userId)
        dtoResponse.dtoUserInfo.toDomain()
    }

    suspend fun modifyUser(userId: String, currentPhone: String, currentEmail: String) = safeCall {
        val dToUser = DtoUserCreateRequest(
            sharePrefs.userName,
            sharePrefs.walletName,
            currentPhone,
            currentEmail
        )
        api.modifyUser(sharePrefs.userId, dToUser)
    }

    suspend fun refreshToken(walletId: String, refreshToken: String) = safeCallWithHttpError {
        val dtoRequest = DtoRefreshTokenRequest(walletId, refreshToken)
        userApi.refreshToken(dtoRequest)
    }

    fun getUserId() = sharePrefs.userId
}