package hiber.repository;

import hiber.model.User;

import java.util.List;

public interface UserRepository {
   void add(User user);
   List<User> getUserList();
   public User getUserByCar(String model, Integer series);

}
