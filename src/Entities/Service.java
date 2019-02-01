/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author AZIZ
 */
public class Service {

    private int id;
    private CategorieService categorie;
    private Date date_fin;
    private Client client;
    private String titre;
    private String description;
    private CategorieService competence;
    private String imgServ;

    public Service() {
    }

    public Service(int id, CategorieService categorie, Date date_fin, Client client, String titre, String description, CategorieService competence, String imgServ) {
        this.id = id;
        this.categorie = categorie;
        this.date_fin = date_fin;
        this.client = client;
        this.titre = titre;
        this.description = description;
        this.competence = competence;
        this.imgServ = imgServ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategorieService getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieService categorie) {
        this.categorie = categorie;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategorieService getCompetence() {
        return competence;
    }

    public void setCompetence(CategorieService competence) {
        this.competence = competence;
    }

    public String getImgServ() {
        return imgServ;
    }

    public void setImgServ(String imgServ) {
        this.imgServ = imgServ;
    }

}
