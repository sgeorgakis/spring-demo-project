package com.jr.interview.persistence;

import com.jr.interview.persistence.HibernateUtil;
import com.jr.interview.persistence.repositories.Test2Repository;

/**
 * Created by s.georgakis on 28/2/2017.
 */
public class Test2RepositoryTest {

    public static void main( String[] args ) {
        System.out.println("Maven + Hibernate + MySQL");
        HibernateUtil hibernateUtil = new HibernateUtil();
        Test2Repository test2Repository = new Test2Repository(hibernateUtil.session());
//        List<Test2> list = test2Repository.findAll();
//        System.out.println("--------------");
//        System.out.println("| ID |  NAME |");
//        System.out.println("--------------");
//        for (Test2 test2 : list) {
//            System.out.println("| " + test2.getTest2Id() + "  | " + test2.getName() + " |");
//        }
//        System.out.println("--------------");
//
        System.out.println(test2Repository.findAll());
        System.out.println(test2Repository.findOne(3).toString());

        hibernateUtil.shutdown();
    }
}
