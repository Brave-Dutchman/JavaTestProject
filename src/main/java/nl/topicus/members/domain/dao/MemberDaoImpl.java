package nl.topicus.members.domain.dao;

import nl.topicus.members.domain.model.Member;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Thijs Reeringh on 4/20/2016.
 */
@Transactional
@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Member> getMembers() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Member.class);
        List<Member> members = (List<Member>) criteria.list();
        return members;
    }

    @Override
    public void save(Member member) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(member);
    }

    @Override
    public boolean emailExist(String email) {
        if (email == null) throw new NullPointerException();

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Member.class);
        criteria.add(Restrictions.eq("email",email));

        return criteria.uniqueResult() != null;
    }

    @Override
    public Member getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Member) session.get(Member.class, id);
    }
}
