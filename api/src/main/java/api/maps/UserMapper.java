package api.maps;

//java lib
import java.util.function.Function;
//spring lib
import org.springframework.stereotype.Service;
import api.models.UserModel;
import api.dtos.UserDTO;


@Service
public class UserMapper implements Function<UserModel, UserDTO>{
    @Override
    public UserDTO apply(UserModel u){
      return new UserDTO(u.getName(), u.getEmail());
    }
}
