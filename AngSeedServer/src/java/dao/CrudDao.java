/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exceptions.RestException;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

/**
 *
 * @author Nikolaj
 */
public class CrudDao<T, PK> {

    EntityManager manager;
    EntityTransaction transaction;

    /**
     * The generic class we reference
     */
    Class<T> entityType;

    public CrudDao() {

        manager = Persistence.createEntityManagerFactory("PU").createEntityManager();
        transaction = manager.getTransaction();

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public T create(T entity) {

        transaction.begin();
        manager.persist(entity);
        transaction.commit();

        return entity;
    }

    /**
     * Finds entity id.
     *
     * @param id
     * @return
     * @throws exceptions.RestException
     */
    public T find(PK id) throws RestException {
        T entity = manager.find(entityType, id);
        
        if(entity != null){
            return manager.find(entityType, id);
        }
        throw new RestException(entityType.getSimpleName() + " not found", Response.Status.NOT_FOUND);
    }

    /**
     * Update entity.
     *
     * @param entity
     * @return
     * @throws exceptions.RestException
     */
    public T update(T entity) throws RestException {

        transaction.begin();
        manager.merge(entity);
        transaction.commit();

        return entity;
    }

    /**
     * Removes the entity.
     *
     * Removes the given entity from the database vs using detach, that would
     * only remove it from the entity manager until next flush.
     *
     *
     * @param id
     * @return
     * @throws exceptions.exceptions.RestException
     */
    public T delete(PK id) throws RestException {
        transaction.begin();
        T entity = manager.find(entityType, id);

        if (entity != null) {
            manager.remove(entity);
            transaction.commit();
            return entity;
        }

        throw new RestException(entityType.getName() + " not found", Response.Status.NOT_FOUND);
    }
}
