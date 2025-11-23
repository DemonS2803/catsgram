package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "id")
public class Image {

    private Long id;
    private Long postId;
    private String originalFileName;
    private String filePath;

}
