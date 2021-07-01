package board.utils;

import board.dto.SearchDto;

public class PageUtils {

  //pageDto로 옮기기
  public void pageCalculate(SearchDto searchDto, int totalCount, int listSize, int pageSize) {
    int startPageNumber = (searchDto.getPageNumber() / pageSize) * pageSize + 1;
    int endPageNumber = (searchDto.getPageNumber() / pageSize + 1) * pageSize;
    int totalEndPageNumber = totalCount / listSize;

    if (searchDto.getPageNumber() % pageSize == 0) {
      startPageNumber = searchDto.getPageNumber() - pageSize + 1;
      endPageNumber = searchDto.getPageNumber();
    }
    if (totalCount % listSize > 0) {
      totalEndPageNumber += 1;
    }
    if (totalEndPageNumber < endPageNumber) {
      endPageNumber = totalEndPageNumber;
    }

    searchDto.setStartPageNumber(startPageNumber);
    searchDto.setEndPageNumber(endPageNumber);
    searchDto.setTotalEndPageNumber(totalEndPageNumber);

  }
}
