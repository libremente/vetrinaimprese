<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO UTENTE-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
èèè
<spring:message var="selectRoleTitleAttr" code="form.dettaglio.utente.selectRoleTitleAttr" text="Seleziona ruolo"/>

<!-- property area -->


<form:form id="frmDettaglio" method="POST" action="/vimp/salvaUtente"
	role="form" modelAttribute="dettaglio">

	<form:hidden id="idUtente" path="idUtente" />
	<form:hidden id="dataCreazione" path="dataCreazione" />
	<form:hidden id="dataModifica" path="dataModifica" />
	<form:hidden id="dataCancellazione" path="dataCancellazione" />
	<form:hidden id="loginOperazione" path="loginOperazione" />

	
	<c:if test="${aggiornamento}">
		
	</c:if>
	<form:hidden id="funzioneImpresa" path="funzioneImpresa" />
	<form:hidden id="funzioneFormatore" path="funzioneFormatore" />
	<form:hidden id="funzioneOpportunita" path="funzioneOpportunita" />
	<form:hidden id="funzioneProgettoRicerca" path="funzioneProgettoRicerca" />


	<input id="passwordStr" name="passwordObj.password" type="hidden"
		value="" />

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
						<strong><spring:message code="errore" text="Errore"/></strong> ${errorMessage}
					</p>
				</div>
			</c:if>

			<c:choose>
				<c:when test="${!utente.isAdministrator()}">
					<div class="alert alert-warning alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="operation_not_permitted" text="Operazione non permessa"/>.
					</div>
				</c:when>
				<c:otherwise>


					<c:choose>
						<c:when test="${!empty systemErrorMessage}">
							<div class="alert alert-danger fade in">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								<p>
									<strong><spring:message code="errore" text="Errore"/></strong> ${systemErrorMessage}
								</p>
							</div>
						</c:when>
						<c:otherwise>

							<div class="row">
								<div class="col-sm-10 col-sm-offset-1 profiel-container">


									<div class="profiel-header">
										<h3>

											<c:choose>
												<c:when test="${aggiornamento}">
													<b><spring:message code="manage.uppercase" text="GESTISCI"/></b> <spring:message code="user.uppercase" text="UTENTE"/> <br>
													<small></small>
												</c:when>
												<c:otherwise>
													<b><spring:message code="insert.uppercase" text="INSERISCI"/></b> <spring:message code="manage.uppercase" text="UTENTE"/> <br>
													<small></small>
												</c:otherwise>
											</c:choose>

										</h3>
										<hr>
									</div>

									<div class="clear">

										<div class="col-sm-5 col-sm-offset-1">

											<div class="form-group">
												<label><spring:message code="surname" text="Cognome"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label> <input
													name="cognome" type="text" class="form-control"
													placeholder="" value="${dettaglio.cognome}" path="cognome"
													maxlength="200">
											</div>
											<div class="form-group">
												<label><spring:message code="mail" text="Email"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label> <input
													name="email" type="text" class="form-control"
													placeholder="" value="${dettaglio.email}" path="email"
													maxlength="200">
											</div>

										</div>
										<div class="col-sm-5 ">
											<div class="form-group">
												<label><spring:message code="name" text="Nome"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label> <input
													name="nome" type="text" class="form-control" placeholder=""
													value="${dettaglio.nome}" path="nome" maxlength="200">
											</div>
											<div class="form-group">
												<label><spring:message code="password" text="Password"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label> <input
													readonly id="password" type="text" class="form-control"
													placeholder="">


												<button type="button"
													class="navbar-btn nav-button wow bounceInRight login"
													onclick="javascript:generaPassword();"
													name="passwordButton" id="passwordButton"
													data-wow-delay="0.45s"><spring:message code="form.dettaglio.utente.generate_reset_password" text="Genera/Reset password"/></button>

											</div>
										</div>

									</div>

									<div class="clear">
										<hr>
										<div class="profiel-header">
											<h3>
												<small><spring:message code="form.dettaglio.utente.role_assignment" text="Assegnazione ruolo"/></small>
											</h3>
											<br>
										</div>
									</div>
									<div class="clear">
										<div class="col-sm-10 col-sm-offset-1">
											<div class="form-group">
												<form:select id="selectRuolo" name="selectRuolo"
													title="${selectRoleTitleAttr}" data-live-search="true"
													data-live-search-style="contains" path="ruolo.idRuolo"
													cssClass="selectpicker"
													onchange="javascript:updateCheckboxArea(this.options[selectedIndex].value);">
													<form:options items="${ruoliList}" />
												</form:select>

												<br /> <br />
												<div class="profiel-header center">
													<h3>
														<small><spring:message code="form.dettaglio.utente.which_data_updates_on_the_work_policies.platform" text="Quali dati aggiorna sulla Piattaforma Politiche Lavoro?"/></small>
													</h3>
												</div>

												<div class="col-sm-10">&nbsp;</div>
												<div class="col-sm-10">&nbsp;</div>

												<div id="checkboxArea" name="checkboxArea" class="col-sm-12">
													<div class="col-sm-3">
														<div class="checkbox">
															<label class="center"> <input
																id="checkboxImprese" name="checkboxImprese"
																type="checkbox" /><br /> <strong>&nbsp;<spring:message code="common_texts.enterprises" text="Imprese"/></strong>
																<label id="richiestaImprese" class="center error"><spring:message code="form.dettaglio.utente.user_request" text="Richiesta utente"/></label>
															</label>

														</div>
													</div>
													<div class="col-sm-3">
														<div class="checkbox">
															<label class="center"> <input
																id="checkboxFormatori" name="checkboxFormatori"
																type="checkbox" /><br /> <strong>&nbsp;<spring:message code="form.dettaglio.utente.trainers" text="Formatori"/></strong>
																<label id="richiestaFormatori" class="center error"><spring:message code="form.dettaglio.utente.user_request" text="Richiesta utente"/></label>
															</label>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="checkbox">
															<label class="center"> <input
																id="checkboxOpportunita" name="checkboxOpportunita"
																type="checkbox" /><br /> <strong>&nbsp;<spring:message code="home.list.opportunity" text="Opportunità"/></strong>
																<label id="richiestaOpportunita" class="center error"><spring:message code="form.dettaglio.utente.user_request" text="Richiesta utente"/></label>
															</label>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="checkbox">
															<label class="center"> <input
																id="checkboxProgettoRicerca"
																name="checkboxProgettoRicerca" type="checkbox" /><br />
																<strong>&nbsp;<spring:message code="form.dettaglio.utente.research_project" text="Progetto ricerca"/></strong> <label
																id="richiestaProgettoRicerca" class="center error"><spring:message code="form.dettaglio.utente.user_request" text="Richiesta utente"/></label>
															</label>
														</div>
													</div>
												</div>

												<div class="col-sm-10">&nbsp;</div>
											</div>
										</div>

									</div>

									<div class="col-sm-10 col-sm-offset-1">

										<br>


										<c:if test="${aggiornamento}">
											<a href="#" id="deleteButton"
												class='btn btn-finish btn-primary pull-left'
												data-toggle="modal" data-target="#chiudiModal"
												value='CANCELLA UTENTE'><spring:message code="form.dettaglio.utente.delete_user" text="CANCELLA UTENTE"/></a>
										</c:if>



										<button id="saveButton"
											class='btn btn-finish btn-primary pull-right'
											onClick="javascript:aggiorna();" value='APPLICA MODIFICHE'><spring:message code="common_texts.apply_changes" text="APPLICA MODIFICHE"/></button>
									</div>
									<br>


									<!-- Modal -->
									<div class="modal fade" id="chiudiModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">


											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only"><spring:message code="close" text="Chiudi"/></span>
													</button>
													<h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.utente.delete" text="Cancellazione utente"/></h4>
												</div>
												<div class="modal-body">
													<strong><spring:message code="warning" text="Attenzione!"/> </strong> <spring:message code="form.dettaglio.utente.deletion_text" text="Procedere con la cancellazione dell'utente"/> <b>${dettaglio.cognome}
														${dettaglio.nome}</b> ?<br>

												</div>
												<div class="modal-footer">
													<a type="button" class="btn btn-default"
														data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
													<button type="button" class="btn btn-primary"
														onClick="javascript:cancella();"><spring:message code="delete.uppercase" text="CANCELLA"/></button>
												</div>
											</div>

										</div>
									</div>


								</div>
							</div>
							<!-- end row -->

						</c:otherwise>
					</c:choose>

				</c:otherwise>
			</c:choose>

		</div>
	</div>
