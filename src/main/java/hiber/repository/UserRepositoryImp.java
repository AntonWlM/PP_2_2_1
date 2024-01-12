package hiber.repository;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {

   private final SessionFactory sessionFactory;



   @Autowired
   public UserRepositoryImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      Session session = null;
      try {
         session = sessionFactory.openSession();
         session.save(user);
      } catch (Exception e) {
         throw new RuntimeException("Session Error");
      }
      finally {
         if (session!=null) session.close();
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserList() {
      Session session = null;
      try {
         session = sessionFactory.openSession();
         TypedQuery<User> getUserListQuery = session.createQuery("from User");
         return getUserListQuery.getResultList();
      } catch (Exception e) {
         throw new RuntimeException("Session Error");
      }
      finally {
         if (session!=null)
            session.close();
      }
   }

   @Override
   public User getUserByCar(String model, Integer series) {
      Session session = null;
      try {
         session = sessionFactory.openSession();
         TypedQuery<User> getUserByCarQuery = session.createQuery("from User user where user.car.model = :model and user.car.series = :series");
         getUserByCarQuery.setParameter("model", model).setParameter("series", series);
         return getUserByCarQuery.setMaxResults(1).getSingleResult();
      } catch (Exception e) {
         throw new RuntimeException("Ошбика сессии или авто не найден");
      }
      finally {
         if (session!=null)
            session.close();
      }
   }
}