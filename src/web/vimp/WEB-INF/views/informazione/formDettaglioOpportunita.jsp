
<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO IMPRESA-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message var="selectTheProgramTypeTitleAttr" code="form.nuova.opportunita.selectTheProgramTypeTitleAttr" text="Seleziona il tipo programma"/>
<spring:message var="selectEligibleSubjectsTitleAttr" code="form.nuova.opportunita.selectEligibleSubjectsTitleAttr" text="Seleziona i soggetti ammissibili"/>
<spring:message var="selectAreaTitleAttr" code="form.nuova.opportunita.selectAreaTitleAttr" text="Seleziona settore"/>
<spring:message var="selectCompanyStatusesTitleAttr" code="form.nuovo.dettaglio.opportunita.selectCompanyStatusesTitleAttr" text="Seleziona stati impresa"/>
<spring:message var="linkedEnterpriseStatusTitleAttr" code="form.dettaglio.opportunita.linked_enterprise_status" text="Stati impresa collegati"/>

<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">
					<b><spring:message code="form.dettaglio.opportunita.title" text="OPPORTUNIT&Agrave;"/></b> ${utils.truncateString(dettaglio.opportunitaTranslation.descNome,100)}
				</h1>
			</div>
		</div>
	</div>
</div>
<!-- End page header -->

<!-- property area -->

