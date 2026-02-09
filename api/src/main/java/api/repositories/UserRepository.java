package api.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import api.models.UserModel;
/* DOCUMENTATION:
 * spring Repositories
 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 * https://www.youtube.com/watch?v=9SGDpanrc8U a 40min
 *
 *
 * */
@Repository
public

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





