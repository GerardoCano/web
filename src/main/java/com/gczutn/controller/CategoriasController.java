package com.gczutn.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gczutn.model.Categoria;
import com.gczutn.service.IntCategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	private IntCategoriasService serviceCategorias;
	
	//@GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista=serviceCategorias.obtenerTodas();
		model.addAttribute("categorias", lista);
		return "categorias/listaCategorias";
	}
	
	//@GetMapping("/crear")
	@RequestMapping(value="/crear", method=RequestMethod.GET)
	public String crear() {
		return "categorias/formCategorias";
	}
	
	@RequestMapping(value="/guardar", method=RequestMethod.POST)
	public String guardar(Categoria cat, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			//Impresión de errores en modo consola
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Error encontrado: "+error.getDefaultMessage());
			}
			return "categorias/formCategorias";
		}
			//actualiza el id de la nueva categoria
			cat.setId(serviceCategorias.obtenerId()+1);
			System.out.println("Categoria: "+cat);
			serviceCategorias.guardar(cat);
			attributes.addFlashAttribute("msg", "¡Operación de guardar exitosa!");
			return "redirect:/categorias/index";
		
	}
	
	@GetMapping("/eliminar")
	public String  eliminar(@RequestParam("id")int idCategoria) {
		serviceCategorias.eliminarCat(idCategoria);
		//System.out.println("idCategoria = " + idCategoria);
		return "redirect:/categorias/index";
		
	}
	
	//@PostMapping("/guardar")
	/*@RequestMapping(value="/guardar", method=RequestMethod.POST)
	public String guardar(@RequestParam("nombre")String nombre, @RequestParam("descripcion")String descripcion, Model model) {
		System.out.println("Nombre Vacante = "+nombre);
		System.out.println("Descripcion = "+descripcion);
		Categoria c=new Categoria();
		c.setNombre(nombre);
		c.setDescripcion(descripcion);
		serviceCategorias.guardar(c);
		return "categorias/listaCategorias";
	}*/
	
	
}