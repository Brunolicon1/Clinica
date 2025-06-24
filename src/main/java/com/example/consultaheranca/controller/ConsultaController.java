package com.example.consultaheranca.controller;
import com.example.consultaheranca.model.entity.Consulta;
import com.example.consultaheranca.model.entity.Medico;
import com.example.consultaheranca.model.repository.ConsultaRepository;
import com.example.consultaheranca.model.repository.MedicoRepository;
import com.example.consultaheranca.model.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaRepository repository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    MedicoRepository medicoRepository;


    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("consultas", repository.consultas());
        return new ModelAndView("/consulta/list", model);
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("termo") String termo, ModelMap model) {
        model.addAttribute("consultas", repository.searchAction(termo));
        return new ModelAndView("/consulta/list", model);
    }

    @GetMapping("/form")
    public ModelAndView form(Consulta consulta, ModelMap model) {
        model.addAttribute("pacientes", pacienteRepository.pacientes());
        model.addAttribute("medicos", medicoRepository.medicos());
        return new ModelAndView("consulta/form", model);
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", repository.consulta(id));
        model.addAttribute("pacientes", pacienteRepository.pacientes());
        model.addAttribute("medicos", medicoRepository.medicos());
        return new ModelAndView("consulta/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Consulta consulta, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return form(consulta,model);
        }
        repository.update(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Consulta consulta, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return form(consulta,model);
        }
        repository.update(consulta);
        return new ModelAndView("redirect:/consulta/list");
    }
}
