package br.com.bank.service;

import java.util.List;

import br.com.bank.model.Usuario;

public interface UsuarioService {
	
	public void cadastrarUsuario(Usuario usuario);
	
	public void editarUsuario(Usuario usuario);
	
	public void excluirUsuario(Long id);
	
	public List<Usuario> recuperarTodos();

}
