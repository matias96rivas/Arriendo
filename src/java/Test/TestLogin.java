/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Duoc.Portafolio.Clases.Usuario;
import Duoc.Portafolio.Dao.DaoLogin;

/**
 *
 * @author Duoc
 */
public class TestLogin {
    
    public static void main(String [] args) {
        
        DaoLogin dao = new DaoLogin();
        
        Usuario u = dao.existeUsuario("ariel1997", "12334");
        
        
        if(u!=null) {
            System.out.println("existe " + u.toString());
        }
        else {
            System.out.println("No existe");
        }
        
    }
    
}
