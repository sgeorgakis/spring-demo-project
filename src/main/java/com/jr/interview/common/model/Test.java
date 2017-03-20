package com.jr.interview.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by s.georgakis on 24/2/2017.
 */
@XmlRootElement(name="Test")
@XmlAccessorType(XmlAccessType.FIELD)
public class Test implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public Test() {
        super();
    }

    public Test(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id: " + this.getId() + ", name: " + this.getName() + "]";
    }
}
