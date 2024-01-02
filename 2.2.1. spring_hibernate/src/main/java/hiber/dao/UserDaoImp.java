package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;//todo: что подсказывает IDE?

   //todo: слоя dao нет - есть уже слой repository

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);//todo: Session (именно Session) обернуть в try_catch на каждом методе (не только на этом, в смысле на каждом)
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {//todo: наименование переменной - действие, например: getUserList()
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");//todo: ..getUserListQuery (везде по коду правильный codeStyle и naming)
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")//todo: нужно?
   public User getUserByCar(String model, int series) {//todo: переходим с примитивов к ссылочным типам
      String hql = "from User user where user.car.model = :model and user.car.series = :series";//todo: не обязательно выносить в отдельную переменную
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", model).setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }
}