<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="js/jquery.maskedinput.js" type="text/javascript"></script>
		<script src="js/jquery.cpfvalidator.js" type="text/javascript"></script>
<%@page contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Título aqui</title>

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<script>
		function focar() {
			document.getElementById("usuario").focus();
		}
	</script>
	<style>
		input.span3, textarea.span3, .uneditable-input.span3 {
		    width: 210px;
		}
		.well {
		    width: 230px;
		    height: 250px;
		}
	</style>
</head>
<body>
<form action="j_spring_security_check" method="post">
<div class="wrapper">
	<div class="header">
    	<div class="container">
        	<div class="row branding">
            	<div class="span6">
                	<h1 class="pull-left"><a href="index.html"><strong>Corporatum</strong></a></h1>
                </div>            	
            </div>
           
        </div>        
    </div>
            <div class="span4 sidebar">
                <div class="well quickSignupForm">
                  <h3>Acesso ao Sistema</h3>
					<%
					if (request.getParameter("msg") != null) {
						out.print("<span style='color: red;font-weight: bold;'>Usuário ou senha incorretos</span>");
					}%>		
                  <label>CPF</label>
                  <input type="text" id="usuario" name="j_username" class="form-control" placeholder="Informe seu CPF." maxlength="14" required="true" autofocus="true" />
                  <script>
								$("#usuario").mask("999.999.999-99");
								/*$("#j_username").mask("999.999.999-99");*/
				  </script>
                  <label>Senha</label>
                  <input name="j_password" type="password" class="span3" placeholder="Informe sua senha."/>
                  <br/>
                  <br/>
                  <table>
                  		<tr>
                  			<td>
                  				<input class="btn btn-large btn-success btnSignup" type="submit" value="Entrar" />
                  			</td>
                  			<td class="btn-confirma">
                  				<h2><a href="" class="btn btn-large btn-warning btn-position">Cadastre-se</a></h2>
                  			</td>
                  		</tr>
                  </table>                  
                </div>
            </div>
            
    	</div>
    </div>
</div>
</body>
</html>