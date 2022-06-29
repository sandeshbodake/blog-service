package db.blogs.utils

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource


fun DataSource.query(sql: String, vararg params: Any) =
    this.connection.use { it.executeQuery(sql, params.toList()) }

fun Connection.executeQuery(sql: String, params: List<Any> = listOf()): ResultSet =
    this.prepareStatement(sql)
        .withParams(params)
        .executeQuery()


fun PreparedStatement.withParams(params: List<Any?> = listOf()): PreparedStatement =
    this.also { self ->
        params.forEachIndexed { index, param -> self.setObject(index + 1, param) }
    }
