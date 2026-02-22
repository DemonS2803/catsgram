package ru.yandex.practicum.catsgram.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.catsgram.model.Post;

@Repository
public class PostRepository extends BaseRepository<Post> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM posts";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM posts WHERE id = ?";
    private static final String FIND_BY_AUTHOR_ID_QUERY = "SELECT * FROM posts WHERE author_id = ?";
    private static final String FIND_ALL_PAGEABLE_QUERY = "SELECT * FROM posts ORDER BY post_date ? LIMIT ? OFFSET ?";
    private static final String INSERT_QUERY = "INSERT INTO posts(author_id, description, post_date)" +
            "VALUES (?, ?, ?, ?) returning id";
    private static final String UPDATE_QUERY = "UPDATE posts SET author_id = ?, description = ?, post_date = ? WHERE id = ?";

    public PostRepository(JdbcTemplate jdbc, RowMapper<Post> mapper) {
        super(jdbc, mapper);
    }

    public List<Post> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public Optional<Post> findByAuthorId(Long authorId) {
        return findOne(FIND_BY_AUTHOR_ID_QUERY, authorId);
    }

    public List<Post> findAllPageable(int offset, int limit, boolean isAsc) {
        return findMany(FIND_ALL_PAGEABLE_QUERY, offset, limit, isAsc ? "ASC" : "DESC");
    }

    public Optional<Post> findById(long postId) {
        return findOne(FIND_BY_ID_QUERY, postId);
    }

    public Post save(Post post) {
        long id = insert(
                INSERT_QUERY,
                post.getAuthorId(),
                post.getDescription(),
                post.getPostDate()
        );
        post.setId(id);
        return post;
    }

    public Post update(Post post) {
        update(
                UPDATE_QUERY,
                post.getAuthorId(),
                post.getDescription(),
                post.getPostDate(),
                post.getId()
        );
        return post;
    }
}
