package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "id")
public class Image {

    private Long id;
    private Long postId;
    private String originalFileName;
    private String filePath;

}
