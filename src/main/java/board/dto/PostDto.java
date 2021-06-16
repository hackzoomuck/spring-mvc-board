package board.dto;

public class PostDto {
  private String title;
  private String content;
  private int titleId;

  public PostDto(){
  }

  public PostDto(String title, String content, int titleId) {
    this.title = title;
    this.content = content;
    this.titleId = titleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getTitleId() {
    return titleId;
  }

  public void setTitleId(int titleId) {
    this.titleId = titleId;
  }
}

