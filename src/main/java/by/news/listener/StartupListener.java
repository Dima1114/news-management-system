package by.news.listener;

import by.news.entity.Article;
import by.news.entity.Comment;
import by.news.repository.ArticleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {


    private ArticleRepository articleRepository;

    public StartupListener(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (articleRepository.findAll().isEmpty()) {

            final String COMMENT = "Aliquam vestibulum morbi blandit cursus risus at ultrices mi " +
                    "tempus imperdiet nulla malesuada pellentesque elit eget gravida cum sociis natoque " +
                    "penatibus et magnis dis parturient";

            final String ARTICLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                    "incididunt ut labore et dolore magna aliqua. Egestas dui id ornare arcu. Enim sit amet venenatis urna. " +
                    "Leo integer malesuada nunc vel risus commodo viverra maecenas. Justo donec enim diam vulputate" +
                    " ut pharetra. At augue eget arcu dictum varius. Fringilla est ullamcorper eget nulla facilisi etiam. " +
                    "Lorem sed risus ultricies tristique nulla aliquet enim. Magna eget est lorem ipsum. Massa enim nec dui " +
                    "nunc. Sed lectus vestibulum mattis ullamcorper velit. Ullamcorper a lacus vestibulum sed arcu non odio " +
                    "euismod lacinia. Ut faucibus pulvinar elementum integer enim. Quis risus sed vulputate odio" +
                    " ut enim blandit.\n" +
                    "\n" +
                    "Cursus sit amet dictum sit amet justo donec enim. Faucibus ornare suspendisse sed nisi lacus. " +
                    "Nunc sed augue lacus viverra vitae. Turpis massa tincidunt dui ut ornare lectus sit amet est. " +
                    "Nullam vehicula ipsum a arcu cursus. Sit amet tellus cras adipiscing enim. Blandit cursus risus at " +
                    "ultrices mi tempus. Risus nec feugiat in fermentum posuere urna. Vulputate enim nulla aliquet porttitor" +
                    " lacus luctus accumsan tortor. Imperdiet nulla malesuada pellentesque elit eget gravida cum. " +
                    "Condimentum id venenatis a condimentum. Et leo duis ut diam. Leo in vitae turpis massa sed elementum" +
                    " tempus egestas sed. Ac tortor vitae purus faucibus ornare suspendisse sed nisi lacus.\n" +
                    "\n" +
                    "Magna ac placerat vestibulum lectus mauris ultrices eros in cursus. Mi quis hendrerit dolor magna eget" +
                    " est lorem. Donec ultrices tincidunt arcu non sodales neque sodales ut. Pulvinar sapien et ligula " +
                    "ullamcorper. Vitae tortor condimentum lacinia quis vel eros donec. Viverra nibh cras pulvinar mattis" +
                    " nunc sed. Rhoncus dolor purus non enim. Posuere urna nec tincidunt praesent. Cursus mattis molestie" +
                    " a iaculis. Semper auctor neque vitae tempus. Ornare arcu odio ut sem nulla pharetra diam.";

            for (int i = 1; i < 22; i++) {

                Article article = new Article();
                article.setTitle("Title " + i);
                article.setText(ARTICLE);
                article.setDate(LocalDate.of(2018, 1, 1 + i / 2));

                List<Comment> comments = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    Comment comment = new Comment();
                    comment.setText(COMMENT);
                    comment.setUsername("user " + (int) (Math.random() * 100));
                    comment.setDate(LocalDate.of(2018, 1, 1 + j));
                    comment.setArticle(article);
                    comments.add(comment);
                }

                article.setComments(comments);
                articleRepository.save(article);
            }
        }

    }
}
