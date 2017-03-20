package com.jr.interview.persistence.repositories;

import com.jr.interview.common.model.Test;
import com.jr.interview.persistence.HibernateUtil;
import com.jr.interview.persistence.repositories.jpa.GenericRepository;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by s.georgakis on 28/2/2017.
 */
public class TestRepository extends GenericRepository<Test, Integer> {

    public TestRepository() {
        super();
        this.setType(Test.class);
    }

    public TestRepository(Session session) {
        this.session = session;
        this.setType(Test.class);
    }

    @Autowired
    public void setSession(HibernateUtil hibernateUtil) {
        this.session = hibernateUtil.getSessionFactory().openSession();
    }
}
