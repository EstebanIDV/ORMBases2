package com.example.demo.conf;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DSConf {
    private static ComboPooledDataSource Ds; //Atributo ComboPooledDataSource

    //Aqui se crea el datasource
	@Bean
	public DataSource dataSource(C3P0DataSourceProperties dataSourcePros) throws PropertyVetoException {

		ComboPooledDataSource pooledDataSource = getDs();//Se llama el get instace que tiene patron singleton 
        //para garantizar que es el mismo PooledDataSource y no se crean otros Pool
        
        //Ademas se setean los valores anteriormente definidos en el aplication properties  
		pooledDataSource.setDriverClass(dataSourcePros.getDriverClass());
		pooledDataSource.setUser(dataSourcePros.getUsername());
		pooledDataSource.setPassword(dataSourcePros.getPassword());
		pooledDataSource.setJdbcUrl(dataSourcePros.getUrl());
		pooledDataSource.setMaxPoolSize(dataSourcePros.getMaxPoolSize());
		pooledDataSource.setMaxIdleTime(dataSourcePros.getMaxIdleTime());
        pooledDataSource.setInitialPoolSize(dataSourcePros.getInitialPoolSize());

		return pooledDataSource;
	}

    //Metodo para crear el ComboPooledDataSource en caso de ser null o retornar el pool creado anteriormente (patron Singleton)
    public static ComboPooledDataSource getDs() { 
		if (Ds == null) { 
			Ds = new ComboPooledDataSource();
		}
		return Ds;
	}
}