package api.dtos

import java.time.LocalDate

data class GroupDto(
    val id: Long,
    val name: String,
    val ownerId: Long,
    val creationDate: LocalDate = LocalDate.now(),
    val members: List<UserDTO>,
)