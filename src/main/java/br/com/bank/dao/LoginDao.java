package br.com.bank.dao;

public interface LoginDao {
	
	public boolean verificarUsuarioCadastrado(String user, String password);
	
}
