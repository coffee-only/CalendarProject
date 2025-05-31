package api.Users;




/* DOCUMENTATION:
 * spring Repositories
 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 * https://www.youtube.com/watch?v=9SGDpanrc8U a 40min
 *
 *
 * */

public class UserRepository{


  public void Create(UserDTO user, String psw) {
      //TODO: not sure how to connect yet
  }

  public void Update(UserDTO user) {
    
  }

  public void Delete(String email, String psw) {
    //requiert le email et le mot de passe pour delete
  }

  public List<UserModel>Read() {
      
  }

  public UserModel getInfo(int id){
    

    return;
  }

}
