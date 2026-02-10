package api.services

import api.models.UserModel
import api.repositories.UserRepository
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Component
import java.io.IOException
import java.time.LocalDateTime


@Component
class UserService(repo: UserRepository) {

    private val _repo: UserRepository = repo

    fun RegisterUser(
        username : String,
        firstname: String,
        lastname : String,
        email    : String
        ):Result<Unit>//Unit is void/ None
    {
        //guard
        if(_repo.existsByEmail(email)){
            return Result.failure(Exception("[bad request] email is already used"));
        }
        //creating user & saving user
        val user = UserModel(
            username = username,
            firstname= firstname,
            lastname = lastname,
            email    = email
        )

        return try {
            _repo.save(user)
            Result.success(Unit)
        } catch (e :Exception) {
            val message = when(e) {
                is IllegalArgumentException -> "[bad request] data supplied was incorrect"
                is IOException              -> "[internal error] Couldn't connect to server"
                else                        -> "[internal error] unknown error will be reported to maintainers"
            }
            Result.failure(Exception(message))
        };
    }

    fun findAllByEmail(email: String): UserModel{
        return _repo.findByEmailIgnoreCase(email);
    }

    fun findByUsername(username: String): Iterable<UserModel> {
        throw Exception("not implemented");
    }
}