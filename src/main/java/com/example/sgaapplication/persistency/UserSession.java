package com.example.sgaapplication.persistency;

public final class UserSession {
    
    private static UserSession instance;

    private String codigo;

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void leaveInstance() {
        instance = null;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigoNew) {
        codigo = codigoNew;
    }
}
