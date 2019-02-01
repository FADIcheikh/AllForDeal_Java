/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Achref
 */
public class Produit {

    private int id;
    private String titre;
    private CategorieProduit categorie;
    private String marque;
    private float prix;
    private Date dateFin;
    private int estValide;
    private int quantite;
    private String pict;
    private String description;
    private int estAchetee;
    private Client client;

    public Produit() {
    }

    public Produit(int id, String titre, CategorieProduit categorie, String marque, float prix, Date dateFin, int estValide, int quantite, String pict, String description, int estAchetee, Client client) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.marque = marque;
        this.prix = prix;
        this.dateFin = dateFin;
        this.estValide = estValide;
        this.quantite = quantite;
        this.pict = pict;
        this.description = description;
        this.estAchetee = estAchetee;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public CategorieProduit getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieProduit categorie) {
        this.categorie = categorie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getEstValide() {
        return estValide;
    }

    public void setEstValide(int estValide) {
        this.estValide = estValide;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstAchetee() {
        return estAchetee;
    }

    public void setEstAchetee(int estAchetee) {
        this.estAchetee = estAchetee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
