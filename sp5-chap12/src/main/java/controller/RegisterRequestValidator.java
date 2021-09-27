package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.RegisterRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRequestValidator implements Validator {

    private static final String emailRegExp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;

    public RegisterRequestValidator(){
        pattern=Pattern.compile(emailRegExp);
    }

    @Override
    public boolean supports(Class<?> clazz){
        return RegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        RegisterRequest regReq=(RegisterRequest) target;

        if(regReq.getEmail()==null || regReq.getEmail().trim().isEmpty()){
            errors.rejectValue("email", "required");
        }
        else{
            Matcher matcher=pattern.matcher(regReq.getEmail());
            if(!matcher.matches()){
                errors.rejectValue("email","bad");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","required");        // target이 파라미터로 넘어오지 않아도, RegisterController 내 Errors 객체에 커맨드 객체의 프로퍼티 정보를 얻을 수 있는 메소드가 제공되어 실행 가능.
        ValidationUtils.rejectIfEmpty(errors, "password", "required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");

        if(!regReq.getPassword().isEmpty()){
            if(!regReq.isPasswordEqualToConfirmPassword()){
                errors.rejectValue("confirmPassword", "nomatch");
            }
        }
    }
}
