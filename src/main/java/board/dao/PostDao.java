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

    StringBuilder addLikePercentToValue = new StringBuilder();
    addLikePercentToValue.append("%").append(postItemValue).append("%");
    StringBuilder query = new StringBuilder("SELECT * FROM post WHERE 1=1");

    if("postAll".equals(postItem)){
      query = new StringBuilder("SELECT * FROM post WHERE post_id LIKE ? OR title LIKE ? OR content LIKE ?");
      String[] postItemValueArray = {addLikePercentToValue.toString(), addLikePercentToValue.toString(), addLikePercentToValue.toString()};
      return Objects.requireNonNull(getJdbcTemplate()).query(query.toString(), BeanPropertyRowMapper.newInstance(PostDto.class), postItemValueArray);
    }
    else if("postId".equals(postItem)){
      query.append(" AND post_id Like ?");
    }
    else if("title".equals(postItem)){
      query.append(" AND title LIKE ?");
    }
    else if("content".equals(postItem)){
      query.append(" AND content LIKE ?");
    }
    else{
      return Objects.requireNonNull(getJdbcTemplate().query(query.toString(), BeanPropertyRowMapper.newInstance(PostDto.class)));
    }

    return Objects.requireNonNull(getJdbcTemplate())
        .query(query.toString(), BeanPropertyRowMapper.newInstance(PostDto.class), addLikePercentToValue.toString());
  }
}
