package board.controller;

import board.dao.PostDao;
import board.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
  private final PostDao postDao;

  public PostController(PostDao postDao){
    this.postDao = postDao;
  }

  @GetMapping(value = "/register")
  public String createPost(Model model) {
    model.addAttribute(new PostDto());
    return "register";
  }

  @PostMapping(value = "/register")
  public String addPost(PostDto postDto) {
    postDao.savePost(postDto);
    return "redirect:/detail/" + postDao.getMostRecentPost().getPostId();
  }

  @GetMapping(value = "detail/{postId}")
  public String showPostDetail(@PathVariable int postId, Model model) {
    model.addAttribute("postDto", postDao.getPostByPostId(postId));
    return "detail/detail";
  }

  @GetMapping(value = "detail/update/{postId}")
  public String showPostUpdate(@PathVariable int postId, Model model) {
    model.addAttribute("postDto", postDao.getPostByPostId(postId));
    return "detail/update";
  }

  @PostMapping(value = "detail/update/{postId}")
  public String updatePost(@PathVariable int postId, PostDto postDto) {
    postDao.updatePost(postDto);
    return "redirect:/detail/" + postId;
  }

  @GetMapping(value = "detail/delete/{postId}")
  public String deletePost(@PathVariable int postId){
    postDao.deletePost(postId);
    return "redirect:/search";
  }
}
