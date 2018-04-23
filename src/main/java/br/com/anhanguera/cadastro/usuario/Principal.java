package br.com.anhanguera.cadastro.usuario;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.anhanguera.cadastro.usuario.controladores.UsuarioController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import com.google.gson.Gson;

import br.com.anhanguera.cadastro.usuario.bd.UsuarioDAO;
import br.com.anhanguera.cadastro.usuario.dominio.Usuario;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		
		Server servidor = new Server(8080);
		
		ServletContextHandler servletHandler = 
				new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletHandler.setContextPath("/");
		servletHandler.setBaseResource(Resource.newResource(
				Principal.class.getClassLoader().getResource("html/").toURI()));
		servletHandler.setWelcomeFiles(new String[]{"index.html"});
		
		ServletHolder holderPadrao = new ServletHolder("padrao", DefaultServlet.class);
		holderPadrao.setInitParameter("dirAllowed", "true");
		servletHandler.addServlet(holderPadrao, "/");


		ServletHolder jerseyServlet = servletHandler.addServlet(
				org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");
		jerseyServlet.setInitOrder(0);

		// Tells the Jersey Servlet which REST service/class to load.
		jerseyServlet.setInitParameter(
				"jersey.config.server.provider.classnames",
				UsuarioController.class.getCanonicalName());


//		servletHandler.addServlet(
//				new ServletHolder(new CadastroUsuarioServlet()),
//				"/usuarios");
//
		servidor.setHandler(servletHandler);
				
		servidor.start();
		servidor.join();

	}
	
	
	static class CadastroUsuarioServlet extends HttpServlet{
		
			@Override
			protected void doGet(HttpServletRequest req, 
					HttpServletResponse resp) 
							throws ServletException, 
							IOException {
				resp.setContentType("application/json");
				resp.setStatus(HttpServletResponse.SC_OK);
				
				resp.getWriter()
					.println(new Gson().toJson(UsuarioDAO.listar()));
			}
			
			@Override
			protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				Long idExcluir = Long.parseLong(req.getParameter("id")) ;
				UsuarioDAO.excluir(idExcluir);
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			
			private Usuario recuperarusuario(HttpServletRequest req){
				StringBuffer jsonBuffer = new StringBuffer();
				String linha = null;
				try{
					BufferedReader reader = req.getReader();
					while((linha = reader.readLine()) != null){
						jsonBuffer.append(linha);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				Usuario usuario = new Gson()
						.fromJson(jsonBuffer.toString(), Usuario.class);
				
				return usuario;
			}
			
			
			@Override
			protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
				Usuario usuario = recuperarusuario(req);
				
				UsuarioDAO.atualizar(usuario);
				
				resp.setContentType("application/json");
				resp.setStatus(HttpServletResponse.SC_OK);
				
				resp.getWriter()
					.println(new Gson().toJson(usuario));
				
			}
			
			@Override
			protected void doPost(HttpServletRequest req, 
					HttpServletResponse resp) 
							throws ServletException, IOException {
			
				Usuario usuario = recuperarusuario(req);	
				
				try {
					Usuario usuarioInserido = UsuarioDAO.inserir(usuario);
					resp.setContentType("application/json");
					resp.setStatus(HttpServletResponse.SC_CREATED);
					
					resp.getWriter()
						.println(new Gson().toJson(usuarioInserido));
					
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		
	}
	
}
