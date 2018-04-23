package br.com.anhanguera.cadastro.usuario.bd;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PoolConexoesMysql {
	
	private static ComboPooledDataSource cpds;
	
	private static ComboPooledDataSource getDataSource() 
			throws PropertyVetoException{
		if(cpds == null){
			//Inicializa o pool de conexoes
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/cadastro");
			cpds.setUser("root");
			cpds.setPassword("facnet");
			cpds.setMinPoolSize(5);
			cpds.setMaxPoolSize(30);	
		}
		return cpds;
	}
	
	public static Connection getConnection() 
			throws SQLException, PropertyVetoException{
		return getDataSource().getConnection();
	}

}
