package nl.topicus.members.domain.model.providers;

import nl.topicus.members.domain.dao.MemberDao;
import nl.topicus.members.domain.model.Member;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MemberProvider extends ASortableDataProvider<Member> {

    @SpringBean
    private MemberDao dao;

    public MemberProvider() {
        InjectorHolder.getInjector().inject(this);
        setSort("name", true);
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