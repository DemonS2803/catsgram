package ru.yandex.practicum.catsgram.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.catsgram.model.Image;

@Repository
public class ImageRepository extends BaseRepository<Image> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM image_storage";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM image_storage WHERE id = ?";
    private static final String FIND_BY_POST_ID_QUERY = "SELECT * FROM image_storage WHERE post_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO image_storage(original_name, file_path, post_id)" +
            "VALUES (?, ?, ?, ?) returning id";
    private static final String UPDATE_QUERY = "UPDATE image_storage SET original_name = ?, file_path = ?, post_id = ? WHERE id = ?";

    public ImageRepository(JdbcTemplate jdbc, RowMapper<Image> mapper) {
        super(jdbc, mapper);
    }

    public List<Image> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public Optional<Image> findById(long id) {
        return findOne(FIND_BY_ID_QUERY);
    }

    public List<Image> findByPostId(long id) {
        return findMany(FIND_BY_POST_ID_QUERY, id);
    }

    public Image save(Image image) {
        long id = insert(INSERT_QUERY,
                image.getOriginalFileName(),
                image.getFilePath(),
                image.getPostId()
        );
        image.setId(id);
        return image;
    }

    public Image update(Image image) {
        update(UPDATE_QUERY,
                image.getOriginalFileName(),
                image.getFilePath(),
                image.getPostId(),
                image.getId()
        );
        return image;
    }

}
