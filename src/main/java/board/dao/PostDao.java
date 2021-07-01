package board.dao;

import board.dto.PostDto;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class PostDao extends NamedParameterJdbcDaoSupport {

  public PostDao(JdbcTemplate jdbcTemplate) {
    setJdbcTemplate(jdbcTemplate);
  }

  public List<PostDto> queryPost(String postItem, String postItemValue) {

    var addLikePercentToValue = "%" + postItemValue + "%";
    var query = "SELECT * FROM post WHERE delete_whether=FALSE";

    if ("postAll".equals(postItem)) {
      query += " AND (title LIKE ? OR content LIKE ?)";
      return Objects.requireNonNull(getJdbcTemplate())
          .query(query, BeanPropertyRowMapper.newInstance(PostDto.class),
              addLikePercentToValue, addLikePercentToValue);
    } else if ("title".equals(postItem)) {
      query += " AND title LIKE ?";
    } else if ("content".equals(postItem)) {
      query += " AND content LIKE ?";
    } else {
      return Objects.requireNonNull(getJdbcTemplate())
          .query(query, BeanPropertyRowMapper.newInstance(PostDto.class));
    }

    return Objects.requireNonNull(getJdbcTemplate())
        .query(query, BeanPropertyRowMapper.newInstance(PostDto.class),
            addLikePercentToValue);
  }

  public void savePost(PostDto postDto) {
    var query = "INSERT INTO post (title, content) VALUES (?, ?)";
    Objects.requireNonNull(getJdbcTemplate())
        .update(query, postDto.getTitle(), postDto.getContent());
  }

  public PostDto getMostRecentPost() {
    var query = "SELECT * FROM post ORDER BY post_id DESC limit 1";
    return Objects.requireNonNull(getJdbcTemplate())
        .queryForObject(query, BeanPropertyRowMapper.newInstance(PostDto.class));
  }

  public PostDto getPostByPostId(int postId) {
    var query = "SELECT * FROM post WHERE post_id = ?";
    return Objects.requireNonNull(getJdbcTemplate())
        .queryForObject(query, BeanPropertyRowMapper.newInstance(PostDto.class), postId);
  }

  public void deletePost(int postId) {
    var query = "UPDATE post SET delete_whether = TRUE WHERE post_id = " + postId;
    Objects.requireNonNull(getJdbcTemplate()).update(query);
  }

  public void updatePost(PostDto postDto) {
    var query = "UPDATE post SET title = ?, content = ? WHERE post_id = ?";
    Objects.requireNonNull(getJdbcTemplate())
        .update(query, postDto.getTitle(), postDto.getContent(),
            String.valueOf(postDto.getPostId()));
  }
}
