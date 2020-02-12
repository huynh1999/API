package com.appdd.api.Controller;

import com.appdd.api.Model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;
@Repository
@RestController
@Transactional
@RequestMapping("/api/user")
public class UserController {
    @PersistenceContext
    EntityManager em;
    @Autowired
    Gson gson;

    @PostMapping("/dangki")
    public String DangKi(@RequestBody String a)
    {
        User dangki=gson.fromJson(a,User.class);
        em.createNativeQuery("INSERT INTO user (username,pass,fullname,token)"
                    + " VALUES ( :a, :b, :c,:d)")
                .setParameter("a", dangki.getUsername())
                .setParameter("b", dangki.getPass())
                .setParameter("c", dangki.getFullname())
                .setParameter("d", Base64.getEncoder().encodeToString((dangki.getUsername()+"&&"+dangki.getPass()).getBytes()))
                .executeUpdate();
        return "Thanh cong";
    }
    @PostMapping("/dangnhap")
    public String DangNhap(@RequestBody String user)
    {
        User dangnhap=gson.fromJson(user,User.class);
        Query query=em.createQuery("select u from user u where u.Username=:id and u.Pass=:ps")
                .setParameter("id",dangnhap.getUsername())
                .setParameter("ps",dangnhap.getPass());
        List<User>a=query.getResultList();
        return gson.toJson(a.get(0));
    }
    @PutMapping("/changepass")
    public String changepass(@RequestBody String a, @RequestHeader(value = "token")String token, HttpServletResponse response)
    {
        Query query =em.createQuery("select u.Username from user u where u.Token =:a")
                .setParameter("a",token);
        List<String> test=null;
        test=query.getResultList();
        if(test.isEmpty())return "Fail";


        User user=gson.fromJson(a,User.class);
        if(!user.getUsername().equals(test.get(0)))
        {
            response.setStatus(500);
            return "Không đồng bộ";
        }
        em.createNativeQuery("update user set pass=:b,token=:e where token=:a")
                .setParameter("a", token)
                .setParameter("b", user.getPass())
                .setParameter("e",  Base64.getEncoder().encodeToString((user.getUsername()+"&&"+user.getPass()).getBytes()))
                .executeUpdate();
        return "Thanh cong";
    }
}
