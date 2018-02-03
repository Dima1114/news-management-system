package by.news.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class CommentDto {

    private Long articleId;

    @Size(min = 3, max = 30)
    private String username;

    @NotEmpty
    private String text;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

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
}
