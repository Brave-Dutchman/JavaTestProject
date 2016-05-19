package nl.topicus.members.web.views;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Created by Thijs Reeringh on 4/21/2016.
 */
public abstract class ActionPanel<T> extends Panel {
    public ActionPanel(String id, final IModel<T> model) {
        super(id, model);

        add(new AjaxLink("select") {
            @Override
            public void onClick(AjaxRequestTarget target) {
               ActionPanel.this.onSubmit(target, model);
            }
        });
    }

    protected abstract void onSubmit(AjaxRequestTarget target, IModel<T> model);
}
