package api.Users;


/*  Pour l'instant la class est basic
 *  Je compte checker plus sur comment spring gere 
 *  Les repos pendant que je check spring security
 *
 *
 * */
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
      USER_REPO.Create(dto, psw);
    
    } catch(Exception ex) {//mieux gerer exception pour repo
      throw new UserModelException("Couldn't find user");
    }//a creer une classe mot de passe pour encapsuler la logic ou sinon quelque fonction
  }

  public void UpdateAccount(UserDTO dto) throws UserModelException{
    try {
      USER_REPO.Update(dto);

    } catch(Exception ex) {
      throw new UserModelException("Couldn't find user");
    }
  }

  public void DeleteAccount(String email, String psw) throws UserModelException{
    try{
      USER_REPO.Delete(email, psw);
    } catch(Exception ex){
      throw new UserModelException("Couldn't find user");
    }
  }

  public UserDTO GetAccountInfo(int id) throws UserModelException{ 
    try {
      return MAPPER.apply(USER_REPO.getInfo(id));
    } catch(Exception ex) {
      throw new UserModelException("Couldn't find user"); 
    }
  }

}
