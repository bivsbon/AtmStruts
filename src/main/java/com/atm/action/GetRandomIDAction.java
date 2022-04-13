package com.atm.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.service.UserService;

public class GetRandomIDAction  extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserService service = new UserService();
		List<String> ids = service.getAllId();
		String randomID;
		PrintWriter out = response.getWriter();
		
		do {
			randomID = getRandomID();
		} while (ids.contains(randomID));
		
		out.print(randomID);
		return null;
	}

	private String getRandomID() {
		StringBuilder sb = new StringBuilder();
		sb.append("9704");
		Random generator = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(generator.nextInt(10));
		}
		
		return sb.toString();
	}
}
