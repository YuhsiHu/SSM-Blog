package com.ssm.blog.controller.admin;


import com.ssm.blog.entity.Tag;
import com.ssm.blog.service.ArticleService;
import com.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * 后台标签controller
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {

    @Autowired
    private ArticleService articleService;//文章服务

    @Autowired
    private TagService tagService;//标签服务

    /**
     * 后台标签列表显示
     * @return
     */
    @RequestMapping(value = "")
    public ModelAndView index()  {
        ModelAndView modelandview = new ModelAndView();
        //获得带有文章数量的标签列表
        List<Tag> tagList = tagService.listTagWithCount();
        modelandview.addObject("tagList",tagList);

        modelandview.setViewName("Admin/Tag/index");
        return modelandview;

    }


    /**
     * 后台添加分类页面显示
     *
     * @param tag
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag)  {
        tagService.insertTag(tag);
        return "redirect:/admin/tag";
    }

    /**
     * 后台删除标签
     *
     * @param id 标签ID
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id)  {
        //获得该标签下文章数量
        Integer count = articleService.countArticleByTagId(id);
        //标签下没有文章才可以删除
        if (count == 0) {
            tagService.deleteTag(id);
        }
        return "redirect:/admin/tag";
    }

    /**
     * 后台编辑标签页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();
        //获得编辑的标签
        Tag tag =  tagService.getTagById(id);
        modelAndView.addObject("tag",tag);
        //获得标签下的文章数量
        List<Tag> tagList = tagService.listTagWithCount();
        modelAndView.addObject("tagList",tagList);

        modelAndView.setViewName("Admin/Tag/edit");
        return modelAndView;
    }


    /**
     * 后台编辑标签提交
     *
     * @param tag
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag)  {
        tagService.updateTag(tag);
        return "redirect:/admin/tag";
    }
}
