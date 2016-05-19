package nl.topicus.members.domain.model.validators;

import nl.topicus.members.domain.dao.MemberDao;
import org.apache.wicket.validation.CompoundValidator;

/**
 * Created by Thijs Reeringh on 4/19/2016.
 */
public class EmailCompoundValidator extends CompoundValidator<String>
{
    private EmailUniqueValidator uniqueValidator;

    public EmailCompoundValidator()
    {
        uniqueValidator = new EmailUniqueValidator();

        add(new EmailStringValidator());
        add(uniqueValidator);
    }

    public void setUpdateMode(boolean updateMode)
    {
        uniqueValidator.SetUpdateMode(updateMode);
    }
}