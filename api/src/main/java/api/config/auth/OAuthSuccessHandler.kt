package api.config.auth


import api.entities.UserEntity
import api.exceptions.InvalidCredentialsException
import api.maps.toDto
import api.repositories.UserRepository
import api.services.TokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuthSuccessHandler(
    private val userRepo: UserRepository,
    private val tokenService: TokenService,
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val auth = authentication.principal as OAuth2User

        val email= auth.getAttribute<String>("email")
            ?: throw InvalidCredentialsException()

        val user = userRepo.findByEmail(email)
            ?: userRepo.save(UserEntity(email = email))


        val token = tokenService.Encode(user.toDto());
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.status = HttpServletResponse.SC_OK
        response.setHeader("Authorization", "Bearer $token")
        response.writer.write("{status: success}")
    }

}