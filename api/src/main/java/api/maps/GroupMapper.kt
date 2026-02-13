package api.maps

import api.dtos.GroupDto
import api.entities.GroupEntity
import api.entities.UserEntity
import api.exceptions.UserNotFoundException
import api.repositories.UserRepository


fun GroupEntity.toDto(): GroupDto = GroupDto(
    id = this.id,
    name = this.name,
    ownerId = this.ownerId,
    creationDate = this.creationDate,
    members = this.members
        .map(UserEntity::toDto)
)

fun GroupDto.toEntity(userRepo: UserRepository): GroupEntity = GroupEntity(
    id = this.id,
    name = this.name,
    ownerId = this.ownerId,
    creationDate = this.creationDate,
    members = this.members.map {
        userRepo.findById(it.id)
            .orElseThrow { UserNotFoundException("User with id ${it.id} Not Found") }
    }.toMutableList()
)
