package com.ex.demo3.entity;

public class Varification {
    String otp;
    String txnId;

    public Varification(String otp, String txnId) {
        this.otp = otp;
        this.txnId = txnId;
    }

    public Varification()
    {

    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
