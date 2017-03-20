package com.jr.interview.persistence.repositories;

import com.jr.interview.common.model.Test2;
import com.jr.interview.persistence.HibernateUtil;
import com.jr.interview.persistence.repositories.jpa.GenericRepository;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by s.georgakis on 24/2/2017.
 */

public class Test2Repository extends GenericRepository<Test2, Integer> {

    public Test2Repository() {
        super();
        this.setType(Test2.class);
    }

    public Test2Repository(Session session) {
        this.session = session;
        this.setType(Test2.class);
    }

    @Autowired
    public void setSession(HibernateUtil hibernateUtil) {
        this.session = hibernateUtil.getSessionFactory().openSession();
    }
}
