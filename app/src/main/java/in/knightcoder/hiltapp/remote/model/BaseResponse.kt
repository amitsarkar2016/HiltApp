package `in`.knightcoder.hiltapp.remote.model

data class BaseResponse(
    val status: Boolean,
    val message: String? = null,
)