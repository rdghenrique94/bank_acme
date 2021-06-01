package br.com.bank.service;

import java.util.List;

import br.com.bank.dao.UsuarioDaoImpl;
import br.com.bank.model.Usuario;

public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioDaoImpl dao;
	
	public UsuarioServiceImpl() {
		this.dao = new UsuarioDaoImpl();
	}
	
	public void cadastrarUsuario(Usuario usuario) {
		this.dao.cadastarNovoUsuario(usuario);	
	}

	public void editarUsuario(Usuario usuario) {
		this.dao.editarUsuario(usuario);
	}
	
	public void excluirUsuario(Long id) {
		this.dao.excluirUsuario(id);
		
	}

	public List<Usuario> recuperarTodos() {
		return this.dao.listarTodos();
	}


}
