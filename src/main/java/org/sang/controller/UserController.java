package org.sang.controller;

import org.sang.entity.User;
import org.sang.service.ArticleService;
import org.sang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther:郑龙
 * @Date:2018-08-01 16:10
 * @Description:和用户登录相关的Controller
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;

    @RequestMapping("/manager")
    public String admin(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("articles", articleService.getArticles(user));
        return "admin/index";
    }

    @RequestMapping("/manager/login")
    public String login() {
        return "admin/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        long rand = System.currentTimeMillis();
        model.addAttribute("userId", rand);
        return "admin/register";
    }

    @RequestMapping("/doRegister")
    public String doRegister(HttpServletRequest request, User user, Model model) {
        System.out.println("user.getUsername():" + user.getUsername() + ";user.getPassword():" + user.getPassword());
        long rand = System.currentTimeMillis();
        switch (userService.register(user.getUsername(), user.getPassword(), user.getNickname())) {
            case -1:
                model.addAttribute("error", "发生未知错误,请稍后重试");
                model.addAttribute("userId", rand);
                return "admin/register";
            case 0:
                model.addAttribute("error", "用户名或昵称已被注册！请重新输入新的昵称!");
                model.addAttribute("userId", rand);
                return "admin/register";
            case 1:
                break;
        }

        request.getSession().setAttribute("user", user);
        model.addAttribute("user", user);
        return "redirect:/manager";
    }

    @RequestMapping(value = "/manager/dologin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, User user, Model model) {
        System.out.println("user.getUsername():" + user.getUsername() + ";user.getPassword():" + user.getPassword());
        if (userService.login(user.getUsername(), user.getPassword())) {
            request.getSession().setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/manager";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "admin/login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/manager/dologin")
    public String doLogin(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            userService.setCurrentUser(user);
            return "admin/login";
        }
        return "redirect:/manager";
    }
}
