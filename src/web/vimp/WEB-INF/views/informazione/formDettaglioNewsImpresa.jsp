<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO PROGETTO RICERCA-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message var="SelectTheTypeOfNewsTitleAttr" code="form.nuova.news.impresa.SelectTheTypeOfNewsTitleAttr" text="Seleziona il tipo della news"/>
<spring:message var="selectTheCompanyLinkedToTheNewsTitleAttr" code="form.nuova.news.impresa.selectTheCompanyLinkedToTheNewsTitleAttr" text="Seleziona l'impresa collegato alla news"/>
<spring:message var="selectTheServiceLinkedToTheNewsTitleAttr" code="form.nuova.news.impresa.selectTheServiceLinkedToTheNewsTitleAttr" text="Seleziona il servizio collegato alla news"/>
<spring:message var="selectTheProductProjectLinkedToTheNewsTitleAttr" code="form.nuova.news.impresa.selectTheProductProjectLinkedToTheNewsTitleAttr" text="Seleziona il progetto prodotto collegato alla news"/>

		
<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">
					<b><spring:message code="form.dettaglio.news.impresa.title" text="NEWS IMPRESA"/></b> ${utils.truncateString(dettaglio.newsImpresaTranslation.descrizione,100)}
				</h1>
			</div>
		</div>
	</div>
</div>
<!-- End page header -->

<!-- property area -->

