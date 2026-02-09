package api.repositories;


import api.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface FriendRepository extends JpaRepository<UserModel, Long> {


    @Modifying
    @Transactional
    @Query(value="INSERT INTO CalUser (name, email, password) VALUES (?1, ?2, ?3)", nativeQuery = true)
    int CreateUser(String username, String email, String psw);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM CalUser WHERE id = ?1", nativeQuery = true)
    int DeleteUser(Long id);

    @Modifying
    @Transactional
    @Query(value="UPDATE CalUser SET name= ?1 WHERE email = ?2", nativeQuery = true)
    int UpdateUser(String username, String email);


    @Query(value= "SELECT * FROM CalUser WHERE id >=?1 AND id<?2",nativeQuery = true)
    List<UserModel> fetchById(int min, int max);
}
