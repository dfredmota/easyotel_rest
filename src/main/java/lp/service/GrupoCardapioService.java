package lp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lp.dao.GrupoCardapioDAO;
import lp.model.GrupoCardapio;

@Service("grupoCardapioService")
public class GrupoCardapioService implements GenericService<GrupoCardapio>{

	@Autowired
	private GrupoCardapioDAO dao;
	
	@Override
	public List<GrupoCardapio> all() {
		return dao.findAll();
	}
	
	public List<GrupoCardapio> searchByFilters(String descricao) {
		return dao.searchByFilters(descricao);
	}

	@Override
	public GrupoCardapio getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public GrupoCardapio create(GrupoCardapio entity) {
		return dao.save(entity);
	}

	@Override
	public GrupoCardapio update(GrupoCardapio entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);		
	}
	

}
