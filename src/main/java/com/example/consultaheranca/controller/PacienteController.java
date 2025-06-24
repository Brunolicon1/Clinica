package com.example.consultaheranca.controller;
import com.example.consultaheranca.model.entity.Paciente;
import com.example.consultaheranca.model.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
//Se acontecer alguma exceção do tipo RuntimeException ou Error, ela faz o rollback, desfazendo tudo o que foi feito até aquele ponto.
@Controller
@RequestMapping("/paciente")
public class PacienteController {


    @Autowired
    PacienteRepository repository;

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("pacientes", repository.pacientes());
        return new ModelAndView("/paciente/list", model);
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("termo") String termo, ModelMap model) {
        model.addAttribute("pacientes", repository.searchAction(termo));
        return new ModelAndView("/paciente/list", model);
    }


    @GetMapping("/form")
    public ModelAndView form(Paciente paciente) {
        return new ModelAndView("/paciente/form");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.paciente(id));
        return new ModelAndView("paciente/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Paciente paciente, BindingResult result) {
        if(result.hasErrors()) {
            return form(paciente);
        }
        repository.update(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Paciente paciente, BindingResult result) {
        if(result.hasErrors()) {
            return form(paciente);
        }

        repository.save(paciente);
        return new ModelAndView("redirect:/paciente/list");
    }
}