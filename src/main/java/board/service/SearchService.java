package board.service;

import board.dao.PostDao;
import board.dto.PostDto;
import board.dto.SearchDto;
import board.utils.PageUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  private static final int LIST_SIZE = 5;
  private static final int PAGE_SIZE = 2;
  private final PostDao postDao;

  public SearchService(PostDao postDao) {
    this.postDao = postDao;
  }

  public SearchDto search(SearchDto searchDto) {

    int totalCount = postDao.queryPost(searchDto.getPostItem(), searchDto.getPostItemValue())
        .size();
    List<PostDto> postDtoList = currentPostList(searchDto);
    var pageUtils = new PageUtils();
    pageUtils.pageCalculate(searchDto, totalCount, LIST_SIZE, PAGE_SIZE);
    searchDto.setTotalCount(totalCount);
    searchDto.setPostDtoList(postDtoList);
    return searchDto;
  }

  public List<PostDto> currentPostList(SearchDto searchDto) {

    List<PostDto> postDtoList = new ArrayList<>(
        postDao.queryPost(searchDto.getPostItem(), searchDto.getPostItemValue()));
    List<PostDto> result = new ArrayList<>();
    int startListNumber = (searchDto.getPageNumber() - 1) * LIST_SIZE;
    int lastListNumber = searchDto.getPageNumber() * LIST_SIZE;
    if (postDtoList.size() < lastListNumber) {
      lastListNumber = postDtoList.size();
    }
    for (int idx = startListNumber; idx < lastListNumber; idx++) {
      result.add(postDtoList.get(idx));
    }
    return result;
  }
}