<form:form id="frmDettaglio" method="POST"
		   action="/vimp/secure/salvaNewsImpresa" role="form"
		   modelAttribute="dettaglio" class="safe-reload">


	<form:hidden id="idNewsImpresa" path="idNewsImpresa" />
	<form:hidden id="newsImpresaTranslation.idNewsImpresa" path="newsImpresaTranslation.idNewsImpresa" />
	<input id="imageData" name="imageData" type="hidden">

	<!-- CAMPI MANCANTI -->
	<c:if test="${!modifica}">
		<form:hidden id="plfImpresa.idPlfImpresa" path="plfImpresa.idPlfImpresa" />
	</c:if>


	<div class="content-area user-profiel">
		&nbsp;
		<div class="container">
		
			<c:if test="${dettaglio.isScaduto()}">
				<div class="col-sm-12 label-scaduto-container">
					<div class="col-sm-10">
						&nbsp;
					</div>
					<div class="col-sm-2">
						<label class="label-scaduto"><spring:message code="expired" text="Scaduto"/></label>
					</div>
				</div>
			</c:if>
			

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

			<div class="row">
				<div class="col-sm-12 profiel-container">
					<div class="clear">
						<div class="col-sm-12">
							<div class="col-sm-6 ">
								<div class="picture-container">
									<div class="picture">
										<c:choose>
											<c:when test="${!empty dettaglio.imageData}">
												<img src="data:image/jpg;base64,${dettaglio.imageData}"
													 class="picture-src" id="wizardPicturePreview" title="" />
												<c:if test="${modifica}">
													<input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
												</c:if>
											</c:when>
											<c:otherwise>
												<img
														src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_news.jpg"
														class="picture-src" id="wizardPicturePreview" title="" />
												<c:if test="${modifica}">
													<input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
												</c:if>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group" ${!modifica && empty dettaglio.plfTTipoNew.descrizione ? 'hidden' : ''}>
									<label><spring:message code="form.dettaglio.news.impresa.news_type" text="Tipo news"/> :
										<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if>
									</label>

									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectTipoNews"
														 title="${SelectTheTypeOfNewsTitleAttr}" data-live-search="true"
														 data-live-search-style="contains"
														 path="plfTTipoNew.id" cssClass="selectpicker">
												<form:options items="${tipoNewsList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTTipoNew.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

								<div class="form-group" ${!modifica && empty dettaglio.newsImpresaTranslation.descrizione ? 'hidden' : ''}>
									<label><spring:message code="title" text="Titolo"/> : <c:if test="${modifica}">
										<small>(<spring:message code="required" text="richiesto"/>)</small>
									</c:if>
									</label>


									<c:choose>
										<c:when test="${modifica}">
											<input name="newsImpresaTranslation.descrizione" type="text" class="form-control"
												   placeholder="" value="${dettaglio.newsImpresaTranslation.descrizione}"
												   path="descrizione" maxlength="400">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.newsImpresaTranslation.descrizione}</b></label>
										</c:otherwise>
									</c:choose>

								</div>

								<div class="form-group" ${!modifica && empty dettaglio.dataInizio ? 'hidden' : ''}>
									<label><spring:message code="date" text="Data"/> : <c:if test="${modifica}">
										<small>(<spring:message code="required" text="richiesto"/>)</small>
									</c:if>
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<fmt:formatDate value="${dettaglio.dataInizio}" type="date"
															pattern="dd/MM/yyyy" var="dataInizioFormatted" />
											<div class="input-group date" data-provide="datepicker" data-date-language="${env_locale}"
												 data-date-format="dd/mm/yyyy" >
												<input name="dataInizio" type="text" class="form-control" id="dataInizio"
													   value="${dataInizioFormatted}" path="dataInizio" autocomplete="off">
												<div class="input-group-addon">
													<span class="glyphicon glyphicon-th"></span>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<fmt:formatDate value="${dettaglio.dataInizio}" type="date"
															pattern="dd/MM/yyyy" var="dataInizioFormatted" />
											<br>
											<label><b>${dataInizioFormatted}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
								
								
								<div class="form-group" ${!modifica && empty dettaglio.dataFine ? 'hidden' : ''}>
									<label><spring:message code="end_date" text="Data"/> : </label>
									<c:choose>
										<c:when test="${modifica}">
											<fmt:formatDate value="${dettaglio.dataFine}" type="date"
															pattern="dd/MM/yyyy" var="dataFineFormatted" />
											<div class="input-group date" data-provide="datepicker" data-date-language="${env_locale}"
												 data-date-format="dd/mm/yyyy" >
												<input name="dataFine" type="text" class="form-control" id="dataFine"
													   value="${dataFineFormatted}" path="dataFine" autocomplete="off">
												<div class="input-group-addon">
													<span class="glyphicon glyphicon-th"></span>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<fmt:formatDate value="${dettaglio.dataFine}" type="date"
															pattern="dd/MM/yyyy" var="dataFineFormatted" />
											<br>
											<label><b>${dataFineFormatted}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

								
							</div>
						</div>
						<br>
					</div>


						<%--PERTIINENZA DELLA NEWS--%>
					<div style="margin: 20px 0">
						<div class="col-sm-12">
							<h5 align="center"><spring:message code="form.dettaglio.news.impresa.news_relevance" text="Pertinenza della news"/>:</h5>
						</div>

						<c:if test="${modifica}">
							<div class="col-sm-12">
								<div class="col-sm-3 col-md-3 col-md-offset-2 col-sm-offset-2">
									<div class="radio">
										<label><input type="radio" id="radioNewsInerente" name="radioNewsInerente" value="1" path="radioNewsInerente">&nbsp;<spring:message code="common_texts.enterprise"/></label>
									</div>
								</div>
								<div class="col-sm-3 col-md-3">
									<div class="radio">
										<label><input type="radio" id="radioNewsInerente" name="radioNewsInerente" value="3" path="radioNewsInerente">&nbsp;<spring:message code="form.dettaglio.impresa.done_projects"/></label>
									</div>
								</div>

								<c:if test="${utente.isStakeholder()}">
									<div class="col-sm-3 col-md-3">
										<div class="radio">
											<label><input type="radio" id="radioNewsInerente" name="radioNewsInerente" value="2" path="radioNewsInerente">&nbsp;<spring:message code="form.dettaglio.impresa.services"/></label>
										</div>
									</div>
								</c:if>

							</div>
						</c:if>

						<div class="col-sm-8 col-sm-offset-2">
							<div class="form-group">
								<div id="labelSelectImpresa" ${!modifica && empty dettaglio.plfImpresa.impresaTranslation.descImpresa ? 'hidden' : ''}>
									<label><spring:message code="common_texts.enterprise" text="Impresa"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectImpresa"
														 title="${selectTheCompanyLinkedToTheNewsTitleAttr}" data-live-search="true"
														 data-live-search-style="contains"
														 path="plfImpresa.idPlfImpresa" cssClass="selectpicker">
												<form:options items="${impresaList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfImpresa.impresaTranslation.descImpresa}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

								<div id="labelSelectProgetti" ${!modifica && empty dettaglio.plfProgettiProdotti.progettiProdottiTranslation.nomeProgettoProdotto ? 'hidden' : ''}>
									<label><spring:message code="form.dettaglio.impresa.done_projects" text="Progetti prodotti"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectProgetti"
														 title="${selectTheProductProjectLinkedToTheNewsTitleAttr}" data-live-search="true"
														 data-live-search-style="contains"
														 path="plfProgettiProdotti.idPlfProgettiProdotti" cssClass="selectpicker">
												<form:options items="${progettiList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfProgettiProdotti.progettiProdottiTranslation.nomeProgettoProdotto}</b></label>
										</c:otherwise>
									</c:choose>

								</div>


								<div id="labelSelectServizi" ${!modifica && empty dettaglio.plfServizi.serviziTranslation.titolo ? 'hidden' : ''}>
									<label><spring:message code="form.dettaglio.impresa.services"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectServizi"
														 title="${selectTheServiceLinkedToTheNewsTitleAttr}" data-live-search="true"
														 data-live-search-style="contains"
														 path="plfServizi.idServizi" cssClass="selectpicker">
												<form:options items="${serviziList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											
											<c:choose>
												<c:when test="${dettaglio.plfServizi.serviziStandard == 'S'}">
													<label><b>${dettaglio.plfServizi.serviziTranslation.descrizione}</b></label>
												</c:when>
												<c:otherwise>
													<label><b>${dettaglio.plfServizi.serviziTranslation.titolo}</b></label>
												</c:otherwise>
											</c:choose>
											
											
										</c:otherwise>
									</c:choose>
								</div>

							</div>
						</div>
					</div>

					<div class="row" ${!modifica && empty dettaglio.newsImpresaTranslation.dataTesto ? 'hidden' : ''}>
						<div class="col-sm-8 col-sm-offset-2">
							<c:choose>
								<c:when test="${modifica}">
									<p class="text-right">(max 4000 <spring:message code="chars" text="caratteri"/>)</p>
									<textarea name="newsImpresaTranslation.dataTesto" id="dataTesto" class="form-control"
											  maxlength="3990">${dettaglio.newsImpresaTranslation.dataTesto}</textarea>
								</c:when>
								<c:otherwise>
									<br>
									<label><b>${dettaglio.newsImpresaTranslation.dataTesto}</b></label>
								</c:otherwise>
							</c:choose>
						</div>
					</div>


					<div class="col-sm-12" style="margin-top: 20px;">
						<div class="form-group col-sm-8 col-sm-offset-2" ${!modifica && empty dettaglio.linkApprofondimento ? 'hidden' : ''}>
							<c:choose>
								<c:when test="${modifica}">
									<label><spring:message code="form.dettaglio.news.deepening_link_edit"/> :</label>
									<input name="linkApprofondimento" type="text" class="form-control"
										   placeholder="" value="${dettaglio.linkApprofondimento}"
										   path="linkApprofondimento" maxlength="400">
								</c:when>
								<c:otherwise>
									<label><spring:message code="form.dettaglio.news.deepening_link"/> :</label>
									<br>
									<c:choose>
										<c:when test="${utils.checkLinkUrl(dettaglio.linkApprofondimento)}">
											<a class="wow fadeInUp animated" target="_blank" href="${utils.anchor(dettaglio.linkApprofondimento)}" data-wow-delay="0.5s">${dettaglio.linkApprofondimento}</a>
										</c:when>
										<c:otherwise>
											<label><b>${dettaglio.linkApprofondimento}</b></label>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>


						<label class="col-md-4 col-md-offset-2">
							<input id="evidenzaPortale" name="evidenzaPortale" type="checkbox" ${!modifica ? 'disabled' : ''}/>
							<strong>&nbsp;<spring:message code="form.dettaglio.news.evidence"/>&nbsp;</strong>
						</label>
						<label class="col-md-4 col-md-offset-2">
							<input id="pubblicato" name="pubblicato" type="checkbox" ${!modifica ? 'disabled' : ''}/>
							<strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
						</label>
					</div>
					<form:select id="selectTags" hidden="true"
								 data-live-search="true" data-live-search-style="contains" multiple="true"
								 path="elencoIdTags">
						<form:options items="${allTags}" itemValue="id"/>
					</form:select>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<div id="tagsContainer" class="form-group">
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
					

				<c:if test="${modifica}">
					<div class="row">
						<div class="col-md-12" style="margin-top: 30px; padding-left: 30px">
							<div class="col-sm-6 save-btns">
								<a id="deleteButton" href="#" class="btn btn-finish btn-primary"
								   data-toggle="modal" data-target="#chiudiModal"><spring:message code="form.dettaglio.news.impresa.erase_news" text="CANCELLA NEWS"/></a>
								<button id="cancelButton" class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" text="ANNULLA"/></button>
							</div>
							<div class="col-sm-6 save-btns">
								<button id="saveButton"
										class='btn btn-finish btn-primary pull-right'
										onClick="javascript:aggiorna(event);"><spring:message code="common_texts.apply_changes" text="APPLICA MODIFICHE"/></button>
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
					<h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.news.impresa.news_deletion" text="Cancellazione news"/></h4>
				</div>
				<div class="modal-body">
					<strong><spring:message code="waring" text="Attenzione!"/> </strong>
					<spring:message code="form.dettaglio.impresa.news_deletion_text" text="Procedere con la cancellazione della news"/> ${dettaglio.newsImpresaTranslation.descrizione}?<br>
				</div>
				<div class="modal-footer">
					<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
					<button type="button" class="btn btn-primary"
							onClick="javascript:cancella();"><spring:message code="delete.uppercase" text="CANCELLA"/></button>
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

	</div>

</form:form>

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
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/summernote-bs4.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/summernote-it-IT.js"></script>


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/checkModify.js"></script>





<script>
	var uploadImage = false;
	var summernoteElement;

	$(document).ready(
			function() {

				if (${modifica})
					$('#frmDettaglio').addClass('safe-reload');
				else
					$('#frmDettaglio').removeClass('safe-reload');

				if (${modifica})
				{
					$('#labelSelectImpresa').show();
					$('#labelSelectServizi').hide();
					$('#labelSelectProgetti').hide();

					if (${dettaglio.radioNewsInerente == '1'})
					{
						$('input[name="radioNewsInerente"]').filter('[value=1]').iCheck('check');
						$('#labelSelectImpresa').show();
						$('#labelSelectServizi').hide();
						$('#labelSelectProgetti').hide();
					}
					else if (${dettaglio.radioNewsInerente == '2'})
					{
						$('input[name="radioNewsInerente"]').filter('[value=2]').iCheck('check');
						$('#labelSelectImpresa').hide();
						$('#labelSelectServizi').show();
						$('#labelSelectProgetti').hide();
					}
					else if (${dettaglio.radioNewsInerente == '3'})
					{
						$('input[name="radioNewsInerente"]').filter('[value=3]').iCheck('check');
						$('#labelSelectImpresa').hide();
						$('#labelSelectServizi').hide();
						$('#labelSelectProgetti').show();
					}

					$('input[name="radioNewsInerente"]').on('ifChecked', function(e) {
						if (e.target.value == 1)
						{
							$('#labelSelectImpresa').show();
							$('#labelSelectServizi').hide();
							$('#labelSelectProgetti').hide();
						}
						else if (e.target.value == 2)
						{
							$('#labelSelectImpresa').hide();
							$('#labelSelectServizi').show();
							$('#labelSelectProgetti').hide();
						}
						else if (e.target.value == 3)
						{
							$('#labelSelectImpresa').hide();
							$('#labelSelectServizi').hide();
							$('#labelSelectProgetti').show();
						}
					});
				}
				else
				{
					if (${dettaglio.radioNewsInerente == '1'})
					{
						$('#labelSelectImpresa').show();
						$('#labelSelectServizi').hide();
						$('#labelSelectProgetti').hide();
					}
					else if (${dettaglio.radioNewsInerente == '2'})
					{
						$('#labelSelectImpresa').hide();
						$('#labelSelectServizi').show();
						$('#labelSelectProgetti').hide();
					}
					else if (${dettaglio.radioNewsInerente == '3'})
					{
						$('#labelSelectImpresa').hide();
						$('#labelSelectServizi').hide();
						$('#labelSelectProgetti').show();
					}
				}

				var summernoteForm = $('#frmDettaglio');
				$('#dataTesto').summernote(
				{
					onChange: function (contents, $editable) {
						summernoteElement.val(summernoteElement.summernote('isEmpty') ? "" : contents);
					},
					lang: '${env_locale}',
					placeholder : "<spring:message code="newsFormNuovaTextPlaceholder" text="Testo della news" />",
					tabsize : 2,
					height : 100,
					fontNames : [ 'Arial', 'Arial Black',
						'Comic Sans MS', 'Courier New' ],
					toolbar : [
						[
							'style',
							[ 'bold', 'italic', 'underline',
								'clear' ] ],
						[ 'fontname', [ 'fontname' ] ],
						[ 'fontsize', [ 'fontsize' ] ],
						[ 'color', [ 'color' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'height', [ 'height' ] ] ]
				});
				var summernoteElement = $('#dataTesto');

				
				$('.datepicker').datepicker({
					format : 'yyyy-mm-dd',
					viewformat : 'dd/mm/yyyy',
					language : '${env_locale}'
				});

				$('#wizard-picture').change(function() {
					uploadImage = true;
				});

				jQuery.validator.addMethod("checkDate", function(e) {
					let valDataInizio = $('#dataInizio').val().split('/');
					let dataInizio = new Date(valDataInizio[2], valDataInizio[1] - 1, valDataInizio[0]);

					let valDataFine = $('#dataFine').val();

					if(valDataFine) {
						let arrDataFine = valDataFine.split("/");
						let dataFine = new Date(arrDataFine[2], arrDataFine[1] - 1, arrDataFine[0]);
						return dataFine > dataInizio;
					}
					return true;
				});

				var summernoteValidator = $('#frmDettaglio').validate({
					ignore: ':hidden:not(#dataTesto),.note-editable.card-block',
					errorPlacement: function(error, element) {
						if(element.parent('.input-group').length) {
							error.insertAfter(element.parent());
						} else {
							error.insertAfter(element);
						}
					},
					rules : {
						'newsImpresaTranslation.descrizione' : "required",
						dataInizio : "required",
						dataFine: "checkDate",
						'newsImpresaTranslation.dataTesto' : {
							required: function(element) {
								return $('#dataTesto').summernote('isEmpty');
							}
						},
					},
					messages : {
						'newsImpresaTranslation.descrizione' : '<spring:message code="newsInserireLaDescrizione" javaScriptEscape="true" />',
						dataInizio : '<spring:message code="newsInserireLaData" javaScriptEscape="true" />',
						dataFine: '<spring:message code="erroreDateNews" javaScriptEscape="true" />',
						'newsImpresaTranslation.dataTesto': '<spring:message code="newsFormNuovaInserireIlTesto" javaScriptEscape="true" />'
					},
					showErrors: function (errorMap, errorList) {
						if (typeof errorList[0] != "undefined") {
							var id = errorList[0].element.id;

							if( id === 'dataTesto') {
								$('html, body').animate({
									scrollTop: 200
								}, 300);
							}
						}
						this.defaultShowErrors();
					}
				});
						

				if(${dettaglio.evidenzaPortale} === true) {
					$('input[name="evidenzaPortale"]').iCheck('check');
				}
				if(${dettaglio.pubblicato} === true) {
					$('input[name="pubblicato"]').iCheck('check');
				}

				<!-- CONTROLLO USCITA PAGINA MODIFICATA SENZA SALVARE-->
				<!-- checkModify.js -->
				if (${modifica})
				{
					setCheckModify('saveButton','cancelButton','deleteButton',['evidenzaPortale','pubblicato'],['dataTesto']);
				}
			});

	function aggiorna(event) {

		event.preventDefault();
		if (uploadImage) {

			var fileSize = $('#wizardPicturePreview').attr('src').length;
			if (fileSize > 1572864) {
				alert("<spring:message code="immagineTroppoGrandeMax1.5M" javaScriptEscape="true" text="Attenzione! Immagine troppo grande (max 1.5 MB)"/>");
				return;
			}

			$("#imageData").val($('#wizardPicturePreview').attr('src'));
			$("#frmDettaglio").submit();
		} else {
			$("#imageData").val("");
			$("#frmDettaglio").submit();
		}
	}

	function toggleEvidenzaPortale(el) {
		console.log(el);
	}

	function cancella() {
		$("#imageData").val("");
		$('#frmDettaglio').attr('action', '/vimp/secure/cancellaNewsImpresa');
		$("#frmDettaglio").submit();
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

	function elaboraErrore(response) {
		if (response.status === 422) {
			return 'Dato non modificabile';
		} else {
			if (response.status === 423) {
				return '<spring:message code="sessioneScadutaRicollegarsiAlSistema" javaScriptEscape="true" text="Sessione scaduta. Ricollegarsi al sistema"/>';
			} else {
				return '<spring:message code="aggiornamentoValoreFallito" javaScriptEscape="true" text="Aggiornamento valore fallito."/>';
			}
		}
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

	function toggleTagSelection(e, id) {
		var startClass = $(e).attr('class');

		$(e).toggleClass('tag-selected').trigger('classChange');

		$('#selectTags > option[value="'+ id +'"]').each(function() {

			if(startClass.includes('tag-selected') && id == this.value) {
				$(this).attr('selected', false);
			} else if(!startClass.includes('tag-selected') && id == this.value) {
				$(this).attr('selected', true);
			}

		});
	}



</script>

<!-- BEGIN DETTAGLIO PROGETTO RICERCA-->
<!-- =========================================================================================== -->