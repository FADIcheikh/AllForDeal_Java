/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Saif Eddine
 */
public class CategorieService {

    private int id;
    private String nom;
    private int nbPts;

    public CategorieService() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbPts() {
        return nbPts;
    }

    public void setNbPts(int nbPts) {
        this.nbPts = nbPts;
    }

    public CategorieService(int id, String nom, int nbPts) {
        this.id = id;
        this.nom = nom;
        this.nbPts = nbPts;
    }

}
