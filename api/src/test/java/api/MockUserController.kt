package api

import api.dtos.GroupDto
import api.entities.UserEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer
import java.time.LocalDate


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
@Sql("/init.sql")
class MockGroupController(@Autowired val mockMvc: MockMvc) {

    lateinit var user: UserEntity;
    lateinit var session: MockHttpSession
    companion object {
        @Container
        @ServiceConnection
        val mysql = MySQLContainer("mysql:8.0.11")
            .withDatabaseName("mydb")
            .withUsername("test")
            .withPassword("test")
    }
    @BeforeEach
    fun setUp() {
        val self = UserEntity (
            id=1,
            firstname = "Alice",
            lastname = "Smith",
            username = "alice01",
            creationDate = LocalDate.parse("2026-01-01"),
            email = "alice@example.com"
        )
        session = MockHttpSession()
        session.setAttribute("USER", self)
    }

    @Test
    @Sql("/test-data.sql")
    fun `should return group from userid`() {
        mockMvc.get("/group")
               .andExpect {
                   status { isOk() }
                   content { contentType(MediaType.APPLICATION_JSON) }
                   jsonPath("$.lenght()") { value("2") }
               }
    }

    @Test
    @Sql("/test-data.sql")
    fun `should return one group by its id`(){


    }


    @Test
    @Sql("/test-data.sql")
    fun `should create a group where creator becomes owner`(){


    }

    @Test
    @Sql("/test-data.sql")
    fun `should create group`() {

    }
}