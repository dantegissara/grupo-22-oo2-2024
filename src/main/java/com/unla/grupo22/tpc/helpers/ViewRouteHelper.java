package com.unla.grupo22.tpc.helpers;

public class ViewRouteHelper {
	/**** Vistas ****/ // VISTAS. LAS VISTAS SE DEFINEN EN LA PARTE DE TEMPLATES
	//INICIO
	public final static String INDEX = "home/index";

	//USER
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";
	
	//PRODUCTO
	public final static String PRODUCTO_INDEX = "producto/index";
	public final static String PRODUCTO_NEW = "producto/new";
	public final static String PRODUCTO_UPDATE = "producto/update"; // seccion html en templates -> producto -> update

	/**** Redirecciones ****/
	public final static String ROUTE = "/index";
	public final static String PRODUCTO_ROOT = "/producto";

}
