package by.news.service.impl;

import by.news.dto.CommentDto;
import by.news.entity.Comment;
import by.news.repository.CommentRepository;
import by.news.service.ArticleService;
import by.news.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ArticleService articleService;

    public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setUsername(commentDto.getUsername());
        comment.setText(commentDto.getText());
        comment.setArticle(articleService.getArticleById(commentDto.getArticleId()));

        commentRepository.save(comment);
    }
}
