<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:message var="selectTheProgramTypeTitleAttr" code="form.nuova.opportunita.selectTheProgramTypeTitleAttr" text="Seleziona il tipo programma"/>
<spring:message var="selectAreaTitleAttr" code="form.nuova.opportunita.selectAreaTitleAttr" text="Seleziona settore"/>
<spring:message var="selectEligibleSubjectsTitleAttr" code="form.nuova.opportunita.selectEligibleSubjectsTitleAttr" text="Seleziona i soggetti ammissibili"/>

<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title"><spring:message code="form.nuova.opportunita.a_new_opportunity" text="Inserisci nuova Opportunit&agrave;"/></h1>
			</div>
		</div>
	</div>
</div>
<!-- End page header -->

<!-- property area -->
<div class="content-area submit-property">
	&nbsp;
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

		<c:choose>
			<c:when test="${!utente.isWriter(parametriRicerca.tipoInformazione)}">
				<div class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="operation_not_permitted" text="Operazione non permessa"/>.
				</div>
			</c:when>
			<c:otherwise>

				<div class="clearfix">
					<div class="wizard-container">



						<div class="wizard-card ct-wizard-orange" id="wizardInsert">


							<form:form id="frmDettaglio" method="POST"
									   action="/vimp/secure/salvaOpportunita" role="form"
									   modelAttribute="dettaglio" class="safe-reload">


								<input id="imageData" name="imageData" type="hidden">




								<div class="wizard-header">
									<h3>
										<b><spring:message code="insert.uppercase" text="INSERISCI"/></b> <spring:message code="form.nuova.opportunita.a_new_opportunity"/> <br>
										<small><spring:message code="promote_visibility"/></small>
									</h3>
								</div>

								<ul>
									<li><a href="#step1" data-toggle="tab"><spring:message code="first_step" text="Passo 1"/> </a></li>
									<li><a href="#step2" data-toggle="tab"><spring:message code="second_step" text="Passo 2"/> </a></li>
									<li><a href="#step3" data-toggle="tab"><spring:message code="finish" text="Finito"/> </a></li>
								</ul>

								<div class="tab-content">

									<div class="tab-pane" id="step1">
										<div class="row p-b-15  ">
											<h4 class="info-text"><spring:message code="start_with_basic_data" text="Iniziamo con i dati di base (con validazione)"/></h4>
											<div class="col-sm-4 col-sm-offset-1">
												<div class="picture-container">
													<div class="picture">
														<img
																src="${evn_urlRisorseStatiche}/vimp/assets/img/default-property.jpg"
																class="picture-src" id="wizardPicturePreview" title="" />
														<input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label><spring:message code="form.dettaglio.opportunita.opportunity_name" text="Nome opportunit&agrave;"/> : <small>(<spring:message code="required" text="richiesto"/>)</small></label>
													<input id="descNome" name="opportunitaTranslation.descNome" type="text"
														   class="form-control" placeholder=""
														   value="${dettaglio.opportunitaTranslation.descNome}" path="descNome"
														   maxlength="1000">
												</div>


												<div class="form-group">
													<label><spring:message code="form.dettaglio.opportunita.economic_value" text="Valore economico"/> : <small>(<spring:message code="required" text="richiesto"/>)</small></label>
													<input name="numValEconomico" id="numValEconomico"
														   type="text" class="form-control" placeholder=""
														   path="numValEconomico"
														   value="<fmt:formatNumber value="${dettaglio.numValEconomico}" pattern="###,###,###,###,##0.00" />"
														   maxlength="18">
												</div>

												<div class="form-group">
													<label><spring:message code="form.dettaglio.opportunita.period" text="Periodo"/> : <small>(<spring:message code="required" text="richiesto"/>)</small></label>
													<textarea rows="2" name="opportunitaTranslation.descPeriodo" class="form-control"
															  maxlength="1000">${dettaglio.opportunitaTranslation.descPeriodo}</textarea>
												</div>
											</div>
										</div>
										<br>
									</div>
									<!--  End step 1 -->

									<div class="tab-pane" id="step2">
										<h4 class="info-text"><spring:message code="common_texts.features" text="Caratteristiche"/> </h4>
										<div class="row">
											<div class="col-sm-12">
												<div class="col-sm-12">
													<div class="form-group">
														<label><spring:message code="form.dettaglio.opportunita.requisite" text="Requisiti"/> :</label>
														<textarea rows="2" name="opportunitaTranslation.descRequisiti"
																  class="form-control" maxlength="990">${dettaglio.opportunitaTranslation.descRequisiti}</textarea>
													</div>
												</div>
											</div>


											<div class="col-sm-12">
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="form.dettaglio.opportunita.program_type" text="Tipo programma"/> : <small>(<spring:message code="required" text="richiesto"/>)</small></label>
														<form:select id="selectTipo"
																	 title="${selectTheProgramTypeTitleAttr}"
																	 data-live-search="true" data-live-search-style="contains"
																	 path="plfTTipoProgramma.id"
																	 cssClass="selectpicker">
															<form:options items="${tipoProgrammaList}" />
														</form:select>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="form.dettaglio.opportunita.area" text="Settore"/> : </label>
														<form:select id="selectSettoreImpresa"
																	 title="${selectAreaTitleAttr}"
																	 data-live-search="true" data-live-search-style="contains"
																	 path="plfTSettoreProgetti.id"
																	 cssClass="selectpicker">
															<form:options items="${settoreImpresaList}" />
														</form:select>
													</div>
												</div>
											</div>


											<div class="col-sm-12">
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="form.dettaglio.opportunita.eligible_subjects" text="Soggetti ammissibili"/> :</label>
														<form:select id="selectSoggAmmissibili"
																	 title="${selectEligibleSubjectsTitleAttr}"
																	 data-live-search="true" data-live-search-style="contains"
																	 path="plfTSoggAmmissibili.id"
																	 cssClass="selectpicker">
															<form:options items="${soggAmmissibiliList}" />
														</form:select>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="form.contatti.contacts_title" text="Contatti"/> :</label> <input name="opportunitaTranslation.descContatti"
																																					  type="text" class="form-control" placeholder=""
																																					  value="${dettaglio.opportunitaTranslation.descContatti}" path="descContatti"
																																					  maxlength="1000">
													</div>
												</div>
											</div>

											<div class="col-sm-12">
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="form.dettaglio.opportunita.opening_date" text="Data apertura"/> : <small>(<spring:message code="required" text="richiesto"/>)</small></label>
														<fmt:formatDate value="${dettaglio.dataApertura}"
																		type="date" pattern="dd/MM/yyyy"
																		var="dataAperturaFormatted" />
														<div class="input-group date" data-provide="datepicker"
															 data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
															<input name="dataApertura" type="text" id="dataApertura"
																   class="form-control" value="${dataAperturaFormatted}"
																   path="dataApertura" autocomplete="off">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="expiry_date" text="Data scadenza"/> : <small>(<spring:message code="required" text="richiesto"/>)</small></label>
														<fmt:formatDate value="${dettaglio.dataScadenza}"
																		type="date" pattern="dd/MM/yyyy"
																		var="dataScadenzaFormatted" />
														<div class="input-group date" data-provide="datepicker"
															 data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
															<input name="dataScadenza" type="text"
																   class="form-control" value="${dataScadenzaFormatted}"
																   path="dataScadenza" id="dataScadenza" autocomplete="off">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="col-sm-12">
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="web_site" text="Sito web"/></label>
														<textarea name="descLink" class="form-control"
																  maxlength="1000"></textarea>
													</div>
												</div>

												<div class="col-sm-6">
													<div class="checkbox">
														<label>
															<input name="pubblicato" type="checkbox"/>
															<strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
														</label>
													</div>
												</div>

											</div>

											<div class="col-sm-12">
												<form:select id="selectTags" hidden="true"
															 data-live-search="true" data-live-search-style="contains" multiple="true"
															 path="elencoIdTags">
													<form:options items="${allTags}" itemValue="id"/>
												</form:select>
												<div class="col-sm-12">
													<div class="col-sm-6">
														<div class="form-group">
															<label>Tag</label><br>
															<c:choose>
																<c:when test="${modifica}">
																	<small><spring:message code="form.dettaglio.impresa.tag_guide"/></small><br>
																	<c:forEach items="${allTags}" var="tag" >
																		<c:choose>
																			<c:when test="${fn:contains(dettaglio.tags, tag)}">
																				<div class="col-md-2 col-sm-2 element-tag tag-interactive tag-selected" onclick="toggleTagSelection(this, '${tag.id}')">${tag.descrizione}</div>
																			</c:when>
																			<c:otherwise>
																				<div class="col-md-2 col-sm-2 element-tag tag-interactive" onclick="toggleTagSelection(this, '${tag.id}')">${tag.descrizione}</div>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${dettaglio.tags == null or fn:length(dettaglio.tags) < 1}">
																			<spring:message code="form.dettaglio.impresa.no_tags"/>
																		</c:when>
																		<c:otherwise>
																			<c:forEach items="${dettaglio.tags}" var="tag" >
																				<div class="col-md-2 col-sm-2 element-tag tag-selected">${tag.descrizione}</div>
																			</c:forEach>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
														</div>
													</div>
													<div class="col-sm-6">
														&nbsp;
													</div>
												</div>
											</div>

												<%--
                                                <div class="col-sm-12">
                                                    <div class="col-sm-12">
                                                        <div class="form-group">
                                                            <input name="opportunitaTranslation.descTag"
                                                                   value="${dettaglio.opportunitaTranslation.descTag}" id="selectedTags"
                                                                   hidden="true">
                                                            <label>Tag </label><br>
                                                            <small><spring:message code="form.dettaglio.impresa.tag_guide"/></small><br>
                                                            <c:forEach items="${allTags}" var="tag">
                                                                <div class="col-md-2 col-sm-2 element-tag tag-interactive"
                                                                     onclick="toggleTagSelection(this)">${tag}</div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                                --%>
											<br>
										</div>
									</div>
									<!-- End step 2 -->

									<div class="tab-pane" id="step3">
										<h4 class="info-text"> <spring:message code="finish_and_send" text="Concludi e Invia"/> </h4>
										<div class="row">
											<div class="col-sm-12">
												<div class="col-sm-1"></div>
												<div class="col-sm-10">
													<p>
														<label><strong><spring:message code="form.richiesta.accreditamento.terms" text="Termini e Condizioni"/></strong></label>
														<spring:message code="form.nuova.news.impresa.terms.text"/>
													</p>

													<div class="checkbox form-grid-checkbox">
														<label>
															<input id="checkboxAccept" name="checkboxAccept" type="checkbox" /> <strong>&nbsp;<spring:message code="accept" text="Accetto"/>&nbsp;</strong>
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!--  End step 3 -->

								</div>

								<div class="wizard-footer">

									<div class="pull-right">
										<!--<input type='button' class='btn btn-next btn-primary'
											name='next' value='Prossimo' />-->
										<button type="button" class='btn btn-next btn-primary' name='next' value='Prossimo'><spring:message code="next1" text="Prossimo"/></button>
										<button type="button" id="saveButton" class='btn btn-finish btn-primary'
												onClick="javascript:aggiorna(event);" value='Finito'>&nbsp;&nbsp;<spring:message code="finish"/>&nbsp;&nbsp;</button>
									</div>
									<div class="pull-left">
										<button type="button" class='btn btn-previous btn-default' name='previous' value='Precedente'><spring:message code="back1" text="Precedente"/></button>
										<button class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" text="ANNULLA"/></button>
									</div>
									<div class="clearfix"></div>
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

<script	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript" charset="UTF-8"></script>

<script
		src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>



<script>
	var uploadImage = false;

	$(document).ready(
			function() {





				$('#numValEconomico').keydown(function (event) {


					if (event.shiftKey == true) {
						event.preventDefault();
					}

					if ((event.keyCode >= 48 && event.keyCode <= 57) ||
							(event.keyCode >= 96 && event.keyCode <= 105) ||
							event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 ||
							event.keyCode == 39 || event.keyCode == 46 || event.keyCode == 190) {

					} else {
						event.preventDefault();
					}

					if($(this).val().indexOf('.') !== -1 && event.keyCode == 190)
						event.preventDefault();
					//if a decimal has been added, disable the "."-button

				});


				$('.datepicker').datepicker({
					language : '${env_locale}',
					format : 'yyyy-mm-dd',
					viewformat : 'dd/mm/yyyy'
				});



				$('#wizard-picture').change(function() {
					uploadImage = true;
				});


				$('#wizardInsert').bootstrapWizard({
					'tabClass': 'nav nav-pills',
					'nextSelector': '.btn-next',
					'previousSelector': '.btn-previous',
					onInit: function (tab, navigation, index) {

						//check number of tabs and fill the entire row
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
						} else if (index == 2) {
							return validateSecondStep();
						} else if (index == 3) {
							return validateThirdStep();
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

						// If it's the last tab then hide the last button and show the finish instead
						if ($current >= $total) {
							$(wizard).find('.btn-next').hide();
							$(wizard).find('.btn-finish').show();
						} else {
							$(wizard).find('.btn-next').show();
							$(wizard).find('.btn-finish').hide();
						}
					}
				});

			});


	function aggiorna(event) {
		event.preventDefault();
		if (uploadImage) {

			var fileSize = $('#wizardPicturePreview').attr('src').length;
			if (fileSize > 1572864) {
				alert('<spring:message code="immagineTroppoGrandeMax1.5M" javaScriptEscape="true" />');
				return;
			}

			$("#imageData").val($('#wizardPicturePreview').attr('src'));
			$("#frmDettaglio").submit();
		} else {
			$("#imageData").val("");
			$("#frmDettaglio").submit();
		}
	}


	function formatNumber(number, c, d, t) {
		c = isNaN(c = Math.abs(c)) ? 2 : c;
		d = d == undefined ? "." : d;
		t = t == undefined ? "," : t;
		s = number < 0 ? "-" : "";
		i = String(parseInt(number = Math.abs(Number(number) || 0).toFixed(c)));
		j = (j = i.length) > 3 ? j % 3 : 0;
		var ret = s + (j ? i.substr(0, j) + t : "")
				+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t)
				+ (c ? d + Math.abs(number - i).toFixed(c).slice(2) : "");
		return ret;
	}

	function getCurrentYear() {
		return (new Date()).getFullYear();
	}

	function toggleTagSelection(e, id) {
		var startClass = $(e).attr('class');

		$(e).toggleClass('tag-selected');

		$('#selectTags > option[value="'+ id +'"]').each(function() {

			if(startClass.includes('tag-selected') && id == this.value) {
				$(this).attr('selected', false);
			} else if(!startClass.includes('tag-selected') && id == this.value) {
				$(this).attr('selected', true);
			}

		});
	}


	function validateFirstStep() {


		jQuery.validator.addMethod("checkDate", function(e) {
			let valDataInizio = $('#dataApertura').val().split('/');
			let dataInizio = new Date(valDataInizio[2], valDataInizio[1] - 1, valDataInizio[0]);

			let valDataFine = $('#dataScadenza').val();

			if(valDataFine) {
				let arrDataFine = valDataFine.split("/");
				let dataFine = new Date(arrDataFine[2], arrDataFine[1] - 1, arrDataFine[0]);
				return dataFine > dataInizio;
			}
			return false;
		});

		$('#frmDettaglio').validate({

			ignore: ":hidden:not(.validate)",
			errorPlacement: function(error, element) {
				if(element.parent('.input-group').length) {
					error.insertAfter(element.parent());
				} 
				else if(element.attr("name") == "numValEconomico") {
					var outNumValEconomico = $("#sliderValEconomico");
					error.appendTo( outNumValEconomico.parent("div"));
				} else {
					error.insertAfter(element);
				}
			},
			rules: {
				'opportunitaTranslation.descNome': 'required',
				numValEconomico : "required",
				"plfTTipoProgramma.id": "required",
				'opportunitaTranslation.descPeriodo' : "required",
				dataApertura: "required",
				dataScadenza: "checkDate",
				checkboxAccept: "required"
			},
			messages: {
				'opportunitaTranslation.descNome': '<spring:message code="opportunitaFormNuovaInserisciNome" javaScriptEscape="true" />',
				numValEconomico: '<spring:message code="opportunitaFormNuovaInserireValore" javaScriptEscape="true" />',
				'plfTTipoProgramma.id': '<spring:message code="opportunitaFormNuovaInserireTipo" javaScriptEscape="true" />',
				'opportunitaTranslation.descPeriodo': '<spring:message code="opportunitaFormNuovaInserirePeriodo" javaScriptEscape="true" />',
				dataApertura: '<spring:message code="opportunitaFormNuovaInserireDataApertura" javaScriptEscape="true" />',
				dataScadenza: '<spring:message code="opportunitaFormNuovaInserireDataScadenza" javaScriptEscape="true" />',
				checkboxAccept: '<spring:message code="perProcedereConRegistrazioneAccettareTermini" javaScriptEscape="true" />'
			}
		});


		if (!$('#frmDettaglio').valid()) {
			return false;
		}

		return true;

	}


	function validateSecondStep() {
		$('#frmDettaglio').validate({
			rules: {
			},
			messages: {
			}
		});

		if (!$('#frmDettaglio').valid()) {
			return false;
		}

		return true;
	}



	function validateThirdStep() {
		$('#frmDettaglio').validate({
			rules: {
			},
			messages: {
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