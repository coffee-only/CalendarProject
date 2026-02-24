package api.repositories

import api.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {

    //search queries
    fun findAllByUsernameIgnoreCase(username: String): Iterable<UserEntity>
    fun existsByEmail(email: String): Boolean
    fun findByEmailIgnoreCase(email: String): UserEntity
}