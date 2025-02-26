
import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.User;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class Main {
    public static void main (String[] args){
        Bdd.getConnection();
        User user = new User("Jean", "Paul","jean-paul@hotmail.fr","1234");
        System.out.println(user);
        System.out.println(user.getFirstname());
    }
}
