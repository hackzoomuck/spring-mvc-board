package board.service;

import board.dao.PostDao;
import board.dto.Page;
import board.dto.Post;
import board.dto.Search;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  private final PostDao postDao;

  public SearchService(PostDao postDao) {
    this.postDao = postDao;
  }

  public List<Post> search(Search search) {
    return currentPostList(search);
  }

  public Page setPage(Search search) {
    int totalCount = postDao.queryPost(search.getPostItem(), search.getPostItemValue()).size();
    var page = new Page();
    page.setTotalCount(totalCount);
    page.setTotalEndPageNumber(search.getPostListSize());
    page.setStartPageNumber(search.getPageNumber());
    page.setEndPageNumber(search.getPageNumber());
    return page;
  }

  public List<Post> currentPostList(Search search) {
    List<Post> postList = new ArrayList<>(
        postDao.queryPost(search.getPostItem(), search.getPostItemValue()));
    List<Post> result = new ArrayList<>();
    int startListNumber = (search.getPageNumber() - 1) * search.getPostListSize();
    int lastListNumber = search.getPageNumber() * search.getPostListSize();
    if (postList.size() < lastListNumber) {
      lastListNumber = postList.size();
    }
    for (int idx = startListNumber; idx < lastListNumber; idx++) {
      result.add(postList.get(idx));
    }
    return result;
  }
}
