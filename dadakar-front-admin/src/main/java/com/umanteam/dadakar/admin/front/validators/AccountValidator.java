package com.umanteam.dadakar.admin.front.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.enums.Role;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Component
public class AccountValidator implements Validator {

	@Autowired
	IAccountService accountService;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Account.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Account account = (Account) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.accountForm.username", "Veuillez renseigner le nom d'utilisateur");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm", "Veuillez renseigner le mot de passe");
//		if (account.getRole() != Role.ADMIN || account.getRole() != Role.SUPERUSER) {
//			errors.rejectValue("role", "NotEmpty.accountForm.role", "Veuillez sélectionner un rôle");
//		}
		Account testAccount = accountService.findByUsername(account.getUsername());
		if (testAccount != null && testAccount.getAccountId() != null 
				&& !testAccount.getAccountId().equals("") && ! testAccount.getAccountId().equals(account.getAccountId())) {
			errors.rejectValue("username", "alreadyExists.accountForm.username", "Un compte utilisateur avec ce nom existe déjà");
		}
	}

}
