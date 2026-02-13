package api.maps;

//java lib
import java.util.function.Function;
//spring lib
import org.springframework.stereotype.Service;
import api.entities.UserEntity;
import api.dtos.UserDTO;


@Service
public class UserMapper implements Function<UserEntity, UserDTO>{
    @Override
    public UserDTO apply(UserEntity u){
      return new UserDTO(u.getName(), u.getEmail());
    }
}
