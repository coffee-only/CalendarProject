package api.repositories

import api.dtos.GroupDto
import api.entities.GroupEntity
import api.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface GroupRepository: JpaRepository<GroupEntity, Long> {
    @Query("SELECT g FROM GroupEntity g WHERE :user MEMBER OF g.members")
    fun findByMembersContains(user: UserModel): List<GroupEntity>
}