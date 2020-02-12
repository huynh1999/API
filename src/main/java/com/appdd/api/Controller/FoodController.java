package com.appdd.api.Controller;

import com.appdd.api.Model.Food;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/food")
public class FoodController {
    @PersistenceContext
    EntityManager em;
    @Autowired
    Gson gson;

    @GetMapping("/getall")
    public String GetFood() {
        Query query = em.createQuery("select f from food f");
        List<Food> a = query.getResultList();
        return gson.toJson(a);
    }
    @GetMapping("/getlikefood")
    public String GetLikeFood(@RequestHeader(value = "token") String token) {
        Query query = em.createQuery("select f from food f,likefood f2 where f.idfood=f2.idfood and f2.token=:token")
                .setParameter("token",token);
        List<Food> a = query.getResultList();
        return gson.toJson(a);
    }
    @PostMapping("/addlikefood")
    public String AddLikeFood(@RequestHeader(value = "token")String token,@RequestBody String body)
    {
        Food food=gson.fromJson(body,Food.class);
        em.createNativeQuery("insert into likefood(idfood,token) " +
                "values(:idfood,:token)")
                .setParameter("idfood",food.getIdfood())
                .setParameter("token",token)
                .executeUpdate();
        return "Thanh cong";
    }
    @DeleteMapping("/deletelikefood")
    public String DeleteLikeFood(@RequestHeader(value = "token")String token,@RequestBody String body)
    {
        Food food=gson.fromJson(body,Food.class);
        em.createNativeQuery("delete from likefood " +
                "where idfood=:idfood and token =:token")
                .setParameter("idfood",food.getIdfood())
                .setParameter("token",token)
                .executeUpdate();
        return "Thành công";
    }

}
