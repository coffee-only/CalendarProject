package api.config

import api.config.auth.OAuth2EntryPoint
import api.config.auth.OAuthSuccessHandler
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * SecurityConfig
 */

@Configuration
class SecurityConfiguration(

    private val authEntryPoint: OAuth2EntryPoint,
    private val oAuthSuccesHandler: OAuthSuccessHandler,
    private val jwtConfig: JwtConfig
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
                   jwt.decoder(jwtConfig.jwtDecoder())
                }
                oauth2.authenticationEntryPoint(authEntryPoint)
            }
            .build()
    }


}
