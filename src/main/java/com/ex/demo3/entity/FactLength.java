package com.ex.demo3.entity;

public class FactLength {
    String fact;
    Integer length;

    public FactLength(String fact, Integer length) {
        this.fact = fact;
        this.length = length;
    }
    public FactLength()
    {

    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
