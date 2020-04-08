<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<spring:message var="selectEnterpriseState" code="ricerca.accreditamenti.list.selectTheCompanyStatusTitleAttr" text="Seleziona lo stato dell'impresa"/>

        <!-- property area -->
        <div class="content-area submit-property">&nbsp;
            <div class="container">
            
            
            <c:if test="${!empty infoMessage}">
				<div class="alert alert-success fade in">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<p>
						<strong>Info!</strong> ${infoMessage}
					</p>
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
            <c:when test="${!utente.isVisitorLogin() && !utente.isImpresa()}">
				<div class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="operation_not_permitted" text="Operazione non permessa"/>.
				</div>
			</c:when>
            <c:otherwise>
            
                <div class="clearfix" > 
                    <div id="wizardContainer" name="wizardContainer" class="wizard-container"> 
                    
                        <div class="wizard-card ct-wizard-orange" id="wizardInsert">
                        
                        <form:form id="frmDettaglio" method="POST" action="/vimp/secure/inviaRichiesta" role="form" modelAttribute="dettaglio">
                      
                      
                      			<form:hidden id="rappresentante" path="rappresentante" />
                      			<form:hidden id="incaricato" path="incaricato" />
                      
                                <div class="wizard-header">
									<h3>
										<spring:message code="form.richiesta.accreditamento.title.1" text="COMPILA"/> <b><spring:message code="form.richiesta.accreditamento.title.2" text="LA RICHIESTA DI ACCREDITAMENTO"/></b><br> 
										<small> <spring:message code="form.richiesta.accreditamento.subtitle"/></small>
									</h3>
								</div>

                                <ul>
                                    <li><a href="#step1" data-toggle="tab"><spring:message code="form.richiesta.accreditamento.first_step" text="Passo"/> 1 </a></li>
                                    <li><a href="#step2" data-toggle="tab"><spring:message code="form.richiesta.accreditamento.first_step" text="Passo"/> 2 </a></li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane" id="step1">
										<h4 class="info-text"><spring:message code="form.richiesta.accreditamento.privacy_info" text="Informativa privacy"/></h4>
										<div class="row">
											<div class="col-sm-12">
												<div class="col-sm-1"></div>
												<div class="col-sm-10">
													<p>
														<label><strong><spring:message code="form.richiesta.accreditamento.terms" text="Termini e Condizioni"/></strong></label> <spring:message code="form.richiesta.accreditamento.privacy_text" text="Testo"/>
													</p>

													<div class="checkbox">
														<label> <input id="checkboxAccept"
															name="checkboxAccept" type="checkbox" /> <strong>&nbsp;<spring:message code="accept" text="Accetto"/>&nbsp;</strong>
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!--  End step 1 -->

                                    
                                    
                                    <div class="tab-pane" id="step2">
                                        <h4 class="info-text"> <spring:message code="form.richiesta.accreditamento.enterprise_data" text="Dati impresa"/></h4>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-12">
                                            	<div class="col-sm-1"></div>                                 
 												<div class="col-sm-2">
 													<div class="form-group">
														<label><spring:message code="form.richiesta.accreditamento.iva" text="Partita IVA"/> :</label>
															<input id="partitaIvaInput" name="partitaIvaInput" type="text" class="form-control"
															placeholder="" value="${dettaglio.partitaIvaInput}"
															path="partitaIvaInput" maxlength="16">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label><spring:message code="form.richiesta.accreditamento.codice_fiscale" text="Codice fiscale"/> :</label>
														<input id="codiceFiscaleInput" name="codiceFiscaleInput" type="text" class="form-control"
															placeholder="" value="${dettaglio.codiceFiscaleInput}"
															path="codiceFiscaleInput" maxlength="16">
													</div>
												</div>
												<div class="col-sm-6">
			                                    	<div class="form-group">
														<label><spring:message code="form.richiesta.accreditamento.enterprise_status" text="Stato impresa"/> :</label>
														<form:select id="selectStatoImpresa"
															title="${selectEnterpriseState}"
															data-live-search="true" data-live-search-style="contains"
															path="idStatoImpresaInput"
															cssClass="selectpicker">
															<form:options items="${statoImpresaList}" />
														</form:select>
													</div>
												</div>
                                            </div>
                                            
                                            <div class="col-sm-12">
                                            	<div class="col-sm-1"></div>                                 
 												<div class="col-sm-4">
 													<div class="form-group">
														<label><spring:message code="form.richiesta.accreditamento.mail" text="Email"/> :</label>
															<input id="emailInput" name="emailInput" type="text" class="form-control"
															placeholder="" value="${dettaglio.emailInput}"
															path="emailInput" maxlength="400">
													</div>
												</div>
												<div class="col-sm-6">
													&nbsp;
												</div>
                                            </div>
                                            
                                            <div class="col-sm-12">
                                            	<br>                                 
                                            </div>
                                            
                                            
                                            <div class="col-sm-12">
                                            	<div class="col-sm-1"></div>      
                                            	<div class="col-sm-6">
                                            		<div class="checkbox">
														<label> <input id="checkboxRappresentante"
															name="checkboxRappresentante" type="checkbox" /> <strong>&nbsp;<spring:message code="form.richiesta.accreditamento.rappresentante" text="Dichiaro di essere Rappresentante Legale dell'impresa"/>&nbsp;</strong>
														</label>
													</div>
                                            	</div>                                 
                                            </div>
                                            
                                            <div class="col-sm-12">
                                            	<div class="col-sm-1"></div>    
                                            	<div class="col-sm-6">
                                            		<div class="checkbox">
														<label> <input id="checkboxIncaricato"
															name="checkboxIncaricato" type="checkbox" /> <strong>&nbsp;<spring:message code="form.richiesta.accreditamento.incaricato" text="Dichiaro di essere incaricato ad operare per l'impresa"/>&nbsp;</strong>
														</label>
													</div>
                                            	</div>                                 
                                            </div>
                                            
                                            <div class="col-sm-12">
                                            	<br>                                 
                                            </div>
                                            
                                            
                                            
                                          
                                        </div>
                                    </div>
                                    <!-- End step 2 -->


                                </div>

                                <div class="wizard-footer">

									<div class="pull-right">
										<!--<input type='button' class='btn btn-next btn-primary'
											name='next' value='Prossimo' />-->
										<button type="button" name="next" class='btn btn-next btn-primary'
											value='Prossimo'>&nbsp;&nbsp;<spring:message code="next" text="Prossimo"/>&nbsp;&nbsp;</button>
										<button type="button" id="saveButton" class='btn btn-finish btn-primary'
											onClick="javascript:aggiorna(event);" value='Invia richiesta'>&nbsp;&nbsp;<spring:message code="send_request" text="Invia richiesta"/>&nbsp;&nbsp;</button>
									</div>

									<div class="pull-left">
										<!--<input type='button' class='btn btn-previous btn-default'
											name='previous' value='Precedente' />-->
										<button type="button" name="previous" class='btn btn-previous btn-primary'
											value='Precedente'>&nbsp;&nbsp;<spring:message code="back" text="Indietro"/>&nbsp;&nbsp;</button>
										<button class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" text="ANNULLA"/></button>
									</div>
									<div class="clearfix"></div>
								</div>
								
								<div class="modal fade" id="cancellaCollegamentoModal" tabindex="-1"
									role="dialog" aria-labelledby="cancellaCollegamentoModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
												</button>
												<h4 class="modal-title" id="cancellaCollegamentoModalLabel"><spring:message code="cancellation" text="Cancellazione"/></h4>
											</div>
											<div class="modal-body">
												<strong><spring:message code="warning" text="Attenzione!"/> </strong> <spring:message code="cancellation_confirm" text="Procedere con la cancellazione"/><br>
											</div>
											<div class="modal-footer">
												<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo" text="Annulla"/></a>
												<button name="cancellaCollegamentoModalButton"
													id="cancellaCollegamentoModalButton" type="button"
													class="btn btn-primary"><spring:message code="delete" text="Cancella"/></button>
											</div>
										</div>
									</div>
								</div>
								
								<div class="modal fade" id="errorModal" tabindex="-1" role="dialog"
									aria-labelledby="errorModalLabel" aria-hidden="true">
									<div class="modal-dialog">
						
						
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
												</button>
												<h4 class="modal-title" id="errorModalTitle"></h4>
											</div>
											<div class="modal-body">
												<strong><spring:message code="warning" text="Attenzione!"/> </strong> <span id="errorModalMessage"></span>
						
												<br>
						
											</div>
											<div class="modal-footer"></div>
										</div>
									</div>
								</div>

                                
                        
                            </form:form>
                        </div>
                        <!-- End submit form -->
                    </div> 
                </div>
                </c:otherwise>
            </c:choose>
            </div>
        </div>

<div class="modal fade" id="resetFormConfirm" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div class="modal-body">
				<spring:message code="messaggioConfermaReload"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onClick="javascript:reloadPage();"><spring:message code="continue"/></button>			
			</div>
		</div>
	</div>
</div>


<div id="accreditamentoSpinner" style="display:none;">
	<span class="spinnerimage">&nbsp;</span>
	<span class="spinnermessage"><spring:message code="form.richiesta.accreditamento.elaboration" text="Richiesta accreditamento in elaborazione"/></span>
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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/summernote-bs4.js" type="text/javascript" ></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.blockUI.js"></script>


<script>

	$(document).ready(
			function() {


				$('#wizardInsert').bootstrapWizard({
			        'tabClass': 'nav nav-pills',
			        'nextSelector': '.btn-next',
			        'previousSelector': '.btn-previous',
			        onInit: function (tab, navigation, index) {
			            var $total = navigation.find('li').length;
			            $width = 100 / $total;
			            $display_width = $(document).width();
			            if ($display_width < 600 && $total > 3) {
			                $width = 50;
			            }
			            navigation.find('li').css('width', $width + '%');
			        },
			        onNext: function (tab, navigation, index) {
			            if (index == 1) {
			                return validateFirstStep();
			            }
			        },
			        onTabClick: function (tab, navigation, index) {
			            // Disable the posibility to click on tabs
			            return false;
			        },
			        onTabShow: function (tab, navigation, index) {
			            var $total = navigation.find('li').length;
			            var $current = index + 1;
			            var wizard = navigation.closest('.wizard-card');
			            if ($current >= $total) {
			                $(wizard).find('.btn-next').hide();
			                $(wizard).find('.btn-finish').show();
			            } else {
			                $(wizard).find('.btn-next').show();
			                $(wizard).find('.btn-finish').hide();
			            }
			        }
			    });



				if (${privacyCheck == 'S'})
					$('#checkboxAccept').iCheck('check');
				else 
					$('#checkboxAccept').iCheck('uncheck');
				
				
				if (${privacyCheck == 'S'})
					$('#wizardInsert').bootstrapWizard('show',1);



				$('#checkboxRappresentante').on('ifChecked', function(event) {
					$('#checkboxIncaricato').iCheck('uncheck');
					$("#rappresentante").val("S");
					$("#incaricato").val("N");
				});
				$('#checkboxRappresentante').on('ifUnchecked', function(event) {
					$("#rappresentante").val("N");
				});

				$('#checkboxIncaricato').on('ifChecked', function(event) {
					$('#checkboxRappresentante').iCheck('uncheck');
					$("#rappresentante").val("N");
					$("#incaricato").val("S");
				});
				$('#checkboxIncaricato').on('ifUnchecked', function(event) {
					$("#incaricato").val("N");
				});
				
			});



	function aggiorna(event) {
		event.preventDefault();
		if (validateFirstStep())
		{
			$.blockUI({ message: $('#accreditamentoSpinner') }); 		
			$("#frmDettaglio").submit();
		}
	}

	function validateFirstStep() {
	    $('#frmDettaglio').validate({

	    	ignore: ":hidden:not(.validate)",
	    	errorPlacement: function(error, element) {
		        if(element.parent('.input-group').length) {
		            error.insertAfter(element.parent());
		        } else {
		            error.insertAfter(element);
		        }
		    },
	    	 rules: {
	    		 	checkboxAccept: "required",
	    		 	partitaIvaInput: {
					      required: function(element) {
					          return $("#codiceFiscaleInput").val().length <= 0;
					      }
					},
					codiceFiscaleInput: {
					      required: function(element) {
					    	  return $("#partitaIvaInput").val().length <= 0;
					      }
					},
					idStatoImpresaInput : "required",
					emailInput: {
					      required: true,
				      	  email: true
					      },					
		        },
		        messages: {
		        	checkboxAccept: "Per procedere con la richiesta di accreditamento e' necessario accettare i Termini e le Condizioni",
		        	partitaIvaInput : "Inserire la partita iva o il codice fiscale dell'impresa",
		        	codiceFiscaleInput : "Inserire la partita iva o il codice fiscale dell'impresa",
					idStatoImpresaInput : "Inserire lo stato dell'impresa",
					emailInput : "Inserire una email di riferimento"
		        }
	    });


	    if (!$('#frmDettaglio').valid()) {
	        return false;
	    }

	    return true;

	}

	function resetForm(){
		$('#resetFormConfirm').modal();
	}

	function reloadPage(){
		var baseUrl = window.location.href.split('/vimp')[0];
		originalUrl = baseUrl + '/vimp' + '${refreshRelativeUrl}';
		window.location.replace(originalUrl); // Usare questo se ${refreshRelativeUrl} arriva dall'handler
		//location.reload(true); // Usare questo nl caso non si voglia usare ${refreshRelativeUrl}
	}
	
</script>



<style>
#checkboxAccept-error {
 width: 1000px;
 max-width: 1000px;
 margin-top: 25px; 
}
</style>


<!-- END DETTAGLIO -->
<!-- =========================================================================================== -->