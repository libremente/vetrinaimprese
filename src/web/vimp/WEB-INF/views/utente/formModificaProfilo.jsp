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
				<h1 class="page-title">
					Ciao : <span class="orange strong">${dettaglio.nome} ${dettaglio.cognome}</span>
				</h1>
			</div>
		</div>
	</div>
</div>
<!-- End page header -->

<!-- property area -->

<form:form id="frmDettaglio" method="POST" action="/vimp/salvaProfilo" role="form" modelAttribute="dettaglio">

<form:hidden id="idUtente" path="idUtente" />	

<form:hidden id="funzioneImpresaRichiesta" path="funzioneImpresaRichiesta" />
<form:hidden id="funzioneFormatoreRichiesta" path="funzioneFormatoreRichiesta" />
<form:hidden id="funzioneOpportunitaRichiesta" path="funzioneOpportunitaRichiesta" />
<form:hidden id="funzioneProgettoRicercaRichiesta" path="funzioneProgettoRicercaRichiesta" />



<div class="content-area user-profiel">
	&nbsp;
	<div class="container">
	
	
		<c:if test="${!empty successMessage}">
			<div class="alert alert-success fade in">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<p>${successMessage}</p>
			</div>
		</c:if>
	
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
	
	
	
		<c:choose>
		<c:when test="${!empty systemErrorMessage}">
			<div class="alert alert-danger fade in">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<p>
					<strong><spring:message code="error" text="Errore"/></strong> ${errorMessage}
				</p>
			</div>
		</c:when>
		<c:otherwise>
		
		
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1 profiel-container">

				<form action="" method="">
					<div class="profiel-header">
						<h3>
							<b><spring:message code="modify.uppercase" text="MODIFICA"/></b> <spring:message code="form.modifica.profilo.your_profile" text="IL TUO PROFILO"/> <br> 
							<small><spring:message code="form.modifica.profilo.this_informations_help_us_know_each_other" text="Queste informazioni aiutano a conoscerci"/></small>
						</h3>
						<hr>
					</div>

					<div class="clear">

						<div class="col-sm-5 col-sm-offset-1">
						
							<div class="profiel-header">
								<div class="col-md-12 col-xs-12 register-blocks">
								 <h2><spring:message code="form.modifica.profilo.user_data" text="Dati utente"/> : </h2> 
								 </div>
								<hr>
							</div>

							<div class="form-group">
								<label><spring:message code="surname" text="Cognome"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label> 
								<input name="cognome" type="text"
									class="form-control" placeholder=""
									value="${dettaglio.cognome}" path="cognome" maxlength="200">
							</div>
							<div class="form-group">
								<label><spring:message code="name" text="Nome"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label>
								<input name="nome" type="text"
									class="form-control" placeholder=""
									value="${dettaglio.nome}" path="nome" maxlength="200">
							</div>
							<div class="form-group">
								<label><spring:message code="mail" text="Email"/></label>
								<input name="email" type="text"
									class="form-control" placeholder=""
									value="${dettaglio.email}" path="email" maxlength="200" disabled>
							</div>
							
							<c:if test="${!utente.isAdministrator()}">
							<div class="profiel-header">
								<div class="col-md-12 col-xs-12 register-blocks">
								 <h2><spring:message code="form.modifica.profilo.what_data_interests_you" text="Quali dati ti interessano?"/></h2> 
								 </div>
								<hr>
							</div>
							
							<div class="form-group">
								<div class="checkbox">
		                            <label>
		                                <input id="checkboxImprese" name="checkboxImprese" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="common_texts.enterprises" text="Imprese"/></strong>
		                                <c:if test="${dettaglio.funzioneImpresaRichiesta}">
		                                	<label class="center error"><small>(<spring:message code="request" text="Richiesta"/>)</small></label>
		                                </c:if>
		                                <c:if test="${dettaglio.funzioneImpresa}">
		                                	<label class="center error"><small>&nbsp;&nbsp;&nbsp;<spring:message code="attivata.uppercase" text="ATTIVATA"/></small></label>
		                                </c:if>
		                            </label>
								</div>
							</div>
							<div class="form-group">
								<div class="checkbox">
		                            <label>
		                                <input id="checkboxFormatori" name="checkboxFormatori" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="form.dettaglio.utente.trainers" text="Formatori"/></strong>
		                                <c:if test="${dettaglio.funzioneFormatoreRichiesta}">
		                                	<label class="center error"><small>(<spring:message code="request" text="Richiesta"/>)</small></label>
		                                </c:if>
		                                <c:if test="${dettaglio.funzioneFormatore}">
		                                	<label class="center error"><small>&nbsp;&nbsp;&nbsp;<spring:message code="attivata.uppercase" text="ATTIVATA"/></small></label>
		                                </c:if>
		                            </label>
								</div>
							</div>
							<div class="form-group">
								<div class="checkbox">
		                            <label>
		                                <input id="checkboxOpportunita" name="checkboxOpportunita" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="home.list.opportunity" text="Opportunit&agrave;"/></strong>
		                                <c:if test="${dettaglio.funzioneOpportunitaRichiesta}">
		                                	<label class="center error"><small>(<spring:message code="request" text="Richiesta"/>)</small></label>
		                                </c:if>
		                                <c:if test="${dettaglio.funzioneOpportunita}">
		                                	<label class="center error"><small>&nbsp;&nbsp;&nbsp;<spring:message code="attivata.uppercase" text="ATTIVATA"/></small></label>
		                                </c:if>
		                            </label>
								</div>
							</div>
							<div class="form-group">
								<div class="checkbox">
		                            <label>
		                                <input id="checkboxProgettoRicerca" name="checkboxProgettoRicerca" type="checkbox" /><strong>&nbsp;&nbsp;&nbsp;<spring:message code="form.dettaglio.utente.research_project" text="Progetto ricerca"/></strong>
		                                <c:if test="${dettaglio.funzioneProgettoRicercaRichiesta}">
		                                	<label class="center error"><small>(<spring:message code="request" text="Richiesta"/>)</small></label>
		                                </c:if>
		                                <c:if test="${dettaglio.funzioneProgettoRicerca}">
		                                	<label class="center error"><small>&nbsp;&nbsp;&nbsp;<spring:message code="attivata.uppercase" text="ATTIVATA"/></small></label>
		                                </c:if>
		                            </label>
								</div>
							</div>
							</c:if>
						</div>
						<div class="col-sm-5 ">
						
							<div class="profiel-header">
								<div class="col-md-12 col-xs-12 register-blocks">
								 <h2><spring:message code="form.modifica.profilo.change_password" text="Cambio password"/> : </h2> 
								 </div>
								<hr>
							</div>
							
							<c:if test="${!empty errorMessagePwd}">
								<div class="alert alert-danger fade in">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<p>
										<strong><spring:message code="error" text="Errore"/></strong> ${errorMessagePwd}
									</p>
								</div>
							</c:if>	
					
							<div class="form-group">
								<label><small><spring:message code="form.modifica.profilo.old" text="Vecchia"/></small> <spring:message code="password" text="Password"/></label> 
								<input name="oldpassword" type="password"
									class="form-control" placeholder=""
									value="${dettaglio.oldpassword}" id="oldpassword" path="oldpassword" maxlength="16" >
							</div>
							<div class="form-group">
								<label><small><spring:message code="form.modifica.profilo.new" text="Nuova"/></small> <spring:message code="password" text="Password"/></label> 
								<input name="password" type="password"
									class="form-control" placeholder=""
									value="${dettaglio.password}"  id="password" path="password" maxlength="16" >
							</div>
							<div class="form-group">
								<label><small><spring:message code="form.modifica.profilo.confirm_password" text="Conferma"/></small> <spring:message code="password" text="Password"/></label> 
								<input name="repassword" type="password"
									class="form-control" placeholder=""
									value="${dettaglio.repassword}" id="repassword" path="repassword" maxlength="16" >
							</div>
							<div class="form-group">
								&nbsp;
							</div>
						</div>

					</div>

				
					<div class="col-sm-10 col-sm-offset-1">
						<br> 
						<button id="saveButton"
							class='btn btn-finish btn-primary pull-right'
							onClick="javascript:aggiorna();"
							value='APPLICA MODIFICHE'><spring:message code="common_texts.apply_changes" text="APPLICA MODIFICHE"/></button>
					</div>
					<br>
				</form>

			</div>
		</div>
		<!-- end row -->

		</c:otherwise>
		</c:choose>	
	</div>
</div>

</form:form>


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
					if (test)
					{
						var oldp = $('#oldpassword').val();
						if (oldp == "")
						{
							checkPasswordMessage = "<spring:message code="profiloFormModificaPasswordInserireVecchia" javaScriptEscape="true" text="Inserire la vecchia password"/>";
							return false;
						}
						else return true;
					}
					else
					{
						checkPasswordMessage = "<spring:message code="errorePasswordRequirements" javaScriptEscape="true" text="La password deve contenere almeno un numero e una lettera maiuscola e minuscola e almeno 8 o più caratteri"/>";
						return false;
					}
				}
				return true;
				
			}, function(params, element) {
			  return checkPasswordMessage;
			});

			
			$('#frmDettaglio').validate({
		        rules: {
		        	cognome: "required",
		        	nome: "required",
		        	password: { checkPassword : true, maxlength: 16 },
					repassword: {equalTo: "#password" }
		        },
		        messages: {
		        	cognome: "<spring:message code="profiloFormModificaPasswordInserisciCognome" javaScriptEscape="true" text="Inserire il cognome"/>",
		        	nome: "<spring:message code="profiloFormModificaPasswordInserisciNome" javaScriptEscape="true" text="Inserire il nome"/>",
		        	repassword: "<spring:message code="errorePasswordNoMatch" javaScriptEscape="true" text="Le password non coincidono"/>"
		        }
		    });


			initCheckboxArea();
			
	});

	function aggiorna() {
		$("#frmDettaglio").submit();
	}

	function initCheckboxArea()
	{
		if (${dettaglio.funzioneImpresa})
		{
			$('#checkboxImprese').iCheck('check');
			$('#checkboxImprese').iCheck('enable');
		}
		else 
			$('#checkboxImprese').iCheck('uncheck');
		$('#checkboxImprese').on('ifChecked', function(event){
			  $('#funzioneImpresaRichiesta').val('true')
		});
		$('#checkboxImprese').on('ifUnchecked', function(event){
			 $('#funzioneImpresaRichiesta').val('false')
		});
		


		if (${dettaglio.funzioneFormatore})
		{
			$('#checkboxFormatori').iCheck('check');
			$('#checkboxFormatori').iCheck('enable');
		}
		else 
			$('#checkboxFormatori').iCheck('uncheck');
		$('#checkboxFormatori').on('ifChecked', function(event){
			  $('#funzioneFormatoreRichiesta').val('true')
		});
		$('#checkboxFormatori').on('ifUnchecked', function(event){
			 $('#funzioneFormatoreRichiesta').val('false')
		});


		if (${dettaglio.funzioneOpportunita})
		{
			$('#checkboxOpportunita').iCheck('check');
			$('#checkboxOpportunita').iCheck('enable');
		}
		else 
			$('#checkboxOpportunita').iCheck('uncheck');
		$('#checkboxOpportunita').on('ifChecked', function(event){
			  $('#funzioneOpportunitaRichiesta').val('true')
		});
		$('#checkboxOpportunita').on('ifUnchecked', function(event){
			 $('#funzioneOpportunitaRichiesta').val('false')
		});


		if (${dettaglio.funzioneProgettoRicerca})
		{
			$('#checkboxProgettoRicerca').iCheck('check');
			$('#checkboxProgettoRicerca').iCheck('enable');
		}
		else 
			$('#checkboxProgettoRicerca').iCheck('uncheck');
		$('#checkboxProgettoRicerca').on('ifChecked', function(event){
			  $('#funzioneProgettoRicercaRichiesta').val('true')
		});
		$('#checkboxProgettoRicerca').on('ifUnchecked', function(event){
			 $('#funzioneProgettoRicercaRichiesta').val('false')
		});
	}


</script>
