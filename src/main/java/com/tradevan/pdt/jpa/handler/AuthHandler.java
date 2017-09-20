package com.tradevan.pdt.jpa.handler;

import java.util.Properties;


public interface  AuthHandler{

    public void init(Properties paramProperties);

    public String getId();
    
    public String getPassword();
    
}