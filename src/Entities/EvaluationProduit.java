/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Maynoo
 */
public class EvaluationProduit {
    
    private int id,note;
    private Client client;
    private Produit produit;

    public EvaluationProduit() {
    }

    public EvaluationProduit(int id, int note, Client client, Produit produit) {
        this.id = id;
        this.note = note;
        this.client = client;
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    
    
}
