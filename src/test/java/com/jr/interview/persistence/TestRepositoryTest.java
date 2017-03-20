package com.jr.interview.persistence;

import com.jr.interview.common.model.Test;
import com.jr.interview.persistence.HibernateUtil;
import com.jr.interview.persistence.repositories.TestRepository;

import java.math.BigInteger;

/**
 * Created by s.georgakis on 28/2/2017.
 */
public class TestRepositoryTest {

    public static void main( String[] args ) {
        System.out.println("Maven + Hibernate + MySQL");
        HibernateUtil hibernateUtil = new HibernateUtil();
        TestRepository testRepository = new TestRepository(hibernateUtil.session());
//        List<Test2> list = test2Repository.findAll();
//        System.out.println("--------------");
//        System.out.println("| ID |  NAME |");
//        System.out.println("--------------");
//        for (Test2 test2 : list) {
//            System.out.println("| " + test2.getTest2Id() + "  | " + test2.getName() + " |");
//        }
//        System.out.println("--------------");
//
        Test test = new Test();
        test.setName("Name2");
        testRepository.save(test);
        System.out.println(testRepository.findAll());
        System.out.println(testRepository.findOne(1).toString());

        hibernateUtil.shutdown();
    }
}
