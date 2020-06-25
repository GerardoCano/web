package com.gczutn.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gczutn.model.Categoria;

@Service
public class categoriaServiceImp implements IntCategoriasService{
	
	private List<Categoria> lista = null;
	
	public categoriaServiceImp() {
		lista=new LinkedList<Categoria>();
		
		Categoria c1=new Categoria();
		c1.setId(1);
		c1.setNombre("Recursos Humanos");
		c1.setDescripcion("Relacionado con trabajos de r.h");
		
		lista.add(c1);
		
		Categoria c2=new Categoria();
		c2.setId(2);
		c2.setNombre("Ventas");
		c2.setDescripcion("Relacionado con ventas en linea");
		
		lista.add(c2);
		
		Categoria c3=new Categoria();
		c3.setId(3);
		c3.setNombre("Programadores web");
		c3.setDescripcion("Relacionado con el desarrollo de app");
		
		lista.add(c3);
		
		Categoria c4=new Categoria();
		c4.setId(4);
		c4.setNombre("Transporte");
		c4.setDescripcion("Relacionado con el transporte foraneo");
		
		lista.add(c4);
		
		Categoria c5=new Categoria();
		c5.setId(5);
		c5.setNombre("Arquitectos");
		c5.setDescripcion("Relacionado con la construcción");
		
		lista.add(c5);
	}
	
	@Override
	public List<Categoria> obtenerTodas() {
		return lista;
	}

	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);
	}

	@Override
	public Categoria buscarPorId(Integer idCat) {
		for(Categoria c:obtenerTodas()) {
			if(c.getId()==idCat) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Integer obtenerId() {
		return obtenerTodas().size();
	}

	@Override
	public void eliminarCat(Integer idCat) {
		lista.remove(buscarPorId(idCat));
		//lista.remove(idCat);
	}
	
}
