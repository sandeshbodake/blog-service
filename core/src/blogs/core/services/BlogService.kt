package blogs.core.services

import blogs.core.models.Blog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogService @Inject constructor(
    private val blogRepository: BlogRepository
) {
    fun getAll(): List<Blog> = blogRepository.getAll()
}
