package api.services

import api.config.JwtConfig
import api.dtos.UserDTO
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TokenService(
    private val jwtConfig: JwtConfig,
) {
    fun Encode(user: UserDTO): String{
        val now = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .subject(user.id.toString())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(3600))
            .claim("email", user.email)
            .build()

        val params = JwsHeader.with(MacAlgorithm.HS256).build()
        return jwtConfig.jwtEncoder()
            .encode(
                JwtEncoderParameters.from(params, claims)
            )
            .tokenValue
    }

}