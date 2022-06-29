package utility

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

data class Response<T>(
    val status: ResponseType,
    val message: String? = null,
    val body: T? = null,
    val headers: Map<CharSequence, CharSequence> = emptyMap()
) {

    fun getHttpResponse(): HttpResponse<Any> =
        when (status) {
            ResponseType.SUCCESS -> HttpResponse.ok(body)
            ResponseType.CREATED -> HttpResponse.created(body)
            ResponseType.NO_CONTENT -> HttpResponse.noContent()
            ResponseType.NOT_FOUND -> HttpResponse.notFound(message ?: body)
            ResponseType.FORBIDDEN -> HttpResponse.status(HttpStatus.FORBIDDEN, message)
            ResponseType.BAD_REQUEST -> HttpResponse.badRequest(message ?: body)
            else -> HttpResponse.serverError(message ?: "Something went wrong.")
        }
}

enum class ResponseType {
    SUCCESS, CREATED, NO_CONTENT, FAILURE, BAD_REQUEST, NOT_FOUND, FORBIDDEN;
}
