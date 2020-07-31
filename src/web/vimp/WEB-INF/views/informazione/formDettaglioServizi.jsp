<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO SERVIZIO -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:message var="selectServiziStandardTitleAttr"
				code="form.dettaglio.servizi.selectServiziStandardTitleAttr" />
<spring:message var="selectDenominazioneTitleAttr"
				code="form.dettaglio.servizi.selectDenominazioneTitleAttr" />
<spring:message var="selectTheServiceTypeTitleAttr"
				code="form.dettaglio.servizi.selectTheServiceTypeTitleAttr" />
<spring:message var="selectTheAreasOfCompetenceTitleAttr"
				code="form.dettaglio.servizi.selectTheAreasOfCompetenceTitleAttr" />
<spring:message var="selectModalitaErogazioneServizioTitleAttr"
				code="form.dettaglio.servizi.selectModalitaErogazioneServizioTitleAttr" />
<spring:message var="selectTipoErogazioneServizioTitleAttr"
				code="form.dettaglio.servizi.selectTipoErogazioneServizioTitleAttr" />
<spring:message var="selectMacroareaServizioTitleAttr"
				code="form.dettaglio.servizi.selectMacroareaServizioTitleAttr" />

<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">
					<b><spring:message code="form.dettaglio.servizi.title" /></b>
					<c:choose>
						<c:when test="${dettaglio.serviziStandard == 'S'}">
							${utils.truncateString(dettaglio.serviziTranslation.descrizione,100)}
						</c:when>
						<c:otherwise>
							${utils.truncateString(dettaglio.serviziTranslation.titolo,100)}
						</c:otherwise>
					</c:choose>
				</h1>
			</div>
		</div>
	</div>
</div>
<!-- End page header -->

<!-- property area -->

