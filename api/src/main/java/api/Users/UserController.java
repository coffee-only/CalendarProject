package api.Users;


import java.util.concurrent.atomic.AtomicLong;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController{
  private final UserService USER_SERVICE;

  public UserController(UserService service){
   USER_SERVICE = service; 
  }
  // non-query actions
  @PostMapping("/register")
  public ResponseEntity<?> Register(@RequestBody RegisterDTO dto)
  { System.out.println("in function");  
    try{
      //create password logic 
      USER_SERVICE.Register(new UserDTO(dto.username, dto.email), dto.password);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }



  @PatchMapping("/update")//probably will be in its own RestController AccountManagement
  public ResponseEntity<?> UpdateAccountUsername(@RequestBody String username,
                                                      @RequestBody String email)
  {
    try{
      
      USER_SERVICE.UpdateAccount(new UserDTO(username, email));
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }



  @DeleteMapping("/delete")//probably will be in its own RestController AccountManagement
  public ResponseEntity<?> DeleteAccount()
  {
    try{
      
      USER_SERVICE.DeleteAccount("","");
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }




  //query actions
  @GetMapping("/getinfo")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> GetAccountInfo(){ //prob have to change type 
    try{
      
      USER_SERVICE.GetAccountInfo(0L);//token a handle a travers spring security
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }

}



class RegisterDTO {
    public String username;
    public String email;
    public String password;

}

