/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Dominio.Cliente;
import datos.ClienteDaoJDBC;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
                String accion = request.getParameter("accion");
        if(accion != null){
            switch(accion){
                case "insertar":   
                    insertarCliente(request, response);
                    break;
                case "modificar":
                    modificarCliente(request,response);
                    break;
                case "eliminar": 
                    eliminarCliente(request, response);
                    break;
                    default: accionDefault(request, response); 
            }
        }else{
            accionDefault(request, response);
        }
    }
    
    private double calcularSaldoTotal(List<Cliente> clientes){
        double saldoTotal = 0;
        for(Cliente cliente: clientes){
            saldoTotal += cliente.getSaldo();
                
        }
        return saldoTotal;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion != null){
            switch(accion){
                case "editar":   
                    editarCliente(request, response);
                    break;
                    default: accionDefault(request, response); 
            }
        }else{
            accionDefault(request, response);
        }
    }
    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", calcularSaldoTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response); 
        response.sendRedirect("clientes.jsp");
    }
    protected void insertarCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if(saldoString != null && !"".equals(saldoString)){
            saldo = Double.parseDouble(saldoString);
        }
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
         int registroModificados  = new ClienteDaoJDBC().insertar(cliente);
         accionDefault(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
        
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if(saldoString != null && !"".equals(saldoString)){
            saldo = Double.parseDouble(saldoString);
        }
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
         int registroModificados  = new ClienteDaoJDBC().actualizar(cliente);
         accionDefault(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int idCliente = Integer.parseInt(request.getParameter("idCliente"));
      
        Cliente cliente = new Cliente(idCliente);
         int registroModificados  = new ClienteDaoJDBC().eliminar(cliente);
         accionDefault(request, response);
    }
    
    
    
}