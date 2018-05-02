package br.com.anhanguera.cadastro.usuario.dao;

import br.com.anhanguera.cadastro.usuario.bd.EntityManagerInit;
import br.com.anhanguera.cadastro.usuario.dominio.Usuario;

import java.util.List;

public class HibernateUsuarioDAO {

    public static Usuario inserir(Usuario usuario){
        EntityManagerInit.getEntityManager().persist(usuario);
        return usuario;
    }

    public static List<Usuario> listar(){
      return EntityManagerInit.getEntityManager().createQuery("from "+Usuario.class.getName()+" u").getResultList();
    }


    public static void atualizar(Usuario usuario){
        EntityManagerInit.getEntityManager().merge(usuario);
    }

    public static void excluir(Long idUsuario){
        EntityManagerInit.getEntityManager().remove(new Usuario(idUsuario));
    }
}
