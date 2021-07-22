package com.ex.demo3.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class count {
        @Id
        @Column(unique = true)
        String type;
        int count;

    public count()
    {

    }
    public count(String type,int count) {
        this.type = type;
        this.count=count;
    }
    public count(String  type)
    {
        this.type=type;
        this.count=1;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
