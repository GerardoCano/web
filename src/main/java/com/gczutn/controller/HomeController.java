package com.gczutn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gczutn.model.Vacantes;
import com.gczutn.service.IntVacantesService;

@Controller
public class HomeController {
	
	//Con la anotacion se crea una sola instancia en el controlador
	//de la clase servicio
	//Se agrega un atributo de la clase de servicio mediante la interfaz
	@Autowired
	private IntVacantesService serviceVacantes;
	
	@GetMapping("/tabla")
	public String tabla(Model model){
		List<Vacantes> lista = serviceVacantes.obtenerTodas();
		model.addAttribute("vacantes",lista);
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacantes vacante = new Vacantes();
		vacante.setNombre("Ingeniero de Comunicaciones");
		vacante.setDescripcion("Para mantenimiento de la red");
		vacante.setFecha(new Date());
		vacante.setSalario(8900.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new ArrayList<String>();
		lista.add("Ingeniero en Sistemas");
		lista.add("Jefe de Mantenimiento");
		lista.add("Ingeniero Civil");
		lista.add("Programador web");
		model.addAttribute("lista", lista);
		return "listado";
	}
	
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		/*model.addAttribute("mensaje", "Empleos web");
		model.addAttribute("fecha", new Date());
		*/
		/*String nombre="Auxiliar de contabilidad";
		String descripcion="Con experiencia de 5 años";
		Date fechaPublicacion= new Date();
		double salario= 8700.0;
		boolean vigente = true;
		model.addAttribute("nombre", nombre);
		model.addAttribute("descripcion", descripcion);
		model.addAttribute("fecha", fechaPublicacion);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);*/
		//Vista del usuario final
		List<Vacantes> lista = serviceVacantes.obtenerTodas();
		model.addAttribute("vacantes",lista);
		return "home";
	}
	
	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}
}