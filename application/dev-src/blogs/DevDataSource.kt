package blogs

import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Replaces
import javax.inject.Singleton
import javax.sql.DataSource
import org.postgresql.ds.PGSimpleDataSource

@Singleton
@Primary
@Replaces(DataSource::class)
class DevDataSource : PGSimpleDataSource() {
    init {
        setURL("jdbc:postgresql://localhost/blogs_dev")
        user = "postgres"
        password = ""
    }
}
