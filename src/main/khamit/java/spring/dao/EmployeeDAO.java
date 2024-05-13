package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.Employees;

import java.util.List;
@Repository
public class EmployeeDAO implements  DAO<Employees>{

    @Autowired
    private SessionFactory sessionFactory;
    @Override

    public List<Employees> getAll() {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("from Employees ",Employees.class).getResultList();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Employees where id="+id).executeUpdate();

    }

    @Override
    public void update(Employees obj) {
    Session session = sessionFactory.getCurrentSession();
    session.update(obj);
    }

    @Override
    public void add(Employees obj) {
    Session session=sessionFactory.getCurrentSession();
    session.merge(obj);
    }

    @Override
    public Employees getById(int id) {
        Session session =sessionFactory.getCurrentSession();
       Employees employees= session.get(Employees.class, id);
       return employees;
    }
}
