package com.gczutn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gczutn.model.Vacantes;
import com.gczutn.service.IntCategoriasService;
import com.gczutn.service.IntVacantesService;
import com.gczutn.util.Utileria;

@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {
	
	@Autowired
	private IntVacantesService serviceVacantes;
	
	@Autowired
	private IntCategoriasService serviceCategorias;
	
	//@GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Vacantes> lista=serviceVacantes.obtenerTodas();
		model.addAttribute("vacantes", lista);
		return "vacantes/listaVacantes";
	}
		
	@GetMapping("/crear")
	public String crear(Vacantes vacantes, Model model) {
		model.addAttribute("categorias", serviceCategorias.obtenerTodas());
		return "vacantes/formVacante";
	}
	
	@PostMapping("/guardar")
	public String guardar(Vacantes vacantes, BindingResult result, RedirectAttributes attributes,@RequestParam("archivoImagen") MultipartFile multiPart) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()) {
				System.out.println("Error: " + error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		
		if(!multiPart.isEmpty()) {
			String ruta="c:/empleos/images/";
			String nombreImg= Utileria.guardarArchivo(multiPart,ruta);
			if(nombreImg != null) {//verdadero si se pudo subir el archivo
				//modificamos el atributo correspondiente a la imagen
				vacantes.setImagen(nombreImg);
			}
		}
		
		System.out.println(vacantes);
		serviceVacantes.guardar(vacantes);
		attributes.addFlashAttribute("msg", "¡Operación de guardar exitosa!");
		return "redirect:/vacantes/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	//anotacion Requestparam para pasar parametros usando peticion GET
	@GetMapping("/eliminar")
	public String  eliminar(@RequestParam("id")int idVacante, Model model) {
		System.out.println("idvacante = " + idVacante);
		model.addAttribute("idVacante", idVacante);
		return "/mensaje";
		
	}
	
	@GetMapping("/detalle/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		//System.out.println("idVacante = " + idVacante);
		//model.addAttribute("idVacante", idVacante);
		//posteriormente se usara para la BD...
		Vacantes vacante = serviceVacantes.buscarPorId(idVacante);
		System.out.println("Vacante = " + vacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/detalle";
	}
	
	@GetMapping("/consulta/{salario}/{fecha}")
	public String consultarPorSalarioFechaPublicacion(@PathVariable("salario") double salario, @PathVariable("fecha") String fecha, Model model ) {
		System.out.println("Salario = " + salario);
		System.out.println("Fecha de publicacion = " + fecha);
		model.addAttribute("salario", salario);
		model.addAttribute("fecha", fecha);
		return "vacantes/consulta";
	}
}
