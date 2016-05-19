package nl.topicus.members.domain.dao;

import nl.topicus.members.domain.model.Member;
import nl.topicus.members.domain.model.MembersGroup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
@Transactional
@Repository
public class MembersGroupImpl implements MembersGroupDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MembersGroup> get() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MembersGroup.class);
        List<MembersGroup> membersGroups = (List<MembersGroup>) criteria.list();
        return membersGroups;
    }

    @Override
    public boolean isMember(Member member) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MembersGroup.class);
        criteria.add(Restrictions.eq("email",member.getEmail()));

        return false;
    }

    @Override
    public void save(MembersGroup membersGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(membersGroup);
    }

    @Override
    public MembersGroup getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (MembersGroup) session.load(MembersGroup.class, id);
    }

    @Override
    public Set<Member> getMembers(int id) {
        return getById(id).getMembers();
    }
}
