package api.Users;


import java.util.concurrent.atomic.AtomicLong;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class UserController{
  private final UserService USER_SERVICE;

  public UserController(UserService service){
   USER_SERVICE = service; 
  }
  // non-query actions
  @PostMapping("user/register")
  public ResponseEntity<String> Register(@RequestBody String username, 
                                         @RequestBody String email,
                                         @RequestBody String psw)
  {  
    try{
      //create password logic 
      USER_SERVICE.Register(new UserDTO(username, email), psw, salt);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {

      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }



  @PatchMapping("user/update")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> UpdateAccountUsername(@RequestBody String username,
                                                      @RequestBody String email)
  {
    try{
      
      USER_SERVICE.UpdateAccount(new UserDTO(username, email));
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }



  @DeleteMapping("user/delete")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> DeleteAccount(@RequestBody String email,
                                              @RequestBody String psw)
  {
    try{
      
      USER_SERVICE.DeleteAccount(email,psw);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }




  //query actions
  @GetMapping("user/getinfo")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> GetAccountInfo(@RequestHeader String TokenJwt){ //prob have to change type 
    try{
      
      USER_SERVICE.GetAccountInfo(TokenJwt.id);//token a handle a travers spring security
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }

}
