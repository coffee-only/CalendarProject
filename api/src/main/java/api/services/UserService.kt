package api.services

import api.dtos.OptionalUserDto
import api.dtos.UserDTO
import api.dtos.UserLoginDTO
import api.dtos.UserRegisterationDto
import api.entities.UserEntity
import api.exceptions.InvalidCredentialsException
import api.exceptions.UserAlreadyExistsException
import api.exceptions.UserNotFoundException
import api.maps.toDto
import api.maps.toEntity
import api.repositories.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(
    val userRepo: UserRepository
) {

    fun register(
        registerationData: UserRegisterationDto
    ): UserDTO = if (userRepo.existsByEmail(registerationData.email))
        // User already exists in database so we throw an exception since we cannot register it
        throw UserAlreadyExistsException("User ${registerationData.email} already exists")
    else userRepo.run {
        // FIXME: register should hash the password before saving it to database
        //  Once fixed, remove the line above
        save(
            registerationData.toEntity()
        ).toDto()
    }

    fun login(
        loginData: UserLoginDTO
    ): UserDTO = userRepo.let {
        val registeredUser = it.findByEmailIgnoreCase(loginData.email)
        // FIXME: login should hash the password before comparing it to the one in database
        //  Once fixed, remove the line above
        if (registeredUser.password == loginData.password) registeredUser.toDto()
        else throw InvalidCredentialsException()
    }

    fun findByEmail(email: String): UserDTO = userRepo.findByEmailIgnoreCase(email)
        .toDto()

    fun findByUsername(username: String): List<UserDTO> = userRepo.run {
        findAllByUsernameIgnoreCase(username)
            .map(UserEntity::toDto)
    }

    fun deleteAccount(id: Long) = userRepo.deleteById(id)

    fun update(userId: Long, userUpdateData: OptionalUserDto): UserDTO = userRepo.run {

        val user = findById(userId)
            .orElseThrow { UserNotFoundException("User with id $userId not found") }

        userUpdateData.name?.let { user.username = it }
        userUpdateData.email?.let { user.email = it }
        userUpdateData.password?.let { user.password = it }

        save(user)
            .toDto()
    }

    fun getById(id: Long): UserDTO = userRepo.run {
        findById(id)
            .orElseThrow { UserNotFoundException("User with id $id not found") }
            .toDto()
    }
}