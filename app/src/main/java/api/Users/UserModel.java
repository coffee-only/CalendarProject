package api.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
/* Model for table CalUser
 *
 * */
@Entity
@Table(name = "CalUser")
public class UserModel{
  @Id
  @Column(name = "id")
  private Long id;
  
  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "password")
  private String password;

  @NotNull
  @Column(name = "salt") 
  private String salt;
  
  //required for JPA
  public UserModel(){}

  public UserModel( String name, String email, String password){
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public Long getId() {return id;}
  public String getName(){return name;} 
  public String getEmail(){return email;}
  public String getPassword(){return password;}
  public String getSalt(){return salt;}
  }
