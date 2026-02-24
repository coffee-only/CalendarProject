package api

import api.dtos.GroupDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
import org.springframework.http.MediaType
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
            .uri("/group")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.length()")
            .isEqualTo(2)

        restClient.get()
            .uri("/group?userId=2")
            .exchange()
            .expectBody()
            //.consumeWith(System.out::println);
            .jsonPath("$.length()")
            .isEqualTo(1)
    }

    @Test
    @Sql("/test-data.sql")
    fun `should get one group by its id`(){
        restClient.get()
        .uri("/group/1")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.lenght()").isEqualTo(1);

    }

    @Test
    @Sql("/test-data.sql")
    fun `should create group`() {
        val dto = GroupDto(name="testing",ownerId=1)
        restClient.post()
            .uri("/group")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.name").isEqualTo("testing")
            .jsonPath("$.ownerId").isEqualTo(1)

    }
}