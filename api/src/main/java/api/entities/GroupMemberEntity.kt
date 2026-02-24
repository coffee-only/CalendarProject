package api.entities

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable


@Embeddable
data class GroupMemberId(
    @Column(name = "group_id") val groupId: Long,
    @Column(name = "user_id") val userId: Long
) : Serializable

@Entity
@Table(name="group_member")
class GroupMemberEntity(
    @EmbeddedId
    val id: GroupMemberId,

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    val group: GroupEntity,

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(name = "group_role")
    @Enumerated(EnumType.STRING)
    val groupRole: GroupRole,
)