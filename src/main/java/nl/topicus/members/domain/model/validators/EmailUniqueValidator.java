package nl.topicus.members.domain.model.validators;

import nl.topicus.members.domain.dao.MemberDao;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;

public class EmailUniqueValidator extends AbstractValidator<String>
{
    private MemberDao dao;

    private boolean updateMode;

    public EmailUniqueValidator(MemberDao dao)
    {
        this.dao = dao;
        this.updateMode = false;
    }

    @Override
    protected void onValidate(IValidatable<String> validatable)
    {
        if (updateMode)return;

        String email = validatable.getValue();

        if (dao.emailExist(email))
        {
            ValidationError error = new ValidationError();
            error.setMessage("Email must be a unique value");

            validatable.error(error);
        }
    }

    public void OnUpdate(boolean update)
    {
        this.updateMode = update;
    }
}
