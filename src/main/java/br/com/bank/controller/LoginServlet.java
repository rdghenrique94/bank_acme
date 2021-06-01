package br.com.bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.bank.model.Usuario;
import br.com.bank.service.LoginServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private LoginServiceImpl loginService;
	
	public LoginServlet() {
		this.loginService = new LoginServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/login.jsp");
		rd.forward(request, response);				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher requestDispatcher;
		HttpSession session = request.getSession();
		
		boolean isValidCredentials = loginService.verificarUsuario(username, password);
			
		if(isValidCredentials) {
			
			session.setAttribute("userLogged", username);
			response.sendRedirect("/acme-bank/admin/home");
			
		}else {
			requestDispatcher =  request.getRequestDispatcher("/admin/login.jsp");
			request.setAttribute("error", "Login ou senha inválidos!");
			requestDispatcher.forward(request, response);
		}

	}
	
}
