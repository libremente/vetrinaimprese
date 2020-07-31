<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO PROGETTO RICERCA-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<spring:message var="selectCompanyStatusTitleAttr" code="form.dettaglio.impresa.form.selectCompanyStatusTitleAttr" text="Seleziona lo stato dell\'impresa"/>
<spring:message var="selectTheLegalNatureOfTheCompanyTitleAttr" code="form.dettaglio.impresa.selectTheLegalNatureOfTheCompanyTitleAttr" text="Seleziona la natura giuridica dell\'impresa"/>
<spring:message var="selectTheMainSectorOfTheCompanyTitleAttr" code="form.dettaglio.impresa.selectTheMainSectorOfTheCompanyTitleAttr" text="Seleziona il settore principale dell'impresa"/>
<spring:message var="selectTheOriginOfTheCompanyTitleAttr" code="form.dettaglio.impresa.selectTheOriginOfTheCompanyTitleAttr" text="Seleziona l'origine dell\'impresa"/>
<spring:message var="selectAtecoTitleAttr" code="form.dettaglio.impresa.selectAtecoTitleAttr" text="Seleziona ateco"/>
<spring:message var="selectTheClassOfEmployeesLastYearTitleAttr" code="form.dettaglio.impresa.selectTheClassOfEmployeesLastYearTitleAttr" text="Seleziona la classe di addetti ultimo anno"/>
<spring:message var="selectTheCapitalClassTitleAttr" code="form.dettaglio.impresa.selectTheCapitalClassTitleAttr" text="Seleziona la classe di capitale"/>
<spring:message var="selectTheProductionClassLastYearTitleAttr" code="form.dettaglio.impresa.selectTheProductionClassLastYearTitleAttr" text="Seleziona la classe di produzione ultimo anno"/>
<spring:message var="selectTheSpecialSectionOfTheCompanyTitleAttr" code="form.dettaglio.impresa.selectTheSpecialSectionOfTheCompanyTitleAttr" text="Seleziona la sezione speciale dell'impresa"/>
<spring:message var="selectTheInnovationElementsTitleAttr" code="form.dettaglio.impresa.selectTheInnovationElementsTitleAttr" text="Seleziona gli elementi di innovazione"/>
<spring:message var="innovationElementsTitleAttr" code="form.dettaglio.impresa.innovation_elements" text="Elementi di innovazione"/>
<spring:message var="selectTheReferenceMarketsTitleAttr" code="form.dettaglio.impresa.selectTheReferenceMarketsTitleAttr" text="Seleziona i mercati di riferimento"/>
<spring:message var="referenceBusinessTitleAttr" code="form.dettaglio.impresa.reference_business" text="Mercati di riferimento"/>
<spring:message var="selectTheRoleOfTheContactInTheCompanyTitleAttr" code="form.dettaglio.impresa.selectTheRoleOfTheContactInTheCompanyTitleAttr" text="Seleziona il ruolo in azienda del contatto"/>
<spring:message var="selectTheTypeOfFemalePrevalenceTitleAttr" code="form.dettaglio.impresa.selectTheTypeOfFemalePrevalenceTitleAttr" text="Seleziona il tipo di prevalenza femminile"/>
<spring:message var="selectTheTypeOfJuvenilePrevalenceTitleAttr" code="form.dettaglio.impresa.selectTheTypeOfJuvenilePrevalenceTitleAttr" text="Seleziona il tipo di prevalenza giovanile"/>
<spring:message var="selectTheTypeOfForeignPrevalenceTitleAttr" code="form.dettaglio.impresa.selectTheTypeOfForeignPrevalenceTitleAttr" text="Seleziona il tipo di prevalenza straniera"/>
<spring:message var="nameTitleAttr" code="name" text="Nome"/>
<spring:message var="descriptionTitleAttr" code="description" text="Descrizione"/>
<spring:message var="selectTheStakeholdersToLinkTitleAttr" code="form.dettaglio.impresa.selectTheStakeholdersToLinkTitleAttr" text="Seleziona gli stakeholder da collegare"/>
<spring:message var="stakeholderTitleAttr" code="common_texts.stakeholder" text="Stakeholder"/>
<spring:message var="selectTheCompaniesToLinkTitleAttr" code="form.dettaglio.impresa.selectTheCompaniesToLinkTitleAttr" text="Seleziona le imprese da collegare"/>
<spring:message var="enterpriseTitleAttr" code="common_texts.enterprise" text="Impresa"/>
<spring:message var="selectTheServicesToLinkTitleAttr" code="form.dettaglio.impresa.selectTheServicesToLinkTitleAttr" text="Seleziona i servizi da collegare"/>
<spring:message var="serviceTitleAttr" code="common_texts.service" text="Servizio"/>
<spring:message var="linkTitleAttr" code="link" text="Link"/>
<spring:message var="newsTitleAttr" code="news" text="News"/>
<spring:message var="selectTheProvinceTitleAttr" code="form.dettaglio.impresa.selectTheProvinceTitleAttr" text="Seleziona la provincia"/>
<spring:message var="selectTheCityTitleAttr" code="form.dettaglio.impresa.selectTheCityTitleAttr" text="Seleziona il comune"/>

<spring:message var="selectTheStreetTitleAttr" code="form.dettaglio.impresa.selectTheStreetTitleAttr" text="Seleziona la strada"/>
<spring:message var="selectTheCivicNumberTitleAttr" code="form.dettaglio.impresa.selectTheCivicNumberTitleAttr" text="Seleziona il numero civico"/>

