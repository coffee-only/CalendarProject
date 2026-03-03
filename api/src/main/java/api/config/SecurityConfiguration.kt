package api.config
import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.boot.security.oauth2.client.autoconfigure.OAuth2ClientProperties
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain



/**
 * SecurityConfig
 */

@Configuration
class SecurityConfiguration(
    private val authEntryPoint: OAuth2EntryPoint,
    private val oAuthSuccesHandler: OAuthSuccessHandler
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .formLogin { it.disable() }         //////////////////////////////
            .httpBasic { it.disable() }         //      stateless api       //
            .csrf      { it.disable() }         //////////////////////////////
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/oauth2/**").permitAll()
                auth.anyRequest().authenticated()
            }
            .oauth2Login { oauth2->
                oauth2.successHandler(oAuthSuccesHandler) //generate token
            }
            .oauth2ResourceServer{ oauth2 ->
                oauth2.jwt { jwt ->
                   // jwt.decoder(jwtDecoder())
                }
                oauth2.authenticationEntryPoint(authEntryPoint)
            }
            .build()
    }
}
