package api.maps

import api.dtos.GroupDto
import api.models.GroupEntity

private object UserMapperSingl: UserMapper() // NOTE: File scoped singleton mapper to use the Java Class UserMapper

fun GroupEntity.toDto(): GroupDto = GroupDto(
    id = this.id,
    name = this.name,
    ownerId = this.ownerId,
    members = this.members
        .map(UserMapperSingl::apply)
)

fun GroupDto.toEntity(): GroupEntity = GroupEntity(
    id = this.id,
    name = this.name,
    ownerId = this.ownerId,
    members = mutableListOf(), // FIXME: mutableListOf() is place holder.
                               //  It should use UserMapper and map the list from UserDTO to UserModel
)