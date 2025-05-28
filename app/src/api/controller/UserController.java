package User;

import java.util.concurrent.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
public class UserController{

  // non-query actions
  @PostMapping("user/register")
  public ResponseEntity<String> Register(@RequestBody String name, 
                                         @RequestBody String email,
                                         @RequestBody String psw)
  {  
    try{
      //todo: implement service
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {

      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST)
    }
  }

  @PatchMapping("user/update")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> UpdateAccountUsername(){
    try{
      //todo: implement service
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST)
    } 
  }

  @DeleteMapping("user/delete")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> DeleteAccount(@RequestBody String email,
                                              @RequestBody String psw)
  {
    try{
      //todo: implement service
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST)
    } 
  }


  //query actions
  @GetMapping("user/getinfo")//probably will be in its own RestController AccountManagement
  public ResponseEntity<String> GetAccountInfo(@RequestHeader String TokenJwt){ //prob have to change type 
    try{
      //todo: implement service
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST)
    } 
  }

}
