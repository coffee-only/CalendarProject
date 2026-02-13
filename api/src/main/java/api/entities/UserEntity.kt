package api.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "username")
    var username: String,
    @Column(name = "firstname")
    var firstname: String,
    @Column(name = "lastname")
    var lastname: String,
    @Column(unique = true, name = "email")
    var email: String,
    @Column(name = "user_creation")
    var creationDate: LocalDateTime = LocalDateTime.now()
)
