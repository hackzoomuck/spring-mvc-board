package board.controller;

import board.dao.PostDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
  private final PostDao postDao;

  public SearchController(PostDao postDao){
    this.postDao = postDao;
  }

  @GetMapping(value="/search")
  public String search(Model model, final String postItem, final String postItemValue) {
    model.addAttribute("postList", postDao.queryPost(postItem, postItemValue));
    model.addAttribute("postItem", postItem);
    return "search";
  }

}
