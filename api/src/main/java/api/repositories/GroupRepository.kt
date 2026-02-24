package api.repositories

import api.entities.GroupEntity
import api.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface GroupRepository: JpaRepository<GroupEntity, Long> {
    fun findByMembersContainsOrOwnerId(user: UserEntity): List<GroupEntity>
}