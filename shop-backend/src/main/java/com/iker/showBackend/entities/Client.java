package com.iker.showBackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client_name;
    private String client_surname;
    private String client_password;
    private String client_phone;
    private String client_email;

    public Client(){}

    public Client(String client_name, String client_surname, String client_password, String client_phone, String client_email) {
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.client_password = client_password;
        this.client_phone = client_phone;
        this.client_email = client_email;
    }

    public Long getId() {
        return id;
    }

    public String getClient_password() {
        return client_password;
    }

    public void setClient_password(String client_password) {
        this.client_password = client_password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }
}
