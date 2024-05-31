/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudlistapersonas.model;

import com.mycompany.crudlistapersonas.coneccion.Coneccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JJAB
 */
public class ListaPersonasModel extends Coneccion {

    public ArrayList tablaPersona() {

        String query = "SELECT * FROM mock_data;";
        PreparedStatement ps;
        ResultSet rs;

        ArrayList<Personas> listaPersonas = new ArrayList<>();

        try {
            ps = conectar().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                listaPersonas.add(new Personas(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("phone")));
            }

        } catch (SQLException e) {

        }

        return listaPersonas;
    }

    public boolean guardarPersona(Personas p) {
        String query = "INSERT INTO mock_data (first_name, last_name, email, gender, phone) VALUES (?,?,?,?,?);";
        PreparedStatement ps;

        try {
            ps = conectar().prepareStatement(query);
            ps.setString(1, p.getFirst_name());
            ps.setString(2, p.getLast_name());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getPhone());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean actualizarPersona(Personas p) {
        String query = "UPDATE mock_data  SET first_name = ?, last_name = ?, email = ?, gender = ?, phone = ? WHERE id = ?;";
        PreparedStatement ps;

        try {
            ps = conectar().prepareStatement(query);
            ps.setString(1, p.getFirst_name());
            ps.setString(2, p.getLast_name());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getPhone());
            ps.setInt(6, p.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("ff " + e);
            return false;
        }

    }

    public boolean eliminarPersona(int id) {
        String query = "DELETE FROM mock_data WHERE id = ?;";
        PreparedStatement ps;

        try {
            ps = conectar().prepareStatement(query);
            ps.setInt(1, id);
            
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
