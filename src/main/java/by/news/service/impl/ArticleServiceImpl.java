package by.news.service.impl;

import by.news.dto.ArticleDto;
import by.news.entity.Article;
import by.news.repository.ArticleRepository;
import by.news.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ArticleServiceImpl")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Page<Article> listArticles(int page) {
        Pageable pageable = new PageRequest(page -1, 5,
                new Sort(Sort.Direction.DESC, "date" , "id"));
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article getArticleById(Long articleId) {
        return articleRepository.findOne(articleId);
    }

    //use it to fetch comments, because in view transaction is closed and comments are not available
    @Override
    public Article getArticleByIdWithComments(Long articleId) {
        Article article = articleRepository.findOne(articleId);
        int i = article.getComments().size();
        return article;
    }

    @Override
    public Long updateArticle(ArticleDto articleDto) {

        Article article = new Article();

        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setDate(articleDto.getDate().toLocalDate());

        article = articleRepository.save(article);
        return article.getId();
    }

    @Override
    public void deleteArticle(Long articleId) {
        articleRepository.delete(articleId);
    }
}
