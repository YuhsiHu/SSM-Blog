package com.ssm.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.blog.entity.*;
import com.ssm.blog.enums.ArticleCommentStatus;
import com.ssm.blog.service.ArticleService;
import com.ssm.blog.entity.*;
import com.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.ssm.blog.mapper.ArticleMapper;
import com.ssm.blog.mapper.ArticleTagRefMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 文章Servie实现
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired(required = false)
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public Integer countArticle(Integer status) {
        //获取文章总数
        Integer count = 0;
        try {
            count = articleMapper.countArticle(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据状态统计文章数, status:{}, cause:{}", status, e);
        }
        return count;
    }

    @Override
    public Integer countArticleComment() {
        //获取评论总数
        Integer count = 0;
        try {
            count = articleMapper.countArticleComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章评论数失败, cause:{}", e);
        }
        return count;
    }


    @Override
    public Integer countArticleView() {
        //获取浏览总数
        Integer count = 0;
        try {
            count = articleMapper.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章访问量失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        //统计这个分类下文章数
        Integer count = 0;
        try {
            count = articleCategoryRefMapper.countArticleByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类统计文章数量失败, categoryId:{}, cause:{}", categoryId, e);
        }
        return count;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        //统计这个标签下文章数
        Integer count=0;
        try{
            count=articleTagRefMapper.countArticleByTagId(tagId);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("根据标签统计文章数量失败, categoryId:{}, cause:{}", tagId, e);
        }
        return count;

    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        //获取所有文章不分页
        return articleMapper.findAll(criteria);
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        //获取近期文章
        return articleMapper.listArticleByLimit(limit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        //修改文章详细信息
        article.setArticleUpdateTime(new Date());
        articleMapper.update(article);

        if (article.getTagList() != null) {
            //删除标签和文章关联
            articleTagRefMapper.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for (int i = 0; i < article.getTagList().size(); i++) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
                articleTagRefMapper.insert(articleTagRef);
            }
        }

        if (article.getCategoryList() != null) {
            //添加分类和文章关联
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
            //删除分类和文章关联
            for (int i = 0; i < article.getCategoryList().size(); i++) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefMapper.insert(articleCategoryRef);
            }
        }
    }

    @Override
    public void updateArticle(Article article) {
        //修改文章简单信息
        articleMapper.update(article);
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        //批量删除文章
        articleMapper.deleteBatch(ids);
    }

    @Override
    public void deleteArticle(Integer id) {
        //删除文章
        articleMapper.deleteById(id);
    }


    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex,
                                         Integer pageSize,
                                         HashMap<String, Object> criteria) {
        //分页显示
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.findAll(criteria);
        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0) {
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);
//            //封装TagList
//            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(articleList.get(i).getArticleId());
//            articleList.get(i).setTagList(tagList);
        }
        System.out.println(articleList.toString());
        return new PageInfo<>(articleList);
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        //文章详情页面显示
        Article article = articleMapper.getArticleByStatusAndId(status, id);
        if (article != null) {
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        }
        return article;
    }


    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        //获取访问量较多的文章
        return articleMapper.listArticleByViewCount(limit);
    }

    @Override
    public Article getAfterArticle(Integer id) {
        //获取上一篇文章
        return articleMapper.getAfterArticle(id);
    }

    @Override
    public Article getPreArticle(Integer id) {
        //获取下一篇文章
        return articleMapper.getPreArticle(id);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        //获取随机文章
        return articleMapper.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleMapper.listArticleByCommentCount(limit);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        //添加文章
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        articleMapper.insert(article);
        //添加分类和文章关联
        for (int i = 0; i < article.getCategoryList().size(); i++) {
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }
        //添加标签和文章关联
        for (int i = 0; i < article.getTagList().size(); i++) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }
    }


    @Override
    public void updateCommentCount(Integer articleId) {
        //更新文章评论数
        articleMapper.updateCommentCount(articleId);
    }

    @Override
    public Article getLastUpdateArticle() {
        //获得最后更新记录
        return articleMapper.getLastUpdateArticle();
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        //获得相关文章
        return articleMapper.findArticleByCategoryId(cateId, limit);
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        //获得相关文章
        if (cateIds == null || cateIds.size() == 0) {
            return null;
        }
        return articleMapper.findArticleByCategoryIds(cateIds, limit);
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        //根据文章ID获得分类ID列表
        return articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        //获得所有文章
        return articleMapper.listAllNotWithContent();
    }

    @Override
    public void insertDraft(Article draft) {
        //添加草稿
        draft.setArticleCreateTime(new Date());
        draft.setArticleUpdateTime(new Date());
        draft.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        draft.setArticleViewCount(0);
        draft.setArticleLikeCount(0);
        draft.setArticleCommentCount(0);
        draft.setArticleOrder(1);
        draft.setArticleStatus(0);
        articleMapper.insertDraft(draft);
    }

    @Override
    public PageInfo<Article> searchArticleByContent(Integer pageIndex,
                                                    Integer pageSize,
                                                    HashMap<String, Object> criteria, String way, String cons) {
        //通过文章内容模糊查询
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.searchArticleByContent(criteria, way, cons);

        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0) {
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);
        }
        return new PageInfo<>(articleList);
    }

    @Override
    public PageInfo<Article> searchArticleByTitle(Integer pageIndex,
                                                  Integer pageSize,
                                                  HashMap<String, Object> criteria, String way, String cons) {
        //通过文章标题模糊查询
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.searchArticleByTitle(criteria, way, cons);

        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0) {
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);
        }
        return new PageInfo<>(articleList);
    }

}
