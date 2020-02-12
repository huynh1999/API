package com.appdd.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity(name = "groupuser")
@Table(name = "groupuser")
public class GroupUser {
    @Id
    @Column(name = "idgroup")
    private int Idgroup;
    @Column(name = "namegroup")
    private String Namegroup;
    @Column(name = "description")
    private String Description;
    @Column(name = "createby")
    private String Createby;
}
