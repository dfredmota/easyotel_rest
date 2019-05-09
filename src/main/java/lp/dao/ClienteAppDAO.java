package lp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lp.model.ClienteApp;

@Repository
@Transactional
public class ClienteAppDAO {
	
	@PersistenceContext
    protected EntityManager em; 
	
	public ClienteApp save(final ClienteApp entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.persist(entity);
		session.flush();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteApp> findAll() {
		return em.createQuery("FROM ClienteApp").getResultList();
	}
	
	public List<ClienteApp> searchByFilters(String descricao) {
		
		StringBuilder sql = new StringBuilder("from ClienteApp clienteApp where 1=1 ");
	     
		if(descricao != null && !descricao.isEmpty()){
	    	 
			sql.append(" and lower(clienteApp.nome) LIKE lower(:nomeFiltro) "); 
		}
	     
		 javax.persistence.Query query = em.createQuery(sql.toString());

		 if(descricao != null && !descricao.trim().isEmpty()){
			 query.setParameter("nomeFiltro", "%"+descricao+"%");
		 }
		 
		 List<ClienteApp> lista = query.getResultList();	
			
		return lista;
	
	
	}
	

	public ClienteApp update(ClienteApp entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.merge(entity);
		session.flush();
		return entity;
	}

	public void delete(final Long id) {
		ClienteApp  entity =  em.find(ClienteApp.class, id);
		em.remove(em.contains(entity) ? entity : em.merge(entity));		
	}
	
	
	@Transactional
	public ClienteApp getById(final Long id) {
		return em.find(ClienteApp.class, id);
	}

}
