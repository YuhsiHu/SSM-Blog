package com.ssm.blog.controller.admin;


import com.ssm.blog.entity.Link;
import com.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.json.JSONObject;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * 后台链接controller
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;//链接服务

    /**
     * 后台链接列表显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public ModelAndView linkList()  {
        ModelAndView modelandview = new ModelAndView();
        //获得链接列表
        List<Link> linkList = linkService.listLink(null);
        modelandview.addObject("linkList",linkList);

        modelandview.setViewName("Admin/Link/index");
        return modelandview;

    }

    /**
     * 后台添加链接页面显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertLinkView()  {
        ModelAndView modelAndView = new ModelAndView();
        //获得链接列表
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }

    /**
     * 后台添加链接页面提交
     *
     * @param link 链接
     * @return 响应
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    @ResponseBody
    public String insertLinkSubmit(Link link)  {
        Map<String, Object> map = new HashMap<String, Object>();
        Link i=linkService.getLinkByLinkName(link.getLinkName());
        if(i!=null){
            map.put("code",0);
            map.put("msg","链接名已存在");
        }
        else {
            map.put("code",1);
            map.put("msg","");
            link.setLinkCreateTime(new Date());
            link.setLinkUpdateTime(new Date());
            link.setLinkStatus(1);
            linkService.insertLink(link);
            //  return "redirect:/admin/link/insert";
        }
        String result = new JSONObject(map).toString();
        return  result;
    }

    /**
     * 后台删除链接
     *
     * @param id 链接ID
     * @return 响应
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id)  {

        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

    /**
     * 后台编辑链接页面显示
     *
     * @param id
     * @return modelAndVIew
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();
        //获得编辑的链接
        Link linkCustom =  linkService.getLinkById(id);
        modelAndView.addObject("linkCustom",linkCustom);
        //获得链接列表
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }


    /**
     * 后台编辑链接提交
     *
     * @param link 链接
     * @return 响应
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editLinkSubmit(Link link)  {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }
}
