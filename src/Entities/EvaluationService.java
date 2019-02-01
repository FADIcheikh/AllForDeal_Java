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
public class EvaluationService {

    private int id, note;
    private Client client;
    private Service service;

    public EvaluationService() {
    }

    public EvaluationService(int id, int note, Client client, Service service) {
        this.id = id;
        this.note = note;
        this.client = client;
        this.service = service;
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

    public Service getProduit() {
        return service;
    }

    public void setProduit(Service service) {
        this.service = service;
    }

}
