/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import Dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Felipe Barrera
 */
public class ClienteDaoJDBC {
    private static final String SQL_SELECT = "select * from cliente";
    private static final String SQL_SELECT_BY_ID = "select * from cliente where id_cliente = ?";
    private static final String SQL_INSERT = "insert into cliente (nombre, apellido, email, telefono, saldo) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update cliente set nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? where id_cliente = ?";
    private static final String SQL_DELETE = "delete from cliente where id_cliente = ?";
    
    public List<Cliente> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            res = stmt.executeQuery();
            while(res.next()){
                int idCliente = res.getInt("id_cliente");
                String nombre = res.getString("nombre");
                String apellido = res.getString("apellido");
                String email = res.getString("email");
                String telefono = res.getString("telefono");
                double saldo = res.getDouble("saldo");
                
                cliente = new Cliente(idCliente,nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        finally{
            Conexion.close(res);
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return clientes;
    }
    public Cliente encontrar (Cliente cliente){
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        
       
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            res = stmt.executeQuery();
            res.absolute(1);
               
                String nombre = res.getString("nombre");
                String apellido = res.getString("apellido");
                String email = res.getString("email");
                String telefono = res.getString("telefono");
                double saldo = res.getDouble("saldo");
                
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEmail(email);
                cliente.setTelefono(telefono);
                cliente.setSaldo(saldo);;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        finally{
            Conexion.close(res);
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return cliente;
    }
    public int insertar (Cliente cliente){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
       
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            
              stmt.executeQuery();
   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        finally{
           
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return rows;
    }
    public int actualizar (Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
       
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());
            
              stmt.executeQuery();
   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        finally{
           
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return rows;  
    }
    public int eliminar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
       
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            
              stmt.executeQuery();
   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        finally{
           
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return rows;
    }
}
