package ru.yandex.practicum.catsgram.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.ImageDto;
import ru.yandex.practicum.catsgram.dto.NewImageRequest;
import ru.yandex.practicum.catsgram.model.Image;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageMapper {
    public static Image mapToImage(NewImageRequest request) {
        Image image = new Image();
        image.setPostId(request.getPostId());
        image.setOriginalFileName(request.getOriginalFileName());
        image.setFilePath(request.getFilePath());

        return image;
    }

    public static ImageDto mapToImageDto(Image image) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setPostId(image.getPostId());
        dto.setFilePath(image.getFilePath());
        dto.setOriginalFileName(image.getOriginalFileName());
        return dto;
    }

}
