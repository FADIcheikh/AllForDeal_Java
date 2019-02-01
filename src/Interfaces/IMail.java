/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.MAIL;

/**
 *
 * @author FADI
 */
public interface IMail {
    
    void send2_admin(MAIL mail);
    void send2_client(MAIL mail);
    
}
