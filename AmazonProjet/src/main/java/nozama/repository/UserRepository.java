package nozama.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nozama.model.Users;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class UserRepository {
	
	Session openSession = HibernateUtil.getSessionFactory().openSession();

	
	public List<Users> getUserByEmailAndPwd(String email, String password) {
		Criteria cr = openSession.createCriteria(Users.class);
		cr.add(Restrictions.eq("emailAdress", email));
		cr.add(Restrictions.eq("password", password));
		return cr.list();
	}
	
	public List<Users> getUserByEmail(String email) {
		Criteria cr = openSession.createCriteria(Users.class);
		cr.add(Restrictions.eq("emailAdress", email));
		return cr.list();
	}

	public void insertUser(Users users) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
                 
        session.save(users);
 
        session.getTransaction().commit();
        HibernateUtil.shutdown();
	}

}