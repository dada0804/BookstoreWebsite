package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDAO<E> {
	private static EntityManagerFactory entityManagerFactory;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		
	}

	public JpaDAO() {
		
	}
	
	//对database进行改动，就需要begin和commit；没有的话就不需要，比如find()
	
	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity; 
	}
	
	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);//merge就是新旧融合，这样就是update了
		entityManager.getTransaction().commit();
		entityManager.close();

		return entity;
	}
	
	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		E entity = entityManager.find(type,  id);
		if (entity != null) {
			entityManager.refresh(entity);

		}
		entityManager.close();

		return entity;
	}
	
	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
		entityManager.getTransaction().commit();
		entityManager.close();

	}
	
	public List<E> findWithNamedQuery(String queryName){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		List<E> result = query.getResultList();
		entityManager.close();

		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName, int firstResult, int maxResult){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<E> result = query.getResultList();

		entityManager.close();

		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		List<E> result = query.getResultList();

		entityManager.close();

		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName, Map<String, Object>parameters){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		//Returns a Set view of the mappings contained in this map. 
		//The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. 
		//If the map is modified while an iteration over the set is in progress 
		//(except through the iterator's own remove operation, or through the setValue operation on a map entry returned by the iterator)
		// the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, 
		// via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.
//		Map<Entry<String,Object>> setParameters = parameters.entrySet();
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {

			query.setParameter(entry.getKey(),entry.getValue());
		}
		List<E> result = query.getResultList();

		entityManager.close();

		return result;
		
	}
	
	public long countWithNamedQuery(String queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		Long result = (long) query.getSingleResult();

		entityManager.close();
		
		return result;
	}
	
	public long countWithNamedQuery(String queryName, String paramName, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		Long result = (long) query.getSingleResult();

		entityManager.close();
		
		return result;
	}
	
	public void close() {
		entityManagerFactory.close();
	}
	

}
