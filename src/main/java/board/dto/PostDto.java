package board.dto;

public class PostDto {
  private String title;
  private String content;
  private int postId;

  public PostDto(){
  }

  public PostDto(String title, String content, int postId) {
    this.title = title;
    this.content = content;
    this.postId = postId;
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

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) { this.postId = postId; }
}

