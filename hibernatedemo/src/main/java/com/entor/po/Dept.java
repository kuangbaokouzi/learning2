package com.entor.po;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
@Table(name = "dept")
public class Dept implements Serializable {

    @Id /* 标明主键 */
    @GeneratedValue /* ID生成策略为自动生成 */
    private Integer id;
    private String dname;
    @Column(name = "create_date") /* 字段映射关系 */
    private Date createDate;
    @OneToMany(mappedBy = "dept")
    @Cascade(SAVE_UPDATE)
    private Set<Emp> emps = new HashSet<>();

    public Dept() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }
}
