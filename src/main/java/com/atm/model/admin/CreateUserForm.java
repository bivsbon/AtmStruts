package com.atm.model.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CreateUserForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uname;
	private String name;
	private String password;
	private String gender;
	private int age;
	private String contact;
	private String email;
	private static final String REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		// Username
		if (uname == null || uname.length() == 0) {
			actionErrors.add("user.username.required", new ActionMessage("error.user.username.required"));
		}
		else if (!uname.matches("[0-9]+") || !uname.startsWith("9704") || uname.length() != 10) {
			actionErrors.add("user.username.invalid", new ActionMessage("error.user.username.invalid"));
		}
		
		// Name
		if (name == null || name.length() == 0) {
			actionErrors.add("user.name.required", new ActionMessage("error.user.name.required"));
		}
		
		// Password
		if (password == null || password.length() == 0) {
			actionErrors.add("user.password.required", new ActionMessage("error.user.password.required"));
		}
		else if (!password.matches("[0-9]+") || password.length() != 4) {
			actionErrors.add("user.password.invalid", new ActionMessage("error.user.password.invalid"));
		}

		// Gender
		if (gender == null || gender.length() == 0) {
			actionErrors.add("user.gender.required", new ActionMessage("error.user.gender.required"));
		}
		
		// Age
		if (age < 15 || age > 100) {
			actionErrors.add("user.username.invalid", new ActionMessage("error.user.username.invalid"));
		}
		
		// Contact
		if (contact == null || contact.length() == 0) {
			actionErrors.add("user.contact.required", new ActionMessage("error.user.contact.required"));
		}
		else if (!contact.matches("[0-9]+")) {
			actionErrors.add("user.contact.invalid", new ActionMessage("error.user.contact.invalid"));
		}
		
		// Email
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(email);
		if (email == null || email.length() == 0) {
			actionErrors.add("user.email.required", new ActionMessage("error.user.email.required"));
		}
		else if (!matcher.matches()) {
			actionErrors.add("user.email.invalid", new ActionMessage("error.user.email.invalid"));
		}
		
		return actionErrors;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		
		uname = "";
		name ="";
		password = "";
		gender = "";
		age = 0;
		contact = "";
		email = "";
	}
}
