package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 向域对象共享数据：
 * 1.通过ModelAndView向请求域共享数据
 * 使用ModelAndView时，可以使用其Model功能向请求域共享数据
 * 使用View功能设置逻辑视图，但是控制器方法一定要将ModelANdView作为方法的返回值
 * 2.使用Model向请求域共享数据
 * 3.使用ModelMap向请求域共享数据
 * 4.使用map向请求域共享数据
 * 5.Model和ModelMap和Map的关系
 * 其实在底层中，这些类型的形参最终都是通过org.springframework.validation.support.BindingAwareModelMap创建
 * 四选一就行，控制器方法统一的返回值为ModelAndView
 */
@Controller
public class TestScopeController {
    @RequestMapping("/test/mav")
    public ModelAndView testMAV(){
        /**
         * ModelAndView包含Model和View的功能
         * Model:向请求域中共享数据
         * View:设置逻辑视图实现页面跳转
         */
        ModelAndView modelAndView = new ModelAndView();
        //向请求域中共享数据
        modelAndView.addObject("testRequestScope","hello,ModelAndView");
        //设置逻辑视图
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/test/model")
    public String testModel(Model model){
        //org.springframework.validation.support.BindingAwareModelMap
        System.out.println(model.getClass().getName());
        model.addAttribute("testRequestScope","hello,Model");
        return "success";
    }

    @RequestMapping("/test/modelMap")
    public String testModelMap(ModelMap modelMap){
        //org.springframework.validation.support.BindingAwareModelMap
        System.out.println(modelMap.getClass().getName());
        modelMap.addAttribute("testRequestScope","hello,ModelMap");
        return "success";
    }

    @RequestMapping("/test/map")
    public String testMap(Map<String,Object> map){
        //org.springframework.validation.support.BindingAwareModelMap
        System.out.println(map.getClass().getName());
        map.put("testRequestScope","hello,map");
        return "success";
    }

    @RequestMapping("/test/session")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope","hello,session");
        return "success";
    }

    @RequestMapping("/test/application")
    public String testApplication(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("testApplicationScope","hello,application");
        return "success";
    }
}
