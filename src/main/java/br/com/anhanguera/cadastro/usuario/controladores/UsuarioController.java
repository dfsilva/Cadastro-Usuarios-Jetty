package br.com.anhanguera.cadastro.usuario.controladores;


import br.com.anhanguera.cadastro.usuario.dao.HibernateUsuarioDAO;
import br.com.anhanguera.cadastro.usuario.dao.UsuarioDAO;
import br.com.anhanguera.cadastro.usuario.dominio.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
public class UsuarioController {

    private HibernateUsuarioDAO dao = new HibernateUsuarioDAO();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listar() {
        return dao.listar();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Long id) {
        dao.excluir(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario atualizar(Usuario usuario) {
        dao.atualizar(usuario);
        return usuario;
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Usuario usuario, @Context UriInfo uriInfo) throws PropertyVetoException, SQLException {
        dao.inserir(usuario);
        return Response.created(uriInfo.getRequestUriBuilder().path(usuario.getId()+"").build()).build();
    }

}
