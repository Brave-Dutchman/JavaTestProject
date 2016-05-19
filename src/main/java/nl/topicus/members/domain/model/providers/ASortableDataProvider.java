package nl.topicus.members.domain.model.providers;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
public abstract class ASortableDataProvider<T> extends SortableDataProvider {
    protected SortableDataProviderComparator<T> comparator;

    public ASortableDataProvider() {
        comparator = new SortableDataProviderComparator<T>();
    }

    class SortableDataProviderComparator<T> implements Comparator<T>, Serializable {
        public int compare(final T o1, final T o2) {
            PropertyModel<Comparable> model1 = new PropertyModel<Comparable>(o1, getSort().getProperty());
            PropertyModel<Comparable> model2 = new PropertyModel<Comparable>(o2, getSort().getProperty());

            int result = model1.getObject().compareTo(model2.getObject());

            if (!getSort().isAscending()) {
                result = -result;
            }

            return result;
        }
    }

    @Override
    public IModel model(Object object) {
        return readonlyModel((T) object);
    }

    public IModel<T> readonlyModel(final T object) {
        return new AbstractReadOnlyModel<T>() {
            @Override
            public T getObject() {
                return object;
            }
        };
    }
}
