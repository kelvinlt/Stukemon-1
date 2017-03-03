/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.StukemonEJB;
import Entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Y3895917F
 */
@WebServlet(name = "RegisterPoke", urlPatterns = {"/RegisterPoke"})
public class RegisterPoke extends HttpServlet {

    @EJB
    StukemonEJB miEjb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterPoke</title>");
            out.println("<link href=\"bootstrap-3.3.7-dist/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body class=\"container\">");
            out.println("<h1>Servlet RegisterPoke at " + request.getContextPath() + "</h1>");
            out.println("<form action=\"NewPoke\" method=\"GET\">");
             out.println("<label>Name</label>");
             out.println("<input type=\"text\" class=\"form-control\" name=\"name\">");
             out.println("<label>Type</label>");
             out.println("<input type=\"text\" class=\"form-control\" name=\"type\">");
             out.println("<label>Ability</label>");
             out.println("<input type=\"text\" class=\"form-control\" name=\"ability\">");
             out.println("<label>ATK</label>");
             out.println("<input type=\"number\" class=\"form-control\" name=\"attack\">");
             out.println("<label>DEF</label>");
             out.println("<input type=\"number\" class=\"form-control\" name=\"defense\">");
             out.println("<label>SPEED</label>");
             out.println("<input type=\"number\" class=\"form-control\" name=\"speed\">");
             out.println("<label>HP</label>");
             out.println("<input type=\"number\" class=\"form-control\" name=\"life\">");
             out.println("<label>trainer</label>");
             out.println("<select class=\"form-control\" name=\"trainer\">");
             
            try {

               
                List<Trainer> allTrainers = miEjb.selectAllTrainers();

                for (Trainer currentTrainer : allTrainers) {

                    
                    //out.println(currentTrainer.getName());
                    out.println("<option class=\"form-control\" value="+currentTrainer.getName()+">"+currentTrainer.getName()+"</option>");
                }
                
//                Trainer t = allTrainers.get(allTrainers.indexOf(new Trainer(name)))
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Register\" class=\"btn btn-info\">");
             out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
