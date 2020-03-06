package com.example.sec.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

import com.example.sec.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User u =(User) target;
		
		ValidationUtils.rejectIfEmpty(errors, "userName", "field.required","User name should not be empty");
		ValidationUtils.rejectIfEmpty(errors, "password","field.required", "password should not be empty");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword","field.required", " Confirm password should not be empty");
		
		if(u.getPassword().length()<4) {
			errors.rejectValue("password","field.invalid", "Password length should be atleast 4");
		}
		
		if(!u.getPassword().equals(u.getConfirmPassword())) {
			errors.rejectValue("password", "field.invalid","Password do not match with Retype Password");
		}
	}

	
}
