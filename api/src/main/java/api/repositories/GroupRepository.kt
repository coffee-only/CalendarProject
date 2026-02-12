package api.repositories

import api.models.GroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface GroupRepository: JpaRepository<GroupEntity, Long> {
}