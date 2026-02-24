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
import org.springframework.test.web.servlet.post
import org.springframework.web.servlet.function.RequestPredicates.contentType
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer
import tools.jackson.databind.ObjectMapper


import java.time.LocalDate

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Sql("/init.sql")
class MockGroupController(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

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
         user = UserEntity (
            id=1,
            firstname = "Alice",
            lastname = "Smith",
            username = "alice01",
            creationDate = LocalDate.parse("2026-01-01"),
            email = "alice@example.com"
        )
        session = MockHttpSession()
        session.setAttribute("USER", user)
    }

    @Test
    @Sql("/test-data.sql")
    fun `should return group from userid`() {
        mockMvc.get("/group") {
            sessionAttr("USER", user)
        }.andExpect {
                   status { isOk() }
                   content { contentType(MediaType.APPLICATION_JSON) }
                   jsonPath("$[0].name") { value("les patates") }
                   jsonPath("$[0].creationDate") { value("2026-01-02") }
                   jsonPath("$[1].name") { value("les tomates") }
                   jsonPath("$[1].creationDate") { value("2026-01-03") }
                   jsonPath("$.length()") { value("2") }
        }

        mockMvc.get("/group")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.length()") { value("3") }
        }
    }

    @Test
    @Sql("/test-data.sql")
    fun `should return one group by its id`(){
        mockMvc.get("/group/1")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("les patates") }
                jsonPath("$.creationDate") { value("2026-01-02") }
                jsonPath("$.members"){ isArray() }
                jsonPath("$.members.length()"){ value(2) }
                jsonPath("$.members[0].id"){ value(1)  }
                jsonPath("$.members[1].id"){ value("3")  }
            }
    }

    @Test
    @Sql("/test-data.sql")
    fun `should create a group where creator becomes owner`(){
        val created = GroupDto(name="testing")
        mockMvc.post("/group/create") {
            sessionAttr("USER", user)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(created)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.name") { value("testing") }
            jsonPath("$.members[0].id") { value(1) }
        }

    }

    @Test
    @Sql("/test-data.sql")
    fun `should update a group`(){
        val created = GroupDto(id=1,name="NewGroup")
        mockMvc.post("/group/update") {
            sessionAttr("USER", user)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(created)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.name") { value("NewGroup") }
            jsonPath("$.members.length()") { value(0) }
        }


        val unauthorised = GroupDto(id=2,name="NewGroup")
        mockMvc.post("/group/update") {
            sessionAttr("USER", user)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(unauthorised)
        }.andExpect {
            status { isUnauthorized() }
        }
    }
}