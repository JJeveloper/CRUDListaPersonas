/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudlistapersonas.model;

/**
 *
 * @author JJAB
 */
public class Personas {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String phone;

    public Personas(int id, String first_name, String last_name, String email, String gender, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    public Personas(String first_name, String last_name, String email, String gender, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }
    
    
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Personas{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", gender=" + gender + ", phone=" + phone + '}';
    }
    
    
    
    
    

}
