package nl.topicus.members.domain.model.providers;

import nl.topicus.members.domain.dao.MembersGroupDao;
import nl.topicus.members.domain.model.MembersGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
public class MembersGroupProvider extends ASortableDataProvider<MembersGroup> {
    private MembersGroupDao dao;

    public MembersGroupProvider(MembersGroupDao dao) {
        setSort("name", true);
        this.dao = dao;
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