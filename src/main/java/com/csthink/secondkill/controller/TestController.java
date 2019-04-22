package com.csthink.secondkill.controller;

import com.csthink.secondkill.global.CodeMsg;
import com.csthink.secondkill.global.Result;
import com.csthink.secondkill.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "hello " + name;
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public String hello2(int id, String name) {
        return "id: " + id + ", name: " + name;
    }

    @GetMapping("/log")
    public void log() {
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
    }

    @RequestMapping(value = "/getUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public User getUser() {
        User u = new User();
        u.setUid(100);
        u.setName("jack");
        return u;
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