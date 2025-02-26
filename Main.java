import com.kevin.sql_java.repository.UserRepository;

public class Main {
    public static void main (String[] args){
        System.out.println(UserRepository.findAll());
    }
}
