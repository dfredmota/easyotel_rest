package lp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lp.dao.ServicoDAO;
import lp.model.Servico;

@Service("servicoService")
public class ServicoService implements GenericService<Servico>{

	@Autowired
	private ServicoDAO dao;
	
	@Override
	public List<Servico> all() {
		return dao.findAll();
	}
	
	public List<Servico> searchByFilters(String descricao) {
		return dao.searchByFilters(descricao);
	}

	@Override
	public Servico getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public Servico create(Servico entity) {
		return dao.save(entity);
	}

	@Override
	public Servico update(Servico entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);		
	}
	

}
