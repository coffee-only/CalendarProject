package api.config


import api.config.auth.OAuth2EntryPoint
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SessionFilter(
    private val authEntryPoint: OAuth2EntryPoint,
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