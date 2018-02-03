package by.news.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment extends IdKeeper{

    private String username;
    private String text;
    private LocalDate date = LocalDate.now();

    @ManyToOne
    private Article article;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
