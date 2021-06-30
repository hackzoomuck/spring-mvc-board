package board.dto;

import java.util.List;

public class SearchDto {

  private int pageNumber = 1;
  private int startPageNumber;
  private int endPageNumber;
  private String postItem;
  private String postItemValue;
  private int totalCount;
  private List<PostDto> postDtoList;

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getStartPageNumber() {
    return startPageNumber;
  }

  public void setStartPageNumber(int startPageNumber) {
    this.startPageNumber = startPageNumber;
  }

  public int getEndPageNumber() {
    return endPageNumber;
  }

  public void setEndPageNumber(int endPageNumber) {
    this.endPageNumber = endPageNumber;
  }

  public String getPostItem() {
    return postItem;
  }

  public void setPostItem(String postItem) {
    this.postItem = postItem;
  }

  public String getPostItemValue() {
    return postItemValue;
  }

  public void setPostItemValue(String postItemValue) {
    this.postItemValue = postItemValue;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public List<PostDto> getPostDtoList() {
    return postDtoList;
  }

  public void setPostDtoList(List<PostDto> postDtoList) {
    this.postDtoList = postDtoList;
  }
}
