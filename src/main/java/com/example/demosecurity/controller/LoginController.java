package com.example.demosecurity.controller;

import com.example.demosecurity.domain.Msg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
@Controller
public class LoginController {

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(path = "/sample/sample-crud")
    public String sampleCurd() {
        return "sample-crud";
    }

    @RequestMapping(path = {"home", "home.html"}, method = RequestMethod.GET)
    public String aaindex(Model model){
        Msg msg =  new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping(path = {"admin"}, method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public String getList(){
        return "hello getList forGetRequest";
    }


    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public String save(){
        return "hello save forPostRequest";
    }


    @RequestMapping(value = "user", method = RequestMethod.PUT)
    @ResponseBody
    public String update(){
        return "hello update forPutRequest";
    }


}
