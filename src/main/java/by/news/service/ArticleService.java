package by.news.service;

import by.news.dto.ArticleDto;
import by.news.entity.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {

    Page<Article> listArticles(int page);

    Article getArticleById(Long articleId);
    Article getArticleByIdWithComments(Long articleId);

    Long updateArticle(ArticleDto articleDto);
    void deleteArticle(Long articleId);
}
