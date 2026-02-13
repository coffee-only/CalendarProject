package api.dtos

data class UserRegisterationDto(
    val username: String,
    val email: String,
    val password: String
)