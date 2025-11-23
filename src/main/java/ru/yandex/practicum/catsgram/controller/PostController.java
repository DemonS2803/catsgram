package ru.yandex.practicum.catsgram.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> findAll(
            @RequestParam(value = "from") Optional<Integer> from,
            @RequestParam(value = "size") Optional<Integer> size,
            @RequestParam(value = "sort") Optional<String> sort
    ) {
        boolean isAsc = true;
        // Вот если бы не это условие,
        // то можно было бы по красоте сделать через дефолты
        if (from.isEmpty() && size.isEmpty() && sort.isEmpty()) {
            return postService.findLatest(10);
        }
        if (from.isEmpty()) {
            from = Optional.of(0);
        }
        if (size.isEmpty()) {
            size = Optional.of(10);
        }
        if (sort.isPresent() && !sort.get().equals("asc")) {
            isAsc = false;
        }
        return postService.findAll(from.get(), size.get(), isAsc);
    }

    @GetMapping("/{postId}")
    public Optional<Post> findById(@PathVariable long postId) {
        return postService.findById(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}
