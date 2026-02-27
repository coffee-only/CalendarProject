package api.config


import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.concurrent.TimeUnit

@Component
class SessionFilter(
    private val authEntryPoint: OAuth2EntryPoint,
    private val redisTemplate: RedisTemplate<String, Any>
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        //check if session is in header
        val token = request.getHeader("Authorization")
            ?: return authEntryPoint.commence(request, response,
                InsufficientAuthenticationException("no session header found"))

        filterChain.doFilter(request, response)
    }

}