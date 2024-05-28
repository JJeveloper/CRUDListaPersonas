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

    private Principal principal;
    private ListaPersonasModel listaModel;

    private ArrayList<Personas> list;

    public ListaPersonasController(Principal principal, ListaPersonasModel listaModel) {
        this.principal = principal;
        this.listaModel = listaModel;
        cargarEventos();
        cargarComboBox();
        cargarTabla();
    }

    private void cargarEventos() {
        principal.getBtnGuardar().addActionListener(this);
        principal.getBtnSeleccionar().addActionListener(this);
        principal.getBtnEditar().addActionListener(this);
        principal.getBtnEliminar().addActionListener(this);

    }

    private void cargarComboBox() {
        principal.cmbGenero.addItem("Masculino");
        principal.cmbGenero.addItem("Femenino");
    }

    /*
     * Aqui se cargar los datos desde la base de datos de la tabla mock_data
     * en un ArrayList de tipo Personas
     * y se mustran en la vista en el JTable
     */
    private void cargarTabla() {

        String[] titulos = new String[]{"Id", "Nombre", "Apellido", "Correo", "Genero", "Telefono"};

        DefaultTableModel tableModel = new DefaultTableModel(titulos, 0);

        principal.getTblPersonas().setModel(tableModel);

        list = listaModel.tablaPersona();

        if (!list.isEmpty()) {

            for (Personas p : list) {
                tableModel.addRow(new Object[]{p.getId(), p.getFirst_name(), p.getLast_name(), p.getEmail(), p.getGender(), p.getPhone()});
            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println("ffv= " + list.get(i).getId());
            }

//            principal.getTblPersonas().setModel(tableModel);
        } else {
            JOptionPane.showMessageDialog(principal, "No hay elementos en la base de datos");
        }

    }

    /*
     * Validacion Telefono
     * con expresion regular que permite numeros de 0 hasta el 9
     * longitud de 10 numeros que corresponden a un numero de celular
     */
    private boolean validarTelefono(String telefono) {
        return telefono.matches("[0-9]+") && telefono.length() == 10;
    }

    /*
     * Validacion de correo electronico
     * con expresion regular
     * comprueba que la dirección de correo electrónico tenga un usuario
     * (parte antes de @), un dominio (parte después de @),
     * y un dominio de nivel superior (TLD) válido (como .com, .org, .es, etc.
     */
    private boolean validarCorreo(String correo) {
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$");
    }

    private boolean validarCampos() {
        validarTelefono(principal.getTxtTelefono().getText().trim());
        return !principal.getTxtNombre().getText().trim().isEmpty() && !principal.getTxtApellido().getText().trim().isEmpty()
                && !principal.getTxtCorreo().getText().trim().isEmpty()
                && !principal.getTxtTelefono().getText().trim().isEmpty();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == principal.getBtnGuardar()) {

            if (validarCampos()) {

                if (validarTelefono(principal.getTxtTelefono().getText().trim())) {

                    if (validarCorreo(principal.getTxtCorreo().getText().trim())) {

                        String first_name = principal.getTxtNombre().getText().trim();
                        String last_name = principal.getTxtApellido().getText().trim();
                        String email = principal.getTxtCorreo().getText().trim();
                        String phone = principal.getTxtTelefono().getText().trim();
                        String gender = "F";

                        if (principal.cmbGenero.getSelectedIndex() == 0) {
                            gender = "M";
                        }

                        boolean ingresarDatos = listaModel.guardarPersona(new Personas(first_name, last_name, email, gender, phone));

                        if (ingresarDatos) {
                            JOptionPane.showMessageDialog(null, "Datos Ingresados Correctamente", "", JOptionPane.INFORMATION_MESSAGE);

                            cargarTabla();
                        } else {
                            JOptionPane.showMessageDialog(null, "No Se Pueden Guardar Los Datos", "", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Por Favor ingrese un correo correcto", "", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Por Favor ingrese un numero de telefono corecto", "", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Por Favor LLene Todos Los Campos", "", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if (e.getSource() == principal.getBtnSeleccionar()) {
            System.out.println("guardasdgr");
        }
        if (e.getSource() == principal.getBtnEditar()) {
            System.out.println("guardasdgr");
        }
        if (e.getSource() == principal.getBtnEliminar()) {
            System.out.println("guardasdgr");
        }
    }

}
