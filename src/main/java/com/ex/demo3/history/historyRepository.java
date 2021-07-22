package com.ex.demo3.history;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface historyRepository extends CrudRepository<count,String > {

    @Query(value = "select * from count c where c.type=?",nativeQuery = true)
    count findByID(String type);


}
