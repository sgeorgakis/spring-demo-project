package com.jr.interview.persistence.repositories.jpa;

import org.hibernate.classic.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by s.georgakis on 24/2/2017.
 */
public class GenericRepository<T, ID extends Serializable> {

    protected Session session;
    protected Class<T> type;

    public void setType(Class<T> type) {
        this.type = type;
    }

    public <S extends T> S save(S entity) {
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    public <S extends T> void persist(S entity) {
        session.persist(entity);
    }

    public <S extends T> S merge(S entity) {
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    public <S extends T> void delete(S entity) {
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public <S> List<S> findAll() {
        return session.createCriteria(type).list();
    }

    public <S> S findOne(ID id) {
        return (S) session.get(type, id);
    }
}
