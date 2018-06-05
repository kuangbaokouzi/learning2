package com.entor.po;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Emp {

    @Id
    @GeneratedValue
    private Integer id;
    private String ename;

    @ManyToOne
    @JoinColumn(name = "did")
    private Dept dept;

    public Emp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
