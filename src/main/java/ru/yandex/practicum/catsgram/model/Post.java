package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {

    private Long id;
    private Long authorId;
    private String description;
    private Instant postDate;

}
