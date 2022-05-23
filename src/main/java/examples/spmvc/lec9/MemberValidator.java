package examples.spmvc.lec9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {
	
	private static final String emailRegExp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	private Pattern pattern;
	
	public MemberValidator() {
		pattern = Pattern.compile(emailRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Member member = (Member)target;
		
		//아이디
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		
		//이름
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		
		//이메일
		if(member.getEmail() == null || member.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = pattern.matcher(member.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		
		//주소
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required");
		
		//전화번호
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "required");
	}

}
