package com.jr.interview.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by s.georgakis on 24/2/2017.
 */
@XmlRootElement(name="Test2")
@XmlAccessorType(XmlAccessType.FIELD)
public class Test2 implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name="id")
    private Integer test2Id;

    @XmlElement
    private String name;

    public Test2() {
        super();
    }

    public Test2(String name) {
        this.name = name;
    }

    public Integer getTest2Id() {
        return test2Id;
    }

    public void setTest2Id(Integer testId) {
        this.test2Id = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id: " + this.getTest2Id() + ", name: " + this.getName() + "]";
    }
}
