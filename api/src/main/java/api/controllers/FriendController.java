import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/friend")
public class FriendController{
  private final FriendService FRIEND_SERVICE;

  @PostMapping("/sendrequest")
  public void SendFriendRequest(@RequestBody FriendRequest request){
    try{


    } catch() {

    }
  }

  @PatchMapping("/accept")
  public void AccepterFriendRequest(){
    try{

    } catch() {

    }
  }

  @DeleteMapping("/deny")
  public void DenyFriendRequest(){
    try {

    } catch() {

    }
  
  }


  @GetMapping("/getsentrequest")
  public void GetSentFriendRequest(){
    try {

    } catch() {

    }
  }
  
  @GetMapping("/getreceivedrequest")
  public void getRecevedRequest(){
    try {

    } catch() {

    }
  }
}
