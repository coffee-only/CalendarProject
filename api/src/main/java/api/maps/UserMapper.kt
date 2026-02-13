package api.maps

import api.dtos.UserDTO
import api.dtos.UserRegisterationDto
import api.entities.UserEntity

fun UserDTO.toEntity(withPassword: String): UserEntity = UserEntity(
    id = this.id,
    username = this.username,
    email = this.email,
    password = withPassword,
    firstname = firstname,
    lastname = this.lastname,
    creationDate = this.creationDate
)

fun UserEntity.toDto(): UserDTO = UserDTO(
    id = this.id,
    username = this.username,
    email = this.email,
    firstname = this.firstname,
    lastname = this.lastname,
    creationDate = this.creationDate
)

fun UserRegisterationDto.toEntity(): UserEntity = UserEntity(
    username = this.username,
    email = this.email,
    password = this.password
)
