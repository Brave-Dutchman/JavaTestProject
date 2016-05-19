package nl.topicus.members.domain.model.providers;

import nl.topicus.members.domain.dao.MemberDao;
import nl.topicus.members.domain.model.Member;

import java.util.*;

public class MemberProvider extends ASortableDataProvider<Member> {
    private MemberDao dao;

    public MemberProvider(MemberDao dao) {
        setSort("name", true);
        this.dao = dao;
    }


    @Override
    public Iterator<Member> iterator(final int first, final int count) {
        List<Member> newList = new ArrayList<Member>(dao.getMembers());

        Collections.sort(newList, comparator);

        return newList.subList(first, first + count).iterator();
    }

    @Override
    public int size() {
        return dao.getMembers().size();
    }
}