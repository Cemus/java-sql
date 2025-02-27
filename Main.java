import com.kevin.sql_java.model.Category;
import com.kevin.sql_java.model.User;
import com.kevin.sql_java.repository.CategoryRepository;
import com.kevin.sql_java.repository.RolesRepository;
import com.kevin.sql_java.repository.TaskRepository;
import com.kevin.sql_java.repository.UserRepository;

import java.sql.Date;

import static java.lang.String.valueOf;

public class Main {
    public static void main (String[] args){
        System.out.println(TaskRepository.findAll());


        System.out.println(TaskRepository.findAll());

    }
}
