package api.controllers

import api.dtos.GroupDto
import api.entities.UserEntity
import api.services.GroupService
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.ui.Model
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
    fun getGroups(@AuthenticationPrincipal jwt: Jwt) =
        service.getUserGroups(jwt.subject.toLong())



    @GetMapping("/{id}")
    fun getOneGroup(@PathVariable("id") id: Long)
    = service.getGroupById(id)


    @PostMapping("/create")
    fun createGroup(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody group: GroupDto
    ) = service.create(jwt.subject.toLong(),group)


    @PatchMapping("/update")
    fun updateGroup(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody group: GroupDto
    ) = service.update(jwt.subject.toLong(),group)
    /*
    @PostMapping("/{groupId}/member/{newMemberId}")
    fun addMemberToGroup(
        @PathVariable groupId: Long,
        @PathVariable newMemberId: Long
    ) = service.addMember(groupId, newMemberId)
    */


    @DeleteMapping("/{id}")
    fun deleteGroup(
       @SessionAttribute jwt: Jwt,
        @PathVariable id: Long
    ) = service.deleteGroup(id)

}