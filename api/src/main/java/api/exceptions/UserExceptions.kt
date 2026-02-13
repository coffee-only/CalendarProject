package api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class UserExceptionsHandler {

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFound(e: UserNotFoundException) = mapOf(
        "message" to e.message,
        "status" to HttpStatus.NOT_FOUND,
        "error" to "USER_NOT_FOUND"
    )

    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUserAlreadyExists(e: UserAlreadyExistsException) = mapOf(
        "message" to e.message,
        "status" to HttpStatus.CONFLICT,
        "error" to "USER_ALREADY_EXISTS"
    )

    @ExceptionHandler(InvalidCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleInvalidCredentials(e: InvalidCredentialsException) = mapOf(
        "message" to e.message,
        "status" to HttpStatus.UNAUTHORIZED,
        "error" to "INVALID_CREDENTIALS"
    )
}

class UserNotFoundException(override val message: String) : Exception(message)
class UserAlreadyExistsException(override val message: String) : Exception(message)
class InvalidCredentialsException : Exception("Invalid credentials were provided")