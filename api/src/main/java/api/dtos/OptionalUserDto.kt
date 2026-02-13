package api.dtos

data class OptionalUserDto(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
)