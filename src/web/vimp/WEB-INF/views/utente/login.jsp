<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO UTENTE-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title"><spring:message code="login.access_to_the_work_policies_platform" text="Accedi alla Piattaforma Politiche del Lavoro"/> </h1>               
            </div>
         </div>
    </div>
</div>
<!-- End page header -->



 

        <!-- register-area -->
        <div class="register-area" style="background-color: rgb(249, 249, 249);">
            <div class="container">
            
            <c:if test="${!empty warningMessage}">
				<div class="alert alert-warning fade in">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<p>
						<strong><spring:message code="warning" text="Attenzione!"/></strong> ${warningMessage}
					</p>
				</div>
			</c:if>
	
			<c:if test="${!empty errorMessage}">
				<div class="alert alert-danger fade in">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<p>
						<strong><spring:message code="error" text="Errore"/></strong> ${errorMessage}
					</p>
				</div>
			</c:if>	
            
            <c:if test="${!empty registerMessage}">
				<div class="alert alert-success fade in">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<p>${registerMessage}</p>
				</div>
			</c:if>
               
                <div class="col-md-6">
                    <div class="box-for overflow">
                        <div class="col-md-12 col-xs-12 register-blocks">
                            <h2><spring:message code="login.new_account" text="Nuovo account"/> : </h2> 
                            <form:form id="frmRegistrati" method="POST" action="/vimp/registra" role="form" modelAttribute="dettaglio">
                            <form:hidden id="currentCall" path="currentCall" />	
                            
                            <form:hidden id="funzioneImpresaRichiesta" path="funzioneImpresaRichiesta" />
							<form:hidden id="funzioneFormatoreRichiesta" path="funzioneFormatoreRichiesta" />
							<form:hidden id="funzioneOpportunitaRichiesta" path="funzioneOpportunitaRichiesta" />
							<form:hidden id="funzioneProgettoRicercaRichiesta" path="funzioneProgettoRicercaRichiesta" />
                            
                            	<div class="form-group">
                                    <label for="cognome"><spring:message code="surname" text="Cognome"/></label>
                                    <input name="cognome" type="text"
										class="form-control" placeholder=""
										value="${dettaglio.cognome}" path="cognome" maxlength="200">
                                </div>
                                <div class="form-group">
                                    <label for="nome"><spring:message code="name" text="Nome"/></label>
                                    <input name="nome" type="text"
										class="form-control" placeholder=""
										value="${dettaglio.nome}" path="nome" maxlength="200">
                                </div>
                                <div class="form-group">
                                    <label for="email"><spring:message code="mail" text="Email"/></label>
                                    <input name="email" type="text"
										class="form-control" placeholder=""
										value="${dettaglio.email}" path="email" maxlength="200">
                                </div>
                                <div class="form-group">
                                    <label for="password"><spring:message code="password" text="Password"/></label>
                                    <input name="password" type="password"
										class="form-control" placeholder=""
										value="${dettaglio.password}" id="password" path="password" maxlength="16">
                                </div>
                                <div class="form-group">
                                    <label for="repassword"><spring:message code="login.repeat_password" text="Ripeti password"/></label>
                                    <input name="repassword" type="password"
										class="form-control" placeholder=""
										value="${dettaglio.repassword}" id="repassword" path="repassword" maxlength="16">
                                </div>
                                
                                
	                           <div class="profiel-header">
									<div class="col-md-12 col-xs-12 register-blocks">
									 <h2><spring:message code="login.what_data_do_you_care_about" text="Quali dati ti interessano?"/></h2> 
									 </div>
									<hr>
							
									<h3>
									<small><spring:message code="login.which_data_updates_on_the_work_policies_platform" text="Quali dati aggiorna sulla Piattaforma Politiche Lavoro?"/></small>
									</h3>
									<br />
								</div>
								
								<div class="form-group">
									<div class="checkbox">
			                            <label>
			                                <input id="checkboxImprese" name="checkboxImprese" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="common_texts.enterprises" text="Imprese"/></strong>
			                            </label>
									</div>
								</div>
								<div class="form-group">
									<div class="checkbox">
			                            <label>
			                                <input id="checkboxFormatori" name="checkboxFormatori" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="form.dettaglio.utente.trainers" text="Formatori"/></strong>
			                            </label>
									</div>
								</div>
								<div class="form-group">
									<div class="checkbox">
			                            <label>
			                                <input id="checkboxOpportunita" name="checkboxOpportunita" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="menu.opportunities" text="Opportunit&agrave;"/></strong>
			                            </label>
									</div>
								</div>
								<div class="form-group">
									<div class="checkbox">
			                            <label>
			                                <input id="checkboxProgettoRicerca" name="checkboxProgettoRicerca" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="form.dettaglio.utente.research_project" text="Progetto ricerca"/></strong>
			                            </label>
									</div>
									<br />
								</div>
                                
                                <div class="text-center">
                                    <button type="submit" class="btn btn-default"><spring:message code="sign_up" text="Registrati"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="box-for overflow">                         
                        <div class="col-md-12 col-xs-12 login-blocks">
                            <h2><spring:message code="log_in" text="Collegati"/> : </h2> 
                            <form:form id="frmAutentica" method="POST" action="/vimp/autentica" role="form" modelAttribute="dettaglio">
                            <form:hidden id="currentCall" path="currentCall" />	
                            
                                <div class="form-group">
                                    <label for="email"><spring:message code="mail" text="Email"/></label>
                                    <input name="email" type="text"
										class="form-control" placeholder=""
										value="${dettaglio.email}" path="email" maxlength="200">
                                </div>
                                <div class="form-group">
                                    <label for="password"><spring:message code="password" text="Password"/></label>
                                    <input name="password" type="password"
										class="form-control" placeholder=""
										value="${dettaglio.password}" path="password" maxlength="16">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-default"> <spring:message code="log_in" text="Collegati"/></button>
                                </div>
                            </form:form>
                        </div>
                        
                    </div>
                </div>

            </div>
        </div>      


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery-1.10.2.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/bootstrap/js/bootstrap.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-select.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-hover-dropdown.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/easypiechart.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.easypiechart.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/owl.carousel.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wow.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/icheck.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/price-range.js"></script>

 
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>


