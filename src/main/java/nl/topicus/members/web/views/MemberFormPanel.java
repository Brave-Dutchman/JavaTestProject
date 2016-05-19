package nl.topicus.members.web.views;

import nl.topicus.members.domain.dao.MemberDao;
import nl.topicus.members.domain.model.validators.EmailCompoundValidator;
import nl.topicus.members.domain.model.Member;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by Thijs Reeringh on 4/21/2016.
 */
public abstract class MemberFormPanel extends Panel
{
    private Form<Member> form;

    @SpringBean
    private MemberDao dao;

    private IModel<Member> memberModel;
    private boolean editMode;

    private EmailCompoundValidator validator;
    private final TextField<String> tEmail;

    public MemberFormPanel(String id, final IModel<Member> fMemberModel) {
        super(id, fMemberModel);

        this.memberModel = fMemberModel;
        this.editMode = false;

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);

        final TextField<String> tName = new TextField<String>("name");
        final TextField<Integer> tAge = new TextField<Integer>("age");
        final DateTextField tDate = new DateTextField("memberDate", "dd-MM-yyyy");
        tEmail = new TextField<String>("email");

        tName.setRequired(true);
        tAge.setRequired(true);
        tDate.setRequired(true);
        tEmail.setRequired(true);

        validator = new EmailCompoundValidator(dao);

        tEmail.add(validator);

        form = new Form<Member>("userForm", new CompoundPropertyModel<Member>(memberModel));

        AjaxSubmitLink submit = new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                Member submitted = memberModel.getObject();

                dao.save(submitted);

                if (editMode)
                {
                    validator.setUpdateMode(false);
                    tEmail.setEnabled(true);
                }

                editMode = false;
                memberModel.setObject(new Member());

                target.addComponent(form);
                target.addComponent(feedback);

                MemberFormPanel.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
                target.addComponent(feedback);
            }
        };

        form.add(submit);

        form.add(new AjaxSubmitLink("reset") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                memberModel.setObject(new Member());
                tEmail.setEnabled(true);
                editMode = false;

                target.addComponent(form);
                target.addComponent(feedback);
            }
        });

        form.add(tName);
        form.add(tAge);
        form.add(tEmail);
        form.add(tDate);

        add(feedback);
        add(form);
    }

    public void setMemberForUpdate(Member member, AjaxRequestTarget target)
    {
        memberModel.setObject(member);
        target.addComponent(form);

        tEmail.setEnabled(false);

        editMode = true;
        validator.setUpdateMode(true);
    }

    protected abstract void onSubmit(AjaxRequestTarget target);
}
