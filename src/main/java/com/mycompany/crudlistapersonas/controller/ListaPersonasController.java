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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JJAB
 */
public class ListaPersonasController implements ActionListener {

    private Principal principal;
    private ListaPersonasModel listaModel;
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String gender = "F";

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
        principal.getBtnLimpiar().addActionListener(this);
        principal.getBtnEditar().addActionListener(this);
        principal.getBtnEliminar().addActionListener(this);

        principal.getBtnEditar().setEnabled(false);
        principal.getBtnEliminar().setEnabled(false);

        principal.getTblPersonas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);

            }
        });
    }

    /*
     * Funcion para llenar los campos JTextField
     * con los datos de la tabla al momento    
     * de dar click en una fila
     */
    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();

        principal.getTxtId().setText(principal.getTblPersonas().getModel().getValueAt(target.getSelectedRow(), 0).toString());

        principal.getTxtNombre().setText(principal.getTblPersonas().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        principal.getTxtApellido().setText(principal.getTblPersonas().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        principal.getTxtCorreo().setText(principal.getTblPersonas().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        principal.getTxtTelefono().setText(principal.getTblPersonas().getModel().getValueAt(target.getSelectedRow(), 5).toString());

        String gender = (String) principal.getTblPersonas().getModel().getValueAt(target.getSelectedRow(), 4);

        if ("M".equals(gender)) {
            principal.cmbGenero.setSelectedIndex(0);
        } else {
            principal.cmbGenero.setSelectedIndex(1);
        }

        principal.getBtnGuardar().setEnabled(false);

        principal.getBtnEditar().setEnabled(true);
        principal.getBtnEliminar().setEnabled(true);
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

    /*
     * Funcion para validar que los campos no esten vacios  
     */
    private boolean validarCampos() {
        validarTelefono(principal.getTxtTelefono().getText().trim());
        return !principal.getTxtNombre().getText().trim().isEmpty() && !principal.getTxtApellido().getText().trim().isEmpty()
                && !principal.getTxtCorreo().getText().trim().isEmpty()
                && !principal.getTxtTelefono().getText().trim().isEmpty();
    }

    /*
     * Cargar los datos de los campos JTextField
     * a las variables locales
     */
    private void cargarDatos() {
        id = Integer.parseInt(principal.getTxtId().getText());
        first_name = principal.getTxtNombre().getText().trim();
        last_name = principal.getTxtApellido().getText().trim();
        email = principal.getTxtCorreo().getText().trim();
        phone = principal.getTxtTelefono().getText().trim();
        gender = "F";

        if (principal.cmbGenero.getSelectedIndex() == 0) {
            gender = "M";
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == principal.getBtnGuardar()) {
            guardarInformacion();
        }
        if (e.getSource() == principal.getBtnLimpiar()) {
            LimpiarCampos();
        }
        if (e.getSource() == principal.getBtnEditar()) {
            editarInformacion();
        }
        if (e.getSource() == principal.getBtnEliminar()) {
            eliminarInformacion();
        }
    }

    private void LimpiarCampos() {
        principal.getTxtId().setText("");
        principal.getTxtNombre().setText("");
        principal.getTxtApellido().setText("");
        principal.getTxtCorreo().setText("");
        principal.getTxtTelefono().setText("");

        principal.getBtnGuardar().setEnabled(true);

        principal.getBtnEditar().setEnabled(false);
        principal.getBtnEliminar().setEnabled(false);
    }

    private void guardarInformacion() {

        if (validarCampos()) {

            if (validarTelefono(principal.getTxtTelefono().getText().trim())) {

                if (validarCorreo(principal.getTxtCorreo().getText().trim())) {

                    cargarDatos();

                    boolean ingresarDatos = listaModel.guardarPersona(new Personas(first_name, last_name, email, gender, phone));

                    if (ingresarDatos) {
                        JOptionPane.showMessageDialog(null, "Datos Ingresados Correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                        cargarTabla();
                        LimpiarCampos();
                        principal.getBtnGuardar().setEnabled(false);
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

    private void editarInformacion() {

        if (validarCampos()) {

            if (validarTelefono(principal.getTxtTelefono().getText().trim())) {

                if (validarCorreo(principal.getTxtCorreo().getText().trim())) {

                    cargarDatos();

                    boolean actualiazarDatos = listaModel.actualizarPersona(new Personas(id, first_name, last_name, email, gender, phone));

                    if (actualiazarDatos) {
                        JOptionPane.showMessageDialog(null, "Actualizacion Correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                        cargarTabla();
                        LimpiarCampos();

                        principal.getBtnGuardar().setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Se Pueden Actualizar Los Datos", "", JOptionPane.ERROR_MESSAGE);
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

    private void eliminarInformacion() {

        if (validarCampos()) {

            if (validarTelefono(principal.getTxtTelefono().getText().trim())) {

                if (validarCorreo(principal.getTxtCorreo().getText().trim())) {

                    cargarDatos();

                    int y = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el Registro?");

                    if (JOptionPane.OK_OPTION == y) {

                        boolean EliminarInformacion = listaModel.eliminarPersona(id);

                        if (EliminarInformacion) {

                            cargarTabla();
                            LimpiarCampos();

                            principal.getBtnGuardar().setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "No Se Puede Eliminar el Registro", "", JOptionPane.ERROR_MESSAGE);
                        }

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
}
