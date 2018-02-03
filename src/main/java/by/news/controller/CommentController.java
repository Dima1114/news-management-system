package by.news.controller;

import by.news.dto.CommentDto;
import by.news.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/comment/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.ok(errors);
        }
        commentService.addComment(commentDto);
        return ResponseEntity.ok().build();
    }
}
