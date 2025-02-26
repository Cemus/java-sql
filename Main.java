import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.User;
import com.kevin.sql_java.repository.UserRepository;;

public class Main {
    public static void main (String[] args){
        User newUser = new User("Jean", "Paul","jean-paul@hotmail.fr","1234");
        System.out.println("isExist :");
        System.out.println(UserRepository.isExist("jean-paul@hotail.fr"));
        System.out.println("findByEmail :");
        System.out.println(UserRepository.findByEmail("jean-paul@hotmail.fr").toString());
    }
}
