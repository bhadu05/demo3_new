package com.ex.demo3.mysql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface customerRepository extends CrudRepository<customer, Integer> {


     customer findByName(String Name);
     customer findByAge(int age);
     @Query(
             value = "SELECT name FROM customer order by name",
             nativeQuery = true)
     List<String> CustomQuery();

     @Query(value = "select count(name) from customer",nativeQuery = true)
     int FindTotal();
     @Query(value="SELECT *  FROM customer c where c.age BETWEEN ? and ?",nativeQuery = true)
     List<customer> FindAgedBetween(int start,int end);
     @Modifying
     @Transactional
     @Query(value="UPDATE customer c SET c.age=56 WHERE c.age=24",nativeQuery = true)
     void SetAge();

     @Query(value = "SELECT * FROM customer c WHERE c.id=?1",nativeQuery = true)
     customer findByID(int id);

/*    @Query(value = "select * from customer c where c.name=:name",nativeQuery = true)
     List<customer> findByFirst(@Param("name") String name);*/


}
