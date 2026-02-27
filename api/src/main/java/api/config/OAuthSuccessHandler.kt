package api.config

import api.entities.UserEntity
import api.exceptions.InvalidCredentialsException
import api.repositories.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class OAuthSuccessHandler(
    private val userRepo: UserRepository,
    private val jwtEncoder: JwtEncoder,
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val auth = authentication.principal as OAuth2User

        val email= auth.getAttribute<String>("email")
            ?: throw InvalidCredentialsException("No email associated with this auth")

        val user = userRepo.findByEmail(email)
            ?: userRepo.save(UserEntity(email=email))

        val token = encode(user)
        response.status = HttpServletResponse.SC_OK
        response.setHeader("Authorization", "Bearer $token")
        response.writer.write("{status: success}")
    }

    fun encode(user: UserEntity): String {

        val header = JwsHeader.with(MacAlgorithm.HS256).build()

        val claims = JwtClaimsSet.builder()
            .issuer("CalendarProject")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(3600)) // Expire dans 1h
            .subject(user.id.toString())
            .build()

        val parameters = JwtEncoderParameters.from(header, claims)
        return jwtEncoder.encode(parameters).tokenValue
    }

}