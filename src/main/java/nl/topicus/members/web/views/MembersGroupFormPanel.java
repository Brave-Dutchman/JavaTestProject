package nl.topicus.members.web.views;

import nl.topicus.members.domain.dao.MembersGroupDao;
import nl.topicus.members.domain.model.Member;
import nl.topicus.members.domain.model.MembersGroup;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;
import java.util.Set;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
public abstract class MembersGroupFormPanel extends Panel {
    private IModel<MembersGroup> groupModel;

    @SpringBean
    private MembersGroupDao dao;

    private Member member;

    private final DropDownChoice<Member> dropDown;

    protected MembersGroup group;

    public MembersGroupFormPanel(String id, final IModel<MembersGroup> membersGroupModel) {
        super(id, membersGroupModel);
        groupModel = membersGroupModel;

        group = membersGroupModel.getObject();

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);

        final TextField<String> tName = new TextField<String>("name");
        final DateTextField tDate = new DateTextField("founded", "dd-MM-yyyy");

        dropDown = new DropDownChoice<Member>("selectMember", new PropertyModel<Member>(this, "member"),
            new LoadableDetachableModel<List<Member>>() {
                @Override
                protected List<Member> load() {
                    return MembersGroupFormPanel.this.getMembers();
                }
            }
        );

        dropDown.setOutputMarkupId(true);

        tName.setRequired(true);
        tDate.setRequired(true);

        final Form<MembersGroup> form = new Form<MembersGroup>("userForm", new CompoundPropertyModel<MembersGroup>(groupModel));

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                MembersGroup submitted = groupModel.getObject();

                Set<Member> members = submitted.getMembers();
                members.add(member);
                submitted.setMembers(members);

                dao.save(submitted);
                groupModel.setObject(new MembersGroup());

                target.addComponent(form);
                target.addComponent(feedback);

                MembersGroupFormPanel.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.addComponent(feedback);
            }
        });

        form.add(tName);
        form.add(tDate);
        form.add(dropDown);

        add(feedback);
        add(form);
    }

    public void updateDropdown(AjaxRequestTarget target) {
        target.addComponent(dropDown);
    }

    protected abstract List<Member> getMembers();

    protected abstract void onSubmit(AjaxRequestTarget target);
}
