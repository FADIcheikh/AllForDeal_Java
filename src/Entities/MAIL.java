/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author FADI
 */
public class MAIL {
   String mail_admin;
   String mail_client;
    String password;
    String subject ;
    String contenu;

    public MAIL() {
    }

    public MAIL(String mail_admin, String mail_client, String password, String subject, String contenu) {
        this.mail_admin = mail_admin;
        this.mail_client = mail_client;
        this.password = password;
        this.subject = subject;
        this.contenu = contenu;
    }

    public String getMail_admin() {
        return mail_admin;
    }

    public String getMail_client() {
        return mail_client;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

    public String getContenu() {
        return contenu;
    }

    public void setMail_admin(String mail_admin) {
        this.mail_admin = mail_admin;
    }

    public void setMail_client(String mail_client) {
        this.mail_client = mail_client;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MAIL other = (MAIL) obj;
        if (!Objects.equals(this.mail_admin, other.mail_admin)) {
            return false;
        }
        if (!Objects.equals(this.mail_client, other.mail_client)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MAIL{" + "mail_admin=" + mail_admin + ", mail_client=" + mail_client + ", password=" + password + ", subject=" + subject + ", contenu=" + contenu + '}';
    }
    
    
}