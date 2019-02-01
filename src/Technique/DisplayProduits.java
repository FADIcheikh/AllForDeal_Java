/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import Dao.ProduitDao;
import Entities.Produit;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Achref
 */
public class DisplayProduits extends AbstractTableModel{
    List<Produit> lst;
String [] columns = {"Id produit","Titre","Catégorie","Marque","Prix","Date fin","est valide","Quantité","Est acheté","Description"};
    public DisplayProduits() {
        
        ProduitDao dao=new ProduitDao();
        lst=dao.findAll();
        
        
    }



    @Override
    public int getRowCount() {
    return lst.size();
    }

    @Override
    public int getColumnCount() {
    return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
   
    
     switch(columnIndex){
         case 0 :
return lst.get(rowIndex).getId();
case 1 :
return lst.get(rowIndex).getTitre();
case 2:
return lst.get(rowIndex).getCategorie().getNom();
case 3 :
return lst.get(rowIndex).getMarque();
case 4:
return lst.get(rowIndex).getPrix();
case 5:
return lst.get(rowIndex).getDateFin();
case 6:
    return lst.get(rowIndex).getEstValide();
case 7:
    return lst.get(rowIndex).getQuantite();
    case 8:
    return lst.get(rowIndex).getEstAchetee();
        case 9:
    return lst.get(rowIndex).getPict();
             case 10:
    return lst.get(rowIndex).getDescription();
                
default: return null;}
    
    
    
    
    }
      @Override
    public String getColumnName(int i){return columns[i];}
}
