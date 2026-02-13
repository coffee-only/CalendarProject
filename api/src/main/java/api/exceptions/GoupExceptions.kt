package api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GroupExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(
        GroupNameNotFoundException::class,
        GroupIdNotFoundException::class,
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onGroupNotFound(e: RuntimeException) = mapOf(
        "message" to e.message,
        "status" to HttpStatus.NOT_FOUND,
        "error" to "GROUP_NOT_FOUND",
    )

    @ExceptionHandler(GroupDeletionPermissionException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun onGroupDeletionPermission(e: GroupDeletionPermissionException) = mapOf(
        "message" to e.message,
        "status" to HttpStatus.FORBIDDEN,
        "error" to "GROUP_DELETION_PERMISSION",
    )
}

class GroupNameNotFoundException(name: String): RuntimeException("Group not found: $name")
class GroupIdNotFoundException(id: Long): RuntimeException("Group not found: $id")
class GroupDeletionPermissionException(override val message: String) : RuntimeException(message)