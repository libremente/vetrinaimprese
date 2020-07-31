	
<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO IMPRESA-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<spring:message var="selectStatusCompanyTitleAttr" code="form.dettaglio.accreditamento.selectStatusCompanyTitleAttr" text="Seleziona lo stato dell\'impresa"/>

<div class="row">
	<h2 class="center"><small><spring:message code="form.dettaglio.accreditamento.title" text="Richiesta accreditamento"/></small></h2>        
</div>
		    
<!-- End page header -->

<!-- property area -->
<form:form id="frmDettaglio" method="POST"
	action="/vimp/secure/validazioneAccreditamento" role="form"
	modelAttribute="dettaglio">
	
	
	<input id="azione" name="azione" type="hidden">
	
	<form:hidden id="idRichiestaAccreditamento" path="idRichiestaAccreditamento" />
	
	<form:hidden id="codFiscaleRichiedente" path="codFiscaleRichiedente" />
	<form:hidden id="dataRichiesta" path="dataRichiesta" />
	<form:hidden id="flagAccreditamento" path="flagAccreditamento" />
	<form:hidden id="ragioneSociale" path="ragioneSociale" />
	<c:if test="${!cambiaStatoImpresa}">
		<form:hidden id="plfTStatoImpresa.id" path="plfTStatoImpresa.id" />
	</c:if>
	<form:hidden id="codFiscale" path="codFiscale" />
	<form:hidden id="partitaIva" path="partitaIva" />
	<form:hidden id="emailContatto" path="emailContatto" />
	<form:hidden id="plfTComune.idComune" path="plfTComune.idComune" />
	<form:hidden id="descIndirizzo" path="descIndirizzo" />
	<form:hidden id="numeroCivico" path="numeroCivico" />
	
	
	
	<form:hidden id="plfImpresa.idPlfImpresa" path="plfImpresa.idPlfImpresa" />
	
	
	

<div class="content-area user-profiel">
	&nbsp;
	<div class="container">

		<c:if test="${!empty warningMessage}">
			<div class="alert alert-warning fade in">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<p>
					<strong><spring:message code="warning" text="Warning!"/></strong> ${warningMessage}
				</p>
			</div>
		</c:if>

		<c:if test="${!empty errorMessage}">
			<div class="alert alert-danger fade in">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<p>
					<strong><spring:message code="error" text="Error"/></strong> ${errorMessage}
				</p>
			</div>
		</c:if>



            
		<div class="row">
			<div class="col-sm-12 profiel-container">


				<div class="clear">
					<br>

					
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.codice_fiscale" text="Codice fiscale richiedente"/>:</label>
								<br><label><b>${dettaglio.codFiscaleRichiedente}</b></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.request_date" text="Data richiesta"/>:</label>
								<fmt:formatDate value="${dettaglio.dataRichiesta}"  
										 	type="date" 
							                pattern="dd/MM/yyyy"
							                var="dataRichiestaFormatted" />
								<br><label><b>${dataRichiestaFormatted}</b></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="menu.accreditation" text="Accreditamento"/>:</label>
								<br><label><b>${dettaglio.flagAccreditamento}</b></label>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/>:</label>
								<br><label><b>${dettaglio.ragioneSociale}</b></label>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.enterprise_status" text="Stato impresa"/>:</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="selectStatoImpresa"
												 title="${selectStatusCompanyTitleAttr}"
												 data-live-search="true" data-live-search-style="contains"
												 path="plfTStatoImpresa.id"
												 cssClass="selectpicker">
											<form:options items="${statoImpresaList}" />
										</form:select>
									</c:when>
									<c:otherwise>
										<br><label><b>${dettaglio.plfTStatoImpresa.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.enterprise_ci" text="Codice fiscale impresa"/>:</label>
								<br><label><b>${dettaglio.codFiscale}</b></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.iva" text="Partita iva"/>:</label>
								<br><label><b>${dettaglio.partitaIva}</b></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.contact_mail" text="Email contatto"/>:</label>
								<br><label><b>${dettaglio.emailContatto}</b></label>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.city" text="Comune"/>:</label>
								<br><label><b>${dettaglio.plfTComune.descComune}</b></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.address" text="Indirizzo"/>:</label>
								<br><label><b>${dettaglio.descIndirizzo}</b></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.civic_number" text="Numero civico"/>:</label>
								<br><label><b>${dettaglio.numeroCivico}</b></label>
							</div>
						</div>
					</div>
					
					
					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.request_status" text="Stato richiesta"/>:</label>
								<br><label><b>${dettaglio.descStatoRichiesta}</b></label>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.request_check" text="Controllo richiesta"/>:</label>
								<br><label><b>${dettaglio.descControlliRichiesta}</b></label>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="col-sm-12">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.request_opinion" text="Parere accreditamento"/>:</label>
								<c:choose>
										<c:when test="${modifica}">
											<input name="parereAccreditamento" type="text" class="form-control"
												placeholder="" value="${dettaglio.parereAccreditamento}"
												path="parereAccreditamento" maxlength="400">
										</c:when>
										<c:otherwise>
											<br>
											<br><label><b>${dettaglio.parereAccreditamento}</b></label>
										</c:otherwise>
									</c:choose>
									
							</div>
						</div>
					</div>
					
					
					<c:if test="${modifica}">
					<div class="col-sm-12">
						<div class="col-sm-6">&nbsp;</div>
						<div class="col-sm-6">&nbsp;</div>


						<div class="col-sm-12">
							<div class="col-sm-6">
								<button id="invalidaButton"
									class='btn btn-finish btn-primary'
									onClick="javascript:invalida(event);" value='INVALIDA LA RICHIESTA'><spring:message code="form.dettaglio.accreditamento.invalid_the_request" text="INVALIDA LA RICHIESTA"/></button>
							</div>
							<div class="col-sm-6">
								<button id="validaButton"
									class='btn btn-finish btn-primary pull-right'
									onClick="javascript:valida(event);" value='VALIDA LA RICHIESTA'><spring:message code="form.dettaglio.accreditamento.valid_the_request" text="VALIDA LA RICHIESTA"/></button>
							</div>
						</div>
					</div>
					</c:if>
					
					
				</div>
			</div>
		</div>

          	
		<!-- Modal -->
		<div class="modal fade" id="chiudiModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">


				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.accreditamento.delete" text="Cancellazione dell'opportunit&agrave;"/></h4>
					</div>
					<div class="modal-body">
						<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="form.dettaglio.accreditamento.confirm" text="Procedere con l'accettazione dell'accreditamento per l'impresa"/> ${dettaglio.ragioneSociale} (id <b>${dettaglio.idRichiestaAccreditamento}</b>)?<br>
					</div>
					<div class="modal-footer">
						<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo" text="Annulla"/></a>
						<button type="button" class="btn btn-primary"
							onClick="javascript:cancella();"><spring:message code="delete" text="Cancella"/></button>
					</div>
				</div>

			</div>
		</div>
		
	</div>
</div>


<div id="accreditamentoSpinner" style="display:none;">
	<span class="spinnerimage">&nbsp;</span>
	<span class="spinnermessage"><spring:message code="form.richiesta.accreditamento.elaboration" text="Richiesta accreditamento in elaborazione"/></span>
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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript" charset="UTF-8"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.blockUI.js"></script>



<script>
function invalida(event) {
	$.blockUI({ message: $('#accreditamentoSpinner') }); 		
	event.preventDefault();
	$("#azione").val("<spring:message code="common_texts.no_validate" javaScriptEscape="true" text="Invalida"/>");
	$("#frmDettaglio").submit();
}

function valida(event) {
	$.blockUI({ message: $('#accreditamentoSpinner') }); 		
	event.preventDefault();
	$("#azione").val("<spring:message code="common_texts.validate" javaScriptEscape="true" text="Valida"/>");
	$("#frmDettaglio").submit();
}
</script>

<!-- END DETTAGLIO RICHIESTA ACCREDITAMENTO-->
<!-- =========================================================================================== -->