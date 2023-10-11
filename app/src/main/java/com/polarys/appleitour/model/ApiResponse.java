package com.polarys.appleitour.model;

public class ApiResponse {

    private int code = 503;
    private String body = "Verifique a conexão com a rede.";
    public ApiResponse(){
    }

    public ApiResponse(int code, String body){
        this.code = code;
        this.body = body;
    }
    public int getCode(){return this.code;}
    public String getBody(){return this.body;}
}
