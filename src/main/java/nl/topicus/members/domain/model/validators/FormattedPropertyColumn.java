package nl.topicus.members.domain.model.validators;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import java.text.Format;

/**
 * Created by Thijs Reeringh on 4/21/2016.
 */
public class FormattedPropertyColumn<T> extends PropertyColumn<T>
{
    private final Format format;

    public FormattedPropertyColumn(IModel<String> displayModel, String sortProperty, String propertyExpression, Format format)
    {
        super(displayModel, sortProperty, propertyExpression);
        this.format = format;
    }

    @Override
    protected IModel<?> createLabelModel(IModel<T> rowModel)
    {
        final IModel<?> originalModel = super.createLabelModel(rowModel);
        return new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject()
            {
                Object value = originalModel.getObject();
                return (value != null) ? format.format(value) : null;
            }
        };
    }
}
