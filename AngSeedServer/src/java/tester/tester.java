/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import dao.UserDao;
import entity.User;
import exchangerates.ExchangeChecker;
import java.util.Arrays;

/**
 *
 * @author Nikolaj
 */
public class tester {
    public static void main(String[] args) {
        new Thread(new ExchangeChecker()).start();
        
        new Thread(new ExchangeChecker()).start();
    }
}
