package com.example.consultaheranca.controller;

import com.example.consultaheranca.model.entity.Medico;
import com.example.consultaheranca.model.entity.Paciente;
import com.example.consultaheranca.model.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("medicos", repository.medicos());
        return new ModelAndView("/medico/list", model);
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("termo") String termo, ModelMap model) {
        model.addAttribute("medicos", repository.searchAction(termo));
        return new ModelAndView("/medico/list", model);
    }

    @GetMapping("/form")
    public ModelAndView form(Medico medico) {
        return new ModelAndView("/medico/form");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/medico/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("medico/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Medico medico, BindingResult result) {
        if(result.hasErrors()) {
            return form(medico);
        }
        repository.update(medico);
        return new ModelAndView("redirect:/medico/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Medico medico, BindingResult result) {
        if(result.hasErrors()) {
            return form(medico);
        }
        repository.update(medico);
        return new ModelAndView("redirect:/medico/list");
    }
}