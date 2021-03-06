package com.ssm.blog.controller.admin;


import com.ssm.blog.entity.User;
import com.ssm.blog.service.UserService;
import com.ssm.blog.util.MyUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 *用户管理
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;

    /**
     * 编辑用户页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        User user =  userService.getUserById(id);
        modelAndView.addObject("user",user);

        modelAndView.setViewName("Admin/User/edit");
        return modelAndView;
    }

    /**
     * 编辑密码页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/editPwd/{id}")
    public ModelAndView editPwdView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        User user =  userService.getUserById(id);
        modelAndView.addObject("user",user);

        modelAndView.setViewName("Admin/User/editPwd");
        return modelAndView;
    }

    /**
     * 编辑密码提交
     *
     * @param user
     * @return
     */

    @RequestMapping(value = "/editPwdSubmit",method = RequestMethod.POST)
    public String editPwdSubmit(User user)  {
        String md5Pwd= MyUtils.strToMd5(user.getUserPass());
        user.setUserPass(md5Pwd);
        userService.updateUserPwd(user);
        return "redirect:/admin";
    }

    /**
     * 编辑用户提交
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user)  {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    /**
     * 基本信息页面显示
     *
     * @return
     */
    @RequestMapping(value = "/profile")
    public ModelAndView userProfileView(HttpSession session)  {

        ModelAndView modelAndView = new ModelAndView();
        User sessionUser = (User) session.getAttribute("user");
        User user =  userService.getUserById(sessionUser.getUserId());
        modelAndView.addObject("user",user);

        modelAndView.setViewName("Admin/User/profile");
        return modelAndView;
    }
}
