package api.services

import api.dtos.GroupDto
import api.exceptions.GroupIdNotFoundException
import api.exceptions.UserModelException
import api.maps.toDto
import api.maps.toEntity
import api.models.GroupEntity
import api.repositories.GroupRepository
import api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class GroupService(
    val grpRepo: GroupRepository,
    val usrRepo: UserRepository,
) {
    fun getGroups(): List<GroupDto> = grpRepo.findAll()
        .map(GroupEntity::toDto)

    fun getUserGroups(userId: Long): List<GroupDto> {
        val userFromId = usrRepo.findById(userId)
            .orElseThrow { UserModelException("User not found: $userId") }

        return grpRepo.findByMembersContains(userFromId)
            .map(GroupEntity::toDto)
    }


    fun getGroup(id: Long): GroupDto = grpRepo.findById(id)
        .orElseThrow { GroupIdNotFoundException(id) }
        .toDto()


    fun upsertGroup(group: GroupDto): GroupDto = grpRepo.save(
            group.toEntity()
        ).toDto()

    fun addMember(newMemberId: Long, groupId: Long): GroupDto {
        val group = grpRepo.findById(groupId)
            .orElseThrow { GroupIdNotFoundException(groupId) }
        val newMember = usrRepo.findById(newMemberId)
            .orElseThrow { UserModelException("User not found: $newMemberId") } // FIXME: No exception handler yet.
                                                                                //  please add it, otherwise the client will receive a 500 instead of a 404

        group.members.add(newMember)
        return grpRepo.save(group)
            .toDto()
    }


    fun deleteGroup(id: Long): Unit = grpRepo.deleteById(id)

}