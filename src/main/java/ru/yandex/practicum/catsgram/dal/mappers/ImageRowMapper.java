package ru.yandex.practicum.catsgram.dal.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.catsgram.model.Image;

@Component
public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Image image = new Image();
        image.setId(resultSet.getLong("id"));
        image.setOriginalFileName(resultSet.getString("original_name"));
        image.setFilePath(resultSet.getString("path"));
        image.setPostId(resultSet.getLong("post_id"));

        return image;
    }
}
