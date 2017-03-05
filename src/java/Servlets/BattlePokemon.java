/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.StukemonEJB;
import Entities.Battle;
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
 * @author Alfredo
 */
public class BattlePokemon extends HttpServlet {

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
            out.println("<title>Servlet BattlePokemon</title>");
            out.println("<link href=\"bootstrap-3.3.7-dist/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body class=\"container-fluid\">");
            out.println("<h1>Servlet BattlePokemon at " + request.getContextPath() + "</h1>");
            Pokemon pOne = miEjb.findPokemon(request.getParameter("pokemonOne"));
            Pokemon pTwo = miEjb.findPokemon(request.getParameter("pokemonTwo"));

            if (pOne.getLife() <= 0 || pTwo.getLife() <= 0) {
                out.println("<h1 class=\"bg-danger\">One pokemon has already fainted!</h1>");
            } else {

                out.println("<div class=\"row\">");
                out.println("<div class=\"col-lg-6\">");
                out.println("<h2>" + pOne.getName() + "</h2>");
                out.println("<hr>");
                out.println("<ul>ATK:" + pOne.getAttack() + "</ul>");
                out.println("<ul>DEF:" + pOne.getDefense() + "</ul>");
                out.println("<ul>ABL:" + pOne.getAbility() + "</ul>");
                out.println("<ul>TYP:" + pOne.getType() + "</ul>");
                out.println("<ul>LVL:" + pOne.getLevel() + "</ul>");
                out.println("<ul>HP:" + pOne.getLife() + "</ul>");
                out.println("<ul>SPD:" + pOne.getSpeed() + "</ul>");
                out.println("</div>");
                out.println("<div class=\"col-lg-6\">");
                out.println("<h2>" + pTwo.getName() + "</h2>");
                out.println("<hr>");
                out.println("<ul>ATK:" + pTwo.getAttack() + "</ul>");
                out.println("<ul>DEF:" + pTwo.getDefense() + "</ul>");
                out.println("<ul>ABL:" + pTwo.getAbility() + "</ul>");
                out.println("<ul>TYP:" + pTwo.getType() + "</ul>");
                out.println("<ul>LVL:" + pTwo.getLevel() + "</ul>");
                out.println("<ul>HP:" + pTwo.getLife() + "</ul>");
                out.println("<ul>SPD:" + pTwo.getSpeed() + "</ul>");
                out.println("</div>");
                out.println("<div>");
                out.println("</body>");
                out.println("</html>");
                if (pOne.getSpeed() > pTwo.getSpeed()) {
                    //pOne attacks firts..
                    while (pokeDead(pOne, pTwo)) {
                        out.println("<p>" + pOne.getName() + " attacks! </p>");
                        turn(pOne, pTwo);
                        out.println("<p>" + pTwo.getName() + " gets hit! its hp is now: " + pTwo.getLife() + "</p>");
                        out.println("<p>" + pTwo.getName() + " attacks! </p>");
                        turn(pTwo, pOne);
                        out.println("<p>" + pOne.getName() + " gets hit! its hp is now: " + pOne.getLife() + "</p>");
                        String winner = testWin(pOne, pTwo);
                        if (winner != null) {

                            out.println("<h1 class=\"bg-success\">Winner: " + winner + "!</h1>");
                        }
                    }
                    //updateTrainers(pOne, pTwo);
                } else if (pOne.getSpeed() < pTwo.getSpeed()) {
                    //pTwo attacks firts..
                    while (pokeDead(pOne, pTwo)) {
                        out.println("<p>" + pTwo.getName() + " attacks! </p>");
                        turn(pTwo, pOne);
                        out.println("<p>" + pOne.getName() + " gets hit! its hp is now: " + pOne.getLife() + "</p>");
                        out.println("<p>" + pOne.getName() + " attacks! </p>");
                        turn(pOne, pTwo);
                        out.println("<p>" + pTwo.getName() + " gets hit! its hp is now: " + pTwo.getLife() + "</p>");
                        String winner = testWin(pOne, pTwo);
                        if (winner != null) {

                            out.println("<h1 class=\"bg-success\">Winner: " + winner + "!</h1>");
                        }
                    }
                    //updateTrainers(pOne, pTwo);
                } else if (pOne.getSpeed() == pTwo.getSpeed()) {
                    //random attacks firts..
                    int range = (1 - 0) + 1;
                    int turn = (int) (Math.random() * range) + 0;
                    if (turn == 1) {
                        while (pokeDead(pOne, pTwo)) {
                            out.println("<p>" + pOne.getName() + " attacks! </p>");
                            turn(pOne, pTwo);
                            out.println("<p>" + pTwo.getName() + " gets hit! its hp is now: " + pTwo.getLife() + "</p>");
                            out.println("<p>" + pTwo.getName() + " attacks! </p>");
                            turn(pTwo, pOne);
                            out.println("<p>" + pOne.getName() + " gets hit! its hp is now: " + pOne.getLife() + "</p>");
                            String winner = testWin(pOne, pTwo);
                            if (winner != null) {

                                out.println("<h1 class=\"bg-success\">Winner: " + winner + "!</h1>");
                            }
                        }
                       // updateTrainers(pOne, pTwo);
                    } else {
                        while (pokeDead(pOne, pTwo)) {
                            out.println("<p>" + pTwo.getName() + " attacks! </p>");
                            turn(pTwo, pOne);
                            out.println("<p>" + pOne.getName() + " gets hit! its hp is now: " + pOne.getLife() + "</p>");
                            out.println("<p>" + pOne.getName() + " attacks! </p>");
                            turn(pOne, pTwo);
                            out.println("<p>" + pTwo.getName() + " gets hit! its hp is now: " + pTwo.getLife() + "</p>");
                            String winner = testWin(pOne, pTwo);
                            if (winner != null) {

                                out.println("<h1 class=\"bg-success\">Winner: " + winner + "!</h1>");
                            }

                        }
                        //updateTrainers(pOne, pTwo);
                    }
                }

            }
        }
    }

    public void turn(Pokemon pokeHitting, Pokemon pokeDeffending) {
        int atk = ((pokeHitting.getAttack() + (2 * pokeHitting.getLevel())) - (pokeDeffending.getDefense()));
        if(atk<=0){
            atk = 1;
        }
        pokeDeffending.setLife(pokeDeffending.getLife() - atk);
        if (pokeDeffending.getLife() < 0) {
            pokeDeffending.setLife(0);
        }
        miEjb.updatePoke(pokeDeffending);
    }

    public void updateTrainers(Pokemon pOne, Pokemon pTwo) {
        //winner
        Trainer winnerTrainer = miEjb.findTrainer(pOne.getTrainer().getName());
        winnerTrainer.setPoints(winnerTrainer.getPoints() + 10);
        miEjb.updateTrainer(winnerTrainer);
        pOne.setLevel(pOne.getLevel()+1);
        miEjb.updatePoke(pOne);
        //Loser
        Trainer loserTrainer = miEjb.findTrainer(pTwo.getTrainer().getName());
        loserTrainer.setPoints(loserTrainer.getPoints() + 1);
        miEjb.updateTrainer(loserTrainer);
        //Battle bat = new Battle();
    }

    public boolean pokeDead(Pokemon pokeHitting, Pokemon pokeDeffending) {
        if (pokeHitting.getLife() == 0 || pokeDeffending.getLife() == 0) {
            return false;
        }
        return true;
    }

    public String testWin(Pokemon pokeHitting, Pokemon pokeDeffending) {
        if (pokeHitting.getLife() == 0) {
            //winner    loser
            updateTrainers(pokeDeffending, pokeHitting);
            Battle bat = new Battle();
            bat.setPokemon1(pokeDeffending);
            bat.setPokemon2(pokeHitting);
            bat.setWinner(pokeDeffending);
            miEjb.insertBattle(bat);
            return pokeDeffending.getName();
        } else if (pokeDeffending.getLife() == 0) {
            updateTrainers(pokeHitting, pokeDeffending);
            Battle bat = new Battle();
            bat.setPokemon1(pokeDeffending);
            bat.setPokemon2(pokeHitting);
            bat.setWinner(pokeHitting);
            miEjb.insertBattle(bat);
            return pokeHitting.getName();
        }

        return null;
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
