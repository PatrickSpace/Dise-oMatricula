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

import com.matricula.entity.Curso;
import com.matricula.service.ICursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private ICursoService cS;
	
	
	@GetMapping("/list")
	public String listar(Model model) {
		model.addAttribute("lista", cS.listar());
		return "curso/listCurso";
	}
	
	@GetMapping("/new")
	public String newalumno(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso/curso";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid Curso cu, Model model, BindingResult result, SessionStatus status, RedirectAttributes redirect) throws Exception {
		
		if (result.hasErrors()) {
			redirect.addFlashAttribute("error", "Se produjo un error");
			return "curso/curso";
		} else {
			cS.insert(cu);
			status.setComplete();
			redirect.addFlashAttribute("mensaje", "Se agrego correctamente");
		} 
		return "redirect:/curso/list";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable(name = "id") Long id, RedirectAttributes redirect, Model model) {
		Curso cu = cS.getCurso(id);
		if (cu ==null) {
			redirect.addFlashAttribute("mensaje", "El alumno no existe");
			return "curso/curso";
		} else {
			model.addAttribute("curso", cu);
		}
		return "curso/curso";
	}
	
	@RequestMapping("/delete/{id}")
	public String eliminar(Model model, RedirectAttributes redirect, @PathVariable(name = "id") Long id) {
		try {
			if (id != null && id > 0) {
				cS.eliminar(id);
				redirect.addFlashAttribute("mensaje","Se eliminó correctamente");
			}
			else {
				redirect.addFlashAttribute("error","elija un curso existente");
				String ret= "redirect:/curso/list";
				return  ret;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirect.addFlashAttribute("error", e.getMessage());
			String ret= "redirect:/curso/list";
			return  ret;
		}
		return  "redirect:/curso/list";
	}
}
