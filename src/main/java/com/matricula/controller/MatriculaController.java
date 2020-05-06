package com.matricula.controller;

import java.time.LocalDate;
import java.util.List;

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
import com.matricula.entity.Matricula;
import com.matricula.service.IAlumnoService;
import com.matricula.service.ICursoService;
import com.matricula.service.IMatriculaService;
import com.matricula.service.IProfesorService;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {

	@Autowired
	private IMatriculaService mS;

	@Autowired
	private IProfesorService pS;

	@Autowired
	private ICursoService cS;

	@Autowired
	private IAlumnoService aS;

	@GetMapping("/list")
	public String listar(Model model) {
		int year = LocalDate.now().getYear();
		model.addAttribute("lista", mS.listar());
		model.addAttribute("ciclo", year);
	
		return "matricula/listMatricula";
	}

	@RequestMapping("/list/{ciclo}")
	public String listarXciclo(@PathVariable(name = "ciclo") int ciclo, Model model) {
		model.addAttribute("ciclo", ciclo);
		model.addAttribute("lista", mS.listarciclo(ciclo));
		return "matricula/listMatricula";
	}

	@RequestMapping("/new")
	public String newmatricula(Model model) {
		Matricula ma = new Matricula();
		int year = LocalDate.now().getYear();
		ma.setCiclo(year);
		model.addAttribute("matricula", ma);
		model.addAttribute("listprofesores", pS.listar());
		model.addAttribute("listcursos", cS.listar());
		return "matricula/matricula";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable(name = "id") Long id, RedirectAttributes redirect, Model model) {
		Matricula ma = mS.getMatricula(id);
		if (ma == null) {
			redirect.addFlashAttribute("error", "El alumno no existe");
			return "matricula/list";
		} else {
			model.addAttribute("matricula", ma);
		}
		return "matricula/matricula";
	}

	@PostMapping("/save")
	public String guardar(@Valid Matricula ma, Model model, BindingResult result, SessionStatus status,
			RedirectAttributes redirect) throws Exception {

		String ret = "redirect:/matricula/list";
		if (result.hasErrors()) {
			redirect.addFlashAttribute("error", "Se produjo un error");
			return "matricula/matricula";
		} else {
			mS.insert(ma);
			status.setComplete();
			redirect.addFlashAttribute("mensaje", "Se agrego correctamente");
		}
		return ret;
	}

	@RequestMapping("/delete/{id}")
	public String eliminar(Model model, RedirectAttributes redirect, @PathVariable(name = "id") Long id) {
		try {
			if (id != null && id > 0) {
				mS.elminar(id);
				redirect.addFlashAttribute("mensaje", "Se eliminó correctamente");
			} else {
				redirect.addFlashAttribute("error", "elija una matricula existente");
				String ret = "redirect:/matricula/list";
				return ret;
			}
		} catch (Exception e) {
			redirect.addFlashAttribute("error", e.getMessage());
			String ret = "redirect:/matricula/list";
			return ret;
		}
		return "redirect:/matricula/list";
	}

	@RequestMapping("/detalle/{id}")
	public String ver(Model model, @PathVariable(name = "id") Long id) {
		Matricula ma = mS.getMatricula(id);
		List<Alumno> alumnos = ma.getAlumnos();
		model.addAttribute("matricula", ma);
		model.addAttribute("alumnos", aS.listar());
		model.addAttribute("alumno", new Alumno());
		model.addAttribute("alumnosinscritos", alumnos);
		return "matricula/detalle";
	}

	@RequestMapping("/inscribiralumno/{idm}/{id}")
	public String inscribiralumno(@PathVariable(name = "idm") Long idm, Model model, @PathVariable(name = "id") Long id,
			RedirectAttributes redirect) throws Exception {

		String ret = "redirect:/matricula/detalle/" + idm;
		Matricula ma = mS.getMatricula(idm);
		List<Alumno> alumnos = ma.getAlumnos();
		model.addAttribute("matricula", ma);
		model.addAttribute("alumnos", aS.listar());
		model.addAttribute("alumnosinscritos", alumnos);
		int rpta;
		Alumno al = aS.getAlumno(id);
		rpta = ma.addAlumno(al);
		if (rpta == 0) {
			redirect.addFlashAttribute("error", "Este curso ha alcanzado su maxima capacidad (10)");
			return ret;
		} else {
			mS.insert(ma);
			redirect.addFlashAttribute("mensaje", "Se agrego correctamente");
		}

		model.addAttribute("alumno", new Alumno());
		return ret;
	}

	@RequestMapping("/borraralumno/{idm}/{id}")
	public String borraralumno(@PathVariable(name = "idm") Long idm, Model model, @PathVariable(name = "id") Long id,
			RedirectAttributes redirect) {

		String ret = "redirect:/matricula/detalle/" + idm;
		Matricula ma = mS.getMatricula(idm);
		List<Alumno> alumnos = ma.getAlumnos();
		model.addAttribute("matricula", ma);
		model.addAttribute("alumnos", aS.listar());
		model.addAttribute("alumnosinscritos", alumnos);
		model.addAttribute("alumno", new Alumno());
		try {
			redirect.addFlashAttribute("error", "No se puede eliminar, por favor elimine la matricula");
			
			
		}catch (Exception e) {
			redirect.addFlashAttribute("error", e.getMessage());
		}
		
		
		return ret;
	}
}