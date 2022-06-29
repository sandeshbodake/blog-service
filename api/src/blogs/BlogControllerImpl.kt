package blogs

import blogs.core.services.BlogService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class BlogControllerImpl(
    private val blogService: BlogService
) : BlogController {
    override fun getAll(): Response<Any> {
        val response = blogService.getAll()
        return Response(ResponseType.SUCCESS, body = response)
    }
}
