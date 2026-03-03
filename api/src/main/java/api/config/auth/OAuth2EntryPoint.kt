package api.config.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.security.oauth2.client.autoconfigure.OAuth2ClientProperties
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tools.jackson.databind.ObjectMapper

@Component
class OAuth2EntryPoint(
    private val property: OAuth2ClientProperties,
    private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .build()
            .toUriString()

        response.apply{
            contentType = MediaType.APPLICATION_JSON_VALUE
            status = HttpServletResponse.SC_UNAUTHORIZED

            //grab available links and create response
            val registry = property.registration
                    .asSequence()
                    .associate {
                        (key,_)-> key to "$baseUrl/oauth2/authorization/$key"
                    }

            val body = mapOf(
                "error" to "invalid token",
                "message" to "session expired",
                "login_options" to registry,
            )
            objectMapper.writeValue(outputStream,body)
        }
    }


}