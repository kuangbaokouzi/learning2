package com.entor.hrm.controller;

import com.entor.hrm.domain.User;
import com.entor.hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.entor.hrm.uitl.common.HrmConstants.USER_SESSION;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("loginName") String loginName,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv) {
        User user = userService.login(loginName, password);
        if (user != null) {
            session.setAttribute(USER_SESSION, user);
            mv.setViewName("redirect:/main");
        } else {
            mv.addObject("message", "loginName or password was invalid");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }
}
