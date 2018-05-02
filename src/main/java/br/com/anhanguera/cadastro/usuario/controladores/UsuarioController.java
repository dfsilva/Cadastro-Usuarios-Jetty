package br.com.anhanguera.cadastro.usuario.controladores;


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

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listar() {
        return UsuarioDAO.listar();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Long id) {
       // UsuarioDAO.excluir(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario atualizar(Usuario usuario) {
       // UsuarioDAO.atualizar(usuario);
        return usuario;
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Usuario usuario, @Context UriInfo uriInfo) throws PropertyVetoException, SQLException {
        //UsuarioDAO.inserir(usuario);
        return Response.created(uriInfo.getRequestUriBuilder().path(usuario.getId()+"").build()).build();
    }

}
