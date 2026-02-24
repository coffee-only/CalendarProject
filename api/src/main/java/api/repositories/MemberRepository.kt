package api.repositories

import api.entities.GroupMemberEntity
import api.entities.GroupMemberId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<GroupMemberEntity, GroupMemberId> {
    fun findByUserId(userId: Long): List<GroupMemberEntity>
}