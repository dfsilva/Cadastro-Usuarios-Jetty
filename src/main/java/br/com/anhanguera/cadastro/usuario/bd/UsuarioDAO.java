package br.com.anhanguera.cadastro.usuario.bd;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.anhanguera.cadastro.usuario.dominio.Usuario;

public class UsuarioDAO {
	
	public static Usuario inserir(Usuario usuario) 
			throws SQLException, PropertyVetoException{
		
		ResultSet rs = null;
		PreparedStatement stm = null;
		Connection con = PoolConexoesMysql.getConnection();
		
		stm = con.prepareStatement("insert into usuario(nome,idade) "
				+ " values(?,?)", Statement.RETURN_GENERATED_KEYS);
		stm.setString(1, usuario.getNome());
		stm.setInt(2, usuario.getIdade());
		
		stm.executeUpdate();
		
		rs = stm.getGeneratedKeys();
		if(rs.next()){
			Long idGerado = rs.getLong(1);
			usuario.setId(idGerado);
		}
		
		return usuario;
	}
	
	
	public static List<Usuario> listar(){
		
		ResultSet rs = null;
		PreparedStatement stm = null;
		Connection con = null;
		
		try {
			
			con = PoolConexoesMysql.getConnection();
			stm = con.prepareStatement("Select * from usuario");
			rs = stm.executeQuery();
			
			List<Usuario> retorno = new ArrayList<>();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong(1));
				usuario.setNome(rs.getString(2));
				usuario.setIdade(rs.getInt(3));
				retorno.add(usuario);
			}
			
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
				if(stm != null){
					stm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public static int atualizar(Usuario usuario){
		PreparedStatement stm = null;
		Connection con = null;
		try {
			con = PoolConexoesMysql.getConnection();
			stm = con.prepareStatement("Update usuario set nome=?, idade=? where id=?");
			
			stm.setString(1, usuario.getNome());
			stm.setInt(2, usuario.getIdade());
			stm.setLong(3, usuario.getId());
						
			return stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if(con != null){
					con.close();
				}
				if(stm != null){
					stm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
	}
	
	public static int excluir(Long idUsuario){
		PreparedStatement stm = null;
		Connection con = null;
		
		try {
			con = PoolConexoesMysql.getConnection();
			stm = con.prepareStatement("delete from usuario where id=?");
			stm.setLong(1, idUsuario);
			
			return stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if(con != null){
					con.close();
				}
				if(stm != null){
					stm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		
	}
}
