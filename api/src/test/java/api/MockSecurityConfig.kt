package api


import api.controllers.GroupController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.servlet.function.RequestPredicates.contentType
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer

import kotlin.test.Test


@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureMockMvc
class MockSecurityConfig(
    @Autowired val mockMvc: MockMvc
) {
    companion object {
        @Container
        @ServiceConnection
        val mysql = MySQLContainer("mysql:8.0.11")
            .withDatabaseName("mydb")
            .withUsername("test")
            .withPassword("test")
    }

    @Test
    fun `shoud return 401`() {
        mockMvc.get("/group")
            .andExpect {
                contentType(MediaType.APPLICATION_JSON)
                status { isUnauthorized() }
            }
    }
}