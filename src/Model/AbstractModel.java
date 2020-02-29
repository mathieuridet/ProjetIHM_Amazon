/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import observer.Observable;
import observer.Observer;

/**
 *
 * @author mathieuridet
 */
public abstract class AbstractModel implements Observable{
    
    private List<Observer> listObserver = new ArrayList<Observer>();   
    
    // Affichage produits
    public abstract ResultSet selectProductInCommand(int IDCommande);
    public abstract ResultSet selectProductByCategory(String cat);
    
    
    //Implémentation du pattern observer
    public void addObserver(Observer obs) {
      this.listObserver.add(obs);
    }

    public void notifyObserver(String str) {
        

        for(Observer obs : listObserver)
            obs.update(str);
    }

    public void removeObserver() {
      listObserver = new ArrayList<Observer>();
    }  
    
}
