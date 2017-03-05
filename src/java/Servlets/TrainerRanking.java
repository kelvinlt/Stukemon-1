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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alfredo
 */
public class TrainerRanking extends HttpServlet {

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
            out.println("<title>Servlet Ranking Trainer</title>");
            out.println("<link href=\"bootstrap-3.3.7-dist/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body class=\"container-fluid\">");
            out.println("<h1>Servlet Ranking Trainer at " + request.getContextPath() + "</h1>");
            List<Trainer> allTrainer = miEjb.getTrainerRanking();
            out.println("<div class=\"row\">");
            out.println("<div class=\"col-lg-4\">");
            out.println("<label>Trainer</label>");
            out.println("</div>");
            out.println("<div class=\"col-lg-4\">");
            out.println("<label>Points</label>");
            out.println("</div>");
            out.println("<div class=\"col-lg-4\">");
            out.println("<label>Potions</label>");
            out.println("</div>");
            out.println("</div>");
            out.println("<hr>");
            out.println("<div class=\"row\">");
            for (Trainer currentTrainer: allTrainer) {
                out.println("<form action=\"DeletePokemon\" method=\"GET\">");

                out.println("<div class=\"col-lg-4\">");
                //out.println(currentTrainer.getName());
                //out.println(currentPoke.toString());
                out.println("<label>" + currentTrainer.getName() + "</label>");
                out.println("</div>");
                out.println("<div class=\"col-lg-4\">");
                //out.println(currentTrainer.getName());
                //out.println(currentPoke.toString());
                out.println("<label>" + currentTrainer.getPoints()+"</label>");
                out.println("</div>");
                out.println("<div class=\"col-lg-4\">");
                out.println("<label>" + currentTrainer.getPotions()+ "</label>");
                //out.println(currentTrainer.getName());
                //out.println(currentPoke.toString());
                //out.println("<input type=\"submit\" value=\"Delete\" class=\"btn btn-info\">");
                //out.println("<input type=\"hidden\" class=\"form-control\" name=\"name\" value=\"" + currentPoke.getName() + "\">");

                out.println("</div>");
                out.println("</form>");
            }

            out.println("</div>");

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
