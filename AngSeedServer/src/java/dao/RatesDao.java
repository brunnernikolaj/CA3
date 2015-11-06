/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ExchangeRate;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nikolaj
 */
public class RatesDao extends CrudDao<ExchangeRate, Integer>{
    
    public List<ExchangeRate> getAllRates(){
        Query query = manager.createQuery("SELECT r FROM ExchangeRate r ORDER BY r.date");
        query.setMaxResults(33);
        return query.getResultList();
    }
    
     public ExchangeRate getByCode(String code){
        Query query = manager.createQuery("SELECT r FROM ExchangeRate r WHERE r.code = :code ORDER BY r.date");
        query.setParameter("code",code);
        query.setMaxResults(1);
        return (ExchangeRate)query.getSingleResult();
        
    }
}
