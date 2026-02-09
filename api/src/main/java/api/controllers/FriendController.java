package api.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.services.FriendService;


@RestController
@RequestMapping(name = "/friend")
public class FriendController{
  private final FriendService FRIEND_SERVICE = new FriendService();

  @PostMapping("/sendrequest")
  public void SendFriendRequest(@RequestBody String request){
    // TODO: Implement FriendController.SendFriendRequest(): void
  }

  @PatchMapping("/accept")
  public void AccepterFriendRequest(){
    // TODO: Implement FriendController.AccepterFriendRequest(): void
  }

  @DeleteMapping("/deny")
  public void DenyFriendRequest(){
    // TODO: Implement FriendController.DenyFriendRequest(): void
  }


  @GetMapping("/getsentrequest")
  public void GetSentFriendRequest(){
    // TODO: Implement FriendController.GetSentFriendRequest(): void
  }
  
  @GetMapping("/getreceivedrequest")
  public void getRecevedRequest(){
    // TODO: Implement FriendController.getRecevedRequest(): void
  }
}
