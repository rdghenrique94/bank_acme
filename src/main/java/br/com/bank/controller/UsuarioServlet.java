package br.com.bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bank.model.Usuario;
import br.com.bank.service.UsuarioServiceImpl;

public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioServiceImpl usuarioService;
       
    public UsuarioServlet() {
    		this.usuarioService = new UsuarioServiceImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher;
		String action = request.getParameter("acao");

			switch(action) {
			
				case "listar":
					request.setAttribute("usuarios", this.usuarioService.recuperarTodos());
					requestDispatcher = request.getRequestDispatcher("admin/pages/listar_usuarios.jsp");
					requestDispatcher.forward(request, response);
					break;
				case "remover":
					
					String idUsuario = request.getParameter("id");
					this.usuarioService.excluirUsuario(Long.parseLong(idUsuario));
					
					request.setAttribute("removido", "Usuario removido com sucesso!");
					requestDispatcher = request.getRequestDispatcher("/usuarioServlet?&acao=listar");
					requestDispatcher.forward(request, response);
					break;
				case "cadastro":
					requestDispatcher = request.getRequestDispatcher("admin/pages/registro_usuarios.jsp");
					requestDispatcher.forward(request, response);
					break;
					
				case "editar":
					
					Usuario usuarioEdit = new Usuario();
					usuarioEdit.setId(Long.parseLong(request.getParameter("id")));
					usuarioEdit.setUsername(request.getParameter("user"));
					usuarioEdit.setPassword(request.getParameter("password"));
		
					request.setAttribute("usuario", usuarioEdit);
					requestDispatcher = request.getRequestDispatcher("admin/pages/editar_usuarios.jsp");
					requestDispatcher.forward(request, response);
					break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String idUsuario = request.getParameter("id");
		String action = request.getParameter("acao");
		
		switch(action) {
		
		case "editar":
			
			Usuario usuarioEdited = Usuario.builder()
				.username(username)
				.password(password)
				.id(Long.parseLong(idUsuario)).build();
			
			this.usuarioService.editarUsuario(usuarioEdited);
			requestDispatcher = request.getRequestDispatcher("admin/pages/listar_usuarios.jsp");
			request.setAttribute("sucesso", "Usuario "+ usuarioEdited.getUsername() +" editado com sucesso");
			request.setAttribute("usuarios", this.usuarioService.recuperarTodos());
			requestDispatcher.forward(request, response);
			break;
		case "cadastrar":
			
			Usuario userToPersist = Usuario.builder()
				.username(username)
				.password(password).build();
			
			usuarioService.cadastrarUsuario(userToPersist);
			requestDispatcher = request.getRequestDispatcher("admin/pages/listar_usuarios.jsp");
			request.setAttribute("sucesso", "Usuario "+ userToPersist.getUsername() +" salvo com sucesso");
			request.setAttribute("usuarios", this.usuarioService.recuperarTodos());
			requestDispatcher.forward(request, response);
			break;

		}
		
	}

}
