package board.controller;

import board.dao.PostDao;
import board.dto.PostDto;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RestBoardController {

  private final PostDao postDao;

  public RestBoardController(PostDao postDao){
    this.postDao = postDao;
  }

  @RequestMapping(value = "/")
  public String hello() {
    return "title : " + postDao.queryPost(1).getTitle() + ", content : " + postDao.queryPost(1).getContent();
  }

  @RequestMapping(value = "/post", method = RequestMethod.GET)
  public PostDto post(final int postId) {
    return postDao.queryPost(postId);
  }

  @RequestMapping("/list")
  public List<PostDto> postList() {
    return postDao.findAll();
  }
}
