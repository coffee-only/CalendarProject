import api.controllers.UserController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureRestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.client.RestTestClient



@SpringBootTest
@AutoConfigureRestClient
class GroupControllerTest {

    lateinit var restClient: RestTestClient;
    @BeforeEach
    fun setup() {
        repository

    }
    @Test
    fun `should return all group`() {
        //Warning should return an empty list
        restClient.get()
            .uri("/api/group")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    fun `should create & update  & fetch`() {

        restClient.get()
            .uri("/api/group/1")
            .
    }

    @Test
    fun `should make a group`() {

        restClient.get()
            .uri("/api/group/1")
            .
    }
}