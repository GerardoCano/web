package com.gczutn.service;

import java.util.List;

import com.gczutn.model.Categoria;

public interface IntCategoriasService {
	public List<Categoria> obtenerTodas();
	public void guardar(Categoria categoria);
	public Categoria buscarPorId(Integer idCategoria);
	public Integer obtenerId();
	public void eliminarCat(Integer idCat);
}
