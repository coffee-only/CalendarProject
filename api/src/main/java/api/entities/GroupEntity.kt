package api.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "user_group")
class GroupEntity(
    @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) var id: Long = 0,
    @Column(
        name = "group_name",
        nullable = false,
    ) var name: String = "",
    @Column(
        name = "group_creation",
        nullable = false,
    ) var creationDate: LocalDate = LocalDate.now(),
    
    @OneToMany(mappedBy = "group")
    var members: MutableList<GroupMemberEntity> = mutableListOf()

)