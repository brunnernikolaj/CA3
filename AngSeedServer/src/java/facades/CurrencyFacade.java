/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dao.RatesDao;
import entity.ExchangeRate;
import java.util.List;

/**
 *
 * @author Nikolaj
 */
public class CurrencyFacade {
    private RatesDao DB = new RatesDao();
    
    public List<ExchangeRate> getAll(){
        return DB.getAllRates();
    }
    
    public ExchangeRate getByCode(String code){
        return DB.getByCode(code);
    }
    
    public double convert(double amount, String from, String to){
        ExchangeRate fromRate = DB.getByCode(from);               
        ExchangeRate toRate = DB.getByCode(to);
        
        return (amount * fromRate.getRate()) / toRate.getRate();
    }
}
