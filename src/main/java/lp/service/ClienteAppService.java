package lp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lp.dao.ClienteAppDAO;
import lp.model.ClienteApp;

@Service("clienteAppService")
public class ClienteAppService implements GenericService<ClienteApp>{

	@Autowired
	private ClienteAppDAO dao;
	
	@Override
	public List<ClienteApp> all() {
		return dao.findAll();
	}
	
	public List<ClienteApp> searchByFilters(String descricao) {
		return dao.searchByFilters(descricao);
	}

	@Override
	public ClienteApp getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public ClienteApp create(ClienteApp entity) {
		return dao.save(entity);
	}

	@Override
	public ClienteApp update(ClienteApp entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);		
	}
	

}
