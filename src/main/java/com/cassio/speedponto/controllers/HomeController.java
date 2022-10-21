package com.cassio.speedponto.controllers;

import com.cassio.speedponto.model.Funcionario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        mv.addObject("funcionario", new Funcionario());
        return mv;
    }

}