<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">
					<c:choose>
						<c:when test="${dettaglio.tipoInformazione == 1}">
							<b><spring:message code="common_texts.enterprise.uppercase" text="IMPRESA"/></b> ${utils.truncateString(dettaglio.impresaTranslation.descImpresa,100)}
						</c:when>
						<c:otherwise>
							<b><spring:message code="common_texts.stakeholder.uppercase" text="STAKEHOLDER"/></b> ${utils.truncateString(dettaglio.impresaTranslation.descImpresa,100)}
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
		   action="/vimp/secure/salvaImpresa" role="form"
		   modelAttribute="dettaglio" class="safe-reload">


	<form:hidden id="idPlfImpresa" path="idPlfImpresa" />
	<form:hidden id="impresaTranslation.idPlfImpresa" path="impresaTranslation.idPlfImpresa"/>
	<form:hidden id="descImpresa" path="impresaTranslation.descImpresa" />
	<form:hidden id="tipoInformazione" path="tipoInformazione" />
	<form:hidden id="plfTTipoImpresa.id" path="plfTTipoImpresa.id" />
	<input id="imageData" name="imageData" type="hidden">
	<form:hidden id="descFonte" path="impresaTranslation.descFonte" />

	<!-- CAMPI MANCANTI -->
	
	<form:hidden id="incubatoreCertificato" path="incubatoreCertificato" />
	
	<form:hidden id="primoRequisitoPmi" path="primoRequisitoPmi" />
	<form:hidden id="secondoRequisitoPmi" path="secondoRequisitoPmi" />
	<form:hidden id="terzoRequisitoPmi" path="terzoRequisitoPmi" />

	<form:hidden id="dataAccreditamento" path="dataAccreditamento" />
	<form:hidden id="dataCancellazione" path="dataCancellazione" />
	<form:hidden id="plfTStatoImpresa.id" path="plfTStatoImpresa.id" />
	
	
	<!-- CAMPI INDIRIZZO TOPONOMASTICA -->
	<form:hidden id="sezioneCensimento2011" path="sezioneCensimento2011" />
	<form:hidden id="sezioneElettorale" path="sezioneElettorale" />
	<form:hidden id="codiceMunicipio" path="codiceMunicipio" />
	<form:hidden id="nomeMunicipio" path="nomeMunicipio" />
	<form:hidden id="codiceCircoscrizione" path="codiceCircoscrizione" />
	<form:hidden id="nomeCircoscrizione" path="nomeCircoscrizione" />
	<form:hidden id="codiceUnitaUrbanistica" path="codiceUnitaUrbanistica" />
	<form:hidden id="nomeUnitaUrbanistica" path="nomeUnitaUrbanistica" />
	
	
	
	<c:if test="${!utente.isBackoffice()}">
		<form:hidden id="codiceFiscaleLegaleRappresentante" path="codiceFiscaleLegaleRappresentante" />
		<form:hidden id="cognomeLegaleRappresentante" path="cognomeLegaleRappresentante" />
		<form:hidden id="nomeLegaleRappresentante" path="nomeLegaleRappresentante" />
	</c:if>



	<div class="content-area user-profiel">
		&nbsp;
		<div class="container">
		
		
			<c:if test="${dettaglio.dataCancellazione != null}">
				<div class="col-sm-12 label-scaduto-container">
					<div class="col-sm-10">
						&nbsp;
					</div>
					<div class="col-sm-2">
						<label class="label-scaduto"><spring:message code="deleted" text="Cancellata"/></label>
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
												<c:choose>
													<c:when test="${dettaglio.tipoInformazione == 1}">
														<img
																src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_imprese.jpg"
																class="picture-src" id="wizardPicturePreview" title="" />
													</c:when>
													<c:otherwise>
														<img
																src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_stakeholder.jpg"
																class="picture-src" id="wizardPicturePreview" title="" />
													</c:otherwise>
												</c:choose>
												<c:if test="${modifica}">
													<input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
												</c:if>
											</c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group" ${!modifica && empty dettaglio.partitaIva ? 'hidden' : ''}>
									<label><spring:message code="form.richiesta.accreditamento.iva" text="Partita IVA"/> : <c:if test="${modifica}">
										<small>(<spring:message code="form.dettaglio.impresa.required.piva"/>)</small>
									</c:if>
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<input id="partitaIva" name="partitaIva" type="text" class="form-control"
												   placeholder="" value="${dettaglio.partitaIva}"
												   path="partitaIva" maxlength="11">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.partitaIva}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="form-group" ${!modifica && empty dettaglio.partitaIva ? 'hidden' : ''}>
									<label><spring:message code="form.richiesta.accreditamento.codice_fiscale" text="Codice fiscale"/> : <c:if test="${modifica}">
										<small>(<spring:message code="form.dettaglio.impresa.required.codfisc"/>)</small>
									</c:if>
									</label>


									<c:choose>
										<c:when test="${modifica}">
											<c:choose>
												<c:when test="${dettaglio.tipoInformazione == 1}">
													<form:hidden id="codFiscale" path="codFiscale" />
													<br><label><b>${dettaglio.codFiscale}</b></label>
												</c:when>
												<c:otherwise>
													<input id="codFiscale" name="codFiscale" type="text" class="form-control"
														   placeholder="" value="${dettaglio.codFiscale}"
														   path="codFiscale" maxlength="16">
												</c:otherwise>
											</c:choose>

										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.codFiscale}</b></label>
										</c:otherwise>
									</c:choose>
								</div>

								<div class="form-group" ${!modifica && empty dettaglio.impresaTranslation.descImpresa ? 'hidden' : ''}>
									<label><spring:message code="batch.log.rag_soc" text="Ragione sociale"/> : <c:if test="${modifica}">
										<small>(<spring:message code="required" text="richiesto"/>)</small>
									</c:if>
									</label>
									<br><label><b>${dettaglio.impresaTranslation.descImpresa}</b></label>
								</div>
							</div>
						</div>
						<br>
					</div>

					<div class="col-sm-12">
					
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTStatoImpresa.id ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.richiesta.accreditamento.enterprise_status" text="Stato impresa"/> :</label>
								<br>
								<label> 
									<b>${dettaglio.plfTStatoImpresa.descrizione}</b>
								</label>
							</div>
						</div>
					
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTNaturaGiuridica.descrizione ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="common_texts.legal_nature" text="Natura giuridica"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTNaturaGiuridica.id" path="plfTNaturaGiuridica.id" />
												<br><label><b>${dettaglio.plfTNaturaGiuridica.descrizione}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectNaturaGiuridica"
															 title="${selectTheLegalNatureOfTheCompanyTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTNaturaGiuridica.id"
															 cssClass="selectpicker">
													<form:options items="${naturaGiuridicaList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTNaturaGiuridica.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					
					
		
					<c:if test="${dettaglio.tipoInformazione == 2}">
					
						<c:choose>
							<c:when test="${modifica}">
								<div class="col-md-12">
									<div class="col-sm-6 col-md-6">
										<div class="checkbox">
											<label class="center homecheckbox">
												<input class="form-control" id="checkboxIncubatore" name="checkboxIncubatore" type="checkbox" /> 
												<strong style="font-weight: 100;"> <spring:message code="stakeholder.incubatori.stakeholder" text="Incubatore certificato"/></strong>
											</label>
										</div>
									</div>
								</div>
								<div class="col-md-12">&nbsp;</div>
							</c:when>
							<c:otherwise>
								<c:if test="${dettaglio.incubatoreCertificato == 1}">
									<div class="col-md-12">
										<div class="col-sm-6 col-md-6">
											<div class="checkbox">
												<label class="center homecheckbox">
													<input disabled class="form-control" id="checkboxIncubatore" name="checkboxIncubatore" type="checkbox" /> 
													<strong style="font-weight: 100;"> <spring:message code="stakeholder.incubatori.stakeholder" text="Incubatore certificato"/></strong>
												</label>
											</div>
										</div>
									</div>
									<div class="col-md-12">&nbsp;</div>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:if>
					
					

					<div class="col-sm-12" ${!modifica && empty dettaglio.impresaTranslation.descMissione ? 'hidden' : ''}>
						<div class="col-sm-12">
							<div class="form-group">
								<label><spring:message code="common_texts.mission" text="Missione"/>  : <c:if test="${modifica}">
									<small>(<spring:message code="required"/>)</small>
								</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<textarea name="impresaTranslation.descMissione" id="descMissione" required
												  class="form-control" maxlength="4000">${dettaglio.impresaTranslation.descMissione}</textarea>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.impresaTranslation.descMissione}</b></label>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>


						<%-- <div class="col-sm-12"> --%>
					<div class="col-sm-6" hidden="true">
						<div class="form-group">
							<label><spring:message code="year" text="Anno"/> :</label>
							<c:choose>
								<c:when test="${modifica}">
									<input name="numAnno" type="number"
										   class="form-control" placeholder=""
										   value="${dettaglio.numAnno}"
										   path="numAnno" maxlength="4">
								</c:when>
								<c:otherwise>
									<br>
									<label><b>${dettaglio.numAnno}</b></label>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-sm-6" hidden="true">
						<div class="form-group">
							<label><spring:message code="form.dettaglio.impresa.effective_start"/> :</label>
							<c:choose>
								<c:when test="${modifica}">
									<fmt:formatDate value="${dettaglio.dataInizioEffettivaAttivita}"
													type="date"
													pattern="dd/MM/yyyy"
													var="dataInizioEffettivaAttivitaFormatted" />
									<div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
										<input  name="dataInizioEffettivaAttivita" type="text" class="form-control" value="${dataInizioEffettivaAttivitaFormatted}" path="dataInizioEffettivaAttivita" autocomplete="off">
										<div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
									</div>
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${dettaglio.dataInizioEffettivaAttivita}"
													type="date"
													pattern="dd/MM/yyyy"
													var="dataInizioEffettivaAttivitaFormatted" />
									<br><label><b>${dataInizioEffettivaAttivitaFormatted}</b></label>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
						<%-- </div> --%>

					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.descTelefono ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="telephone" text="Telefono"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input name="descTelefono" type="text"
											   class="form-control" placeholder=""
											   value="${dettaglio.descTelefono}"
											   path="descTelefono" maxlength="100">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.descTelefono}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.descSito ? 'hidden' : ''}>
							<div class="form-group">
								
								<c:choose>
									<c:when test="${modifica}">
										<label><spring:message code="site_edit" text="Sito"/> :</label>
										<input name="descSito" type="text"
											   class="form-control" placeholder=""
											   value="${dettaglio.descSito}"
											   path="descSito" maxlength="1000">
										<!--
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="descSito" path="descSito" />
												<br>
												
												<c:choose>
													<c:when test="${utils.checkLinkUrl(dettaglio.descSito)}">
														<a target="_blank" href="${dettaglio.descSito}">${dettaglio.descSito}</a>
													</c:when>
													<c:otherwise>
														<label><b>${dettaglio.descSito}</b></label>
													</c:otherwise>
												</c:choose>
												
											</c:when>
											<c:otherwise>
												<input name="descSito" type="text"
													   class="form-control" placeholder=""
													   value="${dettaglio.descSito}"
													   path="descSito" maxlength="1000">
											</c:otherwise>
										</c:choose>
										 -->
									</c:when>
									<c:otherwise>
										<label><spring:message code="site" text="Sito"/> :</label>
										<br>
										
										<c:choose>
											<c:when test="${utils.checkLinkUrl(dettaglio.descSito)}">
												<a target="_blank" href="${utils.anchor(dettaglio.descSito)}">${dettaglio.descSito}</a>
											</c:when>
											<c:otherwise>
												<label><b>${dettaglio.descSito}</b></label>
											</c:otherwise>
										</c:choose>
												
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>



					<c:if test="${dettaglio.tipoInformazione == 1}">
						<div class="col-sm-12">
							<div class="col-sm-6" ${!modifica && empty dettaglio.dataAccreditamento ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.accreditation_date" text="Data accreditamento"/> :</label>
									<fmt:formatDate value="${dettaglio.dataAccreditamento}"
													type="date"
													pattern="dd/MM/yyyy"
													var="dataAccreditamentoFormatted" />
									<br><label><b>${dataAccreditamentoFormatted}</b></label>
								</div>
							</div>
								<%--<div class="col-sm-6">&nbsp;</div> --%>
						</div>
					</c:if>

					<c:if test="${dettaglio.tipoInformazione == 1}">
						<div class="col-sm-12">
							<div class="col-sm-6" ${!modifica && empty dettaglio.nomeLegaleRappresentante ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.solicitor_name" text="Nome legale rappresentante"/> :
											<%--<c:if
                                                test="${modifica}">
                                                <small>(<spring:message code="required" text="richiesto"/>)</small>
                                            </c:if>--%>
									</label>
									<c:choose>
										<c:when test="${modifica && utente.isBackoffice()}">
											<input name="nomeLegaleRappresentante" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.nomeLegaleRappresentante}"
												   path="nomeLegaleRappresentante" maxlength="100">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.nomeLegaleRappresentante}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-sm-6" ${!modifica && empty dettaglio.cognomeLegaleRappresentante ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.solicitor_surname" text="Cognome legale rappresentante"/> :
											<%--<c:if
                                                test="${modifica}">
                                                <small>(<spring:message code="required" text="richiesto"/>)</small>
                                            </c:if>--%>
									</label>
									<c:choose>
										<c:when test="${modifica && utente.isBackoffice()}">
											<input name="cognomeLegaleRappresentante" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.cognomeLegaleRappresentante}"
												   path="cognomeLegaleRappresentante" maxlength="100">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.cognomeLegaleRappresentante}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>


						<c:if test="${modifica}">
							<div class="col-sm-12">
								<div class="col-sm-6" ${!modifica && empty dettaglio.codiceFiscaleLegaleRappresentante ? 'hidden' : ''}>
									<div class="form-group">
										<label><spring:message code="form.dettaglio.impresa.solicitor_cf" text="Codice fiscale legale rappresentante"/> :
												<%--<c:if
	                                                test="${modifica}">
	                                                <small>(<spring:message code="required" text="richiesto"/>)</small>
	                                            </c:if>--%>
										</label>
										<c:choose>
											<c:when test="${modifica && utente.isBackoffice()}">
												<input name="codiceFiscaleLegaleRappresentante" type="text"
													   class="form-control" placeholder=""
													   value="${dettaglio.codiceFiscaleLegaleRappresentante}"
													   path="codiceFiscaleLegaleRappresentante" maxlength="16">
											</c:when>
											<c:otherwise>
												<br>
												<label><b>${dettaglio.codiceFiscaleLegaleRappresentante}</b></label>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="col-sm-6">&nbsp;</div>
							</div>
						</c:if>
						
					</c:if>


					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.impresaTranslation.descBreveImpresa ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.enterprise_description" text="Descrizione dell'impresa"/> :<c:if
										test="${modifica}">
									<small>(<spring:message code="required" text="richiesto"/>)</small>
								</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<textarea name="impresaTranslation.descBreveImpresa" id="descBreveImpresa"
												  class="form-control" maxlength="4000">${dettaglio.impresaTranslation.descBreveImpresa}</textarea>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.impresaTranslation.descBreveImpresa}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.impresaTranslation.descAttivita ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.activity_description"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<textarea name="impresaTranslation.descAttivita" id="descAttivita"
												  class="form-control" maxlength="4000">${dettaglio.impresaTranslation.descAttivita}</textarea>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.impresaTranslation.descAttivita}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>

					<div class="col-sm-12">
						<c:if test="${dettaglio.tipoInformazione == 1}">
							<div class="col-sm-6" hidden="true">
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.organization_type" text="Tipologia dell'organizzazione"/> :</label>
									<br><label><b>${dettaglio.plfTTipoImpresa.descrizione}</b></label>
								</div>
							</div>
						</c:if>
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTSettoreImpresa.descrizione ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.main_sector" text="Settore principale dell'impresa"/> :</label>
								<a tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="Info" data-content="<spring:message code="help.settore" text="Help settore"/>"><span class="pe-7s-info" style="font-size: 32px;"></span></a>
								<c:choose>
									<c:when test="${modifica}">
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTSettoreImpresa.id" path="plfTSettoreImpresa.id" />
												<br><label><b>${dettaglio.plfTSettoreImpresa.descrizione}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectSettoreImpresa"
															 title="${selectTheMainSectorOfTheCompanyTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTSettoreImpresa.id"
															 cssClass="selectpicker">
													<form:options items="${settoreImpresaList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTSettoreImpresa.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>


					<div class="col-sm-12">
						<c:if test="${dettaglio.tipoInformazione == 1}">
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTOrigineImpresa.descrizione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.enterprise_origins" text="Origine impresa"/> :
									</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectOrigineImpresa"
														 title="${selectTheOriginOfTheCompanyTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTOrigineImpresa.id"
														 cssClass="selectpicker">
												<form:options items="${origineImpresaList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTOrigineImpresa.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:if>
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTAteco.descrizione ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.ateco" text="Ateco"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTAteco.idAteco" path="plfTAteco.idAteco" />
												<br><label><b>${dettaglio.plfTAteco.descrizione}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectAteco" title="${selectAtecoTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTAteco.idAteco" cssClass="selectpicker">
													<form:options items="${atecoList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTAteco.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>



					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTClasseAddetti.descrizione ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.class_employees_last_year" text="Classe addetti ultimo anno"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="selectClasseAddetti"
													 title="${selectTheClassOfEmployeesLastYearTitleAttr}"
													 data-live-search="true" data-live-search-style="contains"
													 path="plfTClasseAddetti.id"
													 cssClass="selectpicker">
											<form:options items="${classeAddettiList}" />
										</form:select>
										
										<!-- 
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTClasseAddetti.id" path="plfTClasseAddetti.id" />
												<br><label><b>${dettaglio.plfTClasseAddetti.descrizione}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectClasseAddetti"
															 title="${selectTheClassOfEmployeesLastYearTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTClasseAddetti.id"
															 cssClass="selectpicker">
													<form:options items="${classeAddettiList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
										 -->
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTClasseAddetti.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTClasseCapitale.descrizione ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.class_of_capital" text="Classe di capitale"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="selectClasseCapitale"
													 title="${selectTheCapitalClassTitleAttr}"
													 data-live-search="true" data-live-search-style="contains"
													 path="plfTClasseCapitale.id"
													 cssClass="selectpicker">
											<form:options items="${classeCapitaleList}" />
										</form:select>
										<!-- 
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTClasseCapitale.id" path="plfTClasseCapitale.id" />
												<br><label><b>${dettaglio.plfTClasseCapitale.descrizione}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectClasseCapitale"
															 title="${selectTheCapitalClassTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTClasseCapitale.id"
															 cssClass="selectpicker">
													<form:options items="${classeCapitaleList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
										-->
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTClasseCapitale.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTClasseProduzione.descrizione ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.class_of_production" text="Classe di produzione ultimo anno"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="selectTipoImpresa"
													 title="${selectTheProductionClassLastYearTitleAttr}"
													 data-live-search="true" data-live-search-style="contains"
													 path="plfTClasseProduzione.id"
													 cssClass="selectpicker">
											<form:options items="${classeProduzioneList}" />
										</form:select>
										<!-- 
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTClasseProduzione.id" path="plfTClasseProduzione.id" />
												<br><label><b>${dettaglio.plfTClasseProduzione.descrizione}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectTipoImpresa"
															 title="${selectTheProductionClassLastYearTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTClasseProduzione.id"
															 cssClass="selectpicker">
													<form:options items="${classeProduzioneList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
										-->
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTClasseProduzione.descrizione}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
							<%--<div class="col-sm-6">&nbsp;</div>--%>
					</div>

					<c:if test="${dettaglio.tipoInformazione == 1}">
						<%-- <div class="col-sm-12"> --%>
						<div class="col-sm-6" hidden="true">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.subordinate_employee_number" text="Numero addetti subordinati"/> : </label>
								<c:choose>
									<c:when test="${modifica}">
										<input name="numAddettiSubordinati"
											   type="number" class="form-control" placeholder=""
											   value="${dettaglio.numAddettiSubordinati}" path="numAddettiSubordinati" maxlength="18">
									</c:when>
									<c:otherwise>
										<br><label><b>${dettaglio.numAddettiSubordinati}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" hidden="true">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.last_turnover_value" text="Ultimo valore del fatturato"/> : </label>
								<c:choose>
									<c:when test="${modifica}">

										<input name="numUltimoFatturato" id="numUltimoFatturato"
											   type="text" class="form-control" placeholder="" path="numUltimoFatturato"
											   value="<fmt:formatNumber value="${dettaglio.numUltimoFatturato}" pattern="###,###,###,###,##0.00" />" maxlength="18">

									</c:when>
									<c:otherwise>
										<br><label><b><fmt:formatNumber value="${dettaglio.numUltimoFatturato}"
																		pattern="###,###,###,###,##0.00" /> &euro;</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<%-- </div> --%>
					</c:if>



					<div class="col-sm-12">
						<div class="col-sm-6" hidden="true">
							<div class="form-group">

								<c:if test="${dettaglio.tipoInformazione != 2}">
									<label><spring:message code="form.dettaglio.impresa.changeover_date_PMI_CCIAA" text="Data passaggio PMI CCIAA"/> :</label>
									<fmt:formatDate value="${dettaglio.dataPassaggioPmiCciaa}"
													type="date"
													pattern="dd/MM/yyyy"
													var="dataPassaggioPmiCciaaFormatted" />
									<br><label><b>${dataPassaggioPmiCciaaFormatted}</b></label>
								</c:if>
									<%--
                                    <c:choose>
                                        <c:when test="${modifica}">
                                            <fmt:formatDate value="${dettaglio.dataPassaggioPmiCciaa}"
                                                 type="date"
                                                pattern="dd/MM/yyyy"
                                                var="dataPassaggioPmiCciaaFormatted" />
                                            <div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                <input  name="dataPassaggioPmiCciaa" type="text" class="form-control" value="${dataPassaggioPmiCciaaFormatted}" path="dataPassaggioPmiCciaa">
                                                <div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate value="${dettaglio.dataPassaggioPmiCciaa}"
                                                 type="date"
                                                pattern="dd/MM/yyyy"
                                                var="dataPassaggioPmiCciaaFormatted" />
                                            <br><label><b>${dataPassaggioPmiCciaaFormatted}</b></label>
                                        </c:otherwise>
                                    </c:choose>
                                     --%>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.dataIscrizioneRegistroImprese ? 'hidden' : ''}>
							<c:choose>
								<c:when test="${dettaglio.tipoInformazione == 1}">
									<div class="form-group">
										<label><spring:message code="form.dettaglio.impresa.company_register_registration_date" text="Data iscrizione registro imprese"/> :</label>
										<c:choose>
											<c:when test="${modifica}">
												<fmt:formatDate value="${dettaglio.dataIscrizioneRegistroImprese}"
																type="date"
																pattern="dd/MM/yyyy"
																var="dataIscrizioneRegistroImpreseFormatted" />
												<div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
													<input  name="dataIscrizioneRegistroImprese" type="text" class="form-control" value="${dataIscrizioneRegistroImpreseFormatted}" path="dataIscrizioneRegistroImprese" autocomplete="off">
													<div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
												</div>
											</c:when>
											<c:otherwise>
												<fmt:formatDate value="${dettaglio.dataIscrizioneRegistroImprese}"
																type="date"
																pattern="dd/MM/yyyy"
																var="dataIscrizioneRegistroImpreseFormatted" />
												<br><label><b>${dataIscrizioneRegistroImpreseFormatted}</b></label>
											</c:otherwise>
										</c:choose>
									</div>
								</c:when>
								<c:otherwise>
									&nbsp;
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<c:if test="${dettaglio.tipoInformazione == 1}">
						<div class="col-sm-12">
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTSezioneSpecialeImpresa.descrizione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.special_business_section" text="Sezione speciale impresa"/> :</label>
									<br><label><b>${dettaglio.plfTSezioneSpecialeImpresa.descrizione}</b></label>
								</div>
							</div>
							<div class="col-sm-6" ${!modifica && empty dettaglio.dataIscrizioneSezioneSpeciale ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.special_section_registration_date" text="Data iscrizione sezione speciale"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<fmt:formatDate value="${dettaglio.dataIscrizioneSezioneSpeciale}"
															type="date"
															pattern="dd/MM/yyyy"
															var="dataIscrizioneSezioneSpecialeFormatted" />
											<div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
												<input  name="dataIscrizioneSezioneSpeciale" type="text" class="form-control" value="${dataIscrizioneSezioneSpecialeFormatted}" path="dataIscrizioneSezioneSpeciale" autocomplete="off">
												<div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
											</div>
										</c:when>
										<c:otherwise>
											<fmt:formatDate value="${dettaglio.dataIscrizioneSezioneSpeciale}"
															type="date"
															pattern="dd/MM/yyyy"
															var="dataIscrizioneSezioneSpecialeFormatted" />
											<br><label><b>${dataIscrizioneSezioneSpecialeFormatted}</b></label><br>
											<a class="wow fadeInUp animated" target="_blank" href="http://startup.registroimprese.it/" data-wow-delay="0.5s">
												<spring:message code="form.dettaglio.impresa.special_section_registration_link" text="Sezione speciale registro"/>
											</a>
										</c:otherwise>
									</c:choose>

								</div>
							</div>
						</div>


						<%--<div class="col-sm-12"> --%>
						<div class="col-sm-6" hidden="true">
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.special_register_chamber_of_commerce_link" text="Link registro speciale camera di commercio"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input name="linkRegistroSpecialeCameraCommercio" type="text" class="form-control"
											   placeholder="" value="${dettaglio.linkRegistroSpecialeCameraCommercio}" path="linkRegistroSpecialeCameraCommercio"
											   maxlength="1000">
									</c:when>
									<c:otherwise>
										<c:if test="${!empty dettaglio.linkRegistroSpecialeCameraCommercio}">
											<br>
											<a class="wow fadeInUp animated"
											   href="http://${dettaglio.linkRegistroSpecialeCameraCommercio}" data-wow-delay="0.5s">${dettaglio.linkRegistroSpecialeCameraCommercio}</a>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<%--<div class="col-sm-6">&nbsp;</div>--%>
						<%--</div>--%>
					</c:if>

					<c:if test="${dettaglio.tipoInformazione == 1}">
						<div class="col-sm-12" ${!modifica && empty dettaglio.impresaTranslation.brevetto ? 'hidden' : ''}>
							<div class="col-sm-12">
								<div class="form-group">
									<label><spring:message code="common_texts.patent" text="Brevetto"/> : </label>
									<c:choose>
										<c:when test="${modifica}">
											<textarea name="impresaTranslation.brevetto" id="brevetto"
													  class="form-control" maxlength="3950">${dettaglio.impresaTranslation.brevetto}</textarea>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.impresaTranslation.brevetto}</b></label>
										</c:otherwise>
									</c:choose>

								</div>
							</div>
						</div>
					</c:if>

					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && dettaglio.plfTInnovaziones.size() == 0 && empty dettaglio.impresaTranslation.elementiInnovazioneAltro ? 'hidden':''}>
							<div class="form-group">
								<c:if test="${modifica || dettaglio.plfTInnovaziones.size()>0 || !empty dettaglio.impresaTranslation.elementiInnovazioneAltro}">
									<div class="col-md-12 register-blocks">
										<h2><spring:message code="form.dettaglio.impresa.innovation_elements" text="Elementi di innovazione"/> :</h2>
										<a tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="Info" data-content="<spring:message code="help.mercati.innovazione" text="Help innovazione"/>"><span class="pe-7s-info" style="font-size: 32px;"></span></a>
									</div>
								</c:if>
								<c:if test="${modifica}">
									<form:select id="selectElementiInnovazione"
												 title="${selectTheInnovationElementsTitleAttr}"
												 data-live-search="true" data-live-search-style="contains"
												 cssClass="selectpicker" path="">
										<form:options items="${elementiInnovazioneList}" />
									</form:select>
								</c:if>
							</div>
							<div class="form-group">
								<div class="pull-right">
									<c:if test="${modifica}">
										<a id="collegaElementiInnovazioneButton"
										   class='btn btn-finish btn-primary'
										   onClick="javascript:collegaElementiInnovazione();"><spring:message code="form.dettaglio.impresa.connect" text="Collega"/></a>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<br /> <br /> <br />
								<div class="table-responsive">
									<table id="tabellaElementiInnovazione"
										   class="table table-condensed bordered1 tablesorter">
										<thead>
										<tr>
											<c:if test="${modifica}">
												<th></th>
											</c:if>
											<th data-toggle="tooltip" data-placement="top"
												title="${innovationElementsTitleAttr}"><spring:message code="form.dettaglio.impresa.innovation_elements" text="Elementi di innovazione"/></th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${dettaglio.plfTInnovaziones}"
												   var="plfTInnovaziones">
											<tr
													id='rigaElementiInnovazione${plfTInnovaziones.id}'>
												<c:if test="${modifica}">
													<td><a
															href="javascript:cancellaElementiInnovazioneShowModal(${plfTInnovaziones.id});">
														<span class="glyphicon glyphicon-trash"></span>
													</a></td>
												</c:if>
												<td>${plfTInnovaziones.descrizione}</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<div class="col-sm-6" ${!modifica && dettaglio.plfTMercatis.size() == 0 && empty dettaglio.impresaTranslation.mercatiAltro ? 'hidden':''}>
							<div class="form-group">
								<c:if test="${modifica || dettaglio.plfTMercatis.size() > 0 || !empty dettaglio.impresaTranslation.mercatiAltro}">
									<div class="col-md-12 register-blocks">
										<h2><spring:message code="form.dettaglio.impresa.reference_business" text="Mercati di riferimento"/> :<c:if test="${modifica}">
											<small>(<spring:message code="required" text="richiesto"/>)</small>
										</c:if></h2>
										<a tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="Info" data-content="<spring:message code="help.mercati.riferimento" text="Help mercati"/>"><span class="pe-7s-info" style="font-size: 32px;"></span></a>
									</div>
								</c:if>
								<c:if test="${modifica}">
									<form:select id="selectMercatiRiferimento"
												 title="${selectTheReferenceMarketsTitleAttr}"
												 data-live-search="true" data-live-search-style="contains"
												 cssClass="selectpicker" path="">
										<form:options items="${mercatiRiferimentoList}" />
									</form:select>
								</c:if>
							</div>
							<div class="form-group">
								<div class="pull-right">
									<c:if test="${modifica}">
										<a id="collegaMercatiRiferimentoButton"
										   class='btn btn-finish btn-primary'
										   onClick="javascript:collegaMercatiRiferimento();"><spring:message code="form.dettaglio.impresa.connect" text="Collega"/></a>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<br /> <br /> <br />
								<div class="table-responsive">
									<table id="tabellaMercatiRiferimento"
										   class="table table-condensed bordered1 tablesorter">
										<thead>
										<tr>
											<c:if test="${modifica}">
												<th></th>
											</c:if>
											<th data-toggle="tooltip" data-placement="top"
												title="${referenceBusinessTitleAttr}"><spring:message code="form.dettaglio.impresa.reference_business" text="Mercati di riferimento"/></th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${dettaglio.plfTMercatis}"
												   var="plfTMercatis">
											<tr id='rigaMercatiRiferimento${plfTMercatis.id}'>
												<c:if test="${modifica}">
													<td><a
															href="javascript:cancellaMercatiRiferimentoShowModal(${plfTMercatis.id});">
														<span class="glyphicon glyphicon-trash"></span>
													</a></td>
												</c:if>
												<td>${plfTMercatis.descrizione}</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>

					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && dettaglio.plfTInnovaziones.size() == 0 && empty dettaglio.impresaTranslation.elementiInnovazioneAltro ? 'hidden':''}>
							<div class="form-group">
								<c:if test="${modifica || !empty dettaglio.impresaTranslation.elementiInnovazioneAltro}">
									<label><spring:message code="form.dettaglio.impresa.innovation_elements" text="Elementi di innovazione"/> - <spring:message code="other" text="Altro"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<input id="elementiInnovazioneAltro" name="impresaTranslation.elementiInnovazioneAltro" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.impresaTranslation.elementiInnovazioneAltro}"
												   path="elementiInnovazioneAltro" maxlength="400">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.impresaTranslation.elementiInnovazioneAltro}</b></label>
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && dettaglio.plfTMercatis.size() == 0 && empty dettaglio.impresaTranslation.mercatiAltro ? 'hidden':''}>
							<div class="form-group">
								<c:if test="${modifica || !empty dettaglio.impresaTranslation.mercatiAltro}">
									<label><spring:message code="form.dettaglio.impresa.reference_business" text="Mercati di riferimento"/> - <spring:message code="other" text="Altro"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<input id="mercatiAltro" name="impresaTranslation.mercatiAltro" type="text" class="form-control"
												   placeholder="" value="${dettaglio.impresaTranslation.mercatiAltro}"
												   path="mercatiAltro" maxlength="400">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.impresaTranslation.mercatiAltro}</b></label>
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
						</div>
					</div>

					<div class="col-sm-12"${!modifica && empty dettaglio.nomeContatto && empty dettaglio.impresaTranslation.ruoloContattoAltro
							&& empty dettaglio.emailContatto && empty dettaglio.plfTRuoloAziendale.descrizione && empty dettaglio.cognomeContatto ? 'hidden':''}>&nbsp;</div>
					<div class="col-md-12 col-xs-12 register-blocks" style="text-align: center;"
						${!modifica && empty dettaglio.nomeContatto && empty dettaglio.impresaTranslation.ruoloContattoAltro
								&& empty dettaglio.emailContatto && empty dettaglio.plfTRuoloAziendale.descrizione && empty dettaglio.cognomeContatto ? 'hidden':''}>
						<h2><spring:message code="form.dettaglio.impresa.reference_contact" text="Contatto di riferimento"/></h2>
					</div>
					<div class="col-sm-12" ${!modifica && empty dettaglio.nomeContatto && empty dettaglio.impresaTranslation.ruoloContattoAltro
							&& empty dettaglio.emailContatto && empty dettaglio.plfTRuoloAziendale.descrizione && empty dettaglio.cognomeContatto ? 'hidden':''}>&nbsp;</div>

					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.nomeContatto ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="name" text="Nome"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input name="nomeContatto" type="text" class="form-control"
											   placeholder="" value="${dettaglio.nomeContatto}"
											   path="nomeContatto" maxlength="100">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.nomeContatto}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.cognomeContatto ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="surname" text="Cognome"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input name="cognomeContatto" type="text" class="form-control"
											   placeholder="" value="${dettaglio.cognomeContatto}"
											   path="cognomeContatto" maxlength="100">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.cognomeContatto}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>


					<div class="col-sm-12">
						<c:if test="${dettaglio.tipoInformazione == 1}">
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTRuoloAziendale.descrizione ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.role_in_the_company" text="Ruolo in azienda"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectRuoloAziendale"
														 title="${selectTheRoleOfTheContactInTheCompanyTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTRuoloAziendale.id"
														 cssClass="selectpicker">
												<form:options items="${ruoloAziendaleList}" />
											</form:select>
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTRuoloAziendale.descrizione}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:if>
						<div class="col-sm-6" ${!modifica && empty dettaglio.emailContatto ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="mail" text="Email"/> :<c:if test="${modifica}">
									<small>(<spring:message code="required" text="richiesto"/>)</small>
								</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<input name="emailContatto" type="text" class="form-control"
											   placeholder="" value="${dettaglio.emailContatto}"
											   path="emailContatto" maxlength="200">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.emailContatto}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>


					<c:if test="${dettaglio.tipoInformazione == 1}">
						<div class="col-sm-12" ${!modifica && empty dettaglio.impresaTranslation.ruoloContattoAltro ? 'hidden' : ''}>
							<div class="col-sm-6">
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.role_in_the_company" text="Ruolo in azienda"/> - <spring:message code="other" text="Altro"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<input name="impresaTranslation.ruoloContattoAltro" type="text"
												   class="form-control" placeholder=""
												   value="${dettaglio.impresaTranslation.ruoloContattoAltro}"
												   path="ruoloContattoAltro" maxlength="400">
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.impresaTranslation.ruoloContattoAltro}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-sm-6">&nbsp;</div>
						</div>
					</c:if>

					
					
					
					
						<div class="col-sm-12">&nbsp;</div>
						<div class="col-md-12 col-xs-12 register-blocks" style="text-align: center;">
							<h2><spring:message code="form.dettaglio.impresa.prevalence" text="Prevalenze"/></h2>
						</div>
						<div class="col-sm-12">&nbsp;</div>
	
						<div class="col-sm-12">
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTPrevalenzaFemminile.codice ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.female_prevalence" text="Prevalenza femminile"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectPrevalenzaFemminile"
														 title="${selectTheTypeOfFemalePrevalenceTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTPrevalenzaFemminile.id"
														 cssClass="selectpicker">
												<form:options items="${prevalenzaList}" />
											</form:select>
											<!-- 
											<c:choose>
												<c:when test="${dettaglio.tipoInformazione == 1}">
													<form:hidden id="plfTPrevalenzaFemminile.id" path="plfTPrevalenzaFemminile.id" />
													<br><label><b>${dettaglio.plfTPrevalenzaFemminile.codice}</b></label>
												</c:when>
												<c:otherwise>
													<form:select id="selectPrevalenzaFemminile"
																 title="${selectTheTypeOfFemalePrevalenceTitleAttr}"
																 data-live-search="true" data-live-search-style="contains"
																 path="plfTPrevalenzaFemminile.id"
																 cssClass="selectpicker">
														<form:options items="${prevalenzaList}" />
													</form:select>
												</c:otherwise>
											</c:choose>
											 -->
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTPrevalenzaFemminile.codice}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-sm-6" ${!modifica && empty dettaglio.plfTPrevalenzaGiovanile.codice ? 'hidden' : ''}>
								<div class="form-group">
									<label><spring:message code="form.dettaglio.impresa.youthful_prevalence" text="Prevalenza giovanile"/> :</label>
									<c:choose>
										<c:when test="${modifica}">
											<form:select id="selectPrevalenzaGiovanile"
														 title="${selectTheTypeOfJuvenilePrevalenceTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 path="plfTPrevalenzaGiovanile.id"
														 cssClass="selectpicker">
												<form:options items="${prevalenzaList}" />
											</form:select>
											<!-- 
											<c:choose>
												<c:when test="${dettaglio.tipoInformazione == 1}">
													<form:hidden id="plfTPrevalenzaGiovanile.id" path="plfTPrevalenzaGiovanile.id" />
													<br><label><b>${dettaglio.plfTPrevalenzaGiovanile.codice}</b></label>
												</c:when>
												<c:otherwise>
													<form:select id="selectPrevalenzaGiovanile"
																 title="${selectTheTypeOfJuvenilePrevalenceTitleAttr}"
																 data-live-search="true" data-live-search-style="contains"
																 path="plfTPrevalenzaGiovanile.id"
																 cssClass="selectpicker">
														<form:options items="${prevalenzaList}" />
													</form:select>
												</c:otherwise>
											</c:choose>
											 -->
										</c:when>
										<c:otherwise>
											<br>
											<label><b>${dettaglio.plfTPrevalenzaGiovanile.codice}</b></label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
	
						<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTPrevalenzaStraniera.codice ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.impresa.foreign_prevalence" text="Prevalenza straniera"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="selectPrevalenzaStraniera"
													 title="${selectTheTypeOfForeignPrevalenceTitleAttr}"
													 data-live-search="true" data-live-search-style="contains"
													 path="plfTPrevalenzaStraniera.id"
													 cssClass="selectpicker">
											<form:options items="${prevalenzaList}" />
										</form:select>
										<!-- 
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTPrevalenzaStraniera.id" path="plfTPrevalenzaStraniera.id" />
												<br><label><b>${dettaglio.plfTPrevalenzaStraniera.codice}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectPrevalenzaStraniera"
															 title="${selectTheTypeOfForeignPrevalenceTitleAttr}"
															 data-live-search="true" data-live-search-style="contains"
															 path="plfTPrevalenzaStraniera.id"
															 cssClass="selectpicker">
													<form:options items="${prevalenzaList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
										 -->
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTPrevalenzaStraniera.codice}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6">&nbsp;</div>
					</div>
				

					<c:if test="${dettaglio.tipoInformazione != 2}">
						<div class="col-sm-12">&nbsp;</div>
						<div class="col-md-12 col-xs-12 register-blocks" style="text-align: center;">
							<h2><spring:message code="form.dettaglio.impresa.technological_innovation_requirements" text="Requisiti di innovazione tecnologica"/></h2>
						</div>
						<div class="col-md-12">&nbsp;</div>

						<div class="col-md-12">
							<div class="col-sm-4 col-md-4">
								<div class="checkbox">
									<label class="center homecheckbox"> <input disabled
																			   class="form-control" id="checkboxPrimoRequisito"
																			   name="checkboxPrimoRequisito" type="checkbox" /> <strong
											style="font-weight: 100;"> <spring:message code="form.dettaglio.impresa.r_s" text="R&S"/></strong>
									</label>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="checkbox">
									<label class="center homecheckbox"> <input disabled
																			   class="form-control" id="checkboxSecondoRequisito"
																			   name="checkboxSecondoRequisito" type="checkbox" /> <strong
											style="font-weight: 100;"> <spring:message code="form.dettaglio.impresa.qualified_team" text="Team qualificato"/></strong>
									</label>
								</div>
							</div>
							<div class="col-sm-4 col-md-4">
								<div class="checkbox">
									<label class="center homecheckbox"> <input disabled
																			   class="form-control" id="checkboxTerzoRequisito"
																			   name="checkboxTerzoRequisito" type="checkbox" /> <strong
											style="font-weight: 100;"> <spring:message code="form.dettaglio.impresa.intellectual_property" text="Proprietà intellettuale"/></strong>
									</label>
								</div>
							</div>
						</div>
					</c:if>

					<br>

					<c:choose>
						<c:when test="${!modifica && dettaglio.allegati.size()<=0}">
						</c:when>
						<c:otherwise>
							<div class="col-sm-12">&nbsp;</div>
							<div class="col-md-12 register-blocks" style="text-align: center;">
								<h2><spring:message code="attachments" text="Allegati"/></h2>
							</div>
							<div class="col-md-12">&nbsp;</div>


							<div class="col-md-12">
								<div class="col-sm-6">
									<div class="form-group">
										<div class="table-responsive">
											<table id="tabellaAllegati" class="table table-condensed bordered1 tablesorter">
												<thead>
												<tr>
													<c:if test="${modifica}">
														<th></th>
														<th></th>
													</c:if>
													<th data-toggle="tooltip" data-placement="top" title="${nameTitleAttr}"><spring:message code="name" text="Nome"/></th>
													<th data-toggle="tooltip" data-placement="top" title="${descriptionTitleAttr}"><spring:message code="description" text="Descrizione"/></th>
													<th></th>
												</tr>
												</thead>
												<tbody>
												<c:forEach items="${dettaglio.allegati}" var="allegato">
													<tr id='rigaAllegato${allegato.idImpresaAllegati}'>
														<c:if test="${modifica}">
															<td>
																<a href="javascript:cancellaAllegatoShowModal(${allegato.idImpresaAllegati});">
																	<span class="glyphicon glyphicon-trash"></span>
																</a>
															</td>
															<td>
																<a href="javascript:editAllegatoDescription('${allegato.impresaAllegatiTranslation.idImpresaAllegati}');">
																	<span class="glyphicon glyphicon-pencil"></span>
																</a>
															</td>
														</c:if>
														<td>${allegato.nome}</td>
														<td id="descrizioneAllegato_${allegato.impresaAllegatiTranslation.idImpresaAllegati}">${allegato.impresaAllegatiTranslation.descrizione}</td>
														<td>
															<a target="_blank" href="/vimp/impresaAllegato/${allegato.idImpresaAllegati}">
																<span class="glyphicon glyphicon-folder-open"></span>
															</a>
														</td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>

									<c:if test="${modifica}">
										<div class="form-group" id="FG">
											<br>
											<div class="pull-right">
												<a id="allegaFileButton"
												   class='btn btn-finish btn-primary'
												   onClick="javascript:allegaFileDialog();"><spring:message code="attach_file" text="Allega file"/></a>
											</div>
										</div>
									</c:if>
								</div>
							</div>
						</c:otherwise>
					</c:choose>

					<br>&nbsp;

					<div class="col-md-12">&nbsp;</div>
					<div class="col-md-12 register-blocks" style="text-align: center;" hidden="true">
						<h2><spring:message code="common_texts.connections" text="Collegamenti"/></h2>
					</div>
					<div class="col-sm-12">&nbsp;</div>


					<div class="col-sm-12">
						<div class="col-sm-6" ${!utente.isBackoffice() ? 'hidden' : ''}>
							<c:choose>
								<c:when test="${dettaglio.tipoInformazione != 2}">
									<div class="form-group">
										<c:if test="${modifica || dettaglio.stakeholders.size()>0}">
											<div class="col-md-12 register-blocks">
												<h2><spring:message code="common_texts.stakeholder" text="Stakeholder"/> :</h2>
											</div>
										</c:if>
										<c:if test="${modifica}">
											<form:select id="selectStakeholder"
														 title="${selectTheStakeholdersToLinkTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 cssClass="selectpicker" path="">
												<form:options items="${stakeholderList}" />
											</form:select>
										</c:if>
									</div>
									<div class="form-group">
										<div class="pull-right">
											<c:if test="${modifica}">
												<a id="collegaStakeholderButton"
												   class='btn btn-finish btn-primary'
												   onClick="javascript:collegaStakeholder();"><spring:message code="form.dettaglio.impresa.connect" text="Collega"/></a>
											</c:if>
										</div>
									</div>
									<div class="form-group">
										<br /> <br /> <br />
										<div class="table-responsive">
											<table id="tabellaStakeholder"
												   class="table table-condensed bordered1 tablesorter">
												<thead>
												<tr>
													<c:if test="${modifica}">
														<th></th>
													</c:if>
													<th data-toggle="tooltip" data-placement="top"
														title="${stakeholderTitleAttr}"><spring:message code="common_texts.stakeholder" text="Stakeholder"/></th>
													<th></th>
												</tr>
												</thead>
												<tbody>
												<c:forEach items="${dettaglio.stakeholders}"
														   var="stakeholder">
													<tr id='rigaStakeholder${stakeholder.idPlfImpresa}'>
														<c:if test="${modifica}">
															<td><a
																	href="javascript:cancellaStakeholderShowModal(${stakeholder.idPlfImpresa});">
																<span class="glyphicon glyphicon-trash"></span>
															</a></td>
														</c:if>
														<td>${stakeholder.impresaTranslation.descImpresa}</td>
														<td><a
																href="/vimp/stakeholder/${stakeholder.idPlfImpresa}"><span
																class="glyphicon glyphicon-folder-open"></span></a></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="form-group">
										<c:if test="${modifica || dettaglio.impreseCollegate.size()>0}">
											<div class="col-md-12 register-blocks">
												<h2><spring:message code="common_texts.enterprises" text="Imprese"/> :</h2>
											</div>
										</c:if>
										<c:if test="${modifica}">
											<form:select id="selectImpreseCollegate"
														 title="${selectTheCompaniesToLinkTitleAttr}"
														 data-live-search="true" data-live-search-style="contains"
														 cssClass="selectpicker" path="">
												<form:options items="${impreseList}" />
											</form:select>
										</c:if>
									</div>
									<div class="form-group">
										<div class="pull-right">
											<c:if test="${modifica}">
												<a id="collegaImpreseButton"
												   class='btn btn-finish btn-primary'
												   onClick="javascript:collegaImprese();"><spring:message code="form.dettaglio.impresa.connect" text="Collega"/></a>
											</c:if>
										</div>
									</div>
									<div class="form-group">
										<br /> <br /> <br />
										<div class="table-responsive">
											<table id="tabellaImprese"
												   class="table table-condensed bordered1 tablesorter">
												<thead>
												<tr>
													<c:if test="${modifica}">
														<th></th>
													</c:if>
													<th data-toggle="tooltip" data-placement="top"
														title="${enterpriseTitleAttr}"><spring:message code="common_texts.enterprise" text="Impresa"/></th>
													<th></th>
												</tr>
												</thead>
												<tbody>
												<c:forEach items="${dettaglio.impreseCollegate}"
														   var="impresa">
													<tr id='rigaImpresa${impresa.idPlfImpresa}'>
														<c:if test="${modifica}">
															<td><a
																	href="javascript:cancellaImpresaShowModal(${impresa.idPlfImpresa});">
																<span class="glyphicon glyphicon-trash"></span>
															</a></td>
														</c:if>
														<td>${impresa.impresaTranslation.descImpresa}</td>
														<td><a
																href="/vimp/impresa/${impresa.idPlfImpresa}"><span
																class="glyphicon glyphicon-folder-open"></span></a></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
							<%--
                            <c:if test="${dettaglio.tipoInformazione == 2}">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <c:if test="${modifica || dettaglio.serviziStandard.size()>0}">
                                            <div class="col-md-12 col-xs-12 register-blocks">
                                                    <h2><spring:message code="common_texts.services" text="Servizi"/>:</h2>
                                            </div>
                                        </c:if>
                                        <c:if test="${modifica}">
                                            <form:select id="selectServiziStandard"
                                                title="${selectTheServicesToLinkTitleAttr}"
                                                data-live-search="true" data-live-search-style="contains"
                                                cssClass="selectpicker" path="">
                                                <form:options items="${serviziStandardList}" />
                                            </form:select>
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <div class="pull-right">
                                            <c:if test="${modifica}">
                                                <a id="collegaServiziStandardButton"
                                                    class='btn btn-finish btn-primary'
                                                    onClick="javascript:collegaServiziStandard();"><spring:message code="form.dettaglio.impresa.connect" text="Collega"/></a>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <br /> <br /> <br />
                                        <div class="table-responsive">
                                            <table id="tabellaServiziStandard"
                                                class="table table-condensed bordered1 tablesorter">
                                                <thead>
                                                    <tr>
                                                        <c:if test="${modifica}">
                                                            <th></th>
                                                        </c:if>
                                                        <th data-toggle="tooltip" data-placement="top"
                                                            title="${serviceTitleAttr}"><spring:message code="common_texts.service" text="Servizio"/></th>
                                                        <th data-toggle="tooltip" data-placement="top" title="${newsTitleAttr}"><spring:message code="link" text="Link"/></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${dettaglio.serviziStandard}"
                                                        var="serviziStandard">
                                                        <tr
                                                            id='rigaServiziStandard${serviziStandard.idServizi}'>
                                                            <c:if test="${modifica}">
                                                                <td><a
                                                                    href="javascript:cancellaServiziStandardShowModal(${serviziStandard.idServizi});">
                                                                        <span class="glyphicon glyphicon-trash"></span>
                                                                </a></td>
                                                            </c:if>
                                                            <td>${serviziStandard.denominazioneCalcolata}</td>
                                                            <td><c:if
                                                                    test="${!empty serviziStandard.linkCollegamentoImpresa}">
                                                                    <a target="_blank"
                                                                        href="http://${serviziStandard.linkCollegamentoImpresa}">${serviziStandard.linkCollegamentoImpresa}</a>
                                                                </c:if></td>
                                                            <td><a
                                                                href="/vimp/servizi/${serviziStandard.idServizi}"><span
                                                                    class="glyphicon glyphicon-folder-open"></span></a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            --%>
					</div>



					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group">
								<c:if test="${dettaglio.plfProgettiProdottis.size()>0}">
									<div class="register-blocks">
										<h2><spring:message code="form.dettaglio.impresa.done_projects" text="Progetti prodotti"/> :</h2>
									</div>
									<div class="table-responsive">
										<table id="tabellaProgettiProdotti" class="table table-condensed bordered1 tablesorter">
											<thead>
											<tr>
												<th data-toggle="tooltip" data-placement="top" title="${stakeholderTitleAttr}"><spring:message code="form.dettaglio.impresa.done_projects" text="Progetti prodotti"/></th>
												<th></th>
											</tr>
											</thead>
											<tbody>
											<c:forEach items="${dettaglio.plfProgettiProdottis}" var="progettiProdotti">
												<tr id='rigaProgettiProdotti${progettiProdotti.idPlfProgettiProdotti}'>
													<td>${progettiProdotti.progettiProdottiTranslation.nomeProgettoProdotto}</td>
													<td>
														<a href="/vimp/progetto/${progettiProdotti.idPlfProgettiProdotti}"><span class="glyphicon glyphicon-folder-open"></span></a>
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</c:if>
							</div>
						</div>

					</div>


					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group">
								<c:if test="${dettaglio.plfNewsImpresas.size()>0}">
									<div class="register-blocks">
										<h2><spring:message code="news" text="News"/> :</h2>
									</div>
									<div class="table-responsive">
										<table id="tabellaNewsImpresa" class="table table-condensed bordered1 tablesorter">
											<thead>
											<tr>
												<th data-toggle="tooltip" data-placement="top" title="${newsTitleAttr}"><spring:message code="news" text="News"/></th>
												<th></th>
											</tr>
											</thead>
											<tbody>
											<c:forEach items="${dettaglio.plfNewsImpresas}" var="newsImpresa">
												<tr id='rigaNewsImpresa${newsImpresa.idNewsImpresa}'>
													<td>${newsImpresa.newsImpresaTranslation.descrizione}</td>
													<td>
														<a href="/vimp/news/${newsImpresa.idNewsImpresa}"><span class="glyphicon glyphicon-folder-open"></span></a>
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</c:if>
							</div>
						</div>
						<div class="col-sm-6">
							&nbsp;
						</div>
					</div>


					<c:choose>
						<c:when test="${modifica}">
							<div class="col-sm-12">&nbsp;</div>
							<div class="col-md-12 col-xs-12 register-blocks" style="text-align: center;">
								<h2><spring:message code="form.dettaglio.impresa.social_channels" text="Canali social"/></h2>
								<br><small><spring:message code="social_web_site_edit" text="Link social"/></small>
							</div>
							<div class="col-sm-12">&nbsp;</div>
						</c:when>
						<c:otherwise>
							<c:if
									test="${!empty dettaglio.facebook || !empty dettaglio.youtube || !empty dettaglio.twitter && !empty dettaglio.linkedin || !empty dettaglio.flickr || !empty dettaglio.intragram}">
								<div class="col-sm-12">&nbsp;</div>
								<div class="col-sm-12 ">
									<h5 align="center">Social</h5>
								</div>
								<div class="col-sm-12">&nbsp;</div>
							</c:if>
						</c:otherwise>
					</c:choose>



					<c:choose>
						<c:when test="${modifica}">
							<div class="col-sm-12">
								<div class="col-sm-6">
									<div class="form-group">
										<label><spring:message code="address" text="Indirizzo"/> Facebook</label> <input name="facebook"
																														 type="text" class="form-control" placeholder=""
																														 value="${dettaglio.facebook}" path="facebook" maxlength="200">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label><spring:message code="address" text="Indirizzo"/> Youtube</label> <input name="youtube"
																														type="text" class="form-control" placeholder=""
																														value="${dettaglio.youtube}" path="youtube" maxlength="200">
									</div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-6">
									<div class="form-group">
										<label><spring:message code="address" text="Indirizzo"/> Twitter</label> <input name="twitter"
																														type="text" class="form-control" placeholder=""
																														value="${dettaglio.twitter}" path="twitter" maxlength="200">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label><spring:message code="address" text="Indirizzo"/> Linkedin</label> <input name="linkedin"
																														 type="text" class="form-control" placeholder=""
																														 value="${dettaglio.linkedin}" path="linkedin" maxlength="200">
									</div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-6">
									<div class="form-group">
										<label><spring:message code="address" text="Indirizzo"/> Flickr</label> <input name="flickr"
																													   type="text" class="form-control" placeholder=""
																													   value="${dettaglio.flickr}" path="flickr" maxlength="200">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label><spring:message code="address" text="Indirizzo"/> Instagram</label> <input name="intragram"
																														  type="text" class="form-control" placeholder=""
																														  value="${dettaglio.intragram}" path="intragram"
																														  maxlength="200">
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="social center">
								<ul>
									<c:if test="${!empty dettaglio.facebook}">
										<li><a class="wow fadeInUp animated social-icons" target="_blank"
											   href="http://${dettaglio.facebook}"><i
												class="fa fa-facebook"></i></a></li>
									</c:if>
									<c:if test="${!empty dettaglio.youtube}">
										<li><a class="wow fadeInUp animated social-icons" target="_blank"
											   href="http://${dettaglio.youtube}" data-wow-delay="0.2s"><i
												class="fa fa-youtube"></i></a></li>
									</c:if>
									<c:if test="${!empty dettaglio.twitter}">
										<li><a class="wow fadeInUp animated social-icons" target="_blank"
											   href="http://${dettaglio.twitter}" data-wow-delay="0.3s"><i
												class="fa fa-twitter"></i></a></li>
									</c:if>
									<c:if test="${!empty dettaglio.linkedin}">
										<li><a class="wow fadeInUp animated social-icons" target="_blank"
											   href="http://${dettaglio.linkedin}" data-wow-delay="0.4s"><i
												class="fa fa-linkedin"></i></a></li>
									</c:if>
									<c:if test="${!empty dettaglio.flickr}">
										<li><a class="wow fadeInUp animated social-icons" target="_blank"
											   href="http://${dettaglio.flickr}" data-wow-delay="0.5s"><i
												class="fa fa-flickr"></i></a></li>
									</c:if>
									<c:if test="${!empty dettaglio.intragram}">
										<li><a class="wow fadeInUp animated social-icons" target="_blank"
											   href="http://${dettaglio.intragram}" data-wow-delay="0.6s"><i
												class="fa fa-instagram"></i></a></li>
									</c:if>
								</ul>
							</div>
						</c:otherwise>
					</c:choose>




					<div class="col-sm-12">&nbsp;</div>
					<div class="col-md-12 col-xs-12 register-blocks" style="text-align: center;">
						<h2><spring:message code="form.dettaglio.impresa.company_location" text="Ubicazione sede azienda"/></h2>
					</div>
					<div class="col-sm-12">&nbsp;</div>


					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.provincia ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="ricerca.impresa.form.district" text="Provincia"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="codProvincia" path="codProvincia" />
												<br><label><b>${dettaglio.provincia}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectProvincia"
															 title="${selectTheProvinceTitleAttr}" data-live-search="true"
															 data-live-search-style="contains"
															 path="codProvincia"
															 cssClass="selectpicker"
															 onchange="javascript:updateComuni(this.options[selectedIndex].value);">
													<form:options items="${provinciaList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.provincia}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.plfTComune.descComune ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.city" text="Comune"/> :<c:if test="${modifica}">
									<small>(<spring:message code="required" text="richiesto"/>)</small>
								</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<c:choose>
											<c:when test="${dettaglio.tipoInformazione == 1}">
												<form:hidden id="plfTComune.idComune" path="plfTComune.idComune" />
												<br><label><b>${dettaglio.plfTComune.descComune}</b></label>
											</c:when>
											<c:otherwise>
												<form:select id="selectComune" name="selectComune"
															 title="${selectTheCityTitleAttr}" data-live-search="true"
															 data-live-search-style="contains"
															 path="plfTComune.idComune"
															 cssClass="selectpicker">
													<form:options items="${comuneList}" />
												</form:select>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.plfTComune.descComune}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>


					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.codCap ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="common_texts.cap" text="CAP"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input id="codCap" name="codCap" type="text" class="form-control"
											   placeholder="" value="${dettaglio.codCap}" path="codCap"
											   maxlength="5">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.codCap}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6">&nbsp;</div>
					</div>


					<div class="col-sm-12" id="indirizzoText" name="indirizzoText">
						<div class="col-sm-6" ${!modifica && empty dettaglio.descIndirizzo ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.address" text="Indirizzo"/> :<c:if test="${modifica}">
									<small>(<spring:message code="required" text="richiesto"/>)</small>
								</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<input id="descIndirizzo" name="descIndirizzo" type="text" class="form-control"
											   placeholder="" value="${dettaglio.descIndirizzo}"
											   maxlength="400">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.descIndirizzo}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.numeroCivico ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="form.dettaglio.accreditamento.civic_number" text="Numero civico"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input id="numeroCivico" name="numeroCivico" type="text" class="form-control"
											   placeholder="" value="${dettaglio.numeroCivico}" path="numeroCivico"
											   maxlength="50">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.numeroCivico}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>


					<div class="col-sm-12" id="indirizzoTopoDiv" name="indirizzoTopoDiv">
						<div class="col-sm-6" ${!modifica && empty dettaglio.descIndirizzo ? 'hidden' : ''}>
							<div class="form-group">
								<label>
									<spring:message code="form.dettaglio.accreditamento.address" text="Indirizzo"/> :
									<c:if test="${modifica}">
										<small>(<spring:message code="required" text="richiesto"/>)</small>
										<span id="indirizzoInfo" name="indirizzoInfo">
											<br>
											<ul>
											  <li><small><spring:message code="form.dettaglio.impresa.indirizzo.info1" text="apri la lista associata al campo Indirizzo"/></small></li>
											  <li><small><spring:message code="form.dettaglio.impresa.indirizzo.info2" text="digita l'indirizzo nel campo di ricerca"/></small></li>
											  <li><small><spring:message code="form.dettaglio.impresa.indirizzo.info3" text="seleziona l'indirizzo dalla lista risultato"/></small></li>
											</ul>
										</span>
									</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="indirizzoTopo" name="indirizzoTopo"
													 title="${selectTheStreetTitleAttr}" data-live-search="true"
													 data-live-search-style="contains"
													 path="indirizzoTopo"
													 cssClass="selectpicker">
												<form:options items="${indirizzoTopolist}" />
										</form:select>
										<br>
										<br>
										<ul>
								  			<li><small><spring:message code="form.dettaglio.impresa.indirizzo.info" text="conferma le modifche effettualte selezionando il bottone APPLICA MODIFICHE"/></small></li>
										</ul>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.descIndirizzo}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.numeroCivico ? 'hidden' : ''}>
							<div class="form-group">
								<label>
									<spring:message code="form.dettaglio.accreditamento.civic_number" text="Numero civico"/> :
									<c:if test="${modifica}">
										<span id="numeroCivicoInfo" name="numeroCivicoInfo">
											<br>
											<ul>
											  <li><small><spring:message code="form.dettaglio.impresa.numero_civico.info1" text="apri la lista associata al Numero civico"/></small></li>
											  <li><small><spring:message code="form.dettaglio.impresa.numero_civico.info2" text="seleziona l'indirizzo dalla lista risultato"/></small></li>
											</ul>
											<br>
										</span>
									</c:if>
								</label>
								<c:choose>
									<c:when test="${modifica}">
										<form:select id="selectNumeroCivicoTopo" name="selectNumeroCivicoTopo"
													 title="${selectTheCivicNumberTitleAttr}" data-live-search="true"
													 data-live-search-style="contains"
													 path="numeroCivicoTopo"
													 cssClass="selectpicker">
												<form:options items="${numeroCivicoTopolist}" />
										</form:select>
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.numeroCivico}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>				
					</div>
					
					
						

					<div class="col-sm-12">
						<div class="col-sm-6" ${!modifica && empty dettaglio.coordX ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="common_texts.latitude" text="Latitudine"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input id="coordX" name="coordX" type="number" class="form-control"
											   placeholder="" value="${dettaglio.coordX}" path="coordX"
											   maxlength="20">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.coordX}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-sm-6" ${!modifica && empty dettaglio.coordY ? 'hidden' : ''}>
							<div class="form-group">
								<label><spring:message code="common_texts.longitude" text="Longitudine"/> :</label>
								<c:choose>
									<c:when test="${modifica}">
										<input id="coordY" name="coordY" type="number" class="form-control"
											   placeholder="" value="${dettaglio.coordY}" path="coordY"
											   maxlength="20">
									</c:when>
									<c:otherwise>
										<br>
										<label><b>${dettaglio.coordY}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>

					<div class="col-sm-12">&nbsp;</div>

					<div class="col-sm-12">
						<c:choose>
							<c:when
									test="${(!empty dettaglio.coordX) && !empty dettaglio.coordY}">
								<div id="map" class="map-extend"
									 data-latitude="${dettaglio.coordX}"
									 data-longitude="${dettaglio.coordY}"></div>
							</c:when>
							<c:otherwise>
								<div id="map" class="map-extend" data-nopoint="true"></div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="safe-col">
						<c:if test="${modifica}">
							<div class="col-sm-6">
								<div class="form-grid-checkbox">
									<label>
										<input id="pubblicato" name="pubblicato" type="checkbox"/>
										<strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
									</label>
								</div>
							</div>
						</c:if>
					</div>

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


				<c:if test="${modifica}">
					<div class="col-sm-12">
						<div class="col-sm-6">&nbsp;</div>
						<div class="col-sm-6">&nbsp;</div>


						<div class="col-sm-12">
							<div class="col-sm-6 save-btns">
							
								<c:if test="${cancellaRipristinaImpresa}">
									<c:choose>
										<c:when test="${dettaglio.dataCancellazione != null}">
											<a href="#" class="btn btn-finish btn-restore"
									   			data-toggle="modal" data-target="#ripristinaModal"><spring:message code="form.dettaglio.impresa.recove_enterprise" text="RIPRISTINA IMPRESA"/></a>
										</c:when>
										<c:otherwise>
											<a href="#" class="btn btn-finish btn-delete"
									   			data-toggle="modal" data-target="#chiudiModal"><spring:message code="form.dettaglio.impresa.erase_enterprise" text="CANCELLA IMPRESA"/></a>
										</c:otherwise>
									</c:choose>
									   
								</c:if>
								
								
								<button id="cancelButton" class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" text="ANNULLA"/></button>
							</div>
							<div class="col-sm-6 save-btns">

								<c:choose>
									<c:when test="${utente.isBackoffice() && dettaglio.dataAccreditamento != null}">

									</c:when>
									<c:otherwise>

										<button id="saveButton" type="button"
												class='btn btn-finish btn-primary pull-right'
												onClick="javascript:aggiorna(event); return false;" value="APPLICA MODIFICHE"><spring:message code="common_texts.apply_changes" text="APPLICA MODIFICHE"/></button>

									</c:otherwise>
								</c:choose>


							</div>
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
							<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.impresa.enterprise_deletion" text="Cancellazione impresa"/></h4>
					</div>
					<div class="modal-body">
						<strong><spring:message code="warning" text="Attenzione!"/>
						</strong> <spring:message code="form.dettaglio.impresa.news_deletion_text" text="Procedere con la cancellazione dell'impresa"/>
							${dettaglio.impresaTranslation.descImpresa}?<br>

					</div>
					<div class="modal-footer">
						<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
						<button type="button" class="btn btn-primary"
								onClick="javascript:cancella();"><spring:message code="delete.uppercase" text="CANCELLA"/></button>
					</div>
				</div>

			</div>
		</div>
		
		<div class="modal fade" id="ripristinaModal" tabindex="-1" role="dialog"
			 aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">


				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.impresa.enterprise_recove" text="Ripristino impresa"/></h4>
					</div>
					<div class="modal-body">
						<strong><spring:message code="warning" text="Attenzione!"/>
						</strong> <spring:message code="form.dettaglio.impresa.enterprise_recove_text" text="Procedere con il ripristino dell'impresa"/>
							${dettaglio.impresaTranslation.descImpresa}?<br>

					</div>
					<div class="modal-footer">
						<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
						<button type="button" class="btn btn-primary"
								onClick="javascript:ripristina();"><spring:message code="recove.uppercase" text="RIPRISTINA"/></button>
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
						<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
						<button name="cancellaCollegamentoModalButton"
								id="cancellaCollegamentoModalButton" type="button"
								class="btn btn-primary"><spring:message code="delete.uppercase" text="CANCELLA"/></button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="editAllegatoModal" tabindex="-1" role="dialog"
			 aria-labelledby="editAllegatoLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="editAllegatoClodeModal" name="editAllegatoClodeModal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
						</button>
						<h4 class="modal-title" id="editAllegatoTitle"><spring:message code="form.dettaglio.impresa.edit_allegato"/></h4>
					</div>

					<form id="edit_allegato_form"  method="POST" >
						<div class="modal-body">
							<div>
								<span><spring:message code="warning"/> <spring:message code="form.dettaglio.impresa.edit_allegato_current"/></span>
							</div>
							<div class="form-group">
								<input id="idAllegatoEdit" hidden="true"/>
								<label><spring:message code="description" text="Descrizione"/> : </label>
								<input id="allegatoDescriptionEdit"type="text" class="form-control" maxlength="400">
							</div>
						</div>
						<div class="modal-footer">
							<div class="pull-right">
								<a id="saveDescriptionDialogBtn" class='btn btn-finish btn-primary' onclick="saveAllegatoDescription()"><spring:message code="save"/></a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>


		<div class="modal fade" id="linkServizioStandardModal" tabindex="-1"
			 role="dialog" aria-labelledby="linkServizioStandardLabel"
			 aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
						</button>
						<h4 class="modal-title" id="linkServizioStandardLabel"><spring:message code="form.dettaglio.impresa.connect_service.uppercase" text="COLLEGA SERVIZIO"/></h4>
					</div>
					<div class="modal-body">
						<spring:message code="form.dettaglio.impresa.connect_service_confirm" text="Procedere con il collegamento del servizio"/> <br>

						<div class="form-group">
							<label>Link : </label>
							<input id="linkServizioStandardModalText" name="linkServizioStandardModalText" type="text" class="form-control" maxlength="200">
						</div>
					</div>
					<div class="modal-footer">
						<a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
						<button name="linkServizioStandardModalButton"
								id="linkServizioStandardModalButton" type="button"
								class="btn btn-primary"><spring:message code="form.dettaglio.impresa.connect.uppercase" text="COLLEGA"/></button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
			 aria-labelledby="uploadModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="uploadModalCloseButton" name="uploadModalCloseButton">
							<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
						</button>
						<h4 class="modal-title" id="uploadModalTitle"><spring:message code="description" text="Allega documento"/></h4>
					</div>

					<form enctype="multipart/form-data" id="modal_form_id"  method="POST" >
						<div class="modal-body">
							<strong><spring:message code="select_doc_to_attach"/> (<spring:message code="max_size"/>: 20MB)</strong>
							<span id="uploadModalMessage"></span>
							<br>
							<input type="file" class="fileupload" name="fileAllegato" id="fileAllegato">

							<div class="form-group">
								<label><spring:message code="description" text="Descrizione"/> : </label>
								<input id="allegaFileDialogText" name="allegaFileDialogText" type="text" class="form-control" maxlength="400">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
								<a id="allegaFileDialogButton"
								   name="allegaFileDialogButton"
								   class='btn btn-finish btn-primary'
								   onClick="javascript:allegaFile();"><spring:message code="attach" text="Allega"/></a>
							
						</div>
					</form>
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

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript" charset="UTF-8"></script>

