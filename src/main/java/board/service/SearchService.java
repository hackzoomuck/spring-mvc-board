package board.service;

import board.dao.PostDao;
import board.dto.PostDto;
import board.dto.SearchDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  private final static int LIST_SIZE = 10;
  private final static int PAGE_SIZE = 5;
  private final PostDao postDao;

  public SearchService(PostDao postDao) {
    this.postDao = postDao;
  }

  public SearchDto search(final SearchDto searchDto) {

    /**
     * TODO  검색 기능 구현
     * 0. search 함수 이름이 맞는지?
     * 1. call total count
     *  - type / keyword
     * 2. call list (with pageNumber)
     *  - list_size
     *  - page_number
     *  - type / keyword
     * 3. SearchDto set
     *  - total count
     *  - list
     */
    int totalCount = postDao.queryTotalCount(searchDto.getPostItem(), searchDto.getPostItemValue());
    List<PostDto> postDtoList = postDao
        .queryCurrentPostList(LIST_SIZE, searchDto.getPageNumber(), searchDto.getPostItem(),
            searchDto.getPostItemValue());

    int startPageNumber = (searchDto.getPageNumber() / PAGE_SIZE) * PAGE_SIZE + 1;
    int endPageNumber = (searchDto.getPageNumber() / PAGE_SIZE + 1) * PAGE_SIZE;
    int totalEndPageNumber = totalCount / LIST_SIZE;

    if (searchDto.getPageNumber() % PAGE_SIZE == 0) {
      startPageNumber = searchDto.getPageNumber() - PAGE_SIZE + 1;
      endPageNumber = searchDto.getPageNumber();
    }
    if (totalCount % LIST_SIZE > 0) {
      totalEndPageNumber += 1;
    }
    if (totalEndPageNumber < endPageNumber) {
      endPageNumber = totalEndPageNumber;
    }

    searchDto.setTotalCount(totalCount);
    searchDto.setPostDtoList(postDtoList);
    searchDto.setStartPageNumber(startPageNumber);
    searchDto.setEndPageNumber(endPageNumber);
    return searchDto;
  }
}
