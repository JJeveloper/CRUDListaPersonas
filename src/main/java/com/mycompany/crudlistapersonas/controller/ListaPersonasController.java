/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudlistapersonas.controller;

import com.mycompany.crudlistapersonas.model.ListaPersonasModel;
import com.mycompany.crudlistapersonas.model.Personas;
import com.mycompany.crudlistapersonas.view.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JJAB
 */
public class ListaPersonasController implements ActionListener {

    Principal principal;
    ListaPersonasModel listaModel;

    public ListaPersonasController(Principal principal, ListaPersonasModel listaModel) {
        this.principal = principal;
        this.listaModel = listaModel;
        cargarTabla();
    }

    private void cargarTabla() {

        DefaultTableModel tableModel;
        //
        tableModel = (DefaultTableModel) principal.tblPersonas.getModel();

        ArrayList<Personas> list = listaModel.tablaPersona();

        if (!list.isEmpty()) {
            
            for (Personas p : list) {
                tableModel.addRow(new Object[]{p.getId(), p.getFirst_name(), p.getLast_name(), p.getEmail(), p.getGender(), p.getPhone()});
            }

            principal.tblPersonas.setModel(tableModel);
            
        }else{
            
            JOptionPane.showMessageDialog(principal, "No hay elementos en la base de datos");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
