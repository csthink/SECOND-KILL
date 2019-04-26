package com.csthink.secondkill.controller;

import com.csthink.secondkill.entity.User;
import com.csthink.secondkill.global.CodeMsg;
import com.csthink.secondkill.global.Result;
import com.csthink.secondkill.service.UserService;
import com.csthink.secondkill.utils.MD5Utils;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// 相当于 @Controller + @ResponseBody
//@RestController
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Value(value = "${test.name}")
    private String name;

    @Value("${test.description}")
    private String description;

    @Value("${test.productNum}")
    private Integer productNum;

    @Value("${test.showAd}")
    private Boolean showAd;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        //return "hello world!";
        return String.format("name:%s,description:%s,productNum:%s,showAdvert%s", name, description,
                productNum, showAd);
    }

    @RequestMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable String name) {
        return "hello " + name + ", welcome21";
    }

    @RequestMapping("/hello2")
    @ResponseBody
    // /test/hello2?id=1&name=jack
    public String hello2(int id, String name) {
        return "id: " + id + ", name: " + name;
    }

    @GetMapping("/log")
    @ResponseBody
    public void log() {
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
    }


    @RequestMapping(value = "/getUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result<User> getUser() {
        User user = userService.find(1);
        System.out.println(user);
        return Result.success(user);
    }

    @RequestMapping(value = "/getUsers", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result<List<User>> getUsers() {
        List<User> list = userService.findAll();
        System.out.println(list);
        return Result.success(list);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Result<String> addUser() {
        User user = new User();
        String salt = "geagexege";

        user.setUsername("lucy");
        user.setPassword(MD5Utils.rawPassToDbPass("123456", salt));
        user.setSalt(salt);
        user.setRealName("小莉2");
        user.setPhone("15056992628");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setLastLogin(new Date());
        userService.addUser(user);

        return Result.success(user.getId().toString());
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "jack");
        return "test";
    }

}
