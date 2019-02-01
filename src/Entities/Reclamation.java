/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author macbookpro
 */
public class Reclamation {
    
    private int id_reclamation,id_client;
    private String titre,etat,description;
    private Date date;

    public Reclamation() {
    }

    
    
    public Reclamation(int id_reclamation, int id_client, String titre, String etat, String description,Date date) {
        this.id_reclamation = id_reclamation;
        this.id_client = id_client;
        this.titre = titre;
        this.etat = etat;
        this.description = description;
        this.date = date;
    }

    
    
    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
