package api.repositories

import api.models.UserModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository: CrudRepository<UserModel, Long> {
    //delete
    fun deleteById(id: ULong): Result<Void>

    //search queries
    fun findAllByUsernameIgnoreCase(username: String): Iterable<UserModel>
    fun existsByEmail(email: String): Boolean
    fun findByEmailIgnoreCase(email: String): UserModel
}