package com.bookstore.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

public class JpaDAO<E> {
	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	//对database进行改动，就需要begin和commit；没有的话就不需要，比如find()
	
	public E create(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();
		return entity; 
	}
	
	public E update(E entity) {
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);//merge就是新旧融合，这样就是update了
		entityManager.getTransaction().commit();
		return entity;
	}
	
	public E find(Class<E> type, Object id) {
		E entity = entityManager.find(type,  id);
		if (entity != null) {
			entityManager.refresh(entity);

		}
		return entity;
	}
	
	public void delete(Class<E> type, Object id) {
		entityManager.getTransaction().begin();
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
		entityManager.getTransaction().commit();
	}
	
	public List<E> findWithNamedQuery(String queryName){
		Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
	}
	
	public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue){
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		return query.getResultList();
	}
	
	public long countWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (long) query.getSingleResult();
	}
	

}
