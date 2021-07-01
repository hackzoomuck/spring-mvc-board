package board.dto;

public class Search {

  private int pageNumber = 1;
  private String postItem;
  private String postItemValue;
  private int postListSize = 5;
  private String uri = "/search?postItem=" + getPostItem() + "&postItemValue=" + getPostItemValue();

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
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

  public int getPostListSize() {
    return postListSize;
  }

  public void setPostListSize(int postListSize) {
    this.postListSize = postListSize;
  }

  public String getUri() {
    return uri;
  }
}
