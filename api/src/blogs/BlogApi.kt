package blogs

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/api/v1/blogs")
class BlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Get("/")
    fun getAll(): HttpResponse<Any> {
        val blogs = blogController.getAll()
        return blogs.getHttpResponse()
    }
}
