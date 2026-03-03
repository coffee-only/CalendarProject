package api.services

import api.dtos.GroupDto
import api.exceptions.GroupDeletionPermissionException
import api.exceptions.GroupIdNotFoundException
import api.maps.toDto
import api.entities.GroupEntity
import api.entities.GroupMemberEntity
import api.entities.UserEntity
import api.exceptions.UserNotFoundException
import api.repositories.GroupRepository
import api.repositories.UserRepository
import jakarta.servlet.http.HttpSession
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import api.entities.GroupMemberId
import api.entities.GroupRole
import api.exceptions.InvalidCredentialsException
import api.maps.toEntity
import api.repositories.MemberRepository
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@Service
class GroupService(
    private val groupRepo: GroupRepository,
    private val memberRepo: MemberRepository,
    private val userRepo: UserRepository,
    private val httpServletResponse: HttpServletResponse,
) {
    // todo: command functions
    //      - create group  check
    //      - update group  check
    //      - add member    check
    //      - delete member
    //      - leave group
    @Transactional
    fun create(self: UserEntity, group: GroupDto): GroupDto {
        //create group
        val savedGroup = groupRepo.save(group.toEntity());

        // make creator of group Owner
        val member = GroupMemberEntity(
            id = GroupMemberId(savedGroup.id, self.id),
            group = savedGroup,
            user = self,
            groupRole = GroupRole.OWNER,
        );

        memberRepo.save(member);
        savedGroup.members.add(member);

        return savedGroup.toDto()
    }

    @Transactional
    fun update(self: UserEntity, group: GroupDto): GroupDto {
        //check if user as the necessary permission to update group
        checkRole(self, group, GroupRole.OWNER);

        val savedGroup = groupRepo.save(group.toEntity());
        return savedGroup.toDto();
    }

    @Transactional
    fun addMember(self: UserEntity, group: GroupDto, invited: Long): GroupDto {
        //check if user as the necessary permission to update group
        checkRole(self, group, GroupRole.OWNER);

        val invitedProxy = userRepo.getReferenceById(invited)
        val groupProxy   = groupRepo.getReferenceById(group.id)
        //  make new member
        val member = GroupMemberEntity(
            id = GroupMemberId(group.id, invited),
            group = groupProxy,
            user  = invitedProxy,
            groupRole = GroupRole.OWNER,
        )

        memberRepo.save(member)
        val updatedGroup = groupRepo.findById(group.id)
            .orElseThrow({
                ResponseStatusException(HttpStatus.BAD_REQUEST,"group could not be found")
            })

        return updatedGroup.toDto()
    }

    @Transactional
    fun deleteMember(user : UserEntity, group: GroupEntity): GroupDto {
        //todo: Faut y penser
        throw Exception("not implemented");
    }

    fun deleteGroup(id: Long): Unit = groupRepo.deleteById(id)

    // todo: query functions
    //     - get all group
    //     - get user groups
    //     - get a group by its id
    //     - get members
    fun getAllGroups(): List<GroupDto> = groupRepo.findAll()
        .map(GroupEntity::toDto)

    fun getUserGroups(userId: Long): List<GroupDto>
        = memberRepo.findByIdUserId(userId).map {it.group.toDto()}


    fun getGroupById(id: Long): GroupDto = groupRepo.findById(id)
        .orElseThrow { GroupIdNotFoundException(id) }
        .toDto()





    private fun checkRole(user: UserEntity, group: GroupDto, role: GroupRole): GroupMemberEntity {
        //check if user as the necessary permission to update group
        val membership = memberRepo.findById(
            GroupMemberId(
                user.id,
                group.id
            )
        ).orElseThrow {
            ResponseStatusException(HttpStatus.FORBIDDEN,"User is not in the group")
        }

        if(membership.groupRole != role) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED,"User does not have the necessary rights to do that");
        }
        return membership
    }
}