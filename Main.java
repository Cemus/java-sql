import com.kevin.sql_java.model.User;
import com.kevin.sql_java.repository.UserRepository;

public class Main {
    public static void main (String[] args){
        UserRepository.save("Yvonne", "")
        System.out.println(UserRepository.update(new User("Yvonne","Delapierre","email@truc.fr","1234"),"sdvsdvsv"));
    }
}
