package api.config


import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.cache.CacheManager
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.filter.OncePerRequestFilter
import java.util.concurrent.TimeUnit

@Component
class CacheSessionFilter(
    private val authEntryPoint: OAuth2EntryPoint,
    private val redisTemplate: RedisTemplate<String, Any>
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("check header");
        //check if session is in header
        val token = request.getHeader("session")
        if (token == null) {
            //send auth entry point
            authEntryPoint.commence(request, response,
                InsufficientAuthenticationException("no session header found"))
            return
        }

        //check distributed cache
        println("Check Redis for session")
        val data = redisTemplate.opsForValue().get("session:$token")
        if (data == null) {
            //send auth entry point
            authEntryPoint.commence(request, response,
                InsufficientAuthenticationException("session has expired"))
            return
        }
        redisTemplate.expire("session:$token", 30, TimeUnit.MINUTES)


        filterChain.doFilter(request, response)
    }

}