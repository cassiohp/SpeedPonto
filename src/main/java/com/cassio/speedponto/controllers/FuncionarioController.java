package com.cassio.speedponto.controllers;

import com.cassio.speedponto.dao.FuncionarioDao;
import com.cassio.speedponto.model.Funcionario;
import com.cassio.speedponto.service.ServiceFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioDao funcionarioRepositorio;
    @Autowired
    private ServiceFuncionario serviceFuncionario;

    @GetMapping("/inserir-funcionario")
    public ModelAndView FormFuncionario(Funcionario funcionario){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("funcionario/formFuncionario");
        mv.addObject("funcionario", new Funcionario());

        return mv;
    }

    @PostMapping("InsertFuncionario")
    public ModelAndView InserirFuncionario(@Valid Funcionario funcionario, BindingResult br){
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("funcionario/formFuncionario");
            mv.addObject("funcionario");
        }else{
            mv.setViewName("redirect:/lista-funcionarios");
            funcionarioRepositorio.save(funcionario);
        }
        return mv;
    }

    @GetMapping("/lista-funcionarios")
    public ModelAndView ListagemFuncionarios(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("funcionario/listFuncionarios");
        mv.addObject("funcionariosList", funcionarioRepositorio.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("funcionario/alterar");
        Funcionario funcionario = funcionarioRepositorio.getOne(id);
        mv.addObject("funcionario", funcionario);
        return mv;

    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Funcionario funcionario){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/lista-funcionarios");
        funcionarioRepositorio.save(funcionario);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirFuncionario(@PathVariable("id") Integer id){
        funcionarioRepositorio.deleteById(id);
        return "redirect:/lista-funcionarios";
    }

    /*Login*/

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("funcionario", new Funcionario());
        mv.setViewName("login/cadastro");
        return mv;
    }

    @PostMapping("salvarFuncionario")
    public ModelAndView cadastrar(Funcionario funcionario) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceFuncionario.salvarFuncionario(funcionario);
        mv.setViewName("redirect:/");
        return mv;
}

}
