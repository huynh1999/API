package com.appdd.api.Model;

import com.oracle.jrockit.jfr.ValueDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "onetimepass")
@Table(name = "onetimepass")
public class OneTimePass {
    @Id
    @Column(name = "id")
    private int Id;
    @Column(name = "username")
    private String Username;
    @Column(name = "token")
    private String Token;
    @Column(name = "otp")
    private int Otp;
    @Column(name = "timenow")
    private String Timenow;
}
