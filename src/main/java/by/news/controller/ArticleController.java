package by.news.controller;

import by.news.dto.ArticleDto;
import by.news.entity.Article;
import by.news.service.ArticleService;
import by.news.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Qualifier("ArticleServiceImpl")
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/{articleId}")
    public String showArticle(Model model, @PathVariable Long articleId){
        model.addAttribute("article",articleService.getArticleByIdWithComments(articleId));
        return "pages/article-page";
    }

    @GetMapping("/add")
    public String addArticle(Model model){
        model.addAttribute("articleForm", new ArticleDto());
        return "pages/add-edit-page";
    }

    @GetMapping("/edit/{articleId}")
    public String editArticle(Model model, @PathVariable Long articleId){
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("articleForm", new ArticleDto(article));
        return "pages/add-edit-page";
    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleDto articleDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.ok(errors);
        }
        return ResponseEntity.ok(articleService.updateArticle(articleDto));
    }

    @PostMapping(path = "/delete/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }








}