<form:form id="frmDettaglio" method="POST"
		   action="/vimp/secure/salvaServizi" role="form"
		   modelAttribute="dettaglio" class="safe-reload">


	<form:hidden id="idServizi" path="idServizi" />
	<form:hidden id="serviziTranslation.idServizi"
				 path="serviziTranslation.idServizi" />
	

	<input id="imageData" name="imageData" type="hidden">

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
					<a href="#" onclick="javascript: backToServices()"><spring:message
							code="form.dettaglio.servizi.back_to_services"></spring:message></a>
				</div>
			</c:if>

			<c:if test="${!empty warningMessage}">
				<div class="alert alert-warning fade in">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					<p>
						<strong><spring:message code="warning" /></strong>
							${warningMessage}
					</p>
					<a href="#" onclick="javascript: backToServices()"><spring:message
							code="form.dettaglio.servizi.back_to_services"></spring:message></a>
				</div>
			</c:if>

			<c:if test="${!empty errorMessage}">
				<div class="alert alert-danger fade in">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					<p>
						<strong><spring:message code="error" text="Errore" /></strong>
							${errorMessage}
					</p>
					<a href="#" onclick="javascript: backToServices()"><spring:message
							code="form.dettaglio.servizi.back_to_services"></spring:message></a>
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
													<input type="file" id="wizard-picture"
														   accept=".jpg,.jpeg,.png">
												</c:if>
											</c:when>
											<c:otherwise>
												<img
														src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_servizi.jpg"
														class="picture-src" id="wizardPicturePreview" title="" />
												<c:if test="${modifica}">
													<input type="file" id="wizard-picture"
														   accept=".jpg,.jpeg,.png">
												</c:if>
											</c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>
							<div class="col-sm-6">

								<c:choose>
									<c:when test="${modifica}">
										<div class="form-group">
											<label><spring:message
													code="form.dettaglio.servizi.select_is_standard" /></label>
											<form:select id="selectServiziStandard"
														 title="${selectServiziStandardTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="serviziStandard" cssClass="selectpicker">
												<form:option value="S">
													<spring:message code="yes" />
												</form:option>
												<form:option value="N">
													<spring:message code="no" />
												</form:option>
											</form:select>
										</div>
									</c:when>
									<c:otherwise>
										<div class="form-group"
											${empty dettaglio.serviziStandard ? 'hidden' : ''}>
											<div style="margin-top: 30px">
												<strong> 
													<label ${dettaglio.serviziStandard ne 'S' ? 'hidden' : ''}><spring:message code="form.dettaglio.servizi.standard" /></label> 
													<label ${dettaglio.serviziStandard eq 'S' ? 'hidden' : ''}><spring:message code="form.dettaglio.servizi.nonstandard" /></label>
												</strong>
											</div>
										</div>
									</c:otherwise>
								</c:choose>


								<div class="form-group" id="casellaTitolo"
									${!modifica && empty dettaglio.serviziTranslation.titolo ? 'hidden' : ''}>
									<br /> <label><spring:message
										code="form.dettaglio.servizi.service_name" /> : <c:if
										test="${modifica}">
									<small>(<spring:message code="required"
															text="richiesto" />)
									</small>
								</c:if> </label>


									<c:choose>
										<c:when test="${modifica}">
											<input name="serviziTranslation.titolo" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.serviziTranslation.titolo}" path="titolo"
												   maxlength="400">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.serviziTranslation.titolo}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

								<div class="form-group" id="casellaDenominazione"
									${!modifica && empty dettaglio.denominazioneServizio.descrizione ? 'hidden' : ''}>
									<br /> <label><spring:message
										code="form.dettaglio.servizi.denominazione" /> : <c:if
										test="${modifica}">
									<small>(<spring:message code="required"
															text="richiesto" />)
									</small>
								</c:if> </label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectDenominazione"
														 title="${selectDenominazioneTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="denominazioneServizio.id" cssClass="selectpicker">
												<form:options items="${denominazioniServizioList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.denominazioneServizio.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

								<div class="form-group"
									${!modifica && empty dettaglio.serviziTranslation.descrizione ? 'hidden' : ''}>
									<label><spring:message code="description"
														   text="Descrizione" /> : </label>
									<c:if test="${modifica}">
										<small>(<spring:message code="required"
																text="richiesto" />)
										</small>
									</c:if>
									<c:choose>
										<c:when test="${modifica}">
											<textarea rows="4" name="serviziTranslation.descrizione"
													  class="form-control" maxlength="3990">${dettaglio.serviziTranslation.descrizione}</textarea>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.serviziTranslation.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

							</div>
						</div>
						<br>


						<div class="col-md-12">
								<%-- Impresa --%>
							<div class="col-sm-6">
								<div class="form-group">
									<label><spring:message code="common_texts.stakeholder"
														   text="Stakeholder" /> : <b>${dettaglio.plfImpresa.descImpresa}</b></label>
								</div>
							</div>

								<%-- vuoto --%>
							<div class="col-sm-6">
								<div class="form-group">&nbsp;</div>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="form-group"
									${!modifica && empty dettaglio.plfTTipoServizio.descrizione ? 'hidden' : ''}>
									<label><spring:message
											code="form.dettaglio.servizi.standard.service_type"
											text="Tipo servizio" /> : <c:if test="${modifica}">
										<small>(<spring:message code="required"
																text="richiesto" />)
										</small>
									</c:if> </label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectTipo"
														 title="${selectTheServiceTypeTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTTipoServizio.id" cssClass="selectpicker">
												<form:options items="${tipoServizioList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTTipoServizio.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group"
									${!modifica && empty dettaglio.plfTAreeCompetenza.descrizione ? 'hidden' : ''}>
									<label><spring:message
											code="form.dettaglio.servizi.competence_area" /> : <c:if
											test="${modifica}">
										<small>(<spring:message code="required"
																text="richiesto" />)
										</small>
									</c:if> </label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectAreeCompetenza"
														 title="${selectTheAreasOfCompetenceTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTAreeCompetenza.id" cssClass="selectpicker">
												<form:options items="${areeCompetenzaList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTAreeCompetenza.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

						</div>
						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="form-group"
									${!modifica && empty dettaglio.modalitaErogazioneServizio.descrizione ? 'hidden' : ''}>
									<label><spring:message
											code="form.dettaglio.servizi.modalita_erogazione" /> : <c:if
											test="${modifica}">
										<small>(<spring:message code="required"
																text="richiesto" />)
										</small>
									</c:if> </label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectModalitaErogazioneServizio"
														 title="${selectModalitaErogazioneServizioTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="modalitaErogazioneServizio.id" cssClass="selectpicker">
												<form:options items="${modalitaErogazioneServizioList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.modalitaErogazioneServizio.descrizione}</b></label>
											<input type="hidden" id="selectModalitaErogazioneServizio"
												   value="${dettaglio.modalitaErogazioneServizio.id}" disabled>
										</c:otherwise>
									</c:choose>

								</div>
							</div>
							<div id="casellaOrari" class="col-sm-6"
								${!modifica && empty dettaglio.serviziTranslation.orari ? 'hidden' : ''}
								 hidden>
								<div class="form-group">
									<label><spring:message
											code="form.dettaglio.servizi.orari" /></label>
									<c:choose>
										<c:when test="${modifica}">
											<input id="orari" name="serviziTranslation.orari" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.serviziTranslation.orari}" path="orari"
												   maxlength="3990">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.serviziTranslation.orari}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-6"
								${!modifica && empty dettaglio.tipiErogazione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message
											code="form.dettaglio.servizi.tipo_erogazione" /> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectTipoErogazione"
														 title="${selectTipoErogazioneServizioTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 multiple="true" path="elencoTipoErogazione"
														 cssClass="selectpicker">
												<form:options items="${tipoErogazioneServizioList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<c:forEach items="${dettaglio.tipiErogazione}"
													   var="tipoErogazione">
												<label><b>${tipoErogazione.descrizione}</b></label>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-sm-6"
								${!modifica && empty dettaglio.macroaree ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message
											code="form.dettaglio.servizi.macroarea" /> : <c:if
											test="${modifica}">
										<small>(<spring:message code="required"
																text="richiesto" />)
										</small>
									</c:if> </label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectMacroarea"
														 title="${selectMacroareaServizioTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 multiple="true" path="elencoMacroarea"
														 cssClass="selectpicker">
												<form:options items="${macroareaServizioList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<c:forEach items="${dettaglio.macroaree}" var="macroarea">
												<label><b>${macroarea.descrizione}</b></label>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="form-group"
									${!modifica && empty dettaglio.serviziTranslation.riferimenti ? 'hidden' : ''}>
									<label><spring:message
											code="form.dettaglio.servizi.riferimenti" />:</label>
									<c:choose>
										<c:when test="${modifica}">
											<input id="riferimenti" name="serviziTranslation.riferimenti" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.serviziTranslation.riferimenti}" path="riferimenti"
												   maxlength="1000">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.serviziTranslation.riferimenti}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-sm-6">
								&nbsp;
							</div>
						</div>


						<c:choose>
							<c:when test="${modifica}">
								<div class="col-sm-12">
									<div class="col-sm-6">
										<div class="form-group">
											<label><spring:message code="form.dettaglio.servizi.indirizzo" />:</label>
											<input id="indirizzoErogazione" name="indirizzoErogazione" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.indirizzoErogazione}" path="indirizzoErogazione"
												   maxlength="1000">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label><spring:message code="form.dettaglio.servizi.civico" />:</label>
											<input id="civico" name="civico" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.civico}" path="civico"
												   maxlength="1000">
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<c:if test="${!empty dettaglio.indirizzoErogazione}">
									<div class="col-sm-12">
										<div class="col-sm-6">
											<div class="form-group">
												<label><spring:message code="form.dettaglio.servizi.indirizzo" />:</label>
												<br>
												<label><b>${dettaglio.indirizzoErogazione} ${dettaglio.civico}</b></label>
											</div>
										</div>
										<div class="col-sm-6">&nbsp;</div>
									</div>
								</c:if>
							</c:otherwise>
						</c:choose>



						<div class="col-sm-12">
							<div class="col-sm-6">
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
							</div>
							
							<div class="col-sm-6">
								<div class="form-group" ${!modifica && empty dettaglio.dataFine ? 'hidden' : ''}>
									<label><spring:message code="end_date" text="Data fine"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<fmt:formatDate value="${dettaglio.dataFine}" type="date" pattern="dd/MM/yyyy" var="dataFineFormatted" />
											<div class="input-group date" data-provide="datepicker" data-date-language="${env_locale}" data-date-format="dd/mm/yyyy" >
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




						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="form-group"
									${!modifica && empty dettaglio.linkApprofondimento ? 'hidden' : ''}>
									<c:choose>
										<c:when test="${modifica}">
											<label><spring:message code="form.dettaglio.news.deepening_link_edit" />:</label>
											<input id="orari" name="linkApprofondimento" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.linkApprofondimento}" path="orari"
												   maxlength="3990">
										</c:when>
										<c:otherwise>
											<label><spring:message code="form.dettaglio.news.deepening_link" />:</label>
											<br>
											<c:choose>
												<c:when test="${utils.checkLinkUrl(dettaglio.linkApprofondimento)}">
													<a target="_blank" href="${utils.anchor(dettaglio.linkApprofondimento)}">${dettaglio.linkApprofondimento}</a>
												</c:when>
												<c:otherwise>
													<label><b>${dettaglio.linkApprofondimento}</b></label>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</div>
							</div>


							<c:choose>
								<c:when test="${ utente.isBackoffice()}">
									<div class="col-sm-6">
										<div class="checkbox">
											<label> <input id="pubblicato" name="pubblicato"
														   type="checkbox" disabled /> <strong>&nbsp;<spring:message
													code="form.dettaglio.informazione.published" />&nbsp;
											</strong>
											</label>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-sm-6" ${!modifica ? 'hidden' : ''}>
										<div class="checkbox">
											<label> <input id="pubblicato" name="pubblicato"
														   type="checkbox" /> <strong>&nbsp;<spring:message
													code="form.dettaglio.informazione.published" />&nbsp;
											</strong>
											</label>
										</div>
									</div>
								</c:otherwise>
							</c:choose>





							<c:if test="${not empty pacchetti}">
								<div class="col-sm-6">
									<label><spring:message
											code="form.dettaglio.pacchetto.titolo" /> : </label>
									<ul>
										<c:forEach items="${pacchetti}" var="pacchetto">
											<li><strong>${pacchetto.pacchettoServiziTranslation.titolo}</strong>
												- ${pacchetto.macroarea.descrizione}</li>
										</c:forEach>
									</ul>
								</div>
							</c:if>

								<%-- tags --%>
							<div class="safe-col">
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
									<%--
                                    <div class="col-sm-12"
                                        ${!modifica && empty dettaglio.serviziTranslation.descTag ? 'hidden' : ''}>
                                        <div class="form-group">
                                            <input name="serviziTranslation.descTag"
                                                value="${dettaglio.serviziTranslation.descTag}"
                                                id="selectedTags" hidden="true"> <label>Tag</label><br>
                                            <c:choose>
                                                <c:when test="${modifica}">
                                                    <small><spring:message
                                                            code="form.dettaglio.impresa.tag_guide" /></small>
                                                    <br>
                                                    <c:forEach items="${allTags}" var="tag">
                                                        <div class="col-md-2 col-sm-2 element-tag tag-interactive"
                                                            style="margin-bottom: 15px"
                                                            onclick="toggleTagSelection(this)">${tag}</div>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${tags == null or fn:length(tags) < 1}">
                                                            <spring:message code="form.dettaglio.impresa.no_tags" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${tags}" var="tag">
                                                                <div class="col-md-2 col-sm-2 element-tag tag-selected"
                                                                    style="margin-bottom: 15px">${tag}</div>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    --%>
							</div>
						</div>
						
						<br>&nbsp;

						<c:if test="${modifica}">
							<div class="safe-col" style="margin-top: 30px;">
								<div class="col-sm-6 save-btns">

									<a id="deleteButton" href="#" class="btn btn-finish btn-primary"
									   data-toggle="modal" data-target="#chiudiModal"><spring:message
											code="form.dettaglio.servizi.erase_service" /></a>
									<button id="cancelButton" class='btn btn-default' type="button"
											onClick="javascript:resetForm();">
										<spring:message code="common_texts.reset" />
									</button>
								</div>
								<div class="col-sm-6 save-btns">
									<button id="saveButton"
											class='btn btn-finish btn-primary pull-right'
											onClick="javascript:aggiorna(event);">
										<spring:message code="common_texts.apply_changes" />
									</button>
								</div>
							</div>
						</c:if>
					</div>
				</div>


				<!-- Modal -->
				<div class="modal fade" id="chiudiModal" tabindex="-1" role="dialog"
					 aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
										code="close" text="Chiudi" /></span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="form.dettaglio.servizi.service_deletion" />
								</h4>
							</div>
							<div class="modal-body">
								<strong><spring:message code="warning" /> </strong>
								<c:if test="${not empty pacchetti}">
									<p>
											<spring:message
													code="form.dettaglio.servizi.service_exists_in" />
									<ul>
										<c:forEach items="${pacchetti}" var="pacchetto">
											<li><strong>${pacchetto.pacchettoServiziTranslation.titolo}</strong>
												- ${pacchetto.macroarea.descrizione}</li>
										</c:forEach>
									</ul>
									</p>
								</c:if>
								<spring:message
										code="form.dettaglio.servizi.service_deletion_text" />
									${dettaglio.serviziTranslation.titolo}?<br>
								<spring:message code="form.dettaglio.deletion_news_linked_text"
												text="Verranno cancellate anche le news adesso collegate." />
								<br>
							</div>
							<div class="modal-footer">
								<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message
										code="undo.uppercase" /></a>
								<button type="button" class="btn btn-primary"
										onClick="javascript:cancella();">
									<spring:message code="delete.uppercase" />
								</button>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</form:form>

