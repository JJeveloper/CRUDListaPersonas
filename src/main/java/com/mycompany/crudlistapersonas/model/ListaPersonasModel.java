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

        String query = "SELECT * FROM mock_data";
        PreparedStatement ps;
        ResultSet rs;

        ArrayList<Personas> listaPersonas = new ArrayList<>();

        try {
            ps = conectar().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                listaPersonas.add(new Personas(rs.getInt("id") , rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("phone")));
            }

        } catch (SQLException e) {

        }

        return listaPersonas;
    }

}
