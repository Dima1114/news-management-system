package by.news.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "articles")
public class Article extends IdKeeper{

    private String title;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDate date = LocalDate.now();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("id DESC")
    private List<Comment> comments;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
