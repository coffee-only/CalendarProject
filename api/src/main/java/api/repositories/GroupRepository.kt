package api.repositories

import api.entities.GroupEntity
import api.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository: JpaRepository<GroupEntity, Long> {
}