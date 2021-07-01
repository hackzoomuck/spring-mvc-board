package board.dto;

public class Page {

  private int startPageNumber;
  private int endPageNumber;
  private int totalEndPageNumber;
  private int totalCount;
  private int pageSize = 2;
  
  public int getStartPageNumber() {
    return startPageNumber;
  }

  public void setStartPageNumber(int pageNumber) {
    int result = (pageNumber / pageSize) * pageSize + 1;
    if (pageNumber % pageSize == 0) {
      result = pageNumber - pageSize + 1;
    }
    this.startPageNumber = result;
  }

  public int getEndPageNumber() {
    return endPageNumber;
  }

  public void setEndPageNumber(int pageNumber) {
    int result = ((pageNumber / pageSize) + 1) * pageSize;
    if (pageNumber % pageSize == 0) {
      result = pageNumber;
    }
    if (getTotalEndPageNumber() < result) {
      result = totalEndPageNumber;
    }
    this.endPageNumber = result;
  }

  public int getTotalEndPageNumber() {
    return totalEndPageNumber;
  }

  public void setTotalEndPageNumber(int postListSize) {
    int result = totalCount / postListSize;
    if (totalCount % postListSize > 0) {
      result += 1;
    }
    this.totalEndPageNumber = result;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
