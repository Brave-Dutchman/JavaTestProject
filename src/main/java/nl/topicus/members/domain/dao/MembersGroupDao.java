package nl.topicus.members.domain.dao;

import nl.topicus.members.domain.model.Member;
import nl.topicus.members.domain.model.MembersGroup;

import java.util.List;
import java.util.Set;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
public interface MembersGroupDao {
    List<MembersGroup> get();

    boolean isMember(Member member);

    void save(MembersGroup membersGroup);

    MembersGroup getById(long id);

    Set<Member> getMembers(int id);
}