<div class="modal fade" id="resetFormConfirm" tabindex="-1"
	 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4>
					<spring:message code="warning" />
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="messaggioConfermaReload" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="undo" />
				</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
						onClick="javascript:reloadPage();">
					<spring:message code="continue" />
				</button>
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

<script
		src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
<script
		src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js"
		type="text/javascript" charset="UTF-8"></script>
<script
		src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js"
		type="text/javascript" charset="UTF-8"></script>


<script
		src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/checkModify.js"></script>

<script>
	var uploadImage = false;

	$(document).ready(
			function() {

				if (${modifica})
					$('#frmDettaglio').addClass('safe-reload');
				else
					$('#frmDettaglio').removeClass('safe-reload');

				$('#selectServiziStandard').change(function () {
					showDenominazione();
				});

				showDenominazione();

				$('#selectModalitaErogazioneServizio').change(function(){
					showOrari();
				});

				showOrari();

				$('.datepicker').datepicker({
					format : 'yyyy-mm-dd',
					viewformat : 'dd/mm/yyyy',
					language : '${env_locale}'
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


				$('#wizard-picture').change(function() {
					uploadImage = true;
				});


				$('#frmDettaglio').validate({
					ignore: ":hidden:not(.validate)",
					errorPlacement: function(error, element) {
						if(element.parent('.input-group').length) {
							error.insertAfter(element.parent());
						} else {
							error.insertAfter(element);
						}
					},
					rules:
							{
								'serviziStandard':'required',
								'denominazioneServizio.id': {
									required: {
										depends: function(){
											return $('#selectServiziStandard').val() == 'S';
										}
									}
								},
								'serviziTranslation.titolo': {
									required: {
										depends: function(){
											return $('#selectServiziStandard').val() == 'N';
										}
									}
								},
								'serviziTranslation.descrizione' : 'required',
								'plfTTipoServizio.id': 'required',
								'plfTAreeCompetenza.id':'required',
								'modalitaErogazioneServizio.id':'required',
								'elencoMacroarea':'required',
								dataInizio: 'required',
								dataFine: "checkDate"
							},
					messages: {
						'serviziStandard':'<spring:message code="serviziFormNuovoSelezionaStandard" javaScriptEscape="true" />',
						'denominazionServizio.id':'<spring:message code="serviziFormNuovoSelezionaDenominazione" javaScriptEscape="true" />',
						'serviziTranslation.titolo' : '<spring:message code="serviziFormNuovoInserireNome" javaScriptEscape="true" />',
						'serviziTranslation.descrizione' : '<spring:message code="serviziFormNuovoInserireDescrizione" javaScriptEscape="true" />',
						'plfTTipoServizio.id': '<spring:message code="serviziFormNuovoInserireTipo" javaScriptEscape="true" />',
						'plfTAreeCompetenza.id':'<spring:message code="serviziFormNuovoSelectAreaCompetenza" javaScriptEscape="true" />',
						'modalitaErogazioneServizio.id': '<spring:message code="serviziFormNuovoSelectModalitaErogazione" javaScriptEscape="true" />',
						'elencoMacroarea':'<spring:message code="serviziFormNuovoSelectMacroarea" javaScriptEscape="true" />',
						dataInizio: '<spring:message code="servizioInserireLaData" javaScriptEscape="true" />',
						dataFine: '<spring:message code="erroreDateNews" javaScriptEscape="true" />'
					}
				});

				if(${dettaglio.pubblicato} === true) {
					$('input[name="pubblicato"]').iCheck('check');
				}

				<!-- CONTROLLO USCITA PAGINA MODIFICATA SENZA SALVARE-->
				<!-- checkModify.js -->
				if (${modifica})
				{
					setCheckModify('saveButton','cancelButton','deleteButton',
							['pubblicato'],
							null);
				}

			});

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

	
	function aggiorna(event) {
		event.preventDefault();
		if (uploadImage) {

			var fileSize = $('#wizardPicturePreview').attr('src').length;
			if (fileSize > 1572864) {
				alert("<spring:message code="immagineTroppoGrandeMax1.5M" javaScriptEscape="true"/>");
				return;
			}

			$("#imageData").val($('#wizardPicturePreview').attr('src'));
			$("#frmDettaglio").submit();
		} else {
			$("#imageData").val("");
			$("#frmDettaglio").submit();
		}

	}

	function cancella() {
		$("#imageData").val("");
		$('#frmDettaglio').attr('action', '/vimp/secure/cancellaServizi');
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

	function resetForm(){
		$('#resetFormConfirm').modal();
	}

	function reloadPage(){
		var baseUrl = window.location.href.split('/vimp')[0];
		originalUrl = baseUrl + '/vimp' + '${refreshRelativeUrl}';
		window.location.replace(originalUrl); // Usare questo se ${refreshRelativeUrl} arriva dall'handler
		//location.reload(true); // Usare questo nl caso non si voglia usare ${refreshRelativeUrl}
	}

	function backToServices() {
		var baseUrl = window.location.href.split('/vimp')[0];
		originalUrl = baseUrl + '/vimp/home/3';
		window.location.replace(originalUrl);
	}

	function showDenominazione() {
		var keyCode = $('#selectServiziStandard').val();
		if(keyCode == 'S') {
			$('#casellaTitolo').hide();
			$('#casellaDenominazione').show();
		}else if(keyCode== 'N') {
			$('#casellaTitolo').show();
			$('#casellaDenominazione').hide();
		}
	}

	function showOrari() {
		var keyCode = $('#selectModalitaErogazioneServizio').val();

		if(keyCode == 1) {
			$('#casellaOrari').show();
		} else if(keyCode == 2){
			$('#casellaOrari').hide();
		}
	}

</script>

<!-- BEGIN DETTAGLIO SERVIZI-->
<!-- =========================================================================================== -->