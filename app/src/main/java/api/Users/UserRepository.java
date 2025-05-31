package api.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
/* DOCUMENTATION:
 * spring Repositories
 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 * https://www.youtube.com/watch?v=9SGDpanrc8U a 40min
 *
 *
 * */
@Repository
public interface UserRepository extends JpaRepository<UserModel,Long>{


  @Modifying
  @Transactional
  @Query(value="INSERT INTO CalUser(username,email,psw,salt) value(?1, ?2, ?3, ?4)", nativeQuery = true)
  int CreateUser(String username, String email, String psw, String salt);
  
  @Modifying
  @Transactional
  @Query(value="DELETE FROM CalUser WHERE email = ?1", nativeQuery = true)
  int DeleteUser(String email);
  
  @Modifying
  @Transactional
  @Query(value="UPDATE CalUser SET(username= ?1) WHERE email = ?2")
  int UpdateUser(String username, String email);


  @Query(value= "SELECT * FROM CalUser WHERE id >=?1 && id<?2",nativeQuery = true)
  UserModel fetchById(int min, int max);

  @Query(value = "SELECT * FROM CalUser WHERE email = ?1", nativeQuery = true)
  UserModel findByEmail(String email);

  @Query(value = "SELECT * FROM CalUser WHERE id = ?1", nativeQuery = true)
  UserModel findById(int id);
}
