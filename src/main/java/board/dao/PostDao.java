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

  public PostDto queryPost(int postId) {
    var query = "SELECT * FROM post WHERE post_id = ?";
    return Objects.requireNonNull(getJdbcTemplate())
        .queryForObject(query, BeanPropertyRowMapper.newInstance(PostDto.class), postId);
  }

  public List<PostDto> findAll() {
    var query = "SELECT * FROM post";
    return Objects.requireNonNull(getJdbcTemplate()).query(query, BeanPropertyRowMapper.newInstance(PostDto.class));
  }
}
