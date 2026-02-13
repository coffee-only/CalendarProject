package api.controllers

import api.dtos.GroupDto
import api.services.GroupService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/group")
class GroupController(
    val service: GroupService,
) {
    @GetMapping
    fun getGroups(
        @RequestParam(required = false) userId: Long?,
    ) = if (userId != null) service.getUserGroups(userId)
        else service.getGroups()

    @GetMapping("/{id}")
    fun getGroup(
        @PathVariable id: Long
    ) = service.getGroup(id)

    @PostMapping
    fun upsertGroup(
        @RequestBody group: GroupDto
    ) = service.upsertGroup(group)

    @PostMapping("/{groupId}/member/{newMemberId}")
    fun addMemberToGroup(
        @PathVariable groupId: Long,
        @PathVariable newMemberId: Long
    ) = service.addMember(groupId, newMemberId)

    @DeleteMapping("/{id}")
    fun deleteGroup(
        @PathVariable id: Long
    ) = service.deleteGroup(id)

}