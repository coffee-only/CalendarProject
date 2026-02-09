package api.repositories

import api.models.UserModel
import org.apache.catalina.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CrudRepository<UserModel, Long> {
    //delete
    fun deleteById(id: ULong): Result<Void>

    //search queries
    fun findByUsernameRegex(username: String): Iterable<UserModel>
    fun findByEmail(email: String): Iterable<UserModel>

}