package br.com.ambientinformatica.fatesg.corporatum.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class FabricaLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		FabricaObjetoSpring.setContext(getServletContext());
	}

}