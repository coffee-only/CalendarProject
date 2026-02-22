package api

import org.junit.jupiter.api.Test
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureRestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.client.RestTestClient
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestClient
@Testcontainers
@Sql("../../resources/init.sql")
class GroupControllerTest {
    companion object {
        @Container
        @ServiceConnection
        val mysql = MySQLContainer("mysql:8.0.11")
            .withDatabaseName("mydb")
            .withUsername("test")
            .withPassword("test")
    }

    lateinit var restClient: RestTestClient;


    @Test
    @Sql("/test-data.sql")
    fun `should return group from userid`() {
        restClient.get()
            .uri("/api/group")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(5);

        restClient.get()
            .uri("/api/group?userId=2")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(1);
    }

    @Test
    @Sql("/test-data.sql")
    fun `should return group from id`() {
        restClient.get()
            .uri("/api/group/1")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(1);
    }

    @Test
    @Sql("/test-data.sql")
    fun `should create group`() {
        restClient.get()
            .uri("/api/group")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(5);

        restClient.get()
            .uri("/api/group?userId=2")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(1);
    }
}