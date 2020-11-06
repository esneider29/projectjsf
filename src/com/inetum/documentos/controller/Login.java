package com.inetum.documentos.controller;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.inetum.documentos.dao.FormatoDao;
import com.inetum.documentos.dao.FormatoDao2;
import com.inetum.documentos.entity.Formato;

//@ManagedBean(name = Login.MANAGED_BEAN_NAME)
@Named("login")
@SessionScoped
public class Login implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String MANAGED_BEAN_NAME="login";
	private String saludo ="Bienvenido al administrador de claves";
	
//	@EJB	
//	private FormatoDao2 dao2;
	    
	private FormatoDao dao = new FormatoDao();
	
	public Login() {
		
	}
	
	public String getSaludo(){
		System.out.println("saludando.....");
		Formato formato = new Formato();
		formato.setNombre("formato5");
		formato.setFechahoracreacion(new Date());
		
		//Optional<Formato> saved = dao.save(formato);
		//saved.ifPresent(s -> System.out.println(s.getNombre()));
		
	    //dao2.save(formato);
		return this.saludo;
	}

}
