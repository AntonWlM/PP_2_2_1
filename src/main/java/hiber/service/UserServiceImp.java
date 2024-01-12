package hiber.service;

import hiber.repository.UserRepository;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
   @Autowired
   private UserRepository userDao;

   public UserServiceImp(UserRepository userDao) {
      this.userDao = userDao;
   }
   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUserList() {
      return userDao.getUserList();
   }
   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(String model, Integer series) {
      return userDao.getUserByCar(model, series);
   }
}
