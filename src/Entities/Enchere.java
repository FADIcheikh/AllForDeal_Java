/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Client;
import Entities.Produit;
import java.sql.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Saif Eddine
 */
public class Enchere {
  private int id_enchere; 
  private Produit produit;
  private Date date_enchere;
  private Date date_vente;
  private Client client;
  private int prix;
  private int prix_ajout;

    public Enchere() {
    }

    public Enchere(int id_enchere, Produit produit, Date date_enchere, Date date_vente, Client client, int prix, int prix_ajout) {
        this.id_enchere = id_enchere;
        this.produit = produit;
        this.date_enchere = date_enchere;
        this.date_vente = date_vente;
        this.client = client;
        this.prix = prix;
        this.prix_ajout = prix_ajout;
    }

    public Enchere(int id_enchere, Produit produit, Date date_enchere, Date date_vente, int prix, int prix_ajout) {
        this.id_enchere = id_enchere;
        this.produit = produit;
        this.date_enchere = date_enchere;
        this.date_vente = date_vente;
        this.prix = prix;
        this.prix_ajout = prix_ajout;
    }

    public int getId_enchere() {
        return id_enchere;
    }

    public Produit getProduit() {
        return produit;
    }

    public Date getDate_enchere() {
        return date_enchere;
    }

    public Date getDate_vente() {
        return date_vente;
    }

    public Client getClient() {
        return client;
    }

    public int getPrix() {
        return prix;
    }

    public int getPrix_ajout() {
        return prix_ajout;
    }

    public void setId_enchere(int id_enchere) {
        this.id_enchere = id_enchere;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setDate_enchere(Date date_enchere) {
        this.date_enchere = date_enchere;
    }

    public void setDate_vente(Date date_vente) {
        this.date_vente = date_vente;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setPrix_ajout(int prix_ajout) {
        this.prix_ajout = prix_ajout;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id_enchere;
        hash = 71 * hash + Objects.hashCode(this.produit);
        hash = 71 * hash + Objects.hashCode(this.date_enchere);
        hash = 71 * hash + Objects.hashCode(this.date_vente);
        hash = 71 * hash + Objects.hashCode(this.client);
        hash = 71 * hash + this.prix;
        hash = 71 * hash + this.prix_ajout;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enchere other = (Enchere) obj;
        if (this.id_enchere != other.id_enchere) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (this.prix_ajout != other.prix_ajout) {
            return false;
        }
        if (!Objects.equals(this.date_enchere, other.date_enchere)) {
            return false;
        }
        if (!Objects.equals(this.date_vente, other.date_vente)) {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enchere{" + "id_enchere=" + id_enchere + ", produit=" + produit + ", date_enchere=" + date_enchere + ", date_vente=" + date_vente + ", client=" + client + ", prix=" + prix + ", prix_ajout=" + prix_ajout + '}';
    }
    
  
    
}
