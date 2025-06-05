package api.Users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*  Pour l'instant la class est basic
 *  Je compte checker plus sur comment spring gere 
 *  Les repos pendant que je check spring security
 *
 *
 * */
@Service
public class UserService{
  //tres coupler pour l'instant je suis entrain de checker comment spring boot fait des repo 
  private final UserRepository USER_REPO;//https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html
  private final UserMapper MAPPER;
  //constructor:

  public UserService(UserRepository repo) {
    USER_REPO = repo;
    MAPPER = new UserMapper();
  }
  
  public void Register(UserDTO dto, String psw) throws UserModelException {
     try { 
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);//to taylor 16 migh be too intense
      String password = encoder.encode(psw);

      USER_REPO.CreateUser(dto.getName(),dto.getEmail(), password);
    
    } catch(Exception ex) {//mieux gerer exception pour repo
      throw new UserModelException("Couldn't find user");
    }//a creer une classe mot de passe pour encapsuler la logic ou sinon quelque fonction
  }

  public void UpdateAccount(UserDTO dto) throws UserModelException{
    try {
      USER_REPO.UpdateUser(dto.getName(),dto.getEmail());

    } catch(Exception ex) {
      throw new UserModelException("Couldn't find user");
    }
  }

  public void DeleteAccount(String email, String psw) throws UserModelException{
    try{
      USER_REPO.DeleteUser(email);
    } catch(Exception ex){
      throw new UserModelException("Couldn't find user");
    }
  }

  public UserDTO GetAccountInfo(Long id) throws UserModelException{ 
    try {
      UserModel user = USER_REPO.findById(id)
                                .orElseThrow(()-> new UserModelException("Couldn't find user"));
      return MAPPER.apply(user);
    } catch(Exception ex) {
      throw new UserModelException("Couldn't find user"); 
    }
  }

}