<form:form id="frmDettaglio" method="POST" action="/vimp/secure/salvaOpportunita"
		   role="form" modelAttribute="dettaglio" class="safe-reload">


	<form:hidden id="idPlfOpportunita" path="idPlfOpportunita" />
	<form:hidden id="opportunitaTranslation.idPlfOpportunita" path="opportunitaTranslation.idPlfOpportunita"/>

	<input id="imageData" name="imageData" type="hidden">


	<!-- CAMPI MANCANTI -->
	<form:hidden id="descFonte" path="opportunitaTranslation.descFonte" />


	<div class="content-area user-profiel">
		&nbsp;
		<div class="container">
			<c:if test="${!empty successMessage}">
				<div class="alert alert-success fade in">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					<p>${successMessage}</p>
					<a href="#" onclick="javascript: backToOpportunities()"><spring:message code="form.dettaglio.opportunita.back_to_opportunities"></spring:message></a>
				</div>
			</c:if>

			<c:if test="${!empty warningMessage}">
				<div class="alert alert-warning fade in">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					<p>
						<strong><spring:message code="warning" text="Attenzione!"/></strong> ${warningMessage}
					</p>
					<a href="#" onclick="javascript: backToOpportunities()"><spring:message code="form.dettaglio.opportunita.back_to_opportunities"></spring:message></a>
				</div>
			</c:if>

			<c:if test="${!empty errorMessage}">
				<div class="alert alert-danger fade in">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					<p>
						<strong><spring:message code="error" text="Errore"/></strong> ${errorMessage}
					</p>
					<a href="#" onclick="javascript: backToOpportunities()"><spring:message code="form.dettaglio.opportunita.back_to_opportunities"></spring:message></a>
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
														src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_opportunita.jpg"
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

									<%-- nome opportunità--%>
								<div class="form-group" ${!modifica && empty dettaglio.opportunitaTranslation.descNome ? 'hidden' : ''}>
									<br/>
									<label><spring:message code="form.dettaglio.opportunita.opportunity_name" text="Nome opportunit&agrave;"/> :
										<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if>
									</label>

									<c:choose>
										<c:when test="${modifica}">
											<input name="opportunitaTranslation.descNome" type="text" class="form-control"
												   placeholder="" value="${dettaglio.opportunitaTranslation.descNome}"
												   path="descNome" maxlength="1000">
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.opportunitaTranslation.descNome}</b></label>
										</c:otherwise>
									</c:choose>

								</div>

									<%-- valore economico--%>
								<div class="form-group" ${!modifica && empty dettaglio.numValEconomico ? 'hidden' : ''}>
									<label><spring:message code="form.dettaglio.opportunita.economic_value" text="Valore economico"/> : </label>
									<c:if test="${modifica}">
										<small>(<spring:message code="required" text="richiesto"/>)</small>
									</c:if>
									<c:choose>
										<c:when test="${modifica}">

											<input name="numValEconomico" id="numValEconomico"
												   type="text" class="form-control" placeholder="" path="numValEconomico"
												   value="<fmt:formatNumber value="${dettaglio.numValEconomico}" pattern="###,###,###,###,##0.00" />" maxlength="18">

										</c:when>
										<c:otherwise>
											<br><label><b><fmt:formatNumber value="${dettaglio.numValEconomico}"
																			pattern="###,###,###,###,##0.00" /> &euro;</b></label>
										</c:otherwise>
									</c:choose>
								</div>

									<%-- periodo --%>
								<div class="form-group" ${!modifica && dettaglio.opportunitaTranslation.descPeriodo ? 'hidden' : ''}>
									<label><spring:message code="form.dettaglio.opportunita.period" text="Periodo"/> :
										<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if>
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<textarea rows="2" name="opportunitaTranslation.descPeriodo" class="form-control" maxlength="1000">${dettaglio.opportunitaTranslation.descPeriodo}</textarea>
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.opportunitaTranslation.descPeriodo}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<br>

							<%-- requisiti --%>
						<div class="safe-col" ${!modifica && empty dettaglio.opportunitaTranslation.descRequisiti ? 'hidden' : ''}>
							<div class="col-sm-12">
								<div class="form-group">

									<label><spring:message code="form.dettaglio.opportunita.requisite" text="Requisiti"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<textarea rows="2" name="opportunitaTranslation.descRequisiti" class="form-control" maxlength="990">${dettaglio.opportunitaTranslation.descRequisiti}</textarea>
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.opportunitaTranslation.descRequisiti}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="safe-col">
								<%-- Tipo programma --%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTTipoProgramma.descrizione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.opportunita.program_type" text="Tipo programma"/> :
										<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if>
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectTipo"
														 title="${selectTheProgramTypeTitleAttr}" data-live-search="true"
														 data-live-search-style="contains"
														 path="plfTTipoProgramma.id"
														 cssClass="selectpicker">
												<form:options items="${tipoProgrammaList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.plfTTipoProgramma.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

								<%-- Soggetti ammissimibili --%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTSoggAmmissibili.descrizione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.opportunita.eligible_subjects" text="Soggetti ammissibili"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectSoggAmmissibili"
														 title="${selectEligibleSubjectsTitleAttr}" data-live-search="true"
														 data-live-search-style="contains"
														 path="plfTSoggAmmissibili.id"
														 cssClass="selectpicker">
												<form:options items="${soggAmmissibiliList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.plfTSoggAmmissibili.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="row">&nbsp;</div>

						<div class="safe-col">
								<%-- Settore --%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTSettoreProgetti.descrizione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.opportunita.area" text="Settore"/> : </label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectSettore" title="${selectAreaTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTSettoreProgetti.id" cssClass="selectpicker">
												<form:options items="${settoreImpresaList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.plfTSettoreProgetti.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

								<%-- data apertura --%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.dataApertura ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.opportunita.opening_date" text="Data apertura"/> :
										<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if>
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<fmt:formatDate value="${dettaglio.dataApertura}"
															type="date"
															pattern="dd/MM/yyyy"
															var="dataAperturaFormatted" />
											<div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
												<input  name="dataApertura" type="text" class="form-control" value="${dataAperturaFormatted}" path="dataApertura" id="dataApertura" autocomplete="off">
												<div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
											</div>
										</c:when>
										<c:otherwise>
											<fmt:formatDate value="${dettaglio.dataApertura}"
															type="date"
															pattern="dd/MM/yyyy"
															var="dataAperturaFormatted" />
											<br><label><b>${dataAperturaFormatted}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="safe-col">
								<%-- contatti --%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.opportunitaTranslation.descContatti ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.contatti.contacts_title" text="Contatti"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<input name="opportunitaTranslation.descContatti"
												   type="text" class="form-control" placeholder=""
												   value="${dettaglio.opportunitaTranslation.descContatti}" path="descContatti" maxlength="1000">
										</c:when>
										<c:otherwise>
											<br><label><b>${dettaglio.opportunitaTranslation.descContatti}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

								<%-- data scadenza --%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.dataScadenza ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="expiry_date" text="Data scadenza"/> :
										<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if>
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<fmt:formatDate value="${dettaglio.dataScadenza}"
															type="date"
															pattern="dd/MM/yyyy"
															var="dataScadenzaFormatted" />
											<div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
												<input  name="dataScadenza" type="text" class="form-control" value="${dataScadenzaFormatted}" path="dataScadenza" id="dataScadenza" autocomplete="off">
												<div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
											</div>
										</c:when>
										<c:otherwise>
											<fmt:formatDate value="${dettaglio.dataScadenza}"
															type="date"
															pattern="dd/MM/yyyy"
															var="dataScadenzaFormatted" />
											<br><label><b>${dataScadenzaFormatted}</b></label>
										</c:otherwise>
									</c:choose>

								</div>
							</div>
						</div>

						<div class="safe-col">
								<%--sito web--%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.descLink ? 'hidden' : ''}>
								<div class="form-group">
									<c:choose>
										<c:when test="${modifica}">
											<label for="sito"><spring:message code="web_site_edit" text="Sito web"/> :</label>
											<input name="descLink"
												   type="text" class="form-control" placeholder=""
												   value="${dettaglio.descLink}" path="descLink" maxlength="1000">
										</c:when>
										<c:otherwise>
											<label for="sito"><spring:message code="web_site" text="Sito web"/> :</label>
											<br>
											<c:choose>
												<c:when test="${utils.checkLinkUrl(dettaglio.descLink)}">
													<a class="wow fadeInUp animated" target="_blank" href="http://${dettaglio.descLink}" data-wow-delay="0.5s">${dettaglio.descLink}</a>
												</c:when>
												<c:otherwise>
													<label><b>${dettaglio.descLink}</b></label>
												</c:otherwise>
											</c:choose>
											
										</c:otherwise>
									</c:choose>
								</div>
							</div>

								<%--Pubblicato--%>
							<div class="col-sm-6" ${!modifica ? 'hidden' : ''}>
								<div class="checkbox form-grid-checkbox">
									<label>
										<input id="pubblicato" name="pubblicato" type="checkbox"/>
										<strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
									</label>
								</div>
							</div>
						</div>

							<%--tags--%>
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
                                <div class="col-sm-12" ${!modifica && empty dettaglio.opportunitaTranslation.descTag ? 'hidden' : ''}>
                                    <div class="form-group">
                                        <input name="opportunitaTranslation.descTag"
                                               value="${dettaglio.opportunitaTranslation.descTag}" id="selectedTags"
                                               hidden="true">
                                        <label>Tag</label><br>
                                        <c:choose>
                                            <c:when test="${modifica}">
                                                <small><spring:message
                                                        code="form.dettaglio.impresa.tag_guide"/></small><br>
                                                <c:forEach items="${allTags}" var="tag">
                                                    <div class="col-md-2 col-sm-2 element-tag tag-interactive"
                                                         onclick="toggleTagSelection(this)">${tag}</div>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${tags == null or fn:length(tags) < 1}">
                                                        <spring:message code="form.dettaglio.impresa.no_tags"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach items="${tags}" var="tag">
                                                            <div class="col-md-2 col-sm-2 element-tag tag-selected">${tag}</div>
                                                        </c:forEach>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                 --%>
						</div>

						&nbsp;
						<div class="safe-col">
								<%--stati imprese--%>
							<div class="col-sm-6" ${!modifica && empty dettaglio.statoImpresas ? 'hidden' : ''}>
								<div class="form-group">
									<c:if test="${modifica || dettaglio.statoImpresas.size()>0}">
										<div class="col-md-12 col-xs-12 register-blocks">
											<h2><spring:message code="form.dettaglio.opportunita.enterprises_status" text="Stati imprese"/> :</h2>
										</div>
									</c:if>

									<c:if test="${modifica}">
										<form:select id="selectStatoImpresa" title="${selectCompanyStatusesTitleAttr}"
													 data-live-search="true" data-live-search-style="contains"
													 cssClass="selectpicker"
													 path="">
											<form:options items="${statoImpresaList}" />
										</form:select>
									</c:if>
								</div>

								<div class="form-group">
									<div class="pull-right">
										<c:if test="${modifica}">
											<a id="collegaStatoImpresaButton"

											   class='btn btn-finish btn-primary'
											   onClick="javascript:collegaStatoImpresa();"><spring:message code="common_texts.link_to" text="Collega"/></a>
										</c:if>
									</div>
								</div>

								<div class="form-group">
									<br/>
									<br/>
									<br/>
									<div class="table-responsive">
										<table id="tabellaStatoImpresa" class="table table-condensed bordered1 tablesorter">
											<thead>
											<tr>
												<c:if test="${modifica}">
													<th></th>
												</c:if>
												<th data-toggle="tooltip" data-placement="top" title="${linkedEnterpriseStatusTitleAttr}"><spring:message code="form.dettaglio.opportunita.linked_enterprise_status" text="Stati impresa collegati"/></th>
											</tr>
											</thead>
											<tbody>
											<c:forEach items="${dettaglio.statoImpresas}" var="statoImpresas">
												<tr id='rigaStatoImpresa${statoImpresas.id}'>
													<c:if test="${modifica}">
														<td><a href="javascript:cancellaStatoImpresaShowModal(${statoImpresas.id});">
															<span class="glyphicon glyphicon-trash"></span>
														</a>
														</td>
													</c:if>
													<td>${statoImpresas.descrizione}</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<c:if test="${modifica}">
							<div class="col-sm-12">
								<div class="col-sm-6">&nbsp;</div>
								<div class="col-sm-6">&nbsp;</div>


								<div class="col-sm-12">
									<div class="col-sm-6 save-btns">
										<a href="#" class="btn btn-finish btn-primary"
										   data-toggle="modal" data-target="#chiudiModal"><spring:message code="form.dettaglio.opportunita.delete.link" text="CANCELLA L'OPPORTUNITA"/></a>
										<button class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" text="ANNULLA"/></button>
									</div>

									<div class="col-sm-6 save-btns">
										<button id="saveButton"
												class='btn btn-finish btn-primary pull-right'
												onClick="javascript:aggiorna(event);"
												value='APPLICA MODIFICHE'><spring:message code="common_texts.apply_changes" text="APPLICA MODIFICHE"/></button>
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
							<h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.opportunita.delete" text="Cancellazione dell'opportunit&agrave;"/></h4>
						</div>
						<div class="modal-body">
							<strong><spring:message code="warning" text="Attenzione!"/></strong>
							<spring:message code="form.dettaglio.opportunita.deletion_text"/> ${dettaglio.opportunitaTranslation.descNome}?<br>

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

			<div class="modal fade" id="cancellaCollegamentoModal" tabindex="-1" role="dialog"
				 aria-labelledby="cancellaCollegamentoModalLabel" aria-hidden="true">
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
							<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
							<button name="cancellaCollegamentoModalButton" id="cancellaCollegamentoModalButton" type="button" class="btn btn-primary"><spring:message code="delete.uppercase" text="CANCELLA"/></button>
						</div>
					</div>

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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript" charset="UTF-8"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>



<script>
	var uploadImage = false;

	$(document).ready(
			function() {

				if (${modifica})
					$('#frmDettaglio').addClass('safe-reload');
				else
					$('#frmDettaglio').removeClass('safe-reload');

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


				$("#tabellaStatoImpresa").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				refreshCollegamenti(true);


				$('.datepicker').datepicker({
					language : '${env_locale}',
					format : 'yyyy-mm-dd',
					viewformat : 'dd/mm/yyyy'
				});


				$('#wizard-picture').change(function() {
					uploadImage = true;
				});


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
						} else {
							error.insertAfter(element);
						}
					},
					rules:
							{
								'opportunitaTranslation.descNome': "required",
								numValEconomico: "required",
								'opportunitaTranslation.descPeriodo': "required",
								"plfTTipoProgramma.idTipoProgramma": "required",
								dataApertura: "required",
								dataScadenza: "checkDate"
							},
					messages: {
						'opportunitaTranslation.descNome' : '<spring:message code="opportunitaInserireNome" javaScriptEscape="true" />',
						numValEconomico : '<spring:message code="opportunitaInserireValore" javaScriptEscape="true" />',
						'opportunitaTranslation.descPeriodo' : '<spring:message code="opportunitaInserireIlPeriodo" javaScriptEscape="true" />',
						"plfTTipoProgramma.id": '<spring:message code="inserireIlTipoProgramma" javaScriptEscape="true" />',
						dataApertura: '<spring:message code="opportunitaInserireDataApertura" javaScriptEscape="true" />',
						dataScadenza: '<spring:message code="opportunitaInserireDataScadenza" javaScriptEscape="true" />'
					}
				});

				if(${dettaglio.pubblicato} === true) {
					$('input[name="pubblicato"]').iCheck('check');
				}

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

	function cancella() {
		$("#imageData").val("");
		$('#frmDettaglio').attr('action', '/vimp/secure/cancellaOpportunita');
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


	function refreshCollegamenti(firstLoad)
	{
		if (firstLoad)
		{
			if (${dettaglio.statoImpresas.size()>0})
				$("#tabellaStatoImpresa").show();
			else $("#tabellaStatoImpresa").hide();

		}
		else
		{
			var rowCount = 0;
			$('#tabellaStatoImpresa > tbody  > tr').each(function() {
				$this = $(this);
				var strValue = $this.html();
				if (strValue) {
					rowCount++;
				}
			});
			if (rowCount>0)
				$("#tabellaStatoImpresa").show();
			else $("#tabellaStatoImpresa").hide();

		}
	}




	function collegaStatoImpresa() {

		var statoImpresa = $( "#selectStatoImpresa" ).val();
		if (statoImpresa)
		{
			$.ajax({
				type: "POST",
				url : '/vimp/secure/collegaStatoImpresa',
				data:  { idOpportunita : ${dettaglio.idPlfOpportunita}, idStatoImpresa: statoImpresa },
				success : function(data) {
					var id = data.pk;
					var desc = data.value;


					var row = "<td><a href='javascript:cancellaStatoImpresaShowModal("+ id +");'> " +
							"<span class='glyphicon glyphicon-trash'></span></a></td> " +
							"<td>" + desc + "</td> " ;

					$('#tabellaStatoImpresa').append('<tr id="rigaStatoImpresa'+id+'" role="row">'+ row +'</tr>');
					refreshCollegamenti(false);

				},
				error: function(error){
					$('#errorModalTitle').text("<spring:message code="opportunitaCollegaStatoImpresa" javaScriptEscape="true" text="Collega stato impresa"/>");
					$('#errorModalMessage').text("<spring:message code="opportunitaSelezionareStatoImpresaValido" javaScriptEscape="true" text="Selezionare uno stato impresa valido da collegare"/>");
					$('#errorModal').modal('show');
				}
			});
		}
		else
		{
			$('#errorModalTitle').text("<spring:message code="opportunitaCollegaStatoImpresa" javaScriptEscape="true" text="Collega stato impresa"/>");
			$('#errorModalMessage').text("<spring:message code="opportunitaSelezionareStatoImpresaValido" javaScriptEscape="true" text="Selezionare uno stato impresa valido da collegare"/>");
			$('#errorModal').modal('show');
		}
	}

	function backToOpportunities() {
		var baseUrl = window.location.href.split('/vimp')[0];
		originalUrl = baseUrl + '/vimp/home/5';
		window.location.replace(originalUrl);
	}


	function cancellaStatoImpresa(idStatoImpresa) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaCollegamentoStatoImpresa',
			data:  { idOpportunita : ${dettaglio.idPlfOpportunita}, idStatoImpresa: idStatoImpresa },
			success : function(result,data) {
				$('#rigaStatoImpresa'+idStatoImpresa).html('');
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaStatoImpresaShowModal(idStatoImpresa) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaStatoImpresa('+idStatoImpresa+');');
		$('#cancellaCollegamentoModal').modal('show');
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

<!-- BEGIN DETTAGLIO IMPRESA-->
<!-- =========================================================================================== -->