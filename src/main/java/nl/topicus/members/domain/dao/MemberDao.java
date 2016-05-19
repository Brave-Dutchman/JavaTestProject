package nl.topicus.members.domain.dao;

import nl.topicus.members.domain.model.Member;

import java.util.List;

/**
 * Created by Thijs Reeringh on 4/26/2016.
 */
public interface MemberDao {
    List<Member> getMembers();

    void save(Member member);

    boolean emailExist(String email);

    Member getById(long id);
}
