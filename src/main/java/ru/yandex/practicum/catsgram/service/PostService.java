package ru.yandex.practicum.catsgram.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.catsgram.dal.PostRepository;
import ru.yandex.practicum.catsgram.dto.NewPostRequest;
import ru.yandex.practicum.catsgram.dto.PostDto;
import ru.yandex.practicum.catsgram.dto.UpdatePostRequest;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.mapper.PostMapper;
import ru.yandex.practicum.catsgram.model.Post;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public Collection<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public Collection<PostDto> findAll(int from, int size, boolean isAsc) {
        return postRepository.findAllPageable(from, size, isAsc).stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public Collection<PostDto> findLatest(int size) {
        return postRepository.findAllPageable(0, size, false).stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto findById(long id) {
        return postRepository.findById(id)
                .map(PostMapper::mapToPostDto)
                .orElseThrow(() -> new NotFoundException("Пост не найден с ID: " + id));
    }

    public PostDto createPost(NewPostRequest request) {
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }
        if (!userService.isUserExist(request.getAuthorId())) {
            throw new ConditionsNotMetException("Автор с id = " + request.getAuthorId() + " не найден");
        }

        Post post = PostMapper.mapToPost(request);
        post = postRepository.save(post);

        return PostMapper.mapToPostDto(post);
    }


    public PostDto updatePost(long postId, UpdatePostRequest request) {
        Post updatedPost = postRepository.findById(postId)
                .map(post -> PostMapper.updatePostFields(post, request))
                .orElseThrow(() -> new NotFoundException("Пост не найден"));
        updatedPost = postRepository.update(updatedPost);
        return PostMapper.mapToPostDto(updatedPost);
    }

}
