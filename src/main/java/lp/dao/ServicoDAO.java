package lp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lp.model.Servico;

@Repository
@Transactional
public class ServicoDAO {
	
	@PersistenceContext
    protected EntityManager em; 
	
	public Servico save(final Servico entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.persist(entity);
		session.flush();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<Servico> findAll() {
		return em.createQuery("FROM Servico").getResultList();
	}
	
	public List<Servico> searchByFilters(String descricao) {
		
		StringBuilder sql = new StringBuilder("from Servico servico where 1=1 ");
	     
		if(descricao != null && !descricao.isEmpty()){
	    	 
			sql.append(" and lower(servico.descricao) LIKE lower(:nomeFiltro) "); 
		}
	     
		 javax.persistence.Query query = em.createQuery(sql.toString());

		 if(descricao != null && !descricao.trim().isEmpty()){
			 query.setParameter("nomeFiltro", "%"+descricao+"%");
		 }
		 
		 List<Servico> lista = query.getResultList();	
			
		return lista;
	
	
	}
	

	public Servico update(Servico entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.merge(entity);
		session.flush();
		return entity;
	}

	public void delete(final Long id) {
		Servico  entity =  em.find(Servico.class, id);
		em.remove(em.contains(entity) ? entity : em.merge(entity));		
	}
	
	
	@Transactional
	public Servico getById(final Long id) {
		return em.find(Servico.class, id);
	}

}
