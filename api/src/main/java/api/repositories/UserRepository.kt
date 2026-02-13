package api.repositories

import api.entities.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository: CrudRepository<UserEntity, Long> {

    //search queries
    fun findAllByUsernameIgnoreCase(username: String): Iterable<UserEntity>
    fun existsByEmail(email: String): Boolean
    fun findByEmailIgnoreCase(email: String): UserEntity
}