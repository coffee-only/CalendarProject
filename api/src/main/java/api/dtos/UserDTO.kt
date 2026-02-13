package api.dtos


data class UserDTO(
    val id: Long,
    var name: String,
    var email: String,
    // ommits password for security reasons
)
