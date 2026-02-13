package api.dtos

import java.time.LocalDate


data class UserDTO(
    val id: Long,
    var username: String,
    var email: String,
    val firstname: String? = null,
    val lastname: String? = null,
    val creationDate: LocalDate = LocalDate.now(),
    // ommits password for security reasons
)
