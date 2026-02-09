package api.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import api.repositories.UserRepository;
import api.dtos.UserDTO;
import api.models.UserModel;
import api.maps.UserMapper;
import api.exceptions.UserModelException;



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

  public void DeleteAccount(Long id) throws UserModelException{
    try{
      USER_REPO.DeleteUser(id);
    } catch(Exception ex){
      throw new UserModelException("Couldn't find user");
    }
  }

  public UserDTO GetAccountInfo(Long id) throws UserModelException{ 
    try { 
      System.out.println("REQUEST ID: "+id);
      
      USER_REPO.findAll().forEach(u -> 
      System.out.println("Found user: ID=" + u.getId() + ", Email=" + u.getEmail()));

      System.out.println("after for each");
      UserModel user = USER_REPO.findById(2L)
                                .orElseThrow(()-> new UserModelException("someting"));
 
      return MAPPER.apply(user);
    } catch(Exception ex) {
      throw new UserModelException("Couldn't find user: "+ex.getMessage()); 
    }
  }
}
