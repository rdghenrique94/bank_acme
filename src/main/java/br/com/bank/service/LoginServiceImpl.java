package br.com.bank.service;

import br.com.bank.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {

	private LoginDaoImpl dao;
	
	public LoginServiceImpl() {
		this.dao = new LoginDaoImpl();
	}
	
	public boolean verificarUsuario(String email, String senha) {
		return this.dao.verificarUsuarioCadastrado(email, senha);
	}

}
