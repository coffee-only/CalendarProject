package api.controllers

import api.dtos.OptionalUserDto
import api.dtos.UserDTO
import api.dtos.UserLoginDTO
import api.dtos.UserRegisterationDto
import api.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController(
    private val service: UserService
) {
    @PostMapping("/register")
    fun register(
        @RequestBody registerationData: UserRegisterationDto
    ): ResponseEntity<UserDTO> {
        //create password logic
        val registeredUser = service.register(registerationData)
        return ResponseEntity<UserDTO>(registeredUser, HttpStatus.OK)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginData: UserLoginDTO
    ): ResponseEntity<UserDTO> = service.login(loginData)
        .let { ResponseEntity<UserDTO>(it, HttpStatus.OK) }



    @PatchMapping("/{id}") // Probably will be in its own RestController AccountManagement
    fun updateUsername(
        @PathVariable id: Long,
        @RequestBody userUpdateData: OptionalUserDto
    ): ResponseEntity<UserDTO> {
        val updatedUser = service.update(id, userUpdateData)
        return ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK)
    }


    @DeleteMapping("/{id}") // Probably will be in its own RestController AccountManagement
    fun deleteAccount(
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        service.deleteAccount(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }


    //query actions
    @GetMapping("/{id}") // Probably will be in its own RestController AccountManagement
    fun getAccountInfo(
        @PathVariable id: Long
    ): ResponseEntity<UserDTO> {
        val dto = service.getById(id) // Token a handle a travers spring security
        return ResponseEntity<UserDTO>(dto, HttpStatus.OK)
    }
}





