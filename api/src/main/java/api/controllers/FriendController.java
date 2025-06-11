import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "friend");
public class FriendController{

  @PostMapping("/sendrequest")
  public void SendFriendRequest(@RequestBody ){

  }

  @PatchMapping("/accept")
  public void AccepterFriendRequest(@RequestBody  ){
  

  }

  @DeleteMapping("/deny")
  public void DenyFriendRequest(){

  
  }


  @GetMapping("/getsentrequest")
  public void GetSentFriendRequest(){

  }
  
  @GetMapping("/getreceivedrequest")
  public void getRecevedRequest(){

  }
}
