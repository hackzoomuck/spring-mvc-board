package board.controller;

import board.dao.PostDao;
import board.dto.PostDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
  private final PostDao postDao;

  public RegisterController(PostDao postDao){
    this.postDao = postDao;
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String showRegisterPage() {
    return "register";
  }

  @RequestMapping(value="/list", method = RequestMethod.GET)
  public String showList(Model model){
    List<PostDto> postDtoList = postDao.findAll();
    model.addAttribute("postDtoList", postDtoList);
    return "list";
  }
}
