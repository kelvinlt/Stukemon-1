/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Alfredo
 */
public class BattleDTO {

    private int wins = 0;
    private String pokemon = "";

    public BattleDTO(int wins, String pokemon) {
        this.wins = wins;
        this.pokemon = pokemon;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "BattleDTO{" + "wins=" + wins + ", pokemon=" + pokemon + '}';
    }
    

}
