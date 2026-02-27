package api

import api.config.RSAKeyProperties
import jakarta.annotation.PostConstruct
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import java.sql.DriverManager.println
import javax.sql.DataSource
@EnableConfigurationProperties(RSAKeyProperties::class)
@SpringBootApplication
class Application(
    private val dataSource: DataSource
) {

    @PostConstruct
    fun logDatabaseInfo() {
        try {
            dataSource.connection.use { conn ->
                println("ACTUAL JDBC URL     : " + conn.metaData.url)
                println("ACTUAL DB Driver    : " + conn.metaData.driverName)
                println("ACTUAL DB Version   : " + conn.metaData.databaseProductVersion)
            }
        } catch (e: Exception) {
            System.err
                .println("Failed to log DB metadata: ${e.message}")
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}
