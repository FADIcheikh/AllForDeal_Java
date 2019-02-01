/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Reclamation;
import java.util.List;


/**
 *
 * @author macbookpro
 */
public interface IReclamation {
    public void AjouterReclamation(Reclamation r);
    
    public void ModifierReclamation(Reclamation r);
    
    public void SupprimerReclamation(Reclamation r);
    
    public List<Reclamation> findAll();

    public Reclamation findOne(int id) ;
    
}
