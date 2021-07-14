package com.gjw.controller;

import com.gjw.Utils.sendEmailUtil.SendEmail;
import com.gjw.bean.User;
import com.gjw.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private SendEmail sendEmail;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("msg", "hello");
        return "index";
    }

    @RequestMapping("/user/toAdd")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("/user/add")
    public String add(String addAcc, String addPwd) {
        String hashAlgorithmName = "MD5"; //加密方式
        int hashIterations = 2; //加密的次数
        //盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println(salt);
        //加密
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, addPwd, salt, hashIterations);
        String encryptionPassword = simpleHash.toString();
        userService.insertUser(new User(null, addAcc, encryptionPassword, salt, "add"));
        return "index";
    }

    @RequestMapping("/user/toUpdate")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String acc, String pwd, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(acc, pwd);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "账号错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

//    @RequestMapping("/logout")
//    public String logout() {
//        SecurityUtils.getSubject().logout();
//        return "index";
//    }

    @RequestMapping("/unAuthorized")
    @ResponseBody
    public String unAuthorized() {
        return "未授权的请求";
    }

    @ApiOperation("usersApiOperation")
    @GetMapping("/users")
    @ResponseBody
    public User users() {
        String title = "111hello!!";
        String text = "<h1 style='color:red'>xixi111haha!!!</h1>";
        sendEmail.send(false,true, title, text, "1258046117@qq.com", "1258046117@qq.com");
        return new User(1, "2", "3", "4", "5");
    }

}
