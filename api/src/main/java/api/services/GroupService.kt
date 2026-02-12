package api.services

import api.dtos.GroupDto
import api.exceptions.GroupIdNotFoundException
import api.maps.toDto
import api.maps.toEntity
import api.models.GroupEntity
import api.repositories.GroupRepository
import org.springframework.stereotype.Service

@Service
class GroupService(
    val repository: GroupRepository
) {
    fun getGroups(): List<GroupDto> = repository.findAll()
        .map(GroupEntity::toDto)

    fun getUserGroups(userId: Long): List<GroupDto> = repository.findAll()
        .filter { it.ownerId == userId }
        .map(GroupEntity::toDto)

    fun getGroup(id: Long): GroupDto = repository.findById(id)
        .orElseThrow { GroupIdNotFoundException(id) }
        .toDto()


    fun upsertGroup(group: GroupDto): GroupDto = repository.save(
            group.toEntity()
        ).toDto()


    fun deleteGroup(id: Long): Unit = repository.deleteById(id)

}