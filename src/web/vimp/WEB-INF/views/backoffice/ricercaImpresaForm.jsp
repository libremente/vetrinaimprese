<!-- =========================================================================================== -->
<!-- BEGIN PARAMETRI RICERCA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message var="thereAreActiveFiltersTitleAttr" code="ricerca.impresa.form.thereAreActiveFiltersTitleAttr" text="Ci sono dei filtri attivi"/>
<spring:message var="selectTheAreaTitleAttr" code="form.nuovo.progetto.selectTheAreaTitleAttr" text="Seleziona il settore"/>
<spring:message var="selectCompanyStatusTitleAttr" code="ricerca.impresa.form.selectCompanyStatusTitleAttr" text="Seleziona stato impresa"/>
<spring:message var="selectInnovationElementTitleAttr" code="ricerca.impresa.form.selectInnovationElementTitleAttr" text="Seleziona elemento di innovazione"/>
<spring:message var="selectReferenceMarketTitleAttr" code="ricerca.impresa.form.selectReferenceMarketTitleAttr" text="Seleziona il mercato di riferimento"/>
<spring:message var="selectReferenceStakeholderTitleAttr" code="ricerca.impresa.form.selectReferenceStakeholderTitleAttr" text="Seleziona lo stakeholder di riferimento"/>
<spring:message var="selectTheProvinceTitleAttr" code="form.dettaglio.impresa.selectTheProvinceTitleAttr" text="Seleziona la provincia"/>
<spring:message var="selectTheCityTitleAttr" code="form.dettaglio.impresa.selectTheCityTitleAttr" text="Seleziona il comune"/>

<c:choose>
	<c:when test="${!utente.isBackoffice()}">
		&nbsp;
	</c:when>
	<c:otherwise>
		<form:form id="frmParametri" method="post" action="ricercaImpresa" role="form"
			modelAttribute="parametriRicerca">
		
		
			<form:hidden id="paginaCorrente" path="paginaCorrente" />
			<form:hidden id="numeroRecord" path="numeroRecord" />
			
			
			<form:hidden id="brevetti" path="brevetti" />
			<form:hidden id="iscrizioneRegistro" path="iscrizioneRegistro" />
			<form:hidden id="iscrizioneSpeciale" path="iscrizioneSpeciale" />
			
			<form:hidden id="accreditata" path="accreditata" />
		
		
		    <div class="row">
		        <h2 class="center"><small><spring:message code="ricerca.impresa.form.title" text="Ricerca imprese"/></small></h2>        
		    </div>
		
			
		
			<div class="row">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default panel-enhance-filter">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#pannello-1"> <spring:message code="ricerca.impresa.form.settings" text="Impostazioni di ricerca"/></a>
								<c:if test="${parametriRicerca.isFilterActivate()}">
									<div class="enhance-filter">
										<div class="enhance-filter-number">${totRecord}</div>
										<span class="glyphicon glyphicon-filter"
											title="${thereAreActiveFiltersTitleAttr}"></span>
									</div>
								</c:if>
							</h4>
						</div>
						<div id="pannello-1" class="panel-collapse collapse">
							<div class="panel-body profiel-container">
			
			
								<div id="pannello-body1" class="panel-body">
			
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="ragioneSociale" class="control-label"><spring:message code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/></label> <input
											type="text" name="ragioneSociale" id="ragioneSociale" class="form-control"
											placeholder="Ragione sociale" autocomplete="off"
											value="${parametriRicerca.ragioneSociale}" />
									</div>
			
			
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="settore"><spring:message code="ricerca.impresa.form.settore" text="Settore"/></label>
										<form:select id="selectSettore"
											title="${selectTheAreaTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="settore"
											cssClass="selectpicker">
											<form:options items="${settoreImpresaList}" />
										</form:select>
									</div>
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="statoImpresa"><spring:message code="form.dettaglio.accreditamento.enterprise_status" text="Stato impresa"/></label>
										<form:select id="selectStatoImpresa"
											title="${selectCompanyStatusTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="statoImpresa"
											cssClass="selectpicker">
											<form:options items="${statoImpresaList}" />
										</form:select>
									</div>
									
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="elementiInnovazione"><spring:message code="ricerca.impresa.form.innovation_elements" text="Elementi di innovazione"/></label>
										<form:select id="selectElementiInnovazione"
											title="${selectInnovationElementTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="elementiInnovazione"
											cssClass="selectpicker">
											<form:options items="${innovazioneList}" />
										</form:select>
									</div>
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="mercatiRiferimento"><spring:message code="ricerca.impresa.form.mercati_di_riferimento" text="Mercati di riferimento"/></label>
										<form:select id="selectMercatiRiferimento"
											title="${selectReferenceMarketTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="mercatiRiferimento"
											cssClass="selectpicker">
											<form:options items="${mercatiList}" />
										</form:select>
									</div>
									
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="stakeholder"><spring:message code="ricerca.impresa.form.stakeholder_reference" text="Stakeholder di riferimento"/></label>
										<form:select id="selectStakeholder"
											title="${selectReferenceStakeholderTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="stakeholder"
											cssClass="selectpicker">
											<form:options items="${stakeholderList}" />
										</form:select>
									</div>
									
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="provincia"><spring:message code="ricerca.impresa.form.district" text="Provincia"/></label>
										<form:select id="selectProvincia"
											title="${selectTheProvinceTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="provincia"
											cssClass="selectpicker"
											onchange="javascript:updateComuni(this.options[selectedIndex].value);">
											<form:options items="${provinciaList}" />
										</form:select>
									</div>
									
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<label for="comune"><spring:message code="form.dettaglio.accreditamento.city" text="Comune"/></label>
										<form:select id="selectComune" name="selectComune"
											title="${selectTheCityTitleAttr}" data-live-search="true"
											data-live-search-style="contains"
											path="comune"
											cssClass="selectpicker">
											<form:options items="${comuneList}" />
										</form:select>
									</div>
									
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="homecheckbox"> <input
												id="checkboxBrevetti" name="checkboxBrevetti" type="checkbox" /><br />
												<strong><spring:message code="ricerca.impresa.form.patent_possession" text="Possesso di brevetti"/></strong>
											</label>
										</div>
									</div>
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="homecheckbox"> <input
												id="checkboxIscrizioneRegistro" name="checkboxIscrizioneRegistro" type="checkbox" /><br />
												<strong><spring:message code="ricerca.impresa.form.business_register" text="Iscrizione registro imprese"/></strong>
											</label>
										</div>
									</div>
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="homecheckbox"> <input
												id="checkboxIscrizioneSpeciale" name="checkboxIscrizioneSpeciale" type="checkbox" /><br />
												<strong><spring:message code="ricerca.impresa.form.innovation_start_up" text="Iscrizione sezione speciale registro start up innovative / PMI innovative"/></strong>
											</label>
										</div>
									</div>
									
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="homecheckbox"> <input
												id="checkboxAccreditata" name="checkboxAccreditata" type="checkbox" /><br />
												<strong><spring:message code="ricerca.impresa.form.accredited" text="Accreditata"/></strong>
											</label>
										</div>
									</div>
									
								</div>
			
							</div>
			
							<div class="panel-footer">
								<div class="row">
			
									<div class="col-md-2 text-left">
			
									
											<div class="btn-group ml-5">
												<button type="button" class="btn btn-default"
													onclick="javascript:azzeraFiltri();">
													<i class="glyphicon glyphicon-trash"></i> <spring:message code="reset_filters" text="Azzera filtri"/>
												</button>
											</div>
			
							
			
									</div>
			
									<div class="col-md-10 text-right">
			
											<span class="select-record" id="cambiaNumeroRecord">
												<i class="glyphicon glyphicon-list"></i> <spring:message code="results" text="Risultati"/> <select
													id="numeroRecordSelect" name="numeroRecordSelect"
													onChange="javascript:cambiaNumeroRecord(this.options[selectedIndex].value);">
													<option value="10">10</option>
													<option value="20">20</option>
													<option value="50">50</option>
													<option value="100">100</option>
												</select>
											</span>
			
			
											<a href="#" class="btn btn-default" data-toggle="modal"
												data-target="#colonne"><i
												class="glyphicon glyphicon-eye-open"></i> <spring:message code="columns" text="Colonne"/></a>
												
												
											<button onClick="javascript:cambiaPagina(1);" type="button"
												class="btn btn-primary">
												<i class="glyphicon glyphicon-refresh"></i> <spring:message code="update" text="Aggiorna"/>
											</button>
			
										
			
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
			
				<div class="modal fade" id="colonne" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
								</button>
								<h4 class="modal-title" id="myModalLabel"><spring:message code="columns.display" text="Visualizza colonne"/></h4>
							</div>
							<div class="modal-body">
			
								<c:forEach items="${parametriRicerca.colonne}" var="colonna">
									<div class="checkbox">
			
			
										<label> <c:choose>
												<c:when test="${colonna.checked}">
													<input type="checkbox" checked="checked"
														value="${colonna.nomeDato}" name="colonneResult">
												</c:when>
												<c:otherwise>
													<input type="checkbox" value="${colonna.nomeDato}"
														name="colonneResult">
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${colonna.defaultChecked}">
													<span class="stato"> ${colonna.label}</span>
												</c:when>
												<c:otherwise>
													${colonna.label}
												</c:otherwise>
											</c:choose>
										</label>
									</div>
								</c:forEach>
			
			
			
			
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo" text="Annulla"/></button>
								<button type="button" class="btn btn-primary" data-dismiss="modal"
									onClick="javascript:normalSubmit();"><spring:message code="save" text="Salva"/></button>
							</div>
						</div>
					</div>
				</div>
			
			</div>
		</form:form>
	</c:otherwise>
