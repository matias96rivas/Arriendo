/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Duoc.Portafolio.Dao;

import Duoc.Portafolio.Clases.TipoUsu;
import Duoc.Portafolio.Clases.Usuario;
import Duoc.Portafolio.Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Duoc
 */
public class DaoLogin {

    Connection cone;

    public DaoLogin() {
        cone = new Conexion().obtenerConexion();
    }

    public Usuario existeUsuario(String user, String pass) {
        String sql = "{call sp_login(?,?,?) }";
        Usuario u = null;
        if (this.cone != null) {
            try {
                CallableStatement cs = cone.prepareCall(sql);
                
                cs.setString(1, user);
                cs.setString(2, pass);
                cs.registerOutParameter(3, OracleTypes.CURSOR);
                
                cs.executeQuery();

                 ResultSet rs = (ResultSet) cs.getObject(3);
                
                
                
                while (rs.next()) {
                    u = new Usuario();

                    u.setId_usuario(rs.getInt(1));
                    u.setNombre(rs.getString(2));
                    u.setPassword(rs.getString(3));
                    u.setEstado(rs.getInt(4));                    
                    u.setTipoUsu(rs.getInt(5));
                }
                cone.close();
                return u;               
                
            } catch (Exception e) {
                System.out.println("Error en el dao: " + e.getMessage());
            } finally {
                try {
                    cone.close();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return u;
    }

    public TipoUsu getTipo(int id){
        try {
            String sql = "SELECT * FROM tipo_usuario WHERE id_tipo_usuario = ?";
            
            PreparedStatement ps = cone.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs =ps.executeQuery();
            TipoUsu tu = null;
            if (rs.next()) {
                tu = new TipoUsu();
                tu.setId_tipo_usuario(id);
                tu.setDescripcion(rs.getString(2));
            }
            cone.close();
            return tu;
        } catch (Exception e) {
            return null;
        }
        
    }
}
