package nl.topicus.members.domain.model.providers;

import nl.topicus.members.domain.dao.MembersGroupDao;
import nl.topicus.members.domain.model.MembersGroup;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
public class MembersGroupProvider extends ASortableDataProvider<MembersGroup> {

    @SpringBean
    private MembersGroupDao dao;

    public MembersGroupProvider() {
        InjectorHolder.getInjector().inject(this);
        setSort("name", true);
    }

    @Override
    public Iterator<MembersGroup> iterator(final int first, final int count) {
        List<MembersGroup> newList = new ArrayList<MembersGroup>(dao.get());

        Collections.sort(newList, comparator);

        return newList.subList(first, first + count).iterator();
    }

    @Override
    public int size() {
        return dao.get().size();
    }
}