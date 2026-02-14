package api.repositories

import api.entities.GroupEntity
import api.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface GroupRepository: JpaRepository<GroupEntity, Long> {
    @Query("SELECT g FROM GroupEntity g WHERE :user MEMBER OF g.user_group")
    fun findByMembersContains(user: UserEntity): List<GroupEntity>
}