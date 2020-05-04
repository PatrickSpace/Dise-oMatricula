package com.matricula.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matricula.entity.Profesor;
import com.matricula.service.IProfesorService;

@Controller
@RequestMapping("/profesor")
public class PerofesorController {

	@Autowired
	private IProfesorService dS;

	@GetMapping("/list")
	public String listar(Model model) {
		model.addAttribute("lista", dS.listar());
		return "profesor/listProfesor";
	}

	@GetMapping("/new")
	public String newalumno(Model model) {
		model.addAttribute("profesor", new Profesor());
		return "profesor/profesor";
	}

	@PostMapping("/save")
	public String guardar(@Valid Profesor d, Model model, BindingResult result, SessionStatus status,
			RedirectAttributes redirect) throws Exception {
		if (result.hasErrors()) {
			redirect.addFlashAttribute("error", "Se produjo un error");
			return "profesor/profesor";
		} else {
			dS.insert(d);
			status.setComplete();
			redirect.addFlashAttribute("mensaje", "Se agregó correctamente");
		}
		return "redirect:/profesor/list";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable(name = "id") Long id, RedirectAttributes redirect, Model model) {
		Profesor d = dS.getProfesor(id);
		if (d == null) {
			redirect.addFlashAttribute("mensaje", "El alumno no existe");
			return "profesor/profesor";
		} else {
			model.addAttribute("profesor", d);
		}
		return "profesor/profesor";
	}

	@RequestMapping("/profile/{id}")
	public String ver(Model model, @PathVariable(name = "id") Long id) {
		Profesor d = dS.getProfesor(id);
		model.addAttribute("profesor", d);
		return "profesor/profile";
	}

	@RequestMapping("/delete/{id}")
	public String eliminar(Model model, RedirectAttributes redirect, @PathVariable(name = "id") Long id) {
		try {
			if (id != null && id > 0) {
				dS.eliminar(id);
				redirect.addFlashAttribute("mensaje", "Se eliminó correctamente");
			} else {
				redirect.addFlashAttribute("error", "elija un alumno existente");
				String ret = "redirect:/profesor/profile/" + id;
				return ret;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirect.addFlashAttribute("error", e.getMessage());
			String ret = "redirect:/profesor/profile/" + id;
			return ret;
		}
		return "redirect:/profesor/list";
	}

}
