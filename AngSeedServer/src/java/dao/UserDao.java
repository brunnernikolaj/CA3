/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import javax.persistence.Query;

/**
 *
 * @author Nikolaj
 */
public class UserDao extends CrudDao<User, Integer>{
    
    public User findByUsername(String username){
        Query query = manager.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
        query.setParameter("userName", username);
        return (User) query.getSingleResult();
    }
}
