package org.sang.service;

import org.apache.ibatis.annotations.Param;
import org.sang.dao.ArticleDao;
import org.sang.entity.Article;
import org.sang.entity.Category;
import org.sang.entity.User;
import org.sang.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sang on 17-3-10.
 */
@Service
public class ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Resource
    private UserService userService;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Article getArticleById(Long id) {
        Article article = articleDao.getArticleById(id);
        article.setCategory(articleDao.getCategoryById(article.getCategoryId()).getDisplayName());
        return article;
    }

    public List<Article> searchByKeyWords(@Param("keyWord") String keyWord) {
        return articleDao.searchByKeyWords(keyWord);
    }

    public List<Article> getArticles(User user) {
        return articleDao.getArticles(user);
    }

    public List<Category> getCategories() {
        return articleDao.getCategories();
    }

    public void writeBlog(Article article) {
        Long categoryId = articleDao.getCategoryIdByName(article.getCategory());
        article.setCategoryId(categoryId);
        article.setDate(sdf.format(new Date()));
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            String summary = StringUtil.deleteAllHTMLTag(article.getContent());
            if (article.getContent().length() > 40) {
                article.setSummary(summary.substring(0, 40) + "...");
            } else {
                article.setSummary(summary);
            }
        }

        //获取作者名
        User currentUser = userService.getCurrentUser();
        article.setAuthor(currentUser == null ? null : currentUser.getNickname());

        articleDao.writeBlog(article);
    }

    // 执行软删除
    public void deleteArticleById(Long id) {
        articleDao.deleteArticleById(id);
    }

    public void updateBlog(Article article) {
        article.setDate(sdf.format(new Date()));
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            String summary = StringUtil.deleteAllHTMLTag(article.getContent());
            if (article.getContent().length() > 40) {
                article.setSummary(summary.substring(0, 40) + "...");
            } else {
                article.setSummary(summary);
            }
        }
        articleDao.updateArticleById(article);
    }

    public List<Article> getArticlesByCategoryName(String name) {
        Long categoryId = articleDao.getCategoryIdByName(name);
        List<Article> articles = articleDao.getArticlesByCategoryName(categoryId);
        return articles;
    }
}