</form:form>

<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery-1.10.2.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-select.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-hover-dropdown.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/easypiechart.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.easypiechart.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/owl.carousel.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wow.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/icheck.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/price-range.js"></script>


<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js"
	type="text/javascript"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>


<script>

	$(document).ready(
		function() {


			$('#frmDettaglio').validate({
		        rules: {
		            nome: "required",
		            cognome: "required",
		            email: "required",
		            "ruolo.idRuolo": "required"
		        },
		        messages: {
		        	nome: "<spring:message code="utenteFormDettaglioInserireNome" javaScriptEscape="true" text="Inserire il nome"/>",
		        	cognome: "<spring:message code="utenteFormDettaglioInserireCognome" javaScriptEscape="true" text="Inserire il cognome"/>",
		        	email: "<spring:message code="utenteFormDettaglioInserireEmail" javaScriptEscape="true" text="Inserire l' e-Mail"/>",
		        	"ruolo.idRuolo": "<spring:message code="utenteFormDettaglioInserireSelezionareRuolo" javaScriptEscape="true" text="Selezionare il ruolo"/>"
		        }
		    });

			initCheckboxArea();
			updateCheckboxArea(${dettaglio.ruolo.idRuolo});
	});

	function aggiorna() {

		
		
		$("#frmDettaglio").submit();
	}

	function cancella() {
		$('#frmDettaglio').attr('action', '/vimp/cancellaUtente');
		$("#frmDettaglio").submit();
	}

	function initCheckboxArea()
	{
		if (${dettaglio.funzioneImpresa})
			$('#checkboxImprese').iCheck('check');
		else 
			$('#checkboxImprese').iCheck('uncheck');
		$('#checkboxImprese').on('ifChecked', function(event){
			  $('#funzioneImpresa').val('true')
		});
		$('#checkboxImprese').on('ifUnchecked', function(event){
			 $('#funzioneImpresa').val('false')
		});
		if (${dettaglio.funzioneImpresaRichiesta})
			$('#richiestaImprese').show();
		else $('#richiestaImprese').hide();
		
		

		if (${dettaglio.funzioneFormatore})
			$('#checkboxFormatori').iCheck('check');
		else 
			$('#checkboxFormatori').iCheck('uncheck');
		$('#checkboxFormatori').on('ifChecked', function(event){
			  $('#funzioneFormatore').val('true')
		});
		$('#checkboxFormatori').on('ifUnchecked', function(event){
			 $('#funzioneFormatore').val('false')
		});
		if (${dettaglio.funzioneFormatoreRichiesta})
			$('#richiestaFormatori').show();
		else $('#richiestaFormatori').hide();

		

		if (${dettaglio.funzioneOpportunita})
			$('#checkboxOpportunita').iCheck('check');
		else 
			$('#checkboxOpportunita').iCheck('uncheck');
		$('#checkboxOpportunita').on('ifChecked', function(event){
			  $('#funzioneOpportunita').val('true')
		});
		$('#checkboxOpportunita').on('ifUnchecked', function(event){
			 $('#funzioneOpportunita').val('false')
		});
		if (${dettaglio.funzioneOpportunitaRichiesta})
			$('#richiestaOpportunita').show();
		else $('#richiestaOpportunita').hide();
		

		if (${dettaglio.funzioneProgettoRicerca})
			$('#checkboxProgettoRicerca').iCheck('check');
		else 
			$('#checkboxProgettoRicerca').iCheck('uncheck');
		$('#checkboxProgettoRicerca').on('ifChecked', function(event){
			  $('#funzioneProgettoRicerca').val('true')
		});
		$('#checkboxProgettoRicerca').on('ifUnchecked', function(event){
			 $('#funzioneProgettoRicerca').val('false')
		});
		if (${dettaglio.funzioneProgettoRicercaRichiesta})
			$('#richiestaProgettoRicerca').show();
		else $('#richiestaProgettoRicerca').hide();

		
	}


	function updateCheckboxArea(selectedRuolo) {
		if (selectedRuolo == 2) {
			$('#checkboxImprese').iCheck('enable');
			$('#checkboxFormatori').iCheck('enable');
			$('#checkboxOpportunita').iCheck('enable');
			$('#checkboxProgettoRicerca').iCheck('enable');
		}
		else {
			$('#checkboxImprese').iCheck('disable');
			$('#checkboxFormatori').iCheck('disable');
			$('#checkboxOpportunita').iCheck('disable');
			$('#checkboxProgettoRicerca').iCheck('disable');
		}

	}


	function generaPassword() {
		var text = "";
		var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789&%$£!";

		for (var i = 0; i < 8; i++)
			text += possible
					.charAt(Math.floor(Math.random() * possible.length));

		$('#password').val(text);
		$('#passwordStr').val(text);
	}
</script>
