package br.com.bank.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bank.model.Usuario;
import br.com.bank.util.JPAUtil;

public class UsuarioDaoImpl implements UsuarioDao{
	
	@Override
	public void cadastarNovoUsuario(Usuario usuario) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
			entityManager.close();
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	

	@Override
	public void editarUsuario(Usuario usuario) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Usuario usuarioToEdit = entityManager.find(Usuario.class, usuario.getId());
		usuarioToEdit.setUsername(usuario.getUsername());
		usuarioToEdit.setPassword(usuario.getPassword());
		
		try {
			
			entityManager.persist(usuarioToEdit);
			entityManager.getTransaction().commit();
			entityManager.close();
			
		} catch(NoResultException e) {
			e.getMessage();
			entityManager.close();
		}
		
	}


	@Override
	public List<Usuario> listarTodos() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			
			Query query = entityManager.createQuery("SELECT u FROM Usuario u");
			List<Usuario> usuarios = query.getResultList();
					
			return usuarios;
			
		} catch(NoResultException e) {
			e.getMessage();
			entityManager.close();
		}
		
		return Arrays.asList();
	}

	@Override
	public void excluirUsuario(Long id) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		Usuario usuarioToRemove = entityManager.find(Usuario.class, id);
		
		entityManager.getTransaction().begin();
		entityManager.remove(usuarioToRemove);
		entityManager.getTransaction().commit();
			
	}

}
