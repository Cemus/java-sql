import com.kevin.sql_java.model.User;
import com.kevin.sql_java.repository.UserRepository;

public class Main {
    public static void main (String[] args){
        System.out.println(UserRepository.findAll());
        System.out.println(UserRepository.update(new User("Yvonne","Delapierre","email@truc.fr","1234"),"sdvsdvsv"));
    }
}
