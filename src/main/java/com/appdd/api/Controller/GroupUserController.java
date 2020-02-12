package com.appdd.api.Controller;

import com.appdd.api.Model.Food;
import com.appdd.api.Model.GroupUser;
import com.appdd.api.Model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@RestController
@Transactional
@RequestMapping("/api/group")
public class GroupUserController{
    @PersistenceContext
    EntityManager em;
    @Autowired
    Gson gson;

    @GetMapping("/getgroup")
    public String GetGroup(@RequestHeader(value = "token")String token)
    {
        Query query=em.createQuery("select g from groupuser g,groupuserinfo g2" +
                " where g.Idgroup=g2.Idgroup and g2.Token=:token")
                .setParameter("token",token);
        List<GroupUser>list=query.getResultList();
        return gson.toJson(list);
    }
    @PostMapping("/creategroup")
    public String creategroup(@RequestHeader(value = "token")String token,
                              @RequestHeader(value = "namegroup")String namegroup,
                              @RequestHeader(value = "description")String description)
    {
        em.createNativeQuery("insert into groupuser(namegroup,description,createby) values(:namegroup,:description,:token)")
                .setParameter("namegroup",namegroup)
                .setParameter("description",description)
                .setParameter("token",token)
                .executeUpdate();
        em.createNativeQuery("insert into groupuserinfo(idgroup,token) select max(idgroup),createby from groupuser where createby=:token ")
                .setParameter("token",token)
                .executeUpdate();
        return "Ok";
    }
    @PostMapping("/adduser")
    public String AddUser(@RequestHeader(value = "username")String username,
                          @RequestHeader(value = "idgroup")String idgroup,
                          @RequestHeader(value = "otp")String otp)
    {
        Query quer=em.createQuery("select p.Token from onetimepass p where p.Username=:username and p.Otp=:otp")
                .setParameter("username",username)
                .setParameter("otp", Integer.valueOf(otp));
        List<String>token=quer.getResultList();
        if(token.isEmpty())return "Fail OTP";
        em.createNativeQuery("insert into groupuserinfo(idgroup,token) values(:idgroup,:token) ")
                .setParameter("idgroup",idgroup)
                .setParameter("token",token.get(0))
                .executeUpdate();
        return "ok";
    }
    @PostMapping("/addfood")
    public String AddFood(@RequestHeader(value = "idgroup")String idgroup,
                          @RequestHeader(value = "idfood")String idfood)
    {
        em.createNativeQuery("insert into groupfoodinfo(idgroup,idfood) values(:idgroup,:idfood)")
                .setParameter("idgroup",idgroup)
                .setParameter("idfood",idfood)
                .executeUpdate();
        return "Ok";
    }
    @GetMapping("/getfood")
    public String GetFood(@RequestHeader(value = "idgroup")String idgroup)
    {
        Query query=em.createQuery("Select f from food f,group g where g.idgroup=:idgroup and g.idfood=f.idfood")
                .setParameter("idgroup",idgroup);
        List<Food> foodList=query.getResultList();
        return gson.toJson(foodList);
    }
    @PostMapping("/createotp")
    public String CreateOTP(@RequestBody String body, HttpServletResponse response)
    {
        Random random=new Random();
        User user=gson.fromJson(body,User.class);
        int rd=100000+random.nextInt(899999);
        em.createNativeQuery("insert into onetimepass(username,token,OTP,Timenow) " +
                "values(:username,:token,:otp,now())")
                .setParameter("username",user.getUsername())
                .setParameter("token",user.getToken())
                .setParameter("otp",rd)
                .executeUpdate();
        response.setHeader("code",Integer.toString(rd));
        return "Ok";
    }
    @DeleteMapping("/rmotp")
    public void RmOTP()
    {
        em.createNativeQuery("delete from onetimepass where (SUBTIME(now(), \"0 0:1:0\"))>timenow ")
                .executeUpdate();
    }
}
