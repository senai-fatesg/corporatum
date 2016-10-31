<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		jQuery(function($) {
			$("#usuario").mask("999.999.999-99");
		});
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
    
    <!--  
	<div class="container content">
        <div class="row">
            <div class="span8 leftContent">
            	<h2>subtítulo sistema</h2>
                <div class="row">
                	<div class="span4">
                    	<p class="cntPara simpleDesign" align="justify">
                        	<strong class="lead">Titulo caixa 1</strong>
							Conteúdo caixa 1                        	
                        </p>            	                                                
                    </div>
                    
                    <div class="span4">
                    	<p class="cntPara itsFree" align="justify">
                        	<strong class="lead">Título caixa 2</strong>
							Conteúdo caixa 2
                       </p>      
                    </div>                                        
                </div>
                <hr/>
                <div class="row">
                	<div class="span4">
                    	<p class="cntPara secureApp"  align="justify">
                        	<strong class="lead">Título caixa 2</strong>
							conteúdo caixa 3
                    </div>
                    
                    <div class="span4">
                    	<p class="cntPara easyUse">
                        	<strong class="lead">Título caixa 4</strong>
							conteúdo caixa 4
                         </p>      
                    </div>                                        
                </div>
                <hr/>   
                <div class="row">
                	<div class="span8">
                    	<h3 class="quickTour">Rodapé</h3>
						conteúdo rodapé
                    </div>                                        
                </div>
            </div>
            -->
            
            <div class="span4 sidebar">
                <div class="well quickSignupForm">
                  <h3>Acesso ao Sistema</h3>
					<%
					if (request.getParameter("msg") != null) {
						out.print("<span style='color: red;font-weight: bold;'>Usuário ou senha incorretos</span>");
					}%>		
                  <label>CPF</label>
                  <input type="text" id="usuario" name="j_username" class="form-control" placeholder="Informe seu CPF." required="true" autofocus="true" />
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