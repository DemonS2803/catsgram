package ru.yandex.practicum.catsgram.exception;

import java.io.IOException;

public class ImageFileException extends RuntimeException {
    public ImageFileException(final String message, IOException e) {
        super(message, e);
    }

    public ImageFileException(final String message) {
        super(message);
    }
}
