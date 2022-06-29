package blogs

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/api/v1/blogs")
class BlogApi {
    @Get("/")
    fun getAll(): HttpResponse<String> {
        val string = "success"
        return HttpResponse.ok(string)
    }
}
