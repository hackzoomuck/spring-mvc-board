package board.controller;

import board.dao.PostDao;
import board.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
  private final PostDao postDao;

  public RegisterController(PostDao postDao){
    this.postDao = postDao;
  }

  @GetMapping(value = "/register")
  public String createPost(Model model) {
    model.addAttribute(new PostDto());
    return "register";
  }

  @PostMapping(value = "/register")
  public String addPostFromForm(PostDto postDto) {
    postDao.savePost(postDto);
    return "redirect:/detail/" + postDao.getMostRecentPost().getPostId();
  }

  @GetMapping(value = "detail/{postId}")
  public String showPostDetail(@PathVariable String postId, Model model) {
    PostDto postDto = new PostDto();
    model.addAttribute(postDto.getPostId());
    return "detail";
  }
}
