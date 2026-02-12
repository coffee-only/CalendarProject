package api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GroupExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(GroupNameNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onGroupNameNotFound(e: GroupNameNotFoundException) = mapOf(
        "message" to e.message,
        "error" to "GROUP_NOT_FOUND",
    )
    @ExceptionHandler(GroupIdNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onGroupIdNotFound(e: GroupIdNotFoundException) = mapOf(
        "message" to e.message,
        "error" to "GROUP_NOT_FOUND",
    )
}

class GroupNameNotFoundException(name: String): Exception("Group not found: $name")
class GroupIdNotFoundException(id: Long): Exception("Group not found: $id")