package api.Users;



/* DTO for user doesnt contain any sensitive data
 *
 *
 * */
public record UserDTO( String name,
                       String email
                     )
{
  public String getName(){return name;}
  public String getEmail(){return email;}
}
