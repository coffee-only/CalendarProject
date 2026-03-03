package api.maps

import api.dtos.GroupDto
import api.entities.GroupEntity
import api.entities.UserEntity
import api.exceptions.UserNotFoundException
import api.repositories.UserRepository


fun GroupEntity.toDto(): GroupDto = GroupDto(
    id = this.id,
    name = this.name,
    creationDate = this.creationDate,
    members = this.members.map { it.user.toDto() },
)

fun GroupDto.toEntity(): GroupEntity = GroupEntity(
    id = this.id,
    name = this.name,
    creationDate = this.creationDate,
)
