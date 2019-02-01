/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Panier;
import java.util.List;


/**
 *
 * @author macbookpro
 */
public interface IPanier {
    
    public void AjouterPanier(Panier p);
    
    public void SupprimerPanier(Panier p);
    
    public List<Panier> findAll();

    public List<Panier> findByClient(int id) ;
    
}
