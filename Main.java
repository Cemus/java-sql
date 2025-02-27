import com.kevin.sql_java.model.User;
import com.kevin.sql_java.repository.UserRepository;

public class Main {
    public static void main (String[] args){
        UserRepository.save(new User("Yvonne", "Dupont", "email@mail.com", "motdepasse"));
        System.out.println(UserRepository.findAll());
    }
}
