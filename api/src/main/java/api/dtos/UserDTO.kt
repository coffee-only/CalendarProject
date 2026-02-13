package api.dtos;



/* DTO for user doesnt contain any sensitive data
 *
 *
 * */
public class UserDTO {
  public String username;
  public String email;

  public UserDTO(String username, String email) {
    this.username = username;
    this.email = email;
  }
  public String getName(){return username;}
  public String getEmail(){return email;}
}
