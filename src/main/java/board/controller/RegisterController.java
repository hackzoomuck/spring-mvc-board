package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

  @GetMapping(value = "/register")
  public String showRegisterPage() {
    return "register";
  }

}
