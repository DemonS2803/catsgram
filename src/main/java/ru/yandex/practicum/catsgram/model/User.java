package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Instant registrationDate;

}

