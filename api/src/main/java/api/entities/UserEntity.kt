package api.entities

import jakarta.persistence.*
import java.time.LocalDate


@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) var id: Long = 0,
    @Column(
        name = "username",
        unique = false,
        nullable = false,
    ) var username: String,
    @Column(
        name = "firstname",
        nullable = true,
    ) var firstname: String? = null,
    @Column(
        name = "lastname",
        nullable = true,
    ) var lastname: String? = null,
    @Column(
        name = "email",
        unique = true,
        nullable = false,
    ) var email: String,
    @Column(
        name = "password",
        nullable = false,
    ) var password: String,
    @Column(
        name = "user_creation",
        nullable = false,
    ) var creationDate: LocalDate = LocalDate.now(),
    @ManyToMany(
        mappedBy = "members"
    ) var groups: MutableList<GroupEntity> = mutableListOf()
)
