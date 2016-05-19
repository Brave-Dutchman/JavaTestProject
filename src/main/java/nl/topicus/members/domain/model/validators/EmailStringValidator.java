package nl.topicus.members.domain.model.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;

import java.util.regex.Pattern;

/**
 * Created by Thijs Reeringh on 4/21/2016.
 */
public final class EmailStringValidator extends AbstractValidator<String>
{
    private static final Pattern pattern = Pattern.compile("[0-9a-z.]+[@][a-z.]+[.][a-z]+");

    @Override
    protected void onValidate(IValidatable<String> validatable)
    {
        if (!pattern.matcher(validatable.getValue()).matches())
        {
            ValidationError error = new ValidationError();
            error.setMessage("Email must be a valid email address");

            validatable.error(error);
        }
    }
}
