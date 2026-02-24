package api.services

import api.dtos.GroupDto
import api.exceptions.GroupDeletionPermissionException
import api.exceptions.GroupIdNotFoundException
import api.maps.toDto
import api.maps.toEntity
import api.entities.GroupEntity
import api.exceptions.UserNotFoundException
import api.repositories.GroupRepository
import api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class GroupService(
    val groupRepo: GroupRepository,
    val userRepo: UserRepository,
) {
    fun getAllGroups(): List<GroupDto> = groupRepo.findAll()
        .map(GroupEntity::toDto)

    fun getUserGroups(userId: Long): List<GroupDto> {


        return groupRepo.findByMembersContainsOROwnerId(UserId)
            .map(GroupEntity::toDto)
    }


    fun getGroupById(id: Long): GroupDto = groupRepo.findById(id)
        .orElseThrow { GroupIdNotFoundException(id) }
        .toDto()


    fun upsertGroup(group: GroupDto): GroupDto = groupRepo.save(
            group.toEntity(userRepo)
        ).toDto()

    fun addMember(newMemberId: Long, groupId: Long): GroupDto {
        val group = groupRepo.findById(groupId)
            .orElseThrow { GroupIdNotFoundException(groupId) }

        val newMember = userRepo.findById(newMemberId)
            .orElseThrow { UserNotFoundException("User not found: $newMemberId") }

        group.members.add(newMember)
        return groupRepo.save(group)
            .toDto()
    }

    // NOTE: Use this method instead of deleteGroupe(id) once Auth permits fetching the client's ID
    fun deleteGroupByOwner(ownerId: Long, groupId: Long): Unit {
        val owner = groupRepo.findById(ownerId)
            .orElseThrow { UserNotFoundException("User not found: $ownerId") }

        val group = groupRepo.findById(groupId)
            .orElseThrow { GroupIdNotFoundException(groupId) }

        if (group.ownerId != owner.id)
            throw GroupDeletionPermissionException("User ${owner.name} is not the owner of group $groupId")

        groupRepo.delete(group)
    }

    fun deleteGroup(id: Long): Unit = groupRepo.deleteById(id)

}