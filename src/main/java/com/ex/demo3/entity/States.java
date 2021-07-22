package com.ex.demo3.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class States {
    @Id
    Integer state_id;
    String state_name;

    public States(Integer state_id, String state_name) {
        this.state_id = state_id;
        this.state_name = state_name;
    }

    public States()
    {

    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
