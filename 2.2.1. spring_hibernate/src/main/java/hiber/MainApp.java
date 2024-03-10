package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import javax.persistence.NoResultException;


public class MainApp {
   public static void main(String[] args) throws  NoClassDefFoundError {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Anton", "Krylov", "user1@mail.ru");
      User user2 = new User("Evgeniy", "Sapojnikov", "user2@mail.ru");
      User user3 = new User("Mike", "Tyson", "user3@mail.ru");
      User user4 = new User("Anthony", "Joshua", "user4@mail.ru");

      Car car1 = new Car("tachka", 1);
      Car car2 = new Car("4etyrekolsea", 2);
      Car car3 = new Car("McQueen", 3);
      Car car4 = new Car("Matter", 4);


      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("McQueen", 3));

      try{
         userService.getUserByCar("bmw", 3);
      } catch (NoResultException e){
         System.out.println("Пользователь с такой машиной не найден");
      }


      context.close();
   }
}