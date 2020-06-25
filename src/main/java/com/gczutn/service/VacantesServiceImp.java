package com.gczutn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gczutn.model.Vacantes;

@Service
public class VacantesServiceImp implements IntVacantesService{
	
	private List<Vacantes> lista=null;
	
	public VacantesServiceImp(){
		lista=new LinkedList<Vacantes>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		
		try {
			Vacantes v1=new Vacantes();
			v1.setId(1);
			v1.setNombre("Programador Web");
			v1.setDescripcion("Con amplia experiencia en desarrollo de app");
			v1.setSalario(20000.0);
			v1.setFecha(sdf.parse("12-05-2020"));
			v1.setDestacado(1);
			v1.setImagen("fanta.png");
			v1.setEstatus("Creada");
			
			Vacantes v2=new Vacantes();
			v2.setId(2);
			v2.setNombre("Licenciado en Informatica");
			v2.setDescripcion("Con amplia experiencia en desarrollo de app");
			v2.setSalario(14000.0);
			v2.setFecha(sdf.parse("12-05-2020"));
			v2.setDestacado(0);
			v2.setEstatus("Aprobada");
			
			Vacantes v3=new Vacantes();
			v3.setId(3);
			v3.setNombre("Ingeniero en comunicaciones");
			v3.setDescripcion("Con amplia experiencia en Mantenimiento de Redes");
			v3.setSalario(15000.0);
			v3.setFecha(sdf.parse("20-05-2020"));
			v3.setDestacado(1);
			v3.setImagen("fedex.png");
			v3.setEstatus("Eliminada");
			
			Vacantes v4=new Vacantes();
			v4.setId(4);
			v4.setNombre("Jefe de Almacen");
			v4.setDescripcion("Con amplia experiencia");
			v4.setSalario(8000.0);
			v4.setFecha(sdf.parse("25-03-2020"));
			v4.setDestacado(0);
			v4.setImagen("cocacola.png");
			v4.setEstatus("Creada");
			
			lista.add(v1);
			lista.add(v2);
			lista.add(v3);
			lista.add(v4);
			
		}catch(ParseException ex){
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	@Override
	public Vacantes buscarPorId(Integer idVacante) {
		// TODO Auto-generated method stub
		Vacantes vacante=null;
		int i=0;
		while(i < lista.size()) {
			vacante = lista.get(i);
			if(vacante.getId() == idVacante) {
				return vacante;
			}
			i++;
		}
		return null;
	}

	@Override
	public void guardar(Vacantes vacante) {
		obtenerTodas().add(vacante);
	}

	@Override
	public List<Vacantes> obtenerTodas() {
		return lista;
	}
	
}