<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false&key=${mapsApiKey}&language=${language}&region=${region}"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/gmaps.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/gmaps.init.js"></script>



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
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/summernote-it-IT.js"></script>


<link rel="stylesheet" href="${evn_urlRisorseStatiche}/vimp/assets/css/ajax-bootstrap-select.min.css" >
<script type="text/javascript"
		src="${evn_urlRisorseStatiche}/vimp/assets/js/ajax-bootstrap-select.min.js"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/checkModify.js"></script>

<script>
	var uploadImage = false;

	$(document).ready(
			function() {

				if (${modifica})
					$('#frmDettaglio').addClass('safe-reload');
				else
					$('#frmDettaglio').removeClass('safe-reload');


				$('[data-toggle="popover"]').popover();
				$('.popover-dismiss').popover({
					  trigger: 'focus'
				});

				if (${dettaglio.plfTComune.idComune == idComuneGenova})
				{
					$('#indirizzoText').hide();
					$('#indirizzoTopoDiv').show();
					$('#coordX').prop("readonly", true);
					$('#coordY').prop("readonly", true);
					$('#codCap').prop("readonly", true);
				}
				else
				{
					$('#indirizzoText').show();
					$('#indirizzoTopoDiv').hide();
					$('#coordX').prop("readonly", false);
					$('#coordY').prop("readonly", false);
					$('#codCap').prop("readonly", false);
				}

				$('#selectComune').on('change', function(e){

					$('#coordX').val('');
					$('#coordY').val('');
					$('#codCap').val('');
					$('#descIndirizzo').val('');
					$('#numeroCivico').val('');
					removePointToMap();
					
					if (this.options[this.selectedIndex].value == ${idComuneGenova})
					{
						$('#indirizzoText').hide();
						$('#indirizzoTopoDiv').show();
						$('#coordX').prop("readonly", true);
						$('#coordY').prop("readonly", true);
						$('#codCap').prop("readonly", true);
					}
					else
					{
						$('#indirizzoText').show();
						$('#indirizzoTopoDiv').hide();
						$('#coordX').prop("readonly", false);
						$('#coordY').prop("readonly", false);
						$('#codCap').prop("readonly", false);
					}
				});


				$('#indirizzoTopo')
			    .selectpicker({
			        liveSearch: true
			    })
			    .ajaxSelectPicker({
			        ajax: {
			            url: '/vimp/elencoStrade.json',
			            type: 'GET',
			            dataType: 'json',
			            data    : {
			                nome: '{{{q}}}'
			            }
			        },
			        locale: {
			            emptyTitle: '<spring:message code="common.ricercaStrade" javaScriptEscape="true" text="Ricerca strade..."/>'
			        },
			        preprocessData: function(data){
			        	var i, l = data.length, array = [];
			            if (l) {
			                for (i = 0; i < l; i++) {
			                    array.push($.extend(true, data[i], {
			                        text : data[i].nomeVia,
			                        value: data[i].codiceStrada
			                    }));
			                }
			            }
			            return array;
			        },
			        preserveSelected: false
			    });
				$('#indirizzoTopo').on('change', function(e){
					var codiceStrada = this.options[this.selectedIndex].value;
					if (codiceStrada)
					{
						$('#coordX').val('');
						$('#coordY').val('');
						$('#codCap').val('');
						
						$('#descIndirizzo').val('');
						$('#numeroCivico').val('');
						removePointToMap();
						
					

						$.getJSON("/vimp/elencoCivici.json", {
							codice : codiceStrada
						}, function(data) {

							$("#selectNumeroCivicoTopo").append($("<option></option>").text('<spring:message code="common.selezionaIlCivico" javaScriptEscape="true" text="Seleziona il comune"/>').val('').addClass('bs-title-option'));

							$.each(data, function(index, item) {


								var coloreStyle = '';
								var coloreText = ' (nero)';
								if (item.colore == 'R')
								{
									coloreStyle = 'style="color:red"';
									coloreText = ' (rosso)';
								}
								
								var tcivico = item.numero + coloreText;
								if (item.lettera != '-')
									tcivico = item.numero + item.lettera + coloreText;
								
								$("#selectNumeroCivicoTopo").append(
										$("<option " +  coloreStyle + "></option>")
											.text(tcivico)
											.val(item.query));
							});

							$('#selectNumeroCivicoTopo').selectpicker('refresh');
						});
					}
				});
				$('#selectNumeroCivicoTopo').on('change', function(e){
					var query = this.options[this.selectedIndex].value;
					if (query && query.startsWith("CODICE_STRADA="))
					{
						$("#coordX").val('');
						$("#coordY").val('');
						$("#codCap").val('');

						$.getJSON("/vimp/civicoQuery.json", {
							query : query
						}, function(data) {
							$("#coordX").val(data.lat);
							$("#coordY").val(data.lon);
							$("#codCap").val(data.cap);

							$("#sezioneCensimento2011").val(data.sezioneCensimento2011);
							$("#sezioneElettorale").val(data.sezioneElettorale);
							$("#codiceMunicipio").val(data.codiceMunicipio);
							$("#nomeMunicipio").val(data.nomeMunicipio);
							$("#codiceCircoscrizione").val(data.codiceCircoscrizione);
							$("#nomeCircoscrizione").val(data.nomeCircoscrizione);
							$("#codiceUnitaUrbanistica").val(data.codiceUnitaUrbanistica);
							$("#nomeUnitaUrbanistica").val(data.nomeUnitaUrbanistica);
							
							addPointToMap(data.lat,data.lon);
						});
					}
				});

				
					


				var summernoteForm = $('#frmDettaglio');
				$('#descMissione').summernote(
						{
							onChange: function (contents, $editable) {
								summernoteDescMissioneElement.val(summernoteDescMissioneElement.summernote('isEmpty') ? "" : contents);
							},
							lang: '${env_locale}',
							placeholder : '<spring:message code="impresaMissionePlaceholder" javaScriptEscape="true"/> ',
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
				var summernoteDescMissioneElement = $('#descMissione');

				$('#descBreveImpresa').summernote(
						{
							onChange: function (contents, $editable) {
								summernoteDescBreveImpresaElement.val(summernoteDescBreveImpresaElement.summernote('isEmpty') ? "" : contents);
							},
							lang: '${env_locale}',
							placeholder : '<spring:message code="impresaDescrizionePlaceholder" javaScriptEscape="true" text="Testo descrizione dell'impresa"/>',
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

				var summernoteDescBreveImpresaElement = $('#descBreveImpresa');

				$('#descAttivita').summernote(
						{
							lang: '${env_locale}',
							placeholder : '<spring:message code="impresaDescrizioneAttivitaPlaceholder" javaScriptEscape="true" text="Testo descrizione attivita' dell'impresa"/>',
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

				$('#brevetto').summernote(
						{
							lang: '${env_locale}',
							placeholder : '<spring:message code="impresaBrevettoPlaceholder" javaScriptEscape="true" text="Testo brevetto"/>',
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


				
				if (${dettaglio.incubatoreCertificato == 1})
					$('#checkboxIncubatore').iCheck('check');
				else
					$('#checkboxIncubatore').iCheck('uncheck');
				$('#checkboxIncubatore').on('ifChecked', function(event) {
					$("#incubatoreCertificato").val(1);
				});
				$('#checkboxIncubatore').on('ifUnchecked', function(event) {
					$("#incubatoreCertificato").val(0);
				});
				

				if (${dettaglio.primoRequisitoPmi == 'S'})
					$('#checkboxPrimoRequisito').iCheck('check');
				else
					$('#checkboxPrimoRequisito').iCheck('uncheck');
				$('#checkboxPrimoRequisito').on('ifChecked', function(event) {
					$("#primoRequisitoPmi").val("S");
				});
				$('#checkboxPrimoRequisito').on('ifUnchecked', function(event) {
					$("#primoRequisitoPmi").val("N");
				});
				if (${dettaglio.secondoRequisitoPmi == 'S'})
					$('#checkboxSecondoRequisito').iCheck('check');
				else
					$('#checkboxSecondoRequisito').iCheck('uncheck');
				$('#checkboxSecondoRequisito').on('ifChecked', function(event) {
					$("#secondoRequisitoPmi").val("S");
				});
				$('#checkboxSecondoRequisito').on('ifUnchecked', function(event) {
					$("#secondoRequisitoPmi").val("N");
				});
				if (${dettaglio.terzoRequisitoPmi == 'S'})
					$('#checkboxTerzoRequisito').iCheck('check');
				else
					$('#checkboxTerzoRequisito').iCheck('uncheck');
				$('#checkboxTerzoRequisito').on('ifChecked', function(event) {
					$("#terzoRequisitoPmi").val("S");
				});
				$('#checkboxTerzoRequisito').on('ifUnchecked', function(event) {
					$("#terzoRequisitoPmi").val("N");
				});


				$("#tabellaElementiInnovazione").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				$("#tabellaMercatiRiferimento").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				$("#tabellaStakeholder").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				$("#tabellaImprese").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				$("#tabellaServiziStandard").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				refreshCollegamenti(true);



				$("#tabellaNewsImpresa").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				$("#tabellaServiziImpresa").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
				$("#tabellaProgettiProdotti").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});

				$("#tabellaAllegati").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});


				$('.datepicker').datepicker({
					format : 'yyyy-mm-dd',
					viewformat : 'dd/mm/yyyy',
					language: '${env_locale}'
				});

				$('#wizard-picture').change(function() {
					uploadImage = true;
				});

				// TODO
				var summernoteValidator;
				if (${dettaglio.tipoInformazione == 1})
				{
					summernoteValidator = $('#frmDettaglio').validate({
						ignore: ':hidden:not(#descMissione,#descBreveImpresa),.note-editable.card-block',
						errorPlacement: function(error, element) {
							if(element.parent('.input-group').length) {
								error.insertAfter(element.parent());
							} else {
								error.insertAfter(element);
							}
						},
						rules : {
							partitaIva: {
								required: function(element) {
									//return $("#codFiscale").val().length <= 0;
									return false;
								}
							},
							codFiscale: {
								required: function(element) {
									return $("#partitaIva").val().length <= 0;
								}
							},
							descImpresa : "required",
							'impresaTranslation.descMissione' : {
								required: function(element) {
									return $('#descMissione').summernote('isEmpty');
								}
							},
							nomeLegaleRappresentante : "required",
							cognomeLegaleRappresentante : "required",
							codiceFiscaleLegaleRappresentante : "required",
							'impresaTranslation.descBreveImpresa': {
								required: function(element) {
									return $('#descBreveImpresa').summernote('isEmpty');
								}
							},
							'impresaTranslation.mercatiAltro': {
								required: function(element) {
									return $('#tabellaMercatiRiferimento tr').length <= 1;
								}
							},
							emailContatto : 'required',
							'plfTComune.idComune' : 'required',
							descIndirizzo: {
								required: function(element) {
									if ($('#selectComune option:selected').val() != ${idComuneGenova})
										return $('#descIndirizzo').val() == '';
									return false;
								}
							},
							indirizzoTopo: {
								required: function(element) {
									if ($('#selectComune option:selected').val() == ${idComuneGenova})
										return $('#indirizzoTopo option:selected').text() != '${selectTheStreetTitleAttr}';
									return false;
								}
							}
						},
						messages : {
							partitaIva : '<spring:message code="impresaInserirePartitaIvaCF" javaScriptEscape="true"/>',
							codFiscale : '<spring:message code="impresaInserirePartitaIvaCF" javaScriptEscape="true"/>',
							descImpresa : '<spring:message code="impresaInserireRagSoc" javaScriptEscape="true"/>',
							'impresaTranslation.descMissione' : '<spring:message code="impresaInserireMissione" javaScriptEscape="true"/>',
							nomeLegaleRappresentante : '<spring:message code="impresaInserireNomeLegale" javaScriptEscape="true"/>',
							cognomeLegaleRappresentante : '<spring:message code="impresaInserireCognomeLegale" javaScriptEscape="true"/>',
							codiceFiscaleLegaleRappresentante : '<spring:message code="impresaInserireCFLegale" javaScriptEscape="true"/>',
							'impresaTranslation.descBreveImpresa' : '<spring:message code="impresaInserireDescrizioneBreve" javaScriptEscape="true"/>',
							'impresaTranslation.mercatiAltro' : '<spring:message code="impresaInserireMercatoRiferimento" javaScriptEscape="true"/>',
							emailContatto : '<spring:message code="impresaInserireMailContattoImpresa" javaScriptEscape="true"/>',
							'plfTComune.idComune' : '<spring:message code="impresaInserireComune" javaScriptEscape="true"/>',
							descIndirizzo : '<spring:message code="impresaInserireIndirizzo" javaScriptEscape="true"/>',
							indirizzoTopo : '<spring:message code="impresaInserireIndirizzo" javaScriptEscape="true"/>'
						},
						showErrors: function (errorMap, errorList) {
							// errorList[0].element; // <- index "0" is the first element with an error

							if (typeof errorList[0] != "undefined") {
								var id = errorList[0].element.id;

								if(id === 'descMissione' || id === 'descBreveImpresa') {
									$('html, body').animate({
										scrollTop: 200
									}, 300);
								}
							}
							this.defaultShowErrors(); // keep error messages next to each input element
						}
					});
				}
				else
				{
					summernoteValidator = $('#frmDettaglio').validate({
						ignore: ':hidden:not(#descMissione,#descBreveImpresa),.note-editable.card-block',
						rules : {
							partitaIva: {
								required: function(element) {
									return $("#codFiscale").val().length <= 0;
								}
							},
							codFiscale: {
								required: function(element) {
									return $("#partitaIva").val().length <= 0;
								}
							},
							'impresaTranslation.descImpresa' : "required",
							'impresaTranslation.descMissione' : {
								required: function(element) {
									return $('#descMissione').summernote('isEmpty');
								}
							},
							'impresaTranslation.descBreveImpresa' : {
								required: function(element) {
									return $('#descBreveImpresa').summernote('isEmpty');
								}
							},
							'impresaTranslation.mercatiAltro' : {
								required: function(element) {
									return $('#tabellaMercatiRiferimento tr').length <= 1;
								}
							},
							emailContatto : "required",
							"plfTComune.idComune" : "required",
							descIndirizzo: {
								required: function(element) {
									if ($('#selectComune option:selected').val() != ${idComuneGenova})
										return $('#descIndirizzo').val() == '';
									return false;
								}
							},
							indirizzoTopo: {
								required: function(element) {
									if ($('#selectComune option:selected').val() == ${idComuneGenova})
										return $('#indirizzoTopo option:selected').text() == '${selectTheStreetTitleAttr}';
									return false;
								}
							}
						},
						messages : {
							partitaIva : '<spring:message code="impresaInserirePartitaIvaCF" javaScriptEscape="true" />',
							codFiscale : '<spring:message code="impresaInserirePartitaIvaCF" javaScriptEscape="true" />',
							'impresaTranslation.descImpresa' : '<spring:message code="impresaInserireRagSoc" javaScriptEscape="true" />',
							'impresaTranslation.descMissione' : '<spring:message code="impresaInserireMissione" javaScriptEscape="true" />',
							'impresaTranslation.descBreveImpresa' : '<spring:message code="impresaInserireDescrizioneBreve" javaScriptEscape="true" />',
							'impresaTranslation.mercatiAltro' : '<spring:message code="impresaInserireMercatoRiferimento" javaScriptEscape="true" />',
							emailContatto : '<spring:message code="impresaInserireMailContattoImpresa" javaScriptEscape="true" />',
							"plfTComune.idComune" : '<spring:message code="impresaInserireComune" javaScriptEscape="true" />',
							descIndirizzo : '<spring:message code="impresaInserireIndirizzo" javaScriptEscape="true" />',
							indirizzoTopo : '<spring:message code="impresaInserireIndirizzo" javaScriptEscape="true"/>'
						},
						showErrors: function (errorMap, errorList) {
							// errorList[0].element; // <- index "0" is the first element with an error

							if (typeof errorList[0] != "undefined") {
								var id = errorList[0].element.id;

								if(id === 'descMissione' || id === 'descBreveImpresa') {
									$('html, body').animate({
										scrollTop: 200
									}, 300);
								}
							}
							this.defaultShowErrors(); // keep error messages next to each input element
						}
					});
				}

				if(${dettaglio.pubblicato} === true) {
					$('input[name="pubblicato"]').iCheck('check');
				}
				$('input[name="pubblicato"]').on('ifChecked', function(event) {

					if(${dettaglio.dataAccreditamento == null})
					{
						$('#errorModalTitle').text("<spring:message code="impresaPubblicaTitolo" javaScriptEscape="true" text="Pubblicazione dell impresa"/>");
						$('#errorModalMessage').text("<spring:message code="impresaPubblicaNoAccreditamento" javaScriptEscape="true" text="L impresa verra pubblicata sulla vetrina senza essere accreditata"/>");
						$('#errorModal').modal('show');
					}
				});
				
				disableFieldsOnEdit();

				<!-- CONTROLLO USCITA PAGINA MODIFICATA SENZA SALVARE-->
				<!-- checkModify.js -->
				if (${modifica})
				{
					setCheckModify('saveButton','cancelButton',null,
							['pubblicato'],
							['descMissione','descBreveImpresa','descAttivita','brevetto']);
				}
			});

	function disableFieldsOnEdit() {
		$('.disable-on-edit').prop('disabled', true);
	}

	function aggiorna(event) {
		event.preventDefault();
		if (uploadImage) {

			var fileSize = $('#wizardPicturePreview').attr('src').length;
			if (fileSize > 1572864) {
				alert('<spring:message code="immagineTroppoGrandeMax1.5M" javaScriptEscape="true" text="Attenzione! Immagine troppo grande (max 1.5 MB)"/>');
				return;
			}

			$("#imageData").val($('#wizardPicturePreview').attr('src'));
			handleSummernotesBeforeSave();


			var optionComune = $('#selectComune option:selected').val();
			if (typeof optionComune === "undefined") {
				if (${dettaglio.plfTComune.idComune == idComuneGenova})
				{
					$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
					$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
				}
			}
			else if (optionComune == ${idComuneGenova})
			{
				$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
				$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
			}
			
			
			$("#frmDettaglio").submit();
		} else {
			$("#imageData").val("");
			handleSummernotesBeforeSave();

			var optionComune = $('#selectComune option:selected').val();
			if (typeof optionComune === "undefined") {
				if (${dettaglio.plfTComune.idComune == idComuneGenova})
				{
					$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
					$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
				}
			}
			else if (optionComune == ${idComuneGenova})
			{
				$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
				$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
			}
			
			$("#frmDettaglio").submit();
		}

	}

	function handleSummernotesBeforeSave() {
		if($('#descMissione').summernote('isEmpty')){
			$('#descMissione').val('');
		}

		if($('#descBreveImpresa').summernote('isEmpty')){
			$('#descBreveImpresa').val('');
		}
	}

	function cancella() {

		
		$("#imageData").val("");
		$('#frmDettaglio').attr('action', '/vimp/secure/cancellaImpresa');
		$('#frmDettaglio').validate().settings.ignore = "*";

		var optionComune = $('#selectComune option:selected').val();
		if (typeof optionComune === "undefined") {
			if (${dettaglio.plfTComune.idComune == idComuneGenova})
			{
				$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
				$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
			}
		}
		else if (optionComune == ${idComuneGenova})
		{
			$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
			$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
		}
		
		$("#frmDettaglio").submit();
	}


	function ripristina() {
		$("#imageData").val("");
		$('#frmDettaglio').attr('action', '/vimp/secure/ripristinaImpresa');
		$('#frmDettaglio').validate().settings.ignore = "*";

		var optionComune = $('#selectComune option:selected').val();
		if (typeof optionComune === "undefined") {
			if (${dettaglio.plfTComune.idComune == idComuneGenova})
			{
				$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
				$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
			}
		}
		else if (optionComune == ${idComuneGenova})
		{
			$("#numeroCivico").val($('#selectNumeroCivicoTopo option:selected').text());
			$("#descIndirizzo").val($('#indirizzoTopo option:selected').text());
		}
		
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
			return '<spring:message code="datoNonModificabile" javaScriptEscape="true" text="Dato non modificabile"/>';
		} else {
			if (response.status === 423) {
				return '<spring:message code="sessioneScadutaRicollegarsiAlSistema" javaScriptEscape="true" text="Sessione scaduta. Ricollegarsi al sistema"/>';
			} else {
				return '<spring:message code="aggiornamentoValoreFallito" javaScriptEscape="true" text="Aggiornamento valore fallito."/>';
			}
		}
	}


	function refreshCollegamenti(firstLoad)
	{
		if (firstLoad)
		{
			if (${dettaglio.plfTInnovaziones.size()>0})
				$("#tabellaElementiInnovazione").show();
			else $("#tabellaElementiInnovazione").hide();


			if (${dettaglio.plfTMercatis.size()>0})
				$("#tabellaMercatiRiferimento").show();
			else $("#tabellaMercatiRiferimento").hide();

			if (${dettaglio.stakeholders.size()>0})
				$("#tabellaStakeholder").show();
			else $("#tabellaStakeholder").hide();


			if (${dettaglio.impreseCollegate.size()>0})
				$("#tabellaImprese").show();
			else $("#tabellaImprese").hide();


			if (${dettaglio.serviziStandard.size()>0})
				$("#tabellaServiziStandard").show();
			else $("#tabellaServiziStandard").hide();

		}
		else
		{
			var rowCount = 0;
			$('#tabellaElementiInnovazione > tbody  > tr').each(function() {
				$this = $(this);
				var strValue = $this.html();
				if (strValue) {
					rowCount++;
				}
			});
			if (rowCount>0)
				$("#tabellaElementiInnovazione").show();
			else $("#tabellaElementiInnovazione").hide();


			rowCount = 0;
			$('#tabellaMercatiRiferimento > tbody  > tr').each(function() {
				$this = $(this);
				var strValue = $this.html();
				if (strValue) {
					rowCount++;
				}
			});
			if (rowCount>0)
				$("#tabellaMercatiRiferimento").show();
			else $("#tabellaMercatiRiferimento").hide();


			rowCount = 0;
			$('#tabellaStakeholder > tbody  > tr').each(function() {
				$this = $(this);
				var strValue = $this.html();
				if (strValue) {
					rowCount++;
				}
			});
			if (rowCount>0)
				$("#tabellaStakeholder").show();
			else $("#tabellaStakeholder").hide();


			rowCount = 0;
			$('#tabellaImprese > tbody  > tr').each(function() {
				$this = $(this);
				var strValue = $this.html();
				if (strValue) {
					rowCount++;
				}
			});
			if (rowCount>0)
				$("#tabellaImprese").show();
			else $("#tabellaImprese").hide();


			rowCount = 0;
			$('#tabellaServiziStandard > tbody  > tr').each(function() {
				$this = $(this);
				var strValue = $this.html();
				if (strValue) {
					rowCount++;
				}
			});
			if (rowCount>0)
				$("#tabellaServiziStandard").show();
			else $("#tabellaServiziStandard").hide();

		}
	}



	function collegaElementiInnovazione() {

		var elementoInnovazione = $( "#selectElementiInnovazione" ).val();
		if (elementoInnovazione)
		{
			$.ajax({
				type: "POST",
				url : '/vimp/secure/collegaElementiInnovazione',
				data:  { idImpresa : ${dettaglio.idPlfImpresa}, idElementoInnovazione: elementoInnovazione },
				success : function(data) {
					var id = data.pk;
					var desc = data.value;


					var row = "<td><a href='javascript:cancellaElementiInnovazioneShowModal("+ id +");'> " +
							"<span class='glyphicon glyphicon-trash'></span></a></td> " +
							"<td>" + desc + "</td> " ;

					$('#tabellaElementiInnovazione').append('<tr id="rigaElementiInnovazione'+id+'" role="row">'+ row +'</tr>');
					refreshCollegamenti(false);



					$("#selectElementiInnovazione").val('default');
					$("#selectElementiInnovazione").selectpicker("refresh");

				},
				error: function(error){
					$('#errorModalTitle').text("<spring:message code="impresaCollegaElementiDiInnovazione" javaScriptEscape="true" text="Collega elementi di innovazione"/>");
					$('#errorModalMessage').text("<spring:message code="impresaSelezionareElementoInnovazioneValidoDaCollegare" javaScriptEscape="true" text="Selezionare un elemento di innovazione valido da collegare"/>");
					$('#errorModal').modal('show');
				}
			});
		}
		else
		{
			$('#errorModalTitle').text("<spring:message code="impresaCollegaElementiDiInnovazione" javaScriptEscape="true" text="Collega elementi di innovazione"/>");
			$('#errorModalMessage').text("<spring:message code="impresaSelezionareElementoInnovazioneValidoDaCollegare" javaScriptEscape="true" text="Selezionare un elemento di innovazione valido da collegare"/>");
			$('#errorModal').modal('show');
		}
	}

	function cancellaElementoInnovazione(elementoInnovazione) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaCollegamentoElementiInnovazione',
			data:  { idImpresa : ${dettaglio.idPlfImpresa}, idElementoInnovazione: elementoInnovazione },
			success : function(result,data) {
				$('#rigaElementiInnovazione'+elementoInnovazione).html('');
				$('#rigaElementiInnovazione'+elementoInnovazione).remove();
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaElementiInnovazioneShowModal(elementoInnovazione) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaElementoInnovazione('+elementoInnovazione+');');
		$('#cancellaCollegamentoModal').modal('show');
	}


	function collegaMercatiRiferimento() {

		var mercatoRiferimento = $( "#selectMercatiRiferimento" ).val();
		if (mercatoRiferimento)
		{
			$.ajax({
				type: "POST",
				url : '/vimp/secure/collegaMercatiRiferimento',
				data:  { idImpresa : ${dettaglio.idPlfImpresa}, idMercatoRiferimento: mercatoRiferimento },
				success : function(data) {
					var id = data.pk;
					var desc = data.value;


					var row = "<td><a href='javascript:cancellaMercatiRiferimentoShowModal("+ id +");'> " +
							"<span class='glyphicon glyphicon-trash'></span></a></td> " +
							"<td>" + desc + "</td> " ;

					$('#tabellaMercatiRiferimento').append('<tr id="rigaMercatiRiferimento'+id+'" role="row">'+ row +'</tr>');
					refreshCollegamenti(false);


					$("#selectMercatiRiferimento").val('default');
					$("#selectMercatiRiferimento").selectpicker("refresh");

				},
				error: function(error){
					$('#errorModalTitle').text("<spring:message code="impresaCollegaMercatiDiRiferimento" javaScriptEscape="true" text="Collega mercati di riferimento"/>");
					$('#errorModalMessage').text("<spring:message code="impresaSelezionareUnMercatoDiRiferimentoDaCollegare" javaScriptEscape="true" text="Selezionare un mercato di riferimento valido da collegare"/>");
					$('#errorModal').modal('show');
				}
			});
		}
		else
		{
			$('#errorModalTitle').text("<spring:message code="impresaCollegaMercatiDiRiferimento" javaScriptEscape="true" text="Collega mercati di riferimento"/>");
			$('#errorModalMessage').text("<spring:message code="impresaSelezionareUnMercatoDiRiferimentoDaCollegare" javaScriptEscape="true" text="Selezionare un mercato di riferimento valido da collegare"/>");
			$('#errorModal').modal('show');
		}
	}

	function cancellaMercatoRiferimento(mercatoRiferimento) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaCollegamentoMercatiRiferimento',
			data:  { idImpresa : ${dettaglio.idPlfImpresa}, idMercatiRiferimento: mercatoRiferimento },
			success : function(result,data) {
				$('#rigaMercatiRiferimento'+mercatoRiferimento).html('');
				$('#rigaMercatiRiferimento'+mercatoRiferimento).remove();
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaMercatiRiferimentoShowModal(mercatoRiferimento) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaMercatoRiferimento('+mercatoRiferimento+');');
		$('#cancellaCollegamentoModal').modal('show');
	}



	function collegaStakeholder() {

		var stakeholder = $( "#selectStakeholder" ).val();
		if (stakeholder)
		{
			$.ajax({
				type: "POST",
				url : '/vimp/secure/collegaStakeholder',
				data:  { idImpresa : ${dettaglio.idPlfImpresa}, idStakeholder: stakeholder },
				success : function(data) {
					var id = data.pk;
					var desc = data.value;


					var row = "<td><a href='javascript:cancellaStakeholderShowModal("+ id +");'> " +
							"<span class='glyphicon glyphicon-trash'></span></a></td> " +
							"<td>" + desc + "</td> <td> <a href='/vimp/stakeholder/" + id + "'><span class='glyphicon glyphicon-folder-open'></span></a></td>"

					$('#tabellaStakeholder').append('<tr id="rigaStakeholder'+id+'" role="row">'+ row +'</tr>');
					refreshCollegamenti(false);


					$("#selectStakeholder").val('default');
					$("#selectStakeholder").selectpicker("refresh");

				},
				error: function(error){
					$('#errorModalTitle').text("<spring:message code="impresaCollegaStakeholder" javaScriptEscape="true" text="Collega stakeholder"/>");
					$('#errorModalMessage').text("<spring:message code="impresaSelezionareUnoStakeholderValidoDaCollegare" javaScriptEscape="true" text="Selezionare uno stakeholder valido da collegare"/>");
					$('#errorModal').modal('show');
				}
			});
		}
		else
		{
			$('#errorModalTitle').text("<spring:message code="impresaCollegaStakeholder" javaScriptEscape="true" text="Collega stakeholder"/>");
			$('#errorModalMessage').text("<spring:message code="impresaSelezionareUnoStakeholderValidoDaCollegare" javaScriptEscape="true" text="Selezionare uno stakeholder valido da collegare"/>");
			$('#errorModal').modal('show');
		}
	}

	function cancellaStakeholder(stakeholder) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaCollegamentoStakeholder',
			data:  { idImpresa : ${dettaglio.idPlfImpresa}, idStakeholder: stakeholder },
			success : function(result,data) {
				$('#rigaStakeholder'+stakeholder).html('');
				$('#rigaStakeholder'+stakeholder).remove();
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaStakeholderShowModal(stakeholder) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaStakeholder('+stakeholder+');');
		$('#cancellaCollegamentoModal').modal('show');
	}


	function collegaServiziStandard() {
		var serviziStandard = $( "#selectServiziStandard" ).val();
		if (serviziStandard)
		{
			$('#linkServizioStandardModalText').val("");
			$('#linkServizioStandardModalButton').attr('onclick', 'javascript:collegaServiziStandardInternal('+serviziStandard+');');
			$('#linkServizioStandardModal').modal('show');
		}
		else
		{
			$('#errorModalTitle').text("<spring:message code="impresaCollegaServizio" javaScriptEscape="true" text="Collega servizio"/>");
			$('#errorModalMessage').text("<spring:message code="impresaSelezionareUnServizioValidoDaCollegare" javaScriptEscape="true" text="Selezionare un servizio valido da collegare"/>");
			$('#errorModal').modal('show');
		}
	}

	function collegaServiziStandardInternal(serviziStandard) {

		var linkCollegamentoImpresa = $('#linkServizioStandardModalText').val();
		$('#linkServizioStandardModal').modal('hide');

		$.ajax({
			type: "POST",
			url : '/vimp/secure/collegaServiziStandard',
			data:  { idImpresa : ${dettaglio.idPlfImpresa}, idServizi: serviziStandard, linkCollegamentoImpresa: linkCollegamentoImpresa },
			success : function(data) {
				console.log(data);
				var id = data.pk;
				var desc = data.value;
				var link = linkCollegamentoImpresa;

				var row = "<td><a href='javascript:cancellaServiziStandardShowModal("+ id +");'> " +
						"<span class='glyphicon glyphicon-trash'></span></a></td> " +
						"<td>" + desc + "</td>";

				if (link != null)
				{
					row+="<td><a target='_blank' href='http://" + link + "'>" + link + "</a></td>";
				}
				else
				{
					row+="<td></td>";
				}
				row+="<td> <a href='/vimp/servizi/" + id + "'><span class='glyphicon glyphicon-folder-open'></span></a></td>";

				$('#tabellaServiziStandard').append('<tr id="rigaServiziStandard'+id+'" role="row">'+ row +'</tr>');
				refreshCollegamenti(false);


				$("#selectServiziStandard").val('default');
				$("#selectServiziStandard").selectpicker("refresh");

			},
			error: function(error){
				$('#errorModalTitle').text("<spring:message code="impresaCollegaServizi" javaScriptEscape="true" text="Collega servizi"/>");
				$('#errorModalMessage').text("<spring:message code="impresaSelezionareUnServizioValidoDaCollegare" javaScriptEscape="true" text="Selezionare un servizio valido da collegare"/>");
				$('#errorModal').modal('show');
			}
		});
	}

	function cancellaServiziStandard(serviziStandard) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaCollegamentoServiziStandard',
			data:  { idImpresa : ${dettaglio.idPlfImpresa}, idServiziStandard: serviziStandard },
			success : function(result,data) {
				$('#rigaServiziStandard'+serviziStandard).html('');
				$('#rigaServiziStandard'+serviziStandard).remove();
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaServiziStandardShowModal(serviziStandard) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaServiziStandard('+serviziStandard+');');
		$('#cancellaCollegamentoModal').modal('show');
	}


	function allegaFile() {

		var file = $('#fileAllegato')[0].files[0];
		if(file && file.size >= 20971520) {
			$("#uploadModalCloseButton").show();
			$('#uploadModal').modal('hide');
			$('#errorModalTitle').text("<spring:message code="allegaDocumento" javaScriptEscape="true" text="Allega documento"/>");
			$('#errorModalMessage').text("<spring:message code="file_max_size_exceeded" javaScriptEscape="true"/> (20MB)");
			$('#errorModal').modal('show');

		} else {
			var postData = new FormData();
			postData.append('idPlfImpresa',  ${dettaglio.idPlfImpresa});
			postData.append('allegato', $('#fileAllegato')[0].files[0]);
			postData.append('descrizione', $('#allegaFileDialogText').val());


			$("#uploadModalCloseButton").hide();
			$("#allegaFileDialogButton").hide();


			$.ajax({
						type:'POST',
						enctype: 'multipart/form-data',
						url:'/vimp/secure/allegaFileImpresa',
						processData: false,
						contentType: false,
						data : postData,
						success:function(data){

							$("#uploadModalCloseButton").show();
							$('#uploadModal').modal('hide');
							var id = data.pk;
							var nomeFile = data.value;
							var descrizioneFile = data.fields[0];

							var row = "<td><a href='javascript:cancellaAllegatoShowModal("+ id +");'> " +
									"<span class='glyphicon glyphicon-trash'></span></a></td> ";

							row +=  "<td><a href='javascript:editAllegatoDescription("+ id +");'> " +
									"<span class='glyphicon glyphicon-pencil'></span></a></td> " +
									"<td>" + nomeFile + "</td> ";


									
							if (descrizioneFile != null)
							{
								row+= "<td>" + descrizioneFile + "</td> ";
							}
							else
							{
								row+= "<td></td>";
							}
							row+= "<td><a href='/vimp/impresaAllegato/"+ id +"'><span class='glyphicon glyphicon-folder-open'></span></a></td> ";

							$('#tabellaAllegati').append('<tr id="rigaAllegato'+id+'" role="row">'+ row +'</tr>');
							refreshCollegamenti(false);

						},
						error: function(error){
							$("#uploadModalCloseButton").show();
							$('#uploadModal').modal('hide');
							$('#errorModalTitle').text("<spring:message code="allegaDocumento" javaScriptEscape="true" text="Allega documento"/>");
							$('#errorModalMessage').text("<spring:message code="erroreNelTrasferimentoDelDocumento" javaScriptEscape="true" text="Errore nel trasferimento del documento."/>");
							$('#errorModal').modal('show');
						},
						complete: function(){
							$("#allegaFileDialogButton").show();
						}
					}
			);
		}
	}

	function allegaFileDialog() {
		$('#uploadModal').modal('show');
		$('#allegaFileDialogText').val("");
	}

	function cancellaImpresaAllegato(idAllegatoParam) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaImpresaAllegato',
			data:  { idAllegato: idAllegatoParam },
			success : function(result,data) {
				$('#rigaAllegato'+idAllegatoParam).html('');
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaAllegatoShowModal(idAllegato) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaImpresaAllegato('+idAllegato+');');
		$('#cancellaCollegamentoModal').modal('show');
	}

	function collegaImprese() {

		var idImpresa = $( "#selectImpreseCollegate" ).val();
		if (idImpresa)
		{
			$.ajax({
				type: "POST",
				url : '/vimp/secure/collegaImpresa',
				data:  { idStakeholder : ${dettaglio.idPlfImpresa}, idImpresa: idImpresa },
				success : function(data) {
					var id = data.pk;
					var desc = data.value;


					var row = "<td><a href='javascript:cancellaImpresaShowModal("+ id +");'> " +
							"<span class='glyphicon glyphicon-trash'></span></a></td> " +
							"<td>" + desc + "</td> <td> <a href='/vimp/impresa/" + id + "'><span class='glyphicon glyphicon-folder-open'></span></a></td>"

					$('#tabellaImprese').append('<tr id="rigaImpresa'+id+'" role="row">'+ row +'</tr>');
					refreshCollegamenti(false);

					$("#selectImpreseCollegate").val('default');
					$("#selectImpreseCollegate").selectpicker("refresh");

				},
				error: function(error){
					$('#errorModalTitle').text("<spring:message code="impresaCollegaA" javaScriptEscape="true" text="Collega impresa"/>");
					$('#errorModalMessage').text("<spring:message code="impresaSelezionaImpresaValidaDaCollegare" javaScriptEscape="true" text="Selezionare una impresa valida da collegare"/>");
					$('#errorModal').modal('show');
				}
			});
		}
		else
		{
			$('#errorModalTitle').text("<spring:message code="impresaCollegaA" javaScriptEscape="true" text="Collega impresa"/>");
			$('#errorModalMessage').text("<spring:message code="impresaSelezionaImpresaValidaDaCollegare" javaScriptEscape="true" text="Selezionare una impresa valida da collegare"/>");
			$('#errorModal').modal('show');
		}
	}


	function cancellaImpresaCollegata(impresa) {
		$.ajax({
			type: "POST",
			url : '/vimp/secure/cancellaCollegamentoImpresa',
			data:  { idStakeholder : ${dettaglio.idPlfImpresa}, idImpresa: impresa },
			success : function(result,data) {
				$('#rigaImpresa'+impresa).html('');
				$('#rigaImpresa'+impresa).remove();
				refreshCollegamenti(false);
				$('#cancellaCollegamentoModal').modal('hide');
			},
			error: function(response,data){
				$('#cancellaCollegamentoModal').modal('hide');
				elaboraErrore(response);
			}
		});
	}

	function cancellaImpresaShowModal(impresa) {
		$('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaImpresaCollegata('+impresa+');');
		$('#cancellaCollegamentoModal').modal('show');
	}



	function updateComuni(selectedProvincia) {
		if (selectedProvincia > 0) {

			$('#coordX').val('');
			$('#coordY').val('');
			$('#codCap').val('');
			$('#descIndirizzo').val('');
			$('#numeroCivico').val('');
			removePointToMap();
			
			$("#selectComune option").remove();

			$.getJSON("/vimp/elencoComuni.json", {
				provincia : selectedProvincia
			}, function(data) {

				$("#selectComune").append($("<option></option>").text('<spring:message code="common.selezionaIlComune" javaScriptEscape="true" text="Seleziona il comune"/>').val('').addClass('bs-title-option'));

				$.each(data, function(index, item) {
					$("#selectComune").append(
							$("<option></option>").text(item.text).val(
									item.value));
				});

				$('#selectComune').selectpicker('refresh');

				$('#indirizzoText').show();
				$('#indirizzoTopo').hide();
				$('#coordX').prop("readonly", false);
				$('#coordY').prop("readonly", false);
				$('#codCap').prop("readonly", false);
			});
		}
	}

	function resetForm(){
		$('#resetFormConfirm').modal();
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

	function editAllegatoDescription(idImpresaAllegati) {
		$('#idAllegatoEdit').val(idImpresaAllegati);

		var descrizione = $('#descrizioneAllegato_' + idImpresaAllegati).html();
		$('#allegatoDescriptionEdit').val(descrizione);
		$('#editAllegatoModal').modal('show');

	}

	function saveAllegatoDescription() {
		var id = $('#idAllegatoEdit').val();
		var descr = $('#allegatoDescriptionEdit').val();

		var data = {};
		data['idImpresaAllegati'] = id;
		data['descrizione'] = descr;

		$.ajax({
			type: "POST",
			url : '/vimp/secure/updateImpresaAllegatoDescription',
			data:  data,
			success : function(data) {
				$('#descrizioneAllegato_' + data.idImpresaAllegati).html(data.descrizione);
			},
			error: function (response) {
				$('#errorModal').modal('show');
				$('#errorModalMessage').html(elaboraErrore(response));
			},
			complete: function() {
				$('#editAllegatoModal').modal('hide');
			}
		});
	}

	function reloadPage(){
		var baseUrl = window.location.href.split('/vimp')[0];
		originalUrl = baseUrl + '/vimp' + '${refreshRelativeUrl}';
		window.location.replace(originalUrl); // Usare questo se ${refreshRelativeUrl} arriva dall'handler
		//location.reload(true); // Usare questo nl caso non si voglia usare ${refreshRelativeUrl}
	}


	


</script>

<!-- END DETTAGLIO IMPRESA-->
<!-- =========================================================================================== -->