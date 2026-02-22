package api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureWebTestClient
@Sql("/init.sql")
@WithMockUser(username = "admin", roles = ["USER"])
class GroupControllerTest(@Autowired val restClient: WebTestClient) {

    companion object {
        @Container
        @ServiceConnection
        val mysql = MySQLContainer("mysql:8.0.11")
            .withDatabaseName("mydb")
            .withUsername("test")
            .withPassword("test")
    }


    @Test
    @Sql("/test-data.sql")
    fun `should return group from userid`() {
        restClient.get()
            .uri("/api/group")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(5)

        /*restClient.get()
            .uri("/api/group?userId=2")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(1)*/
    }
/*
    @Test
    @Sql("/test-data.sql")
    fun `should return group from id`() {
        restClient.get()
            .uri("/api/group/1")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(1)
    }

    @Test
    @Sql("/test-data.sql")
    fun `should create group`() {
        restClient.get()
            .uri("/api/group")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(5)

        restClient.get()
            .uri("/api/group?userId=2")
            .exchange()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(1)
    }
    */
}