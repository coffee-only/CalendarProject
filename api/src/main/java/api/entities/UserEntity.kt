package api.entities

import jakarta.persistence.*


@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name") // NOTE: column name in database is currently "name" and not "username"
    var username: String,
    // FIXME: Ommited since it is not currently in database schema
    // @Column(name = "firstname")
    // var firstname: String,
    // FIXME: Ommited since it is not currently in database schema
    // @Column(name = "lastname")
    // var lastname: String,
    @Column(unique = true, name = "email")
    var email: String,
    @Column(name = "password")
    var password: String,
    // FIXME: Ommited since it is not currently in database schema
    // @Column(name = "user_creation")
    // var creationDate: LocalDateTime = LocalDateTime.now()
)
