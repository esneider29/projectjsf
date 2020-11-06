package com.inetum.documentos.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inetum.documentos.HibernateUtils;

public abstract class GenericDao<T> {

	private Class<T> entityClass;

	Transaction transaction = null;
    Session session = null; 
    
    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Optional<T> findById(Integer id){
    	Optional<T> entityOpt = null;
    	
    	try {
    	//entity = (T) getSession().getNamedQuery(entityClass.getSimpleName().concat(".findById")).getSingleResult();    	
       entityOpt = Optional.of((T) getSession().find(entityClass, id));
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return entityOpt;
    }
    
    public List<T> findAll() {
    	List<T> lista = new ArrayList<T>(1);
    	try {
    	lista = getSession().getNamedQuery(entityClass.getSimpleName().concat(".findAll")).getResultList();    	
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return lista;    	
    }
    
    public Optional<T> save(T entity) {
    	try {
            transaction = getSession().beginTransaction();
            getSession().save(entity);
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
        	if (transaction != null) {
        		transaction.rollback();
        	}
            e.printStackTrace();
        }
        return Optional.empty();
    	
    }
    
    public Optional<T> update(T entity) {
    	try {        	
            transaction = getSession().beginTransaction();
            getSession().update(entity);
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
        	if (transaction != null) {
        		transaction.rollback();
        	}
            e.printStackTrace();
        }
        return Optional.empty();
    	
    }   
    
    public void remove(T entity) {
    	try {        	
    		transaction = getSession().beginTransaction();
        	session.delete(entity);
        	transaction.commit();
        	System.out.println("eliminado. DaoG");    
        } catch (Exception e) {
        	if (transaction != null) {
        		transaction .rollback();
        	}
            e.printStackTrace();
        }    	
    }


    
    
    
    protected Session getSession() {
    	if (session == null) {
    		session = HibernateUtils.getSessionFactory().openSession();
    	}
    	return session;
    }
    
    
}
