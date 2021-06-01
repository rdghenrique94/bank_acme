package br.com.bank.dao;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bank.model.Usuario;
import br.com.bank.util.JPAUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public boolean verificarUsuarioCadastrado(String user, String password) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		try {
		
			Query query = entityManager.createQuery("SELECT u FROM Usuario u where u.username = :user and u.password = :password");
			query.setParameter("user", user);
			query.setParameter("password", password);
			
			Usuario usuarioCredenciais = (Usuario) query.getSingleResult();
		
			return Objects.nonNull(usuarioCredenciais);			
			
		} 
		catch(NoResultException e) {
			e.getMessage();
			entityManager.close();
		}
		
		return false;
	}

}
