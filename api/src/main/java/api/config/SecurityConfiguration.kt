package api.config
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * SecurityConfig
 */

@Configuration
class SecurityConfiguration(
    private val sessionFilter: CacheSessionFilter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .formLogin { it.disable() }         //////////////////////////////
            .httpBasic { it.disable() }         //      stateless api       //
            .csrf      { it.disable() }         //////////////////////////////
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/auth2/**").permitAll()
                auth.anyRequest().authenticated()
            }
            .oauth2Login {}
            .addFilterBefore(
                sessionFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }
}
