package by.news.controller;

import by.news.entity.Article;
import by.news.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/")
    public String home(Model model, @RequestParam(name = "page", defaultValue = "1") int page){
        Page<Article> articlePage = articleService.listArticles(page);
        model.addAttribute("articles", articlePage.getContent() );
        model.addAttribute("totalPages", articlePage.getTotalPages());
        model.addAttribute("page", page);
        return "pages/news-page";
    }
}
