package nl.topicus.members.web.views;

import nl.topicus.members.domain.dao.MemberDao;
import nl.topicus.members.domain.dao.MembersGroupDao;
import nl.topicus.members.domain.model.*;
import nl.topicus.members.domain.model.providers.MemberProvider;
import nl.topicus.members.domain.model.providers.MembersGroupProvider;
import nl.topicus.members.domain.model.validators.FormattedPropertyColumn;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Homepage
 */
public class RegisterMemberPage extends WebPage {
	private static final long serialVersionUID = 1L;

    @SpringBean
    private MemberDao dao;

    @SpringBean
    private MembersGroupDao membersGroupDao;

    private final MemberFormPanel frmPanel;
    private final MembersGroupFormPanel groupFrmPanel;

    private final DataTable<Member> memberDataTable;
    private final DataTable<MembersGroup> membersGroupsDataTable;

    public RegisterMemberPage() {
        frmPanel = new MemberFormPanel("registerMember", new Model<Member>(new Member())) {

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                target.addComponent(memberDataTable);
                groupFrmPanel.updateDropdown(target);
            }
        };
        add(frmPanel);

        groupFrmPanel = new MembersGroupFormPanel("registerGroup", new Model<MembersGroup>(new MembersGroup())) {

            @Override
            protected List<Member> getMembers() {
                return dao.getMembers();
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                target.addComponent(membersGroupsDataTable);
            }
        };
        add(groupFrmPanel);

        memberDataTable = new DefaultDataTable<Member>("table", createMemberColumns(), new MemberProvider(dao), 8);
        memberDataTable.setOutputMarkupId(true);
        add(memberDataTable);

        membersGroupsDataTable = new DefaultDataTable<MembersGroup>("membersGroupTable", createMemberGroupColumns(), new MembersGroupProvider(membersGroupDao),8);
        membersGroupsDataTable.setOutputMarkupId(true);
        add(membersGroupsDataTable);
    }

    private List<IColumn<MembersGroup>> createMemberGroupColumns() {
        final List<IColumn<MembersGroup>> groupsColumns = new ArrayList<IColumn<MembersGroup>>();

        groupsColumns.add(new AbstractColumn<MembersGroup>(Model.of("Actions")) {

            public void populateItem(Item<ICellPopulator<MembersGroup>> cellItem, String componentId, IModel<MembersGroup> model) {

                cellItem.add(new ActionPanel<MembersGroup>(componentId, model) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, IModel<MembersGroup> model) {

                    }
                });
            }
        });

        groupsColumns.add(new PropertyColumn<MembersGroup>(Model.of("Name"), "name","name"));
        groupsColumns.add(new FormattedPropertyColumn<MembersGroup>(Model.of("Founded"), "founded", "founded", new SimpleDateFormat("dd-MM-yyyy")));

        return groupsColumns;
    }

    private List<IColumn<Member>> createMemberColumns() {
        List<IColumn<Member>> memberColumns = new ArrayList<IColumn<Member>>();

        memberColumns.add(new AbstractColumn<Member>(Model.of("Actions")) {
            public void populateItem(Item<ICellPopulator<Member>> cellItem, String componentId, IModel<Member> model) {
                cellItem.add(new ActionPanel<Member>(componentId, model) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, IModel<Member> model) {
                        frmPanel.setMemberForUpdate(model.getObject(), target);
                    }
                });
            }
        });

        memberColumns.add(new PropertyColumn<Member>(Model.of("Name"), "name","name"));
        memberColumns.add(new PropertyColumn<Member>(Model.of("Email"), "email", "email"));
        memberColumns.add(new PropertyColumn<Member>(Model.of("Age"), "age", "age"));
        memberColumns.add(new FormattedPropertyColumn<Member>(Model.of("MemberDate"), "memberDate", "memberDate", new SimpleDateFormat("dd-MM-yyyy")));

        return memberColumns;
    }
}
