package api.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "user_group")
class GroupEntity(
    @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) var id: Long = 0,
    @Column(
        name = "owner_id",
        nullable = false,
    ) var ownerId: Long = 0,
    @Column(
        name = "group_name",
        nullable = false,
    ) var name: String = "",
    @Column(
        name = "group_creation",
        nullable = false,
    ) var creationDate: LocalDate = LocalDate.now(),
    
    @ManyToMany
    @JoinTable(
        name = "GroupMember",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")],
    ) var members: MutableList<UserEntity> = mutableListOf()

)