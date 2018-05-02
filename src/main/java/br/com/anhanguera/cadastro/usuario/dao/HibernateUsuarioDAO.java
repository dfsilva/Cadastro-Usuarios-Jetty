package br.com.anhanguera.cadastro.usuario.dao;

import br.com.anhanguera.cadastro.usuario.bd.CadastroEMFactory;
import br.com.anhanguera.cadastro.usuario.dominio.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateUsuarioDAO {

    private EntityManager em = CadastroEMFactory.getEntityManager();

    public Usuario inserir(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        return usuario;
    }

    public List<Usuario> listar(){
      return em.createQuery("from "+Usuario.class.getName()+" u").getResultList();
    }

    public void atualizar(Usuario usuario){
        em.merge(usuario);
    }

    public void excluir(Long idUsuario){
        em.remove(new Usuario(idUsuario));
    }
}