<script>

	$(document).ready(
		function() {

			var checkPasswordMessage;
			$.validator.addMethod('checkPassword', function (value, element) {
				if (value)
				{
					var test = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/.test(value);
					if (!test)
					{
						checkPasswordMessage = "<spring:message code="errorePasswordRequirements" javaScriptEscape="true" text="La password deve contenere almeno un numero e una lettera maiuscola e minuscola e almeno 8 o più caratteri"/>";
						return false;
					}
				}
				return true;
				
			}, function(params, element) {
			  return checkPasswordMessage;
			});
			


			$('#frmAutentica').validate({
		        rules: {
		        	email: "required",
		        	password: "required"
		        },
		        messages: {
		        	email: "<spring:message code="loginInserisciMail" javaScriptEscape="true" text="Inserire l'indirizzo e-Mail"/>",
		        	password: "<spring:message code="loginInserisciPass" javaScriptEscape="true" text="Inserire la password"/>"
		        }
		    });

			$('#frmRegistrati').validate({
		        rules: {
		        	cognome: "required",
		        	nome: "required",
		        	email: "required",
		        	password: { checkPassword : true, maxlength: 16 },
					repassword: {equalTo: "#password" }
		        },
		        messages: {
		        	cognome: "<spring:message code="loginInserisciCognome" javaScriptEscape="true" text="Inserire il cognome"/>",
		        	nome: "<spring:message code="loginInserisciNome" javaScriptEscape="true" text="Inserire il nome"/>",
		        	email: "<spring:message code="loginInserisciMail" javaScriptEscape="true" text="Inserire l'indirizzo e-Mail"/>",
		        	repassword: "<spring:message code="errorePasswordNoMatch" javaScriptEscape="true" text="Le password non coincidono"/>"
		        }
		    });

			initCheckboxArea();
			
	});


	function initCheckboxArea()
	{
		$('#checkboxImprese').on('ifChecked', function(event){
			  $('#funzioneImpresaRichiesta').val('true')
		});
		$('#checkboxImprese').on('ifUnchecked', function(event){
			 $('#funzioneImpresaRichiesta').val('false')
		});
		
		$('#checkboxFormatori').on('ifChecked', function(event){
			  $('#funzioneFormatoreRichiesta').val('true')
		});
		$('#checkboxFormatori').on('ifUnchecked', function(event){
			 $('#funzioneFormatoreRichiesta').val('false')
		});

		$('#checkboxOpportunita').on('ifChecked', function(event){
			  $('#funzioneOpportunitaRichiesta').val('true')
		});
		$('#checkboxOpportunita').on('ifUnchecked', function(event){
			 $('#funzioneOpportunitaRichiesta').val('false')
		});

		$('#checkboxProgettoRicerca').on('ifChecked', function(event){
			  $('#funzioneProgettoRicercaRichiesta').val('true')
		});
		$('#checkboxProgettoRicerca').on('ifUnchecked', function(event){
			 $('#funzioneProgettoRicercaRichiesta').val('false')
		});
	}
	


</script>