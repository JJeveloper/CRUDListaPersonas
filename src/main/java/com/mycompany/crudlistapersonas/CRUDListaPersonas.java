/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.crudlistapersonas;

import com.mycompany.crudlistapersonas.coneccion.Coneccion;
import com.mycompany.crudlistapersonas.controller.ListaPersonasController;
import com.mycompany.crudlistapersonas.model.ListaPersonasModel;
import com.mycompany.crudlistapersonas.view.Principal;
import java.util.ArrayList;

/**
 *
 * @author JJAB
 */
public class CRUDListaPersonas {

    public static void main(String[] args) {
        
        Principal view = new Principal();
        
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        
        ListaPersonasModel model = new ListaPersonasModel();
        
                
        ListaPersonasController  controller = new ListaPersonasController(view, model);
        
        

    }
}
