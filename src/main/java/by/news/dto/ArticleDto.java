package by.news.dto;

import by.news.entity.Article;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

public class ArticleDto {

    private Long id;

    @Size(min = 4, max = 255)
    private String title;

    @Size(min = 10)
    private String text;

    @Past
    @NotNull
    private Date date;

    public ArticleDto(){}

    public ArticleDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.text = article.getText();
        this.date = Date.valueOf(article.getDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
