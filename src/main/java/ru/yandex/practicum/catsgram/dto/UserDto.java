package ru.yandex.practicum.catsgram.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant registrationDate;
}
