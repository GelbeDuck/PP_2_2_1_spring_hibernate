package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Rubens", "Barrichello", "rb@f1.com", new Car("Ferrari", 123)));
      userService.add(new User("Mika", "Häkkinen", "mh@f1.com", new Car("Mercedes", 456)));
      userService.add(new User("David", "Coulthard", "dc@f1.com", new Car("BMW", 789)));
      userService.add(new User("Fernando", "Alonso", "fa@f1.com", new Car("Renault", 900)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
/** тест */
//      System.out.println(userService.getUserByModelAndSeries("Ferrari", 123));
//      System.out.println(userService.getUserByModelAndSeries("Mercedes", 456));
//      System.out.println(userService.getUserByModelAndSeries("BMW", 789));
//      System.out.println(userService.getUserByModelAndSeries("Renault", 900));
/** Красивый вывод */
      List<User> userCar = userService.getUserByModelAndSeries("BMW", 789);
      for (User user : userCar) {
         System.out.println("Машиной " + user.getUserCar().getModel() + " " + user.getUserCar().getSeries() + " владеет пользователь:");
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
      }

      context.close();
   }
}
