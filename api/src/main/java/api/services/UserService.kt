package api.services

import api.entities.UserEntity
import api.repositories.UserRepository
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class UserService(repo: UserRepository) {

    private val _repo: UserRepository = repo
    
    /*
     * function that registers user if he doesnt exist
     *  @throws Exception
     */
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
        val user = UserEntity(
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



    fun findAllByEmail(email: String): UserEntity{
        return _repo.findByEmailIgnoreCase(email);
    }

    fun findByUsername(username: String): Iterable<UserEntity> {
        throw Exception("not implemented");
    }
}