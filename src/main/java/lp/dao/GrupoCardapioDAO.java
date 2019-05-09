package lp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lp.model.GrupoCardapio;

@Repository
@Transactional
public class GrupoCardapioDAO {
	
	@PersistenceContext
    protected EntityManager em; 
	
	public GrupoCardapio save(final GrupoCardapio entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.persist(entity);
		session.flush();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoCardapio> findAll() {
		return em.createQuery("FROM GrupoCardapio").getResultList();
	}
	
	public List<GrupoCardapio> searchByFilters(String descricao) {
		
		StringBuilder sql = new StringBuilder("from GrupoCardapio grupo where 1=1 ");
	     
		if(descricao != null && !descricao.isEmpty()){
	    	 
			sql.append(" and lower(grupo.nome) LIKE lower(:nomeFiltro) "); 
		}
	     
		 javax.persistence.Query query = em.createQuery(sql.toString());

		 if(descricao != null && !descricao.trim().isEmpty()){
			 query.setParameter("nomeFiltro", "%"+descricao+"%");
		 }
		 
		 List<GrupoCardapio> lista = query.getResultList();	
			
		return lista;
	
	
	}
	

	public GrupoCardapio update(GrupoCardapio entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.merge(entity);
		session.flush();
		return entity;
	}

	public void delete(final Long id) {
		GrupoCardapio  entity =  em.find(GrupoCardapio.class, id);
		em.remove(em.contains(entity) ? entity : em.merge(entity));		
	}
	
	
	@Transactional
	public GrupoCardapio getById(final Long id) {
		return em.find(GrupoCardapio.class, id);
	}

}
