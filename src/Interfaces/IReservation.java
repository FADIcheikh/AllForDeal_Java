/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Reservation;

/**
 *
 * @author macbookpro
 */
public interface IReservation {
    public void reserver(Reservation r);
    public void supprimerReservation (Reservation r);
    
}
