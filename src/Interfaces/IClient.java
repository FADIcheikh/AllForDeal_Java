/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Client;

/**
 *
 * @author macbookpro
 */
public interface IClient {
    void ajouterClient(Client client);
    void modifierClient(Client client);
    void supprimerClient(int id_personne);
    
//     void chercherClientBynom(String nom );
//    void chercherClientBymail(String mail );
//    void AffichageClient();
    
    
}
