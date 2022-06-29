package db.blogs

import blogs.core.models.Blog
import blogs.core.services.BlogRepository
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class BlogRepositoryImpl(
    @Inject private val dataSource: DataSource
) : BlogRepository {
    override fun getAll(): List<Blog> {
        val query = dataSource.connection.prepareStatement("SELECT * FROM blogs")
        val result = query.executeQuery()
        val blogs = mutableListOf<Blog>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name = result.getString("title") as String
            val content = result.getString("content") as String

            blogs.add(Blog(id, name, content))
        }
        return blogs
    }
}
