package br.com.bank.dao;

import java.util.List;

import br.com.bank.model.Usuario;

public interface UsuarioDao {

	public void cadastarNovoUsuario(Usuario usuario);
	
	public void editarUsuario(Usuario usuario);
	
	public List<Usuario> listarTodos();
	
	public void excluirUsuario(Long id);

}
