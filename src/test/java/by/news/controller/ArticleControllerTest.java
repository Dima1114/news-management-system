package by.news.controller;

import by.news.config.TestConfig;
import by.news.config.WebConfig;
import by.news.dto.ArticleDto;
import by.news.entity.Article;
import by.news.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class ArticleControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleService articleService;

    @Before
    public void setUp() {
        //init mock annotations
        MockitoAnnotations.initMocks(this);
        //build mock mvc
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void shouldReturnArticleAndView() throws Exception {
        Article article = new Article();
        when(articleService.getArticleByIdWithComments(1L)).thenReturn(article);
        mockMvc.perform(get("/article/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/article-page"))
                .andExpect(model().attribute("article", article));

        verify(articleService, times(1)).getArticleByIdWithComments(1L);
    }

    @Test
    public void shouldReturnArticleFormAndView() throws Exception {
        mockMvc.perform(get("/article/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/add-edit-page"))
                .andExpect(model().attributeExists("articleForm"));
    }

    @Test
    public void shouldReturnPopulatedArticleFormAndView() throws Exception {
        when(articleService.getArticleById(1L)).thenReturn(new Article());

        mockMvc.perform(get("/article/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/add-edit-page"))
                .andExpect(model().attributeExists("articleForm"));

        verify(articleService, times(1)).getArticleById(1L);
    }
}
