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

import com.matricula.entity.Alumno;
import com.matricula.service.IAlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private IAlumnoService aS;

	@GetMapping("/list")
	public String listar(Model model) {
		model.addAttribute("lista", aS.listar());
		return "alumno/listAlumnos";
	}

	@GetMapping("/new")
	public String newalumno(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "alumno/alumno";
	}

	@PostMapping("/save")
	public String guardar(@Valid Alumno al, Model model, BindingResult result, SessionStatus status,
			RedirectAttributes redirect) throws Exception {

		if (result.hasErrors()) {
			return "alumno/alumno";
		} else {
			aS.insert(al);
			status.setComplete();
			redirect.addFlashAttribute("msg", "Se agregó correctamente");
		}
		return "redirect:/alumno/list";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable(name = "id") Long id, RedirectAttributes redirect, Model model) {
		Alumno al = aS.getAlumno(id);
		if (al == null) {
			redirect.addFlashAttribute("mensaje", "El alumno no existe");
			return "alumno/alumno";
		} else {
			model.addAttribute("alumno", al);
		}
		return "alumno/alumno";
	}

	@RequestMapping("/profile/{id}")
	public String ver(Model model, @PathVariable(name = "id") Long id) {
		Alumno al = aS.getAlumno(id);
		model.addAttribute("alumno", al);
		return "alumno/profile";
	}

	@RequestMapping("/delete/{id}")
	public String eliminar(Model model, RedirectAttributes redirect, @PathVariable(name = "id") Long id) {
		try {
			if (id != null && id > 0) {
				aS.eliminar(id);
				redirect.addFlashAttribute("mensaje", "Se eliminó correctamente");
			} else {
				redirect.addFlashAttribute("error", "elija un alumno existente");
				String ret = "redirect:/alumno/profile/" + id;
				return ret;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirect.addFlashAttribute("error", e.getMessage());
			String ret = "redirect:/alumno/profile/" + id;
			return ret;
		}
		return "redirect:/alumno/list";
	}
}
