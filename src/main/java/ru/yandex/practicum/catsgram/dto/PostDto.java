package ru.yandex.practicum.catsgram.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long authorId;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant postDate;
}
