package br.com.pagingcompose.network

sealed class Resource<out R>(val status: Status, val data: R?, val exception: Exception?) {
    data class Success<out T>(val content: T?) : Resource<T>(Status.SUCCESS, content, null)
    data class Error(val e: Exception) : Resource<Nothing>(Status.ERROR, null, exception = e)
}
