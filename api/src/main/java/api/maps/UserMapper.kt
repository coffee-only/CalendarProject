package api.maps

import api.dtos.UserDTO
import api.dtos.UserRegisterationDto
import api.entities.UserEntity

fun UserDTO.toEntity(withPassword: String): UserEntity = UserEntity(
    id = this.id,
    username = this.name,
    email = this.email,
    password = withPassword
)

fun UserEntity.toDto(): UserDTO = UserDTO(
    id = this.id,
    name = this.username,
    email = this.email
)

fun UserRegisterationDto.toEntity(): UserEntity = UserEntity(
    username = this.username,
    email = this.email,
    password = this.password
)