</c:choose>




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

<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-editable.js"></script>	


<script>
	function cambiaPagina(numeroPagina) {
		$("#frmParametri").get(0).setAttribute('action', 'ricercaImpresa');
		$("#paginaCorrente").val(numeroPagina);
		$("#frmParametri").submit();
	}

	function normalSubmit() {
		$("#frmParametri").get(0).setAttribute('action', 'ricercaImpresa');
		$("#frmParametri").submit();
	}

	function cambiaNumeroRecord(numeroRecord) {
		$("#frmParametri").get(0).setAttribute('action', 'ricercaImpresa');
		$("#numeroRecord").val(numeroRecord);
		$("#paginaCorrente").val(1);
		$("#frmParametri").submit();
	}

	function azzeraFiltri() {
		$("#ragioneSociale").val("");

		$("#selectSettore").val("");
		$('#selectSettore').selectpicker('refresh');

		$("#selectStatoImpresa").val("");
		$('#selectStatoImpresa').selectpicker('refresh');

		$("#selectElementiInnovazione").val("");
		$('#selectElementiInnovazione').selectpicker('refresh');

		$("#selectMercatiRiferimento").val("");
		$('#selectMercatiRiferimento').selectpicker('refresh');

		$("#selectStakeholder").val("");
		$('#selectStakeholder').selectpicker('refresh');

		$("#selectProvincia").val("");
		$('#selectProvincia').selectpicker('refresh');
		$("#selectComune option").remove();
		$('#selectComune').selectpicker('refresh');


		$('#checkboxBrevetti').iCheck('uncheck');
		$("#brevetti").val("");
		$('#checkboxIscrizioneRegistro').iCheck('uncheck');
		$("#iscrizioneRegistro").val("");
		$('#checkboxIscrizioneSpeciale').iCheck('uncheck');
		$("#iscrizioneSpeciale").val("");

		$('#checkboxAccreditata').iCheck('uncheck');
		$("#accreditata").val("");
		

		
	}

	$(document).ready(
			function() {
				$('#numeroRecordSelect').val($("#numeroRecord").val());


				if (${parametriRicerca.brevetti == 'S'})
					$('#checkboxBrevetti').iCheck('check');
				else 
					$('#checkboxBrevetti').iCheck('uncheck');
				$('#checkboxBrevetti').on('ifChecked', function(event) {
					$("#brevetti").val("S");
				});
				$('#checkboxBrevetti').on('ifUnchecked', function(event) {
					$("#brevetti").val("N");
				});

				if (${parametriRicerca.iscrizioneRegistro == 'S'})
					$('#checkboxIscrizioneRegistro').iCheck('check');
				else 
					$('#checkboxIscrizioneRegistro').iCheck('uncheck');
				$('#checkboxIscrizioneRegistro').on('ifChecked', function(event) {
					$("#iscrizioneRegistro").val("S");
				});
				$('#checkboxIscrizioneRegistro').on('ifUnchecked', function(event) {
					$("#iscrizioneRegistro").val("N");
				});

				if (${parametriRicerca.iscrizioneSpeciale == 'S'})
					$('#checkboxIscrizioneSpeciale').iCheck('check');
				else 
					$('#checkboxIscrizioneSpeciale').iCheck('uncheck');
				$('#checkboxIscrizioneSpeciale').on('ifChecked', function(event) {
					$("#iscrizioneSpeciale").val("S");
				});
				$('#checkboxIscrizioneSpeciale').on('ifUnchecked', function(event) {
					$("#iscrizioneSpeciale").val("N");
				});


				if (${parametriRicerca.accreditata == 'S'})
					$('#checkboxAccreditata').iCheck('check');
				else 
					$('#checkboxAccreditata').iCheck('uncheck');
				$('#checkboxAccreditata').on('ifChecked', function(event) {
					$("#accreditata").val("S");
				});
				$('#checkboxAccreditata').on('ifUnchecked', function(event) {
					$("#accreditata").val("N");
				});
				

				
			});

	function updateComuni(selectedProvincia) {
		if (selectedProvincia > 0) {
			$("#selectComune option").remove();

			$.getJSON("/vimp/elencoComuni.json", {
				provincia : selectedProvincia
			}, function(data) {

				$("#selectComune").append($("<option></option>").text("<spring:message code="common.selezionaIlComune" javaScriptEscape="true" text="Seleziona il comune"/>").val('').addClass('bs-title-option'));
				
				$.each(data, function(index, item) {
					$("#selectComune").append(
							$("<option></option>").text(item.text).val(
									item.value));
				});

				$('#selectComune').selectpicker('refresh');
			});
		}
	}
// End -->
</script>


<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->