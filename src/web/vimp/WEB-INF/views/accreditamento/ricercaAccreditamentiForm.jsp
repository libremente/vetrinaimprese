<!-- =========================================================================================== -->
<!-- BEGIN PARAMETRI RICERCA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<c:choose>
	<c:when test="${!utente.isBackoffice()}">
		&nbsp;
	</c:when>
	<c:otherwise>
		<form:form id="frmParametri" method="post" action="accreditamento" role="form"
			modelAttribute="parametriRicerca">
		
		
			<form:hidden id="paginaCorrente" path="paginaCorrente" />
			<form:hidden id="numeroRecord" path="numeroRecord" />
			
			
			<form:hidden id="findValidate" path="findValidate" />
			<form:hidden id="findValidateAuto" path="findValidateAuto" />
			<form:hidden id="findNonValidate" path="findNonValidate" />
			<form:hidden id="findAttesa" path="findAttesa" />
		
			
		
		
		    <div class="row">
		        <h2 class="center"><small><spring:message code="ricerca.accreditamento.form.title" text="Ricerca richieste accreditamento"/></small></h2>        
		    </div>
		
			
		
			<div class="row">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default panel-enhance-filter">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle accordion-btn" type="button" data-toggle="collapse"
									data-parent="#accordion" href="#pannello-1">
									<spring:message code="search_params.title"/>
								</a>
								<c:if test="${parametriRicerca.isFilterActivate()}">
									<div class="enhance-filter">
										<div class="enhance-filter-number">${totRecord}</div>
										<span class="glyphicon glyphicon-filter"
											title="Ci sono dei filtri attivi"></span>
									</div>
								</c:if>
							</h4>
						</div>
						<div id="pannello-1" class="panel-collapse collapse">
							<div class="panel-body profiel-container">
			
			
								<div id="pannello-body1" class="panel-body">
			
									<div class="form-group col-md-offset-1 col-md-6 col-sm-6 col-xs-12">
										<label for="testo" class="control-label"><spring:message code="ricerca.accreditamento.form.search_text" text="Testo ricerca"/></label> <input
											type="text" name="testo" id="testo" class="form-control"
											placeholder="Testo ricerca" autocomplete="off"
											value="${parametriRicerca.testo}" />
									</div>
			
			
									<div class="form-group col-md-4 col-sm-6 col-xs-12">
										<div class="controls">
											<label for="dateRange"><spring:message code="ricerca.accreditamento.form.request_date" text="Data richiesta"/></label>
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i
													class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> <input
													type="text" name="dateRange" id="dateRange"
													class="form-control" value="${parametriRicerca.dateRange}" />
											</div>
										</div>
									</div>
									
									<div class="form-group col-md-12 col-sm-12 col-xs-12">
										<br>
									</div>
									
									<div class="form-group col-md-offset-2 col-md-2 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="center homecheckbox"> <input
												id="checkboxValidateAuto" name="checkboxValidateAuto" type="checkbox" /><br />
												<strong><spring:message code="ricerca.accreditamento.form.auto_validate" text="Validate in automatico"/></strong>
											</label>
										</div>
									</div>
									
									<div class="form-group col-md-2 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="center homecheckbox"> <input
												id="checkboxAttesa" name="checkboxAttesa" type="checkbox" /><br />
												<strong><spring:message code="ricerca.accreditamento.form.wait_validate" text="In attesa di validazione"/></strong>
											</label>
										</div>
									</div>
									
									<div class="form-group col-md-2 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="center homecheckbox"> <input
												id="checkboxNonValidate" name="checkboxNonValidate" type="checkbox" /><br />
												<strong><spring:message code="ricerca.accreditamento.form.no_validate" text="Non validate"/></strong>
											</label>
										</div>
									</div>
									
									<div class="form-group col-md-2 col-sm-6 col-xs-12">
										<div class="checkbox">
											<label class="center homecheckbox"> <input
												id="checkboxValidate" name="checkboxValidate" type="checkbox" /><br />
												<strong><spring:message code="ricerca.accreditamento.form.validate" text="Validate"/></strong>
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
												
												
											<button type="button" onClick="javascript:cambiaPagina(1);" type="button"
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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-daterangepicker-master/moment.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-daterangepicker-master/daterangepicker.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/daterangepicker.langs.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>



<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-editable.js"></script>	


<script>
	function cambiaPagina(numeroPagina) {
		$("#frmParametri").get(0).setAttribute('action', 'accreditamento');
		$("#paginaCorrente").val(numeroPagina);
		$("#frmParametri").submit();
	}

	function normalSubmit() {
		$("#frmParametri").get(0).setAttribute('action', 'accreditamento');
		$("#frmParametri").submit();
	}

	function cambiaNumeroRecord(numeroRecord) {
		$("#frmParametri").get(0).setAttribute('action', 'accreditamento');
		$("#numeroRecord").val(numeroRecord);
		$("#paginaCorrente").val(1);
		$("#frmParametri").submit();
	}

	function azzeraFiltri() {
		$("#testo").val("");

		
		$("#dateRange").val("");
		$('#checkboxValidateAuto').iCheck('uncheck');
		$('#checkboxAttesa').iCheck('uncheck');
		$('#checkboxNonValidate').iCheck('uncheck');
		$('#checkboxValidate').iCheck('uncheck');
				
	}

	$(document).ready(
			function() {
				$('#numeroRecordSelect').val($("#numeroRecord").val());

				if (${parametriRicerca.findValidateAuto == 'S'})
					$('#checkboxValidateAuto').iCheck('check');
				else 
					$('#checkboxValidateAuto').iCheck('uncheck');
				$('#checkboxValidateAuto').on('ifChecked', function(event) {
					$("#findValidateAuto").val("S");
				});
				$('#checkboxValidateAuto').on('ifUnchecked', function(event) {
					$("#findValidateAuto").val("N");
				});

				if (${parametriRicerca.findAttesa == 'S'})
					$('#checkboxAttesa').iCheck('check');
				else 
					$('#checkboxAttesa').iCheck('uncheck');
				$('#checkboxAttesa').on('ifChecked', function(event) {
					$("#findAttesa").val("S");
				});
				$('#checkboxAttesa').on('ifUnchecked', function(event) {
					$("#findAttesa").val("N");
				});


				if (${parametriRicerca.findNonValidate == 'S'})
					$('#checkboxNonValidate').iCheck('check');
				else 
					$('#checkboxNonValidate').iCheck('uncheck');
				$('#checkboxNonValidate').on('ifChecked', function(event) {
					$("#findNonValidate").val("S");
				});
				$('#checkboxNonValidate').on('ifUnchecked', function(event) {
					$("#findNonValidate").val("N");
				});


				if (${parametriRicerca.findValidate == 'S'})
					$('#checkboxValidate').iCheck('check');
				else 
					$('#checkboxValidate').iCheck('uncheck');
				$('#checkboxValidate').on('ifChecked', function(event) {
					$("#findValidate").val("S");
				});
				$('#checkboxValidate').on('ifUnchecked', function(event) {
					$("#findValidate").val("N");
				});

				var optionSet = {
						//locale : 'en',
						locale: dateRangePickerLangs("${env_locale}"),
						startDate : moment().subtract(29, 'days'),
						endDate : moment(),
						minDate : '01/01/2010',
						maxDate : '31/12/2071',
						showDropdowns : true,
						showWeekNumbers : true,
						timePicker : false,
						timePickerIncrement : 1,
						timePicker12Hour : true,
						ranges : {
							"<spring:message javaScriptEscape="true" code="last_month" text="Ultimo mese"/>" : [
									moment().subtract(1, 'month').startOf('month'),
									moment().subtract(1, 'month').endOf('month') ],
							"<spring:message javaScriptEscape="true" code="last_year" text="Ultimo anno"/>" : [
									moment().subtract(12, 'month'),
									moment() ]
						},
						opens : 'left',
						buttonClasses : [ 'btn btn-default' ],
						applyClass : 'btn-small btn-primary',
						cancelClass : 'btn-small',
						format : 'DD/MM/YYYY'

					};
					$("#dateRange").daterangepicker(
							optionSet,
							function(start, end, label) {
								console.log(start.toISOString(), end.toISOString(),
										label);
							});

				
			});


// End -->
</script>


<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->