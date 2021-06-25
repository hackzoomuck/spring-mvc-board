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

    var addLikePercentToValue = new StringBuilder();
    addLikePercentToValue.append("%").append(postItemValue).append("%");
    var query = new StringBuilder("SELECT * FROM post WHERE delete_whether=FALSE");

    if("postAll".equals(postItem)){
      query = new StringBuilder("SELECT * FROM post WHERE title LIKE ? OR content LIKE ?");
      String[] postItemValueArray = {addLikePercentToValue.toString(), addLikePercentToValue.toString()};
      return Objects.requireNonNull(getJdbcTemplate()).query(query.toString(), BeanPropertyRowMapper.newInstance(PostDto.class), postItemValueArray);
    }
    else if("title".equals(postItem)){
      query.append(" AND title LIKE ?");
    }
    else if("content".equals(postItem)){
      query.append(" AND content LIKE ?");
    }
    else{
      return Objects.requireNonNull(getJdbcTemplate()).query(query.toString(), BeanPropertyRowMapper.newInstance(PostDto.class));
    }

    return Objects.requireNonNull(getJdbcTemplate())
        .query(query.toString(), BeanPropertyRowMapper.newInstance(PostDto.class), addLikePercentToValue.toString());
  }

  public void savePost(PostDto postDto){
    var query = "INSERT INTO post (title, content) VALUES (?, ?)";
    String[] savePostValueArray = {postDto.getTitle(), postDto.getContent()};
    Objects.requireNonNull(getJdbcTemplate()).update(query, savePostValueArray);
  }

  public PostDto getMostRecentPost(){
    var query = "SELECT * FROM post ORDER BY post_id DESC limit 1";
    return Objects.requireNonNull(getJdbcTemplate()).queryForObject(query, BeanPropertyRowMapper.newInstance(PostDto.class));
  }

  public PostDto getPostByPostId(int postId){
    var query = "SELECT * FROM post WHERE post_id = ?";
    return Objects.requireNonNull(getJdbcTemplate()).queryForObject(query,BeanPropertyRowMapper.newInstance(PostDto.class), postId);
  }

  public int deletePost(int postId){
    var query = "UPDATE post SET delete_whether = TRUE WHERE post_id = " + postId;
    return getJdbcTemplate().update(query);
  }

  public void updatePost(PostDto postDto){
    var query = "UPDATE post SET title = ?, content = ? WHERE post_id = ?";
    String[] updatePostValueArray = {postDto.getTitle(), postDto.getContent(),
        String.valueOf(postDto.getPostId())};
    getJdbcTemplate().update(query, updatePostValueArray);
  }
}
