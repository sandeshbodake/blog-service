package blogs.core.services

import blogs.core.models.Blog

interface BlogRepository {
    fun getAll(): List<Blog>
}
