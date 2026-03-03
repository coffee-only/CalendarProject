package api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.servlet.function.RequestPredicates.contentType
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

import kotlin.test.Test


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MockSecurityConfig(
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `shoud return 401`() {
        mockMvc.get("/group")
            .andExpect {
                contentType(MediaType.APPLICATION_JSON)
                status { isUnauthorized() }
            }
    }
}