package api.controllers

import api.dtos.GroupDto
import api.entities.UserEntity
import api.services.GroupService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.SessionAttribute

@RestController
@RequestMapping("/group")
class GroupController(
    val service: GroupService,
)
{
    @GetMapping
    fun getGroups( @SessionAttribute(name="USER") self: UserEntity?,)
    = if (self != null) service.getUserGroups(self.id)
    else service.getAllGroups()


    @GetMapping("/{id}")
    fun getOneGroup(@PathVariable("id") id: Long)
    = service.getGroupById(id)


    @PostMapping("/create")
    fun createGroup(
        @SessionAttribute(name="USER") self: UserEntity,
        @RequestBody group: GroupDto
    ) = service.create(self,group)

    @PatchMapping("/update")
    fun updateGroup(
        @SessionAttribute(name="USER") self: UserEntity,
        @RequestBody group: GroupDto
    ) = service.update(self,group)
    /*
    @PostMapping("/{groupId}/member/{newMemberId}")
    fun addMemberToGroup(
        @PathVariable groupId: Long,
        @PathVariable newMemberId: Long
    ) = service.addMember(groupId, newMemberId)
    */


    @DeleteMapping("/{id}")
    fun deleteGroup(
        @SessionAttribute(name="USER") self: UserEntity,
        @PathVariable id: Long
    ) = service.deleteGroup(id)

}