package com.ssm.blog.controller.admin;


import com.ssm.blog.entity.Notice;
import com.ssm.blog.enums.NoticeStatus;
import com.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * 后台公告controller
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private NoticeService noticeService;//公告服务

    /**
     * 后台公告列表显示
     *
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        //获得公告列表
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";

    }

    /**
     * 后台添加公告显示
     *
     * @return
     */
    @RequestMapping(value = "/insert")
    public String insertNoticeView() {
        return "Admin/Notice/insert";
    }

    /**
     * 后台添加公告提交
     *
     * @param notice
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice";
    }

    /**
     * 后台删除公告
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);

        return "redirect:/admin/notice";
    }

    /**
     * 后台编辑公告页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editNoticeView(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "Admin/Notice/edit";
    }


    /**
     * 后台编辑公告页面显示
     *
     * @param notice
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }


}
