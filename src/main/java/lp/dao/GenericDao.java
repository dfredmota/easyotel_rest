package lp.dao;
//package lp.dao;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.hibernate.Session;
//import org.springframework.transaction.annotation.Transactional;
//
//
//public class GenericDao<T, PK extends Serializable> {
//
//	@PersistenceContext(unitName="entityManagerFactory")
//	private EntityManager entityManager;
//
//	protected Class<T> entityClass;
//
//	@SuppressWarnings("unchecked")
//	public GenericDao() {
//		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
//		this.entityClass = ((Class<T>) genericSuperclass.getActualTypeArguments()[0]);
//	}
// 
//	public T save(final T entity) {
//		entityManager=entityManager.getEntityManagerFactory().createEntityManager();
//		Session session = (Session) entityManager.unwrap(Session.class);
//		session.persist(entity);
//		session.flush();
//		return entity;
//	}
//
//	@Transactional
//	public T getById(final PK id) {
//		return entityManager.find(getTypeClass(), id);
//	}
//
//	public T update(T entity) {
//		entityManager=entityManager.getEntityManagerFactory().createEntityManager();
//		Session session = (Session) entityManager.unwrap(Session.class);
//		session.merge(entity);
//		session.flush();
//		return entity;
//	}
//
//	public void delete(final T entity) {
//		entityManager.remove(entity);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<T> findAll() {
//		System.out.println("ENTROU NA PESQUISA: ");
//		System.out.println("ENTITY MANAGER: "+entityManager);
//		return entityManager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
//	}
//
//	public EntityManager getEntityManager() {
//		return entityManager;
//	}
//
//	private Class<T> getTypeClass() {
//		return entityClass;
//	}
//
//	protected Session getSession() {
//		return (Session) getEntityManager().getDelegate();
//	}
//
//}