package api.dtos

data class GroupDto(
    val id: Long,
    val name: String,
    val ownerId: Long,
    val members: List<UserDTO>,
)