package api.controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;


import api.dtos.UserDTO;



@RestController
@RequestMapping("/user")
public class UserController{
  private final UserService USER_SERVICE;

  public UserController(UserService service){
   USER_SERVICE = service; 
  }
  // non-query actions
  /* BUG:
  @PostMapping("/register")
  public ResponseEntity<?> Register(@RequestBody RegisterRequest dto)
  { 
    try{
      //create password logic 
      USER_SERVICE.Register(new UserDTO(dto.username(), dto.email()), dto.password());
      return new ResponseEntity<>(null, HttpStatus.OK); // FIXME: constructor does not exist
    } catch(Exception ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }


  @PatchMapping("/update")//probably will be in its own RestController AccountManagement
  public ResponseEntity<?> UpdateAccountUsername(@RequestBody UpdateRequest dto)
  {
    try{
      
      USER_SERVICE.UpdateAccount(new UserDTO(dto.username(), dto.email()));
      return new ResponseEntity<>(null, HttpStatus.OK); // FIXME: constructor does not exist
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }



  @DeleteMapping("/delete/{id}")//probably will be in its own RestController AccountManagement
  public ResponseEntity<?> DeleteAccount(@PathVariable Long id)
  {
    try{
      
      USER_SERVICE.DeleteAccount(id);
      return new ResponseEntity<>(null, HttpStatus.OK); // FIXME: constructor does not exist
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  } */




  //query actions
  @GetMapping("/getinfo/{id}")//probably will be in its own RestController AccountManagement
  public ResponseEntity<?> GetAccountInfo(@PathVariable Long id)
  { //prob have to change type 
    try{
      UserDTO dto = USER_SERVICE.GetAccountInfo(id);//token a handle a travers spring security
      return new ResponseEntity<>(dto, HttpStatus.OK);
    } catch(Exception ex) {
      
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } 
  }

}





