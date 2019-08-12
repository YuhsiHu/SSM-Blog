package com.ssm.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.ssm.blog.dto.ArticleParam;
import com.ssm.blog.entity.Article;
import com.ssm.blog.service.ArticleService;
import com.ssm.blog.service.CategoryService;
import com.ssm.blog.service.TagService;

import com.ssm.blog.entity.Category;
import com.ssm.blog.entity.Tag;
import com.ssm.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 后台文章controller
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;//文章服务

    @Autowired
    private TagService tagService;//标签服务

    @Autowired
    private CategoryService categoryService;//分类服务

    /**
     * 后台index文章列表显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status, Model model) {
        //查询条件
        HashMap<String, Object> criteria = new HashMap<>(1);
        //分页
        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }

        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "Admin/Article/index";
    }


    /**
     * 后台添加文章页面显示
     *
     * @return
     */
    @RequestMapping(value = "/insert")
    public String insertArticleView(Model model) {
        //获取分类列表
        List<Category> categoryList = categoryService.listCategory();
        //获取标签列表
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        return "Admin/Article/insert";
    }

    /**
     * 后台添加文章提交操作
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        //获得用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        //设置文章标题
        article.setArticleTitle(articleParam.getArticleTitle());
        //设置文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //设置文章内容
        article.setArticleContent(articleParam.getArticleContent());
        //设置文章状态
        article.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }


    /**
     * 后台删除文章
     *
     * @param id 文章ID
     */
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
    }


    /**
     * 后台编辑文章页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        //获取文章
        Article article = articleService.getArticleByStatusAndId(null, id);
        modelAndView.addObject("article", article);
        //获取分类列表
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);
        //获取标签列表
        List<Tag> tagList = tagService.listTag();

        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }


    /**
     * 后台编辑文章提交
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam) {
        Article article = new Article();
        //设置文章ID
        article.setArticleId(articleParam.getArticleId());
        //设置文章标题
        article.setArticleTitle(articleParam.getArticleTitle());
        //设置文章内容
        article.setArticleContent(articleParam.getArticleContent());
        //设置文章状态
        article.setArticleStatus(articleParam.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.updateArticleDetail(article);
        return "redirect:/admin/article";
    }

    /**
     * 后台添加文章草稿
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/insertDraft", method = RequestMethod.POST)
    public String insertDraftSubmit(HttpSession session, ArticleParam articleParam) {
        Article draft = new Article();
        //获得用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            draft.setArticleUserId(user.getUserId());
        }
        //设置草稿标题
        draft.setArticleTitle(articleParam.getArticleTitle());
        //设置草稿内容
        draft.setArticleContent(articleParam.getArticleContent());

        articleService.insertDraft(draft);
        return "redirect:/admin/article";
    }

    /**
     * 后台文章列表搜索
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/searchArticle")
    public String searchArticle(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) String status,
                                @RequestParam("way") String way,
                                @RequestParam("cons") String cons,
                                Model model) {
        //查询条件
        HashMap<String, Object> criteria = new HashMap<>(1);
        //分页
        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article/search?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article/search?status=" + status + "&pageIndex");
        }
        //判断根据content还是title查
        if (way.equals("article_content")) {
            PageInfo<Article> articlePageInfo = articleService.searchArticleByContent(pageIndex, pageSize, criteria, way, cons);
            model.addAttribute("pageInfo", articlePageInfo);
        } else if (way.equals("article_title")) {
            PageInfo<Article> articlePageInfo = articleService.searchArticleByTitle(pageIndex, pageSize, criteria, way, cons);
            model.addAttribute("pageInfo", articlePageInfo);
        }

        return "Admin/Article/search";
    }

    /**
     * 后台搜索文章页面首次文章列表显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/search")
    public String search(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                         @RequestParam(required = false) String status, Model model) {
        //查询条件
        HashMap<String, Object> criteria = new HashMap<>(1);
        //分页
        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }

        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);

        model.addAttribute("pageInfo", articlePageInfo);
        return "Admin/Article/search";
    }
}



