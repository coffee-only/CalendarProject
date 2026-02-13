package api.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity @Table(name = "CalGroup")
class GroupEntity(
    @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) var id: Long = 0,
    // TODO: Column does not exist yet in db, please add it
    @Column(
        name = "name",
        unique = true,
        nullable = false,
    ) var name: String = "",
    @Column(
        name = "owner_id",
        nullable = false,
    ) var ownerId: Long = 0,
    @ManyToMany
    @JoinTable(
        name = "GroupMember",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")],
    ) var members: MutableList<UserModel> = mutableListOf()

)