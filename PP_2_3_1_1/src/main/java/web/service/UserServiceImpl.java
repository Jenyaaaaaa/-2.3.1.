package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import web.DAO.UserDAO;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @PersistenceContext
    private EntityManager entityManager;
   private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getSingleUserById(Long id) {
        return userDao.getSingleUserById(id);
    }

    @Override
    public void update(User user){
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }


}