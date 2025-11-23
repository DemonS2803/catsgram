package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "id")
public class Post {

    private Long id;
    private Long authorId;
    private String description;
    private Instant postDate;

}
