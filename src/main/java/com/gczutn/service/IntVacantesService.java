package com.gczutn.service;

import java.util.List;

import com.gczutn.model.Vacantes;


public interface IntVacantesService{
	public List<Vacantes> obtenerTodas();
	public void guardar(Vacantes vacante);
	public Vacantes buscarPorId(Integer idVacante);
}
