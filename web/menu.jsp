<%-- 
    Document   : manu
    Created on : 04-09-2018, 4:23:18
    Author     : Matias
--%>

<%@page import="Duoc.Portafolio.Clases.TipoUsu"%>
<%@page import="Duoc.Portafolio.Dao.DaoLogin"%>
<%@page import="Duoc.Portafolio.Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    DaoLogin dao = new DaoLogin();

    Usuario u = (Usuario) session.getAttribute("user");

    if (u == null) {
        response.sendRedirect("index.jsp");
    } else {       

        TipoUsu tu = dao.getTipo(u.getTipoUsu());

        switch (tu.getId_tipo_usuario()) {
            case 1:
                response.sendRedirect("administrador/perfil.jsp");
                break;
            case 2:
                response.sendRedirect("arrendatario/perfil.jsp");
                break;
            case 3:
                response.sendRedirect("arrendador/perfil.jsp");
                break;
        }
    }
%>

