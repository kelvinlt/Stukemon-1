/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.StukemonEJB;
import Entities.Pokemon;
import Entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Y3895917F
 */
public class NewPoke extends HttpServlet {

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
            out.println("<title>Servlet NewPoke</title>");
            out.println("<link href=\"bootstrap-3.3.7-dist/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewPoke at " + request.getContextPath() + "</h1>");

            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String ability = request.getParameter("ability");
            Integer atk = Integer.parseInt(request.getParameter("attack"));
            Integer def = Integer.parseInt(request.getParameter("defense"));
            Integer spd = Integer.parseInt(request.getParameter("speed"));
            Integer hp = Integer.parseInt(request.getParameter("life"));
            String trainer = request.getParameter("trainer");

            Pokemon p = new Pokemon(name, type, ability, atk, def, spd, hp, 0);
            Trainer t = miEjb.findTrainer(trainer);
            p.setTrainer(t);
            if (miEjb.hasSixPokemon(miEjb.findTrainer(t.getName()))) {

                //out.println(miEjb.hasSixPokemon(miEjb.findTrainer(t.getName())));
                if (miEjb.insertPoke(p)) {
                    out.println("<h1 class=\"bg-success\">New Pokemon created!</h1>");
                } else {
                    out.println("<h1 class=\"bg-danger\">That Pokemon already exists!</h1>");
                }
            }else{
                out.println("<h1 class=\"bg-danger\">You already have 6 pokemons!</h1>");
            }
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
