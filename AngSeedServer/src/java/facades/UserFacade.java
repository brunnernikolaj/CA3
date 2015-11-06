package facades;

import dao.UserDao;
import entity.User;
import exceptions.RestException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class UserFacade {

    private UserDao DB = new UserDao();
    private StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public UserFacade() {

    }

    public User getUserByUserId(int id) throws RestException {
        return DB.find(id);
    }
    
    public User getUserByUsername(String username){
        return DB.findByUsername(username);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
        DB.create(user);
    }

    /*
     Return the Roles if users could be authenticated, otherwise null
     */
    public List<String> authenticateUser(String userName, String password) {
        User user = DB.findByUsername(userName);

        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user.getRoles();
        } else {
            return null;
        }
    }

}
