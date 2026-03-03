package api.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import javax.crypto.spec.SecretKeySpec



@Configuration
class JwtConfig {
    val secret = "12345678901234567890123456789011"
    @Bean
    fun jwtDecoder(): JwtDecoder {
        //si vous voyez ceci n'accepter pas le changement je mock pour configurer rapidement
        //il se peut que j'oublie
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")

        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        // 1. Create a JWK (JSON Web Key) from your symmetric secret
        val jwk = OctetSequenceKey.Builder(secret.toByteArray())
            .build()

        // 2. Wrap it in a Source
        val jwks = ImmutableJWKSet<SecurityContext>(JWKSet(jwk))

        return NimbusJwtEncoder(jwks)
    }
}