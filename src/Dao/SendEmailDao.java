/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.MAIL;
import Interfaces.IMail;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author FADI
 */
public class SendEmailDao implements IMail{
  
    
public void send2_admin(MAIL mail) {

    
    
    


final String username = mail.getMail_client();
 final String password = mail.getPassword();

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
//Auth
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication( username, password);
    }                            
});
// Creer 1ere partie (destinataire + contenu + sujet)
    try {
        
        Message message = new MimeMessage(session);
        //mail depuis lequel envoyer
        message.setFrom(new InternetAddress(mail.getMail_client()));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("AllForDeal123@gmail.com"));//@ verslaquelle envoer mail 
        message.setSubject(mail.getSubject());    
        message.setText("PFA");
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        messageBodyPart.setText(mail.getContenu());
        multipart.addBodyPart(messageBodyPart);


    
    
        message.setContent(multipart);

       
        System.out.println("sending");
        Transport.send(message);
        System.out.println("Done");
        
   
  
            
}catch (MessagingException e) {
        e.printStackTrace();
    }
  }
public void send2_client(MAIL mail) {

    
    
    


final String username = "AllForDeal123@gmail.com";
 final String password = "allallall";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
//Auth
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication( username, password);
    }                            
});
// Creer 1ere partie (destinataire + contenu + sujet)
    try {
        
        Message message = new MimeMessage(session);
        //mail depuis lequel envoyer
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(mail.getMail_client()));//@ verslaquelle envoer mail 
        message.setSubject(mail.getSubject());    
        message.setText("PFA");
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        messageBodyPart.setText(mail.getContenu());
        multipart.addBodyPart(messageBodyPart);


    
    
        message.setContent(multipart);

       
        System.out.println("sending");
        Transport.send(message);
        System.out.println("Done");
        
   
   //attacher un fichier ( 2eme bodypart)----------------------
        String file = "path of file";
        String fileName = "AnyName1";
    messageBodyPart = new MimeBodyPart();   
    DataSource source = new FileDataSource(file);      
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(fileName);
    multipart.addBodyPart(messageBodyPart);
    //----------------------------------------------------------
            
}catch (MessagingException e) {
        e.printStackTrace();
    }
  }

}

