package com.castro.sbpay;

public class PaymentResponse {
    private StatusPagamento statusPagamento;
    private String error;

    public PaymentResponse(StatusPagamento statusPagamento){
        this.statusPagamento=statusPagamento;
    }

    public PaymentResponse(String error){
        this.error=error;
    }

    public StatusPagamento getStatusPagamento(){
        return statusPagamento;
    }

    public String getError(){
        return error;
    }

}
