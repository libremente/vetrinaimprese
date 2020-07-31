<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO PROGETTO RICERCA-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message var="selectTheProjectTypeTitleAttr" code="form.nuovo.progetto.selectTheProjectTypeTitleAttr"
                text="Seleziona il tipo progetto"/>
<spring:message var="selectTheProjectStatusTitleAttr" code="form.nuovo.progetto.selectTheProjectStatusTitleAttr"
                text="Seleziona lo stato del progetto"/>
<spring:message var="selectTipologyTitleAttr" code="form.nuovo.progetto.selectTipologyTitleAttr"
                text="Seleziona la tipologia"/>
<spring:message var="selectTheAreaTitleAttr" code="form.nuovo.progetto.selectTheAreaTitleAttr"
                text="Seleziona il settore"/>
<spring:message var="selectTheCompanyTitleAttr" code="form.dettaglio.progetto.selectTheCompanyTitleAttr"
                text="Seleziona l' impresa"/>
<spring:message var="selectTheCountryTitleAttr" code="form.dettaglio.progetto.selectTheCountryTitleAttr"
                text="Seleziona la nazione"/>
<spring:message var="selectTheRegionTitleAttr" code="form.dettaglio.progetto.selectTheRegionTitleAttr"
                text="Seleziona la regione"/>
<spring:message var="selectTheProvinceTitleAttr" code="form.dettaglio.impresa.selectTheProvinceTitleAttr"
                text="Seleziona la provincia"/>
<spring:message var="selectTheCityTitleAttr" code="form.dettaglio.impresa.selectTheCityTitleAttr" text="Seleziona il comune"/>
<spring:message var="ragSocTitleAttr" code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/>
<spring:message var="fiscalCodeTitleAttr" code="common_texts.fiscal_code" text="Codice fiscale"/>
<spring:message var="partitaIvaTitleAttr" code="form.richiesta.accreditamento.iva" text="Partita IVA"/>
<spring:message var="countryTitleAttr" code="common_texts.country" text="Nazione"/>
<spring:message var="cityTitleAttr" code="common_texts.cityTitleAttr" text="Comune"/>
<spring:message var="collaborationStartDateTitleAttr" code="form.dettaglio.progetto.collaboration_start_date"
                text="Data inizio collaborazione"/>
<spring:message var="collaborationEndDateTitleAttr" code="form.dettaglio.progetto.collaboration_end_date"
                text="Data fine collaborazione"/>
<spring:message var="nameTitleAttr" code="name" text="Nome"/>
<spring:message var="descriptionTitleAttr" code="description" text="Descrizione"/>
<spring:message var="selectTheCompanyLinkedToTheNewsTitleAttr" code="form.nuova.news.impresa.selectTheCompanyLinkedToTheNewsTitleAttr" text="Seleziona l'impresa collegato alla news"/>
<spring:message var="selectTheCompanyLinkedToTheProjectTitleAttr" code="form.nuova.news.impresa.selectTheCompanyLinkedToTheProjectTitleAttr" text="Seleziona l'impresa collegato al progetto"/>


<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">
                    <b><spring:message code="form.dettaglio.progetto.title"
                                       text="PROGETTO PRODOTTO, TECNOLOGIA"/></b> ${utils.truncateString(dettaglio.progettiProdottiTranslation.nomeProgettoProdotto,100)}
                </h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->

<!-- property area -->

<form:form id="frmDettaglio" method="POST"
           action="/vimp/secure/salvaProgetto" role="form"
           modelAttribute="dettaglio" class="safe-reload">


    <form:hidden id="idPlfProgettiProdotti" path="idPlfProgettiProdotti"/>
    <form:hidden id="progettiProdottiTranslation.idPlfProgettiProdotti"
                 path="progettiProdottiTranslation.idPlfProgettiProdotti"/>

    <input id="imageData" name="imageData" type="hidden">
    <form:hidden id="progettoOrigine" path="progettoOrigine"/>

    <!-- CAMPI MANCANTI -->
    <form:hidden id="descFonte" path="progettiProdottiTranslation.descFonte"/>

	


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
                        aria-hidden="true">&times;
                </button>
                <p>${successMessage}</p>
            </div>
        </c:if>

        <c:if test="${!empty warningMessage}">
            <div class="alert alert-warning fade in">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;
                </button>
                <p>
                    <strong><spring:message code="warning" text="Attenzione!"/></strong> ${warningMessage}
                </p>
            </div>
        </c:if>

        <c:if test="${!empty errorNameDuplicate}">
            <div class="alert alert-danger fade in">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;
                </button>
                <p>
                    <strong><spring:message code="error" text="Errore"/></strong> <spring:message code="erroreDuplicazioneProgettoRicerca"/> ${errorNameDuplicate}
                </p>
            </div>
        </c:if>

        <c:if test="${!empty errorMessage}">
            <div class="alert alert-danger fade in">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;
                </button>
                <p>
                    <strong><spring:message code="error" text="Errore"/></strong> ${errorMessage}
                </p>
            </div>
        </c:if>

        <div class="row">
            <div class="col-sm-12 profiel-container">
                <div class="clear">
                    <div class="col-sm-12">

                            <%-- Colonna immagine --%>
                        <div class="col-sm-6">
                            <div class="picture-container">
                                <div class="picture">
                                    <c:choose>
                                        <c:when test="${!empty dettaglio.imageData}">
                                            <img src="data:image/jpg;base64,${dettaglio.imageData}"
                                                 class="picture-src" id="wizardPicturePreview" title=""/>
                                            <c:if test="${modifica}">
                                                <input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${dettaglio.plfTTipoProgettiProdotti.id eq 1}">
                                                    <img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_progetti.jpg"
                                                         class="picture-src" id="wizardPicturePreview" title="">
                                                </c:when>
                                                <c:when test="${dettaglio.plfTTipoProgettiProdotti.id eq 2}">
                                                    <img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_prodotto.jpg"
                                                         class="picture-src" id="wizardPicturePreview" title="">
                                                </c:when>
                                                <c:when test="${dettaglio.plfTTipoProgettiProdotti.id eq 3}">
                                                    <img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_tecnologie.jpg"
                                                         class="picture-src" id="wizardPicturePreview" title="">
                                                </c:when>
                                            </c:choose>
                                            <c:if test="${modifica}">
                                                <input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>


                        <div class="col-sm-6" style="padding-top: 30px">

                                <%--tipo progetto--%>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.progetto.type" text="Tipo"/> :<b>${dettaglio.plfTTipoProgettiProdotti.descrizione}</b></label>
                            </div>

                                <%--Nome progetto--%>
                            <div class="form-group" ${!modifica && empty dettaglio.progettiProdottiTranslation.nomeProgettoProdotto ? 'hidden' : ''}>
                                <label><spring:message code="form.dettaglio.progetto.project_name"
                                                       text="Nome progetto"/> : <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="Richiesto"/>)</small>
                                </c:if>
                                </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input name="progettiProdottiTranslation.nomeProgettoProdotto" type="text"
                                               class="form-control" placeholder=""
                                               value="${dettaglio.progettiProdottiTranslation.nomeProgettoProdotto}"
                                               path="nomeProgettoProdotto" maxlength="400">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.progettiProdottiTranslation.nomeProgettoProdotto}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </>

                                <%--Descrizione progetto (DISABILITATO)--%>
                            <div class="form-group" hidden>
                                <label name="descProgettoLabel" id="descProgettoLabel"><spring:message
                                        code="description"/> :
                                    <c:if test="${modifica}">
                                        <small>(<spring:message code="required" text="Richiesto"/>)</small>
                                    </c:if>
                                </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input name="progettiProdottiTranslation.descProgetto" id="descProgetto"
                                               type="text" class="form-control"
                                               placeholder=""
                                               value="${dettaglio.progettiProdottiTranslation.descProgetto}"
                                               path="descProgetto" maxlength="3990">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.progettiProdottiTranslation.descProgetto}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                                <%-- Tema --%>
                            <div class="form-group" ${!modifica && empty dettaglio.progettiProdottiTranslation.descrizione ? 'hidden' : ''}>
                                <label id="descrizioneLabel" name="descrizioneLabel"><spring:message
                                        code="form.dettaglio.progetto.topic"/> :
                                    <c:if test="${modifica}"><small>(<spring:message code="required"/>)</small></c:if>
                                </label>
                                <c:choose>
                                    <c:when test="${modifica}">
											<textarea rows="4" name="progettiProdottiTranslation.descrizione"
                                                      class="form-control"
                                                      maxlength="3990">${dettaglio.progettiProdottiTranslation.descrizione}</textarea>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.progettiProdottiTranslation.descrizione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>



                        </div>
                    </div>

                        <%--Progetto origine (DISABILITATO)--%>
                    <div class="form-group" hidden>
                        <div class="checkbox">
                            <label class="center homecheckbox"> <input
                                    id="checkboxProgettoOrigine" name="checkboxProgettoOrigine"
                                    type="checkbox"/> <strong>&nbsp;<spring:message
                                    code="form.dettaglio.progetto.origin_project"/><small>&nbsp;(<spring:message
                                    code="form.dettaglio.progetto.origin_project.info"/>...) </small></strong>
                            </label>
                        </div>
                    </div>

                        <%--Impresa (DISABILITATO)--%>
                    <div class="form-group" hidden>
                        <label style="margin-top: 12px;"><spring:message code="common_texts.enterprise" text="Impresa"/> :
                            <b>${dettaglio.plfImpresa.impresaTranslation.descImpresa}</b></label>&nbsp;
                        <a href="/vimp/impresa/${dettaglio.plfImpresa.idPlfImpresa}"><span
                                class="glyphicon glyphicon-folder-open"></span></a>
                    </div>

                        <%--Tipo (DISABILITATO)--%>
                    <div class="form-group" hidden>
                        <label><spring:message code="form.dettaglio.progetto.type" text="Tipo"/> :
                            <c:if test="${modifica}"><small>(<spring:message code="required" text="richiesto"/>)</small>
                            </c:if>
                        </label>
                        <c:choose>
                            <c:when test="${modifica}">
                                <form:select id="selectTipo" name="selectTipo"
                                             title="${selectTheProjectTypeTitleAttr}" data-live-search="true"
                                             data-live-search-style="contains"
                                             path="plfTTipoProgettiProdotti.id"
                                             cssClass="selectpicker">
                                    <form:options items="${tipoProgettoList}"/>
                                </form:select>
                            </c:when>
                            <c:otherwise>
                                <br>
                                <label><b>${dettaglio.plfTTipoProgettiProdotti.descrizione}</b></label>
                            </c:otherwise>
                        </c:choose>
                    </div>



                    <div class="col-md-12">
                        &nbsp;
                    </div>


                    <div class="col-md-12">

                            <%-- Impresa --%>
                        <div class="col-sm-6">
                            <div class="form-group">
                            	<c:choose>
									<c:when test="${modifica}">
										<label><spring:message code="common_texts.enterprise" text="Impresa"/> :</label>
										<form:select id="selectImpresa"
													 title="${selectTheCompanyLinkedToTheProjectTitleAttr}" data-live-search="true"
													 data-live-search-style="contains"
													 path="plfImpresa.idPlfImpresa" cssClass="selectpicker">
											<form:options items="${impresaList}" />
										</form:select>
									</c:when>
									<c:otherwise>
                                		<label><spring:message code="common_texts.enterprise" text="Impresa"/> : <b>${dettaglio.plfImpresa.descImpresa}</b></label>
                                	</c:otherwise>
								</c:choose>
                            </div>
                        </div>

                            <%-- vuoto --%>
                        <div class="col-sm-6">
                            <div class="form-group">
                                &nbsp;
                            </div>
                        </div>
                    </div>


                    <div class="safe-col">
                            <%-- Stato progetto--%>
                        <div class="col-sm-6" ${(!modifica && empty dettaglio.plfTStatoProgetto.descrizione) || dettaglio.plfTTipoProgettiProdotti.id  != 1 ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.progetto.project_status"
                                                       text="Stato progetto"/> :</label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <form:select id="selectStatoProgetto"
                                                     title="${selectTheProjectStatusTitleAttr}"
                                                     data-live-search="true" data-live-search-style="contains"
                                                     path="plfTStatoProgetto.id"
                                                     cssClass="selectpicker">
                                            <form:options items="${statoProgettoList}"/>
                                        </form:select>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.plfTStatoProgetto.descrizione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                            <%-- Tipologia --%>
                        <div class="col-sm-6" ${(!modifica && empty dettaglio.plfTTipologiaProgetto.descrizione) || dettaglio.plfTTipoProgettiProdotti.id != 1 ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.progetto.tipology" text="Tipologia"/>
                                    :</label>
                                <c:if test="${modifica}"><small>(<spring:message code="required"/>)</small></c:if>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <form:select id="selectTipologia"
                                                     title="${selectTipologyTitleAttr}" data-live-search="true"
                                                     data-live-search-style="contains"
                                                     path="plfTTipologiaProgetto.id"
                                                     cssClass="selectpicker">
                                            <form:options items="${tipologiaProgettoList}"/>
                                        </form:select>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.plfTTipologiaProgetto.descrizione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="safe-col">
                            <%-- Settore --%>
                        <div class="col-sm-6" ${!modifica and empty dettaglio.settoriProgettiProdotti and empty dettaglio.settoriTecnologie ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.progetto.area" text="Settore"/> :
                                    <c:if test="${modifica}"><small>(<spring:message code="required"/>)</small></c:if></label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <c:set var="isTecnologia" value="${dettaglio.plfTTipoProgettiProdotti.id eq 3}"></c:set>
                                        <div ${isTecnologia? '' : 'hidden'}>
                                            <form:select id="selectSettoreTecnologia" title="${selectTheAreaTitleAttr}" multiple="true"
                                                         data-live-search="true" data-live-search-style="contains"
                                                         path="elencoSettoreTecnologie"
                                                         cssClass="selectpicker">
                                                <form:options items="${listaSettoriTecnologie}" itemValue="id" itemLabel="descrizione"/>
                                            </form:select>
                                        </div>
                                        <div ${isTecnologia? 'hidden' : ''}>
                                            <form:select id="selectSettoreProgetto" title="${selectTheAreaTitleAttr}" multiple="true"
                                                         data-live-search="true" data-live-search-style="contains"
                                                         path="elencoSettoreProgettiProdotti"
                                                         cssClass="selectpicker">
                                                <form:options items="${listaSettoriProgetto}"  itemValue="id" itemLabel="descrizione"/>
                                            </form:select>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <c:forEach items="${dettaglio.settoriProgettiProdotti}" var="settore">
                                            <label><b>${settore.descrizione}</b></label>&nbsp;&nbsp;
                                        </c:forEach>
                                        <c:forEach items="${dettaglio.settoriTecnologie}" var="settore">
                                            <label><b>${settore.descrizione}</b></label>&nbsp;&nbsp;
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                            <%-- Requisiti (DISABILITATO) --%>
                        <div class="form-group" hidden>
                            <label><spring:message code="form.dettaglio.progetto.requirements" text="Requisiti"/>
                                :</label>
                            <c:choose>
                                <c:when test="${modifica}">
                                                <textarea rows="4" name="progettiProdottiTranslation.descRequisiti"
                                                          class="form-control"
                                                          maxlength="3990">${dettaglio.progettiProdottiTranslation.descRequisiti}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <label><b>${dettaglio.progettiProdottiTranslation.descRequisiti}</b></label>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>

                    <div class="safe-col">
                            <%-- Finalità (DISABILITATO) --%>
                        <div class="form-group" hidden>
                            <label><spring:message code="form.dettaglio.progetto.finality" text="Finalit&agrave;"/>
                                :</label>
                            <c:choose>
                                <c:when test="${modifica}">
                                                <textarea rows="4" name="progettiProdottiTranslation.descFinalita"
                                                          class="form-control"
                                                          maxlength="3990">${dettaglio.progettiProdottiTranslation.descFinalita}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <label><b>${dettaglio.progettiProdottiTranslation.descFinalita}</b></label>
                                </c:otherwise>
                            </c:choose>
                        </div>

                            <%-- Risultati (DISABILITATO) --%>
                        <div class="form-group" hidden>
                            <label><spring:message code="results" text="Risultati"/> :</label>
                            <c:choose>
                                <c:when test="${modifica}">
                                        <textarea name="progettiProdottiTranslation.descRisultati"
                                                  class="form-control"
                                                  maxlength="3990">${dettaglio.progettiProdottiTranslation.descRisultati}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <label><b>${dettaglio.progettiProdottiTranslation.descRisultati}</b></label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="safe-col">
                            <%-- Valore economico (DISABILITATO) --%>
                        <div class="col-sm-6" hidden>
                            <div class="form-group" hidden>
                                <label><spring:message code="form.dettaglio.progetto.economic_value"
                                                       text="Valore economico"/> : </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input name="numValoreEconomico" id="numValoreEconomico"
                                               type="text" class="form-control" placeholder=""
                                               path="numValoreEconomico"
                                               value="<fmt:formatNumber value="${dettaglio.numValoreEconomico}" pattern="###,###,###,###,##0.00" />"
                                               maxlength="18">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b><fmt:formatNumber
                                                value="${dettaglio.numValoreEconomico}"
                                                pattern="###,###,###,###,##0.00"/> &euro;</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                            <%-- Durata --%>
                        <div class="col-sm-6" ${(!modifica && empty dettaglio.numDurata) || dettaglio.plfTTipoProgettiProdotti.id != 1 ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="duration" text="Durata"/> : <small> (<spring:message
                                        code="days" text="giorni"/>)</small></label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input id="numDurata" name="numDurata" type="number" class="form-control"
                                               placeholder="" value="${dettaglio.numDurata}" min="0" max="99999999999999999">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.numDurata}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="row">&nbsp;</div>

                    <div class="safe-col">
                            <%-- Data scadenza --%>
                        <div class="col-sm-6" ${(!modifica && empty dettaglio.dataScadenza) || dettaglio.plfTTipoProgettiProdotti.id != 1 ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.progetto.expiry_date"
                                                       text="Data scadenza"/> :</label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <fmt:formatDate value="${dettaglio.dataScadenza}" type="date"
                                                        pattern="dd/MM/yyyy" var="dataScadenzaFormatted"/>
                                        <div class="input-group date" data-provide="datepicker"
                                             data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
                                            <input name="dataScadenza" type="text" class="form-control"
                                                   value="${dataScadenzaFormatted}" path="dataScadenza" autocomplete="off">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${dettaglio.dataScadenza}" type="date"
                                                        pattern="dd/MM/yyyy" var="dataScadenzaFormatted"/>
                                        <br>
                                        <label><b>${dataScadenzaFormatted}</b></label>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                            <%-- Sito WEB --%>
                        <div class="col-sm-6" ${!modifica && empty dettaglio.descSito ? 'hidden' : ''}>
                            <div class="form-group">
                                <c:choose>
                                    <c:when test="${modifica}">
                                    	<label><spring:message code="web_site_edit" text="Sito WEB"/> :</label>
                                        <input name="descSito" type="text" class="form-control"
                                               placeholder="" value="${dettaglio.descSito}" path="descSito"
                                               maxlength="1000">
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${!empty dettaglio.descSito}">
                                        	<label><spring:message code="web_site" text="Sito WEB"/> :</label>
                                            <br>
                                            
                                            <c:choose>
                                    			<c:when test="${utils.checkLinkUrl(dettaglio.descSito)}">
                                    				<a class="wow fadeInUp animated" target="_blank" href="${utils.anchor(dettaglio.descSito)}" data-wow-delay="0.5s">${dettaglio.descSito}</a>
                                    			</c:when>
                                    			<c:otherwise>
                                    				<label><b>${dettaglio.descSito}</b></label>
                                    			</c:otherwise>
                                    		</c:choose>
                                            
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                    </div>

                    <div class="row">&nbsp;</div>

                    <div class="safe-col">
                            <%-- Obiettivi --%>
                        <div class="col-sm-6" ${(!modifica && empty dettaglio.progettiProdottiTranslation.obiettivi) || dettaglio.plfTTipoProgettiProdotti.id != 1 ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.progetto.targets"/>
                                    : </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                            <textarea rows="4" id="obiettivi"
                                                      name="progettiProdottiTranslation.obiettivi"
                                                      class="form-control"
                                                      maxlength="1000">${dettaglio.progettiProdottiTranslation.obiettivi}</textarea>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.progettiProdottiTranslation.obiettivi}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                            <%-- Contatti --%>
                        <div class="col-sm-6" ${(!modifica && empty dettaglio.progettiProdottiTranslation.descContatti) || dettaglio.plfTTipoProgettiProdotti.id != 1 ? 'hidden' : ''}>
                            <div class="form-group">
                                <label><spring:message code="form.contatti.contacts_title"/> :</label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <textarea rows="4" name="progettiProdottiTranslation.descContatti"
                                                  class="form-control"
                                                  maxlength="1000">${dettaglio.progettiProdottiTranslation.descContatti}</textarea>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.progettiProdottiTranslation.descContatti}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                        <%-- Caratteristiche tecniche --%>
                    <div class="col-md-12" ${!modifica && empty dettaglio.progettiProdottiTranslation.caratteristicheTecniche ? 'hidden' : ''}>
                        <div class="form-group">
                            <label id="caratteristicheTecnicheLabel"
                                   name="caratteristicheTecnicheLabel"><spring:message code="form.dettaglio.progetto.caratteristiche_tecniche"/></label>
                            <c:choose>
                                <c:when test="${modifica}">
                                    <textarea name="progettiProdottiTranslation.caratteristicheTecniche" id="caratteristicheTecniche"
                                              class="form-control" maxlength="3990">${dettaglio.progettiProdottiTranslation.caratteristicheTecniche}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <label><b>${dettaglio.progettiProdottiTranslation.caratteristicheTecniche}</b></label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="col-md-12 col-xs-12">

                            <%-- Allegati --%>
                        <c:choose>
                            <c:when test="${!modifica && dettaglio.allegati.size()<=0}">
                            </c:when>
                            <c:otherwise>
                                <div class="row">&nbsp;</div>
                                <div class="col-md-6 col-xs-6 register-blocks" style="text-align: center;">
                                    <h2><spring:message code="attachments"/></h2>
                                </div>
                                <div class="row">&nbsp;</div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class="table-responsive">
                                            <table id="tabellaAllegati"
                                                   class="table table-condensed bordered1 tablesorter">
                                                <thead>
                                                <tr>
                                                    <c:if test="${modifica}">
                                                        <th>&nbsp;</th>
                                                        <th>&nbsp;</th>
                                                    </c:if>
                                                    <th data-toggle="tooltip" data-placement="top"
                                                        title="${nameTitleAttr}"><spring:message code="name"
                                                                                                 text="Nome"/></th>
                                                    <th data-toggle="tooltip" data-placement="top"
                                                        title="${descriptionTitleAttr}"><spring:message
                                                            code="description" text="Descrizione"/></th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${dettaglio.allegati}" var="allegato">
                                                    <tr id='rigaAllegato${allegato.idProgettiProdottiAllegati}'>
                                                        <c:if test="${modifica}">
                                                            <td>
                                                                <a href="javascript:cancellaAllegatoShowModal(${allegato.idProgettiProdottiAllegati});">
                                                                    <span class="glyphicon glyphicon-trash"></span>
                                                                </a>
                                                            </td>
                                                            <td>
                                                                <a href="javascript:editAllegatoDescription('${allegato.progettiProdottiAllegatiTranslation.idProgettiProdottiAllegati}');">
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </a>
                                                            </td>
                                                        </c:if>
                                                        <td>${allegato.nome}</td>
                                                        <td id="descrizioneAllegato_${allegato.progettiProdottiAllegatiTranslation.idProgettiProdottiAllegati}">${allegato.progettiProdottiAllegatiTranslation.descrizione}</td>
                                                        <td><a target="_blank"
                                                               href="/vimp/progettoProdottoAllegato/${allegato.idProgettiProdottiAllegati}">
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
                                        <div class="form-group">
                                            <div class="pull-right">
                                                <a id="allegaFileButton"
                                                   class='btn btn-finish btn-primary'
                                                   onClick="javascript:allegaFileDialog();"><spring:message
                                                        code="attach_file" text="Allega file"/></a>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </c:otherwise>
                        </c:choose>

                            <%-- Pubblicato --%>
                        <c:choose>
                            <c:when test="${ utente.isBackoffice()}">
                                <div class="col-sm-6">
                                    <div class="checkbox form-grid-checkbox">
                                        <label>
                                            <input id="pubblicato" name="pubblicato" type="checkbox" disabled/>
                                            <strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
                                        </label>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-sm-6" ${!modifica ? 'hidden' : ''}>
                                    <div class="checkbox form-grid-checkbox">
                                        <label>
                                            <input id="pubblicato" name="pubblicato" type="checkbox"/>
                                            <strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
                                        </label>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>


                        <%-- Tag --%>
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
                        <div class="col-sm-12" ${(!modifica && fn:length(tags) < 1) ? 'hidden' : ''}>
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <input name="progettiProdottiTranslation.descTag"
                                           value="${dettaglio.progettiProdottiTranslation.descTag}" id="selectedTags"
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
                        </div>
                    </div>
                    --%>
                        <%--Collaborazioni--%>
                    <c:if test="${modifica || dettaglio.plfCollaborazionis.size()>0}">

                        <div class="col-md-12 col-xs-12 register-blocks" style="text-align: center; display: none;">
                            <h2><spring:message code="form.dettaglio.progetto.collaborations"
                                                text="Collaborazioni"/></h2>
                        </div>

                        <c:if test="${modifica}">
                            <div class="col-sm-12" style="display: none;">
                                <div class="panel panel-default no-space">

                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a class="accordion-toggle" data-toggle="collapse"
                                               data-parent="#accordion" href="#operatoriPanel"><spring:message
                                                    code="form.dettaglio.progetto.add_collaborations"
                                                    text="Aggiungi collaborazioni"/></a>
                                        </h4>
                                    </div>

                                    <div id="operatoriPanel" class="panel-collapse collapse">

                                        <div class="panel-body">
                                            <div class="col-sm-12">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message
                                                                code="form.dettaglio.progetto.select_enterprise_or_fill_below_fields"
                                                                text="Selezionare un'impresa oppure compilare le informazioni sottostanti"/>
                                                            :</label>
                                                        <form:select id="selectCollaborazioneImpresa"
                                                                     name="selectCollaborazioneImpresa"
                                                                     title="${selectTheCompanyTitleAttr}"
                                                                     data-live-search="true"
                                                                     data-live-search-style="contains"
                                                                     path=""
                                                                     cssClass="selectpicker">
                                                            <form:options items="${impresaList}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    &nbsp;
                                                </div>
                                            </div>


                                            <div class="col-sm-12" id="collaborazioneRagioneSocialeGroup"
                                                 name="collaborazioneRagioneSocialeGroup">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message
                                                                code="form.dettaglio.accreditamento.rag_soc"
                                                                text="Ragione sociale"/> :</label>
                                                        <input id="collaborazioneRagioneSociale"
                                                               name="collaborazioneRagioneSociale" type="text"
                                                               class="form-control" placeholder="" value=""
                                                               maxlength="400">
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    &nbsp;
                                                </div>
                                            </div>
                                            <div class="col-sm-12" id="collaborazionePIvaCodFiscaleGroup"
                                                 name="collaborazionePIvaCodFiscaleGroup">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="form.richiesta.accreditamento.iva"
                                                                               text="Partita IVA"/> :</label>
                                                        <input id="collaborazionePartitaIva"
                                                               name="collaborazionePartitaIva" type="text"
                                                               class="form-control" placeholder="" value=""
                                                               maxlength="16">
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message
                                                                code="form.richiesta.accreditamento.codice_fiscale"
                                                                text="Codice fiscale"/> :</label>
                                                        <input id="collaborazioneCodiceFiscale"
                                                               name="collaborazioneCodiceFiscale" type="text"
                                                               class="form-control" placeholder="" value=""
                                                               maxlength="16">
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-sm-12" id="selectCollaborazioneNazioneGroup"
                                                 name="selectCollaborazioneNazioneGroup">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="common_texts.country"
                                                                               text="Nazione"/> :</label>
                                                        <form:select id="selectCollaborazioneNazione"
                                                                     name="selectCollaborazioneNazione"
                                                                     title="${selectTheCountryTitleAttr}"
                                                                     data-live-search="true"
                                                                     data-live-search-style="contains"
                                                                     path=""
                                                                     cssClass="selectpicker"
                                                                     onchange="javascript:updateRegioni(this.options[selectedIndex].value);">
                                                            <form:options items="${nazioneList}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group" id="selectCollaborazioneRegioneForm"
                                                         name="selectCollaborazioneRegioneForm">
                                                        <label><spring:message code="common_texts.region"
                                                                               text="Regione"/> :</label>
                                                        <form:select id="selectCollaborazioneRegione"
                                                                     name="selectCollaborazioneRegione"
                                                                     title="${selectTheRegionTitleAttr}"
                                                                     data-live-search="true"
                                                                     data-live-search-style="contains"
                                                                     path=""
                                                                     cssClass="selectpicker"
                                                                     onchange="javascript:updateProvincie(this.options[selectedIndex].value);">
                                                            <form:options items="${regioneList}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <div class="col-sm-6">
                                                    <div class="form-group" id="selectCollaborazioneProvinciaForm"
                                                         name="selectCollaborazioneProvinciaForm">
                                                        <label><spring:message code="common_texts.province"
                                                                               text="Provincia"/> :</label>
                                                        <form:select id="selectCollaborazioneProvincia"
                                                                     name="selectCollaborazioneProvincia"
                                                                     title="${selectTheProvinceTitleAttr}"
                                                                     data-live-search="true"
                                                                     data-live-search-style="contains"
                                                                     path=""
                                                                     cssClass="selectpicker"
                                                                     onchange="javascript:updateComuni(this.options[selectedIndex].value);">
                                                            <form:options items="${provinciaList}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group" id="selectCollaborazioneComuneForm"
                                                         name="selectCollaborazioneComuneForm">
                                                        <label><spring:message code="common_texts.city" text="Comune"/>
                                                            :</label>
                                                        <form:select id="selectCollaborazioneComune"
                                                                     name="selectCollaborazioneComune"
                                                                     title="${selectTheCityTitleAttr}"
                                                                     data-live-search="true"
                                                                     data-live-search-style="contains"
                                                                     path=""
                                                                     cssClass="selectpicker">
                                                            <form:options items="${comuneList}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message
                                                                code="form.dettaglio.progetto.collaboration_start_date"
                                                                text="Data inizio collaborazione"/> :</label>
                                                        <fmt:formatDate value="${dataInizioCollaborazione}" type="date"
                                                                        pattern="dd/MM/yyyy"
                                                                        var="collaborazioneDataInizioFormatted"/>
                                                        <div class="input-group date" data-provide="datepicker"
                                                             data-date-format="dd/mm/yyyy"
                                                             data-date-language="${env_locale}">
                                                            <input id="collaborazioneDataInizio"
                                                                   name="collaborazioneDataInizio" type="text"
                                                                   class="form-control"
                                                                   value="${collaborazioneDataInizioFormatted}" autocomplete="off">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message
                                                                code="form.dettaglio.progetto.collaboration_end_date"
                                                                text="Data fine collaborazione"/> :</label>
                                                        <fmt:formatDate value="${dataFineCollaborazione}" type="date"
                                                                        pattern="dd/MM/yyyy"
                                                                        var="collaborazioneDataFineFormatted"/>
                                                        <div class="input-group date" data-provide="datepicker"
                                                             data-date-format="dd/mm/yyyy"
                                                             data-date-language="${env_locale}">
                                                            <input id="collaborazioneDataFine"
                                                                   name="collaborazioneDataFine" type="text"
                                                                   class="form-control"
                                                                   value="${collaborazioneDataFineFormatted}" autocomplete="off">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <!--/panel-body-->

                                        <div class="panel-footer text-right">
                                            <a href="#" class="btn btn-default"
                                               onClick="javascript:collaborazioni();"><spring:message code="add"
                                                                                                      text="Aggiungi"/></a>
                                        </div>

                                    </div>
                                    <!--/ pannello-2-->
                                </div>
                            </div>
                        </c:if>
                        <!--/ panel-default-->

                        <div class="col-sm-12" style="display: none;">
                            <div class="table-responsive">
                                <table id="tabellaCollaborazioni"
                                       class="table table-condensed bordered1 tablesorter">
                                    <thead>
                                    <tr>
                                        <c:if test="${modifica}">
                                            <th></th>
                                        </c:if>
                                        <th data-toggle="tooltip" data-placement="top" title="${ragSocTitleAttr}">
                                            <spring:message code="form.dettaglio.accreditamento.rag_soc"
                                                            text="Ragione sociale"/></th>
                                        <th data-toggle="tooltip" data-placement="top" title="${fiscalCodeTitleAttr}">
                                            <spring:message code="form.richiesta.accreditamento.codice_fiscale"
                                                            text="Codice fiscale"/></th>
                                        <th data-toggle="tooltip" data-placement="top" title="${partitaIvaTitleAttr}">
                                            <spring:message code="form.richiesta.accreditamento.iva"
                                                            text="Partita IVA"/></th>
                                        <th data-toggle="tooltip" data-placement="top" title="${countryTitleAttr}">
                                            <spring:message code="common_texts.country" text="Nazione"/></th>
                                        <th data-toggle="tooltip" data-placement="top" title="${cityTitleAttr}">
                                            <spring:message code="common_texts.city" text="Comune"/></th>
                                        <th data-toggle="tooltip" data-placement="top"
                                            title="${collaborationStartDateTitleAttr}"><spring:message code="start_date"
                                                                                                       text="Data inizio"/></th>
                                        <th data-toggle="tooltip" data-placement="top"
                                            title="${collaborationEndDateTitleAttr}"><spring:message code="end_date"
                                                                                                     text="Data fine"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${dettaglio.plfCollaborazionis}" var="riga">
                                        <tr id='riga${riga.idPlfCollaborazioni}'>

                                            <c:if test="${modifica}">
                                                <td><a
                                                        href="javascript:cancellaCollaborazioneShowModal(${riga.idPlfCollaborazioni});">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </a></td>
                                            </c:if>
                                            <td>${riga.ragioneSociale}</td>
                                            <td>${riga.codiceFiscale}</td>
                                            <td>${riga.partitaIva}</td>
                                            <td>${riga.plfTNazione.descrizione}</td>
                                            <td>${riga.plfTComune.descComune}</td>
                                            <td>${riga.dataInizioCollaborazione}</td>
                                            <td>${riga.dataFineCollaborazione}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>

                    <div class="row">&nbsp;</div>

                    <c:if test="${modifica}">
                        <div class="col-sm-12">
                            <div class="col-sm-6">&nbsp;</div>
                            <div class="col-sm-6">&nbsp;</div>

                            <div class="col-sm-12">
                                <div class="col-sm-6 save-btns">
                                    <a id="cancelButton" href="#" class="btn btn-finish btn-primary"
                                       data-toggle="modal" data-target="#chiudiModal"><spring:message
                                            code="delete.uppercase" text="CANCELLA"/></a>
                                    <button id="deleteButton" class='btn btn-default' type="button" onClick="javascript:resetForm();">
                                        <spring:message code="common_texts.reset" text="ANNULLA"/></button>
                                </div>
                                <div class="col-sm-6 save-btns">
                                    <button id="saveButton"
                                            class='btn btn-finish btn-primary pull-right'
                                            onClick="javascript:aggiorna(event);" value='APPLICA MODIFICHE'>
                                        <spring:message code="common_texts.apply_changes"
                                                        text="APPLICA MODIFICHE"/></button>
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
                                <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                                    code="close" text="Chiudi"/></span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel"><spring:message
                                    code="form.dettaglio.progetto.delete" text="Cancellazione progetto, prodotto, tecnologia"/></h4>
                        </div>
                        <div class="modal-body">
                            <strong><spring:message code="warning" text="Attenzione!"/> </strong>
                            <spring:message code="form.dettaglio.progetto.project_deletion_text" text="Procedere con la cancellazione del progetto, prodotto, tecnologia"/>
                                ${dettaglio.progettiProdottiTranslation.nomeProgettoProdotto}?<br>
                            <spring:message code="form.dettaglio.deletion_news_linked_text" text="Verranno cancellate anche le news adesso collegate."/><br>
                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                                    code="undo.uppercase" text="ANNULLA"/> </a>
                            <button type="button" class="btn btn-primary"
                                    onClick="javascript:cancella();"><spring:message code="delete.uppercase"
                                                                                     text="CANCELLA"/></button>
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
                                <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                                    code="close" text="Chiudi"/></span>
                            </button>
                            <h4 class="modal-title" id="errorModalTitle"></h4>
                        </div>
                        <div class="modal-body">
                            <strong><spring:message code="warning" text="Attenzione!"/> </strong> <span
                                id="errorModalMessage"></span>

                            <br>

                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                                    code="close.uppercase" text="CHIUDI"/></a>
                        </div>
                    </div>
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
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="undo"/></button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"
                        onClick="javascript:reloadPage();"><spring:message code="continue"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
     aria-labelledby="uploadModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" id="uploadModalCloseButton"
                        name="uploadModalCloseButton">
                    <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close"
                                                                                                 text="Chiudi"/></span>
                </button>
                <h4 class="modal-title" id="uploadModalTitle"><spring:message code="description"
                                                                              text="Allega documento"/></h4>
            </div>

            <form enctype="multipart/form-data" id="modal_form_id" method="POST">
                <div class="modal-body">
                    <strong><spring:message code="select_doc_to_attach"/> (<spring:message code="max_size"/>: 20MB)</strong>
                    <span id="uploadModalMessage"></span>
                    <br>
                    <input type="file" class="fileupload" name="fileAllegato" id="fileAllegato">
                    <div class="form-group">
                        <label><spring:message code="description" text="Descrizione"/> : </label>
                        <input id="allegaFileDialogText" name="allegaFileDialogText" type="text" class="form-control"
                               maxlength="400">
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

<div class="modal fade" id="editAllegatoModal" tabindex="-1" role="dialog"
     aria-labelledby="editAllegatoLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" id="editAllegatoClodeModal"
                        name="editAllegatoClodeModal">
                    <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close"
                                                                                                 text="Chiudi"/></span>
                </button>
                <h4 class="modal-title" id="editAllegatoTitle"><spring:message
                        code="form.dettaglio.impresa.edit_allegato"/></h4>
            </div>

            <form id="edit_allegato_form" method="POST">
                <div class="modal-body">
                    <div>
                        <span><spring:message code="warning"/> <spring:message
                                code="form.dettaglio.impresa.edit_allegato_current"/></span>
                    </div>
                    <div class="form-group">
                        <input id="idAllegatoEdit" hidden="true"/>
                        <label><spring:message code="description" text="Descrizione"/> : </label>
                        <input id="allegatoDescriptionEdit" type="text" class="form-control" maxlength="400">
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="pull-right">
                        <a id="saveDescriptionDialogBtn" class='btn btn-finish btn-primary'
                           onclick="saveAllegatoDescription()"><spring:message code="save"/></a>
                    </div>
                </div>
            </form>
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
                    <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close"
                                                                                                 text="Chiudi"/></span>
                </button>
                <h4 class="modal-title" id="cancellaCollegamentoModalLabel"><spring:message code="cancellation"
                                                                                            text="Cancellazione"/></h4>
            </div>
            <div class="modal-body">
                <strong><spring:message code="warning" text="Attenzione!"/> </strong> <spring:message
                    code="cancellation_confirm" text="Procedere con la cancellazione"/><br>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" text="ANNULLA"/></a>
                
                <!-- JAGOOOOOOOOOOO -->
                <button name="cancellaCollegamentoModalButton"
                        id="cancellaCollegamentoModalButton" type="button"
                        class="btn btn-primary"><spring:message code="delete.uppercase" text="CANCELLA"/></button>
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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript"
        charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript"
        charset="UTF-8"></script>

<script
        src="${evn_urlRisorseStatiche}/vimp/assets/js/moment.min.js"
        type="text/javascript"></script>

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

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/checkModify.js"></script>

<script>
    var uploadImage = false;

    $(document)
        .ready(
            function () {

                if (${modifica})
                    $('#frmDettaglio').addClass('safe-reload');
                else
                    $('#frmDettaglio').removeClass('safe-reload');

                $('#selectTipo').on('change', function () {

                    if ($('#selectTipo').val() == 2 || $('#selectTipo').val() == 3) {
                        if (${modifica})
                            $("#descProgettoLabel").html("<spring:message code="description"/> : <small>(<spring:message code="required" javaScriptEscape="true" text="richiesto"/>)</small>");
                        $("#selectTipologia").prop("disabled", true);
                        $("#numDurata").prop("disabled", true);
                        $("#dataScadenza").prop("disabled", true);
                    } else {
                        $("#descProgettoLabel").html("<spring:message code="description"/> :");
                        $("#descProgetto").prop("disabled", false);
                        $("#numDurata").prop("disabled", false);
                        $("#dataScadenza").prop("disabled", false);
                    }

                    if ($('#selectTipo').val() == 1) {
                        $("#descProgetto").val("");
                        $("#descProgetto").prop("disabled", true);
                    } else
                        $("#descProgetto").prop("disabled", false);
                });


                if (${dettaglio.plfTTipoProgettiProdotti.id} == 2 || ${dettaglio.plfTTipoProgettiProdotti.id} == 3)
                {
                    if (${modifica})
                        $("#descProgettoLabel").html("<spring:message code="description"/> : <small>(<spring:message code="required" javaScriptEscape="true" text="richiesto"/>)</small>");
                    $("#selectTipologia").prop("disabled", true);
                    $("#numDurata").prop("disabled", true);
                    $("#dataScadenza").prop("disabled", true);
                }
            else
                {
                    $("#descProgettoLabel").html("<spring:message code="description"/> : ");
                    $("#descProgetto").prop("disabled", false);
                    $("#numDurata").prop("disabled", false);
                    $("#dataScadenza").prop("disabled", false);
                }


                $("#selectCollaborazioneComuneForm").hide();
                $("#selectCollaborazioneProvinciaForm").hide();
                $("#selectCollaborazioneRegioneForm").hide();


                $("#tabella").tablecloth({
                    theme: "default",
                    striped: true,
                    sortable: true,
                    condensed: true,
                    bordered: true
                });


                if (${dettaglio.progettoOrigine == 'S'})
                    $('#checkboxProgettoOrigine').iCheck('check');
                else
                    $('#checkboxProgettoOrigine').iCheck('uncheck');
                $('#checkboxProgettoOrigine').on('ifChecked', function (event) {
                    $("#progettoOrigine").val("S");
                });
                $('#checkboxProgettoOrigine').on('ifUnchecked', function (event) {
                    $("#progettoOrigine").val("N");
                });

                if (${!modifica})
                    $('#checkboxProgettoOrigine').iCheck('disable');


                $('#numValoreEconomico')
                    .keydown(
                        function (event) {

                            if (event.shiftKey == true) {
                                event.preventDefault();
                            }

                            if ((event.keyCode >= 48 && event.keyCode <= 57)
                                || (event.keyCode >= 96 && event.keyCode <= 105)
                                || event.keyCode == 8
                                || event.keyCode == 9
                                || event.keyCode == 37
                                || event.keyCode == 39
                                || event.keyCode == 46
                                || event.keyCode == 190) {

                            } else {
                                event.preventDefault();
                            }

                            if ($(this).val().indexOf('.') !== -1
                                && event.keyCode == 190)
                                event.preventDefault();
                            //if a decimal has been added, disable the "."-button

                        });

                $('.datepicker').datepicker({
                    language: '${env_locale}',
                    format: 'yyyy-mm-dd',
                    viewformat: 'dd/mm/yyyy'
                });

                $('#wizard-picture').change(function () {
                    uploadImage = true;
                });


                $('#frmDettaglio')
                    .validate(
                        {
                            errorPlacement: function(error, element) {
                                if(element.parent('.input-group').length) {
                                    error.insertAfter(element.parent());
                                } else {
                                    error.insertAfter(element);
                                }
                            },
                            rules: {
                                'progettiProdottiTranslation.nomeProgettoProdotto': "required",
                                'plfTTipoProgettiProdotti.id': 'required',
                                'progettiProdottiTranslation.descrizione': 'required'
                            },
                            messages: {
                                'progettiProdottiTranslation.nomeProgettoProdotto': '<spring:message code="dettaglioProgettoInserireNome" javaScriptEscape="true" />',
                                'plfTTipoProgettiProdotti.id': '<spring:message code="dettaglioProgettoInserireTipo" javaScriptEscape="true" />',
                                'progettiProdottiTranslation.descrizione': '<spring:message code="form.nuovo.progetto.insert_description" javaScriptEscape="true" />',
                                numDurata: '<spring:message code="form.progetti.prodotti.max_durata" javaScriptEscape="true"/> 99999999999999999'
                            }
                        });

                let validateSettings = $('#frmDettaglio').validate().settings;

                if ($('#selectTipo').val() == 1 || $('#selectTipo').val() == 2) {
                    validateSettings.rules['elencoSettoreProgettiProdotti'] = 'required';
                    validateSettings.messages['elencoSettoreProgettiProdotti'] = '<spring:message code="form.progetti.prodotti.settore_required" javaScriptEscape="true"/>';

                    if($('#selectTipo').val() == 1) {
                        validateSettings.rules['plfTTipologiaProgetto.id'] = 'required';
                        validateSettings.messages['plfTTipologiaProgetto.id'] = '<spring:message code="form.progetti.prodotti.tipologia_required" javaScriptEscape="true"/>';
                    }

                } else if ($('#selectTipo').val() == 3) {
                    validateSettings.rules['elencoSettoreTecnologie'] = 'required';
                    validateSettings.messages['elencoSettoreTecnologie'] = '<spring:message code="form.progetti.prodotti.settore_required" javaScriptEscape="true"/>';
                }


                $('#selectCollaborazioneImpresa').on('change', function () {
                    if (this.value == '') {
                        $('#collaborazioneRagioneSocialeGroup').show();
                        $('#collaborazionePIvaCodFiscaleGroup').show();
                        $('#selectCollaborazioneNazioneGroup').show();
                        $('#selectCollaborazioneRegioneForm').hide();
                        $('#selectCollaborazioneProvinciaForm').hide();
                        $('#selectCollaborazioneComuneForm').hide();

                    } else {
                        $('#collaborazioneRagioneSocialeGroup').hide();
                        $('#collaborazionePIvaCodFiscaleGroup').hide();
                        $('#selectCollaborazioneNazioneGroup').hide();
                        $('#selectCollaborazioneRegioneForm').hide();
                        $('#selectCollaborazioneProvinciaForm').hide();
                        $('#selectCollaborazioneComuneForm').hide();
                    }
                });

                $('#caratteristicheTecniche').summernote(
                    {
                        onChange: function (contents, $editable) {
                            summernoteCaratTecnicheElement.val(summernoteCaratTecnicheElement.summernote('isEmpty') ? "" : contents);
                            summernoteValidator.element(summernoteCaratTecnicheElement);
                        },
                        lang: '${env_locale}',
                        placeholder : '<spring:message code="form.dettaglio.progetto.caratteristiche_tecniche" javaScriptEscape="true"/>',
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

                var summernoteCaratTecnicheElement = $('caratteristicheTecniche');

                if(${dettaglio.pubblicato} === true) {
                    $('input[name="pubblicato"]').iCheck('check');
                }


                <!-- CONTROLLO USCITA PAGINA MODIFICATA SENZA SALVARE-->
				<!-- checkModify.js -->
				if (${modifica})
				{
					setCheckModify('saveButton','cancelButton','deleteButton',
							['pubblicato'],
							['caratteristicheTecniche']);
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

    function cancella() {
        $("#imageData").val("");
        $('#frmDettaglio').attr('action',
            '/vimp/secure/cancellaProgetto');
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

    function setSettoriByType(tipo) {
        if(tipo === 3) {
            $('#selectSettoreProgetto').hide();
            $('#selectSettoreTecnologie').show();
        } else {
            $('#selectSettoreProgetto').show();
            $('#selectSettoreTecnologie').hide();
        }
    }


    function updateRegioni(selectedNazione) {
        if (selectedNazione == 'Z000') {
            $("#selectCollaborazioneComune option").remove();
            $("#selectCollaborazioneProvincia option").remove();
            $("#selectCollaborazioneRegione option").remove();

            $("#selectCollaborazioneComuneForm").show();
            $("#selectCollaborazioneProvinciaForm").show();
            $("#selectCollaborazioneRegioneForm").show();

            $.getJSON("/vimp/elencoRegioni.json", {
                nazione: selectedNazione
            }, function (data) {

                $("#selectCollaborazioneRegione").append($("<option></option>").text("<spring:message code="common.selezionaLaRegione" javaScriptEscape="true" text="Seleziona la regione"/>").val('').addClass('bs-title-option'));

                $.each(data, function (index, item) {
                    $("#selectCollaborazioneRegione").append(
                        $("<option></option>").text(item.text).val(
                            item.value));
                });

                $('#selectCollaborazioneRegione').selectpicker('refresh');
            });
        } else {
            $("#selectCollaborazioneComune option").remove();
            $("#selectCollaborazioneProvincia option").remove();
            $("#selectCollaborazioneRegione option").remove();

            $("#selectCollaborazioneComuneForm").hide();
            $("#selectCollaborazioneProvinciaForm").hide();
            $("#selectCollaborazioneRegioneForm").hide();
        }
    }


    function updateProvincie(selectedRegione) {
        if (selectedRegione > 0) {
            $("#selectCollaborazioneProvincia option").remove();

            $.getJSON("/vimp/elencoProvincie.json", {
                regione: selectedRegione
            }, function (data) {

                $("#selectCollaborazioneProvincia").append($("<option></option>").text("<spring:message code="common.selezioneLaProvincia" javaScriptEscape="true" text="Seleziona la provincia"/>").val('').addClass('bs-title-option'));

                $.each(data, function (index, item) {
                    $("#selectCollaborazioneProvincia").append(
                        $("<option></option>").text(item.text).val(
                            item.value));
                });

                $('#selectCollaborazioneProvincia').selectpicker('refresh');
            });
        }
    }


    function updateComuni(selectedProvincia) {
        if (selectedProvincia > 0) {
            $("#selectCollaborazioneComune option").remove();

            $.getJSON("/vimp/elencoComuni.json", {
                provincia: selectedProvincia
            }, function (data) {

                $("#selectCollaborazioneComune").append($("<option></option>").text("<spring:message code="common.selezionaIlComune" javaScriptEscape="true" text="Seleziona il comune"/>").val('').addClass('bs-title-option'));

                $.each(data, function (index, item) {
                    $("#selectCollaborazioneComune").append(
                        $("<option></option>").text(item.text).val(
                            item.value));
                });

                $('#selectCollaborazioneComune').selectpicker('refresh');
            });
        }
    }


    function collaborazioni() {
        var idProgettoVar = ${dettaglio.idPlfProgettiProdotti};
        var idImpresaVar = $("#selectCollaborazioneImpresa").val();
        var ragioneSocialeVar = $("#collaborazioneRagioneSociale").val();
        var partitaIvaVar = $("#collaborazionePartitaIva").val();
        var codiceFiscaleVar = $("#collaborazioneCodiceFiscale").val();
        var comuneVar = $("#selectCollaborazioneComune").val();
        var nazioneVar = $("#selectCollaborazioneNazione").val();
        var dataInizioVar = $("#collaborazioneDataInizio").val();
        var dataFineVar = $("#collaborazioneDataFine").val();

        var dataInizioDate = null
        var dataFineDate = null;

        if (dataInizioVar)
            dataInizioDate = moment(dataInizioVar, "DD/MM/YYYY");
        if (dataFineVar)
            dataFineDate = moment(dataFineVar, "DD/MM/YYYY");

        if (dataInizioVar && dataFineDate && dataInizioDate.isAfter(dataFineDate)) {
            $('#errorModalTitle').text("<spring:message code="creaColloborazione" javaScriptEscape="true" text="Crea collaborazione"/>");
            $('#errorModalMessage').text("<spring:message code="dataFineCollaborazioneErrorEndDateLowerThanStart" javaScriptEscape="true" text="La data fine collaborazione non puo' essere antecedente alla data inizio collaborazione"/>");
            $('#errorModal').modal('show');
            return;
        }


        $.ajax({
            type: "POST",
            url: '/vimp/secure/collaborazioneProgetto',
            data: {
                idProgetto: idProgettoVar,
                idImpresa: idImpresaVar,
                ragioneSociale: ragioneSocialeVar,
                partitaIva: partitaIvaVar,
                codiceFiscale: codiceFiscaleVar,
                comune: comuneVar,
                nazione: nazioneVar,
                dataInizio: dataInizioVar,
                dataFine: dataFineVar
            },
            success: function (data) {

                var errore = data.error;

                if (errore) {
                    $('#errorModalTitle').text("<spring:message code="creaColloborazione" javaScriptEscape="true" text="Crea collaborazione"/>");
                    $('#errorModalMessage').text(errore);
                    $('#errorModal').modal('show');
                } else {
                    var id = data.id;
                    var ragioneSociale = data.ragioneSociale;
                    var partitaIva = data.partitaIva;
                    var codiceFiscale = data.codiceFiscale;
                    var nazione = data.nazione;
                    var comune = data.comune;
                    var dataInizio = data.dataInizio;
                    var dataFine = data.dataFine;


                    var row = "<td><a href='javascript:cancellaCollaborazioneShowModal(" + id + ");'><span class='glyphicon glyphicon-trash'></span></a></td>" +
                        "<td>" + ragioneSociale + "</td>" +
                        "<td>" + codiceFiscale + "</td>" +
                        "<td>" + partitaIva + "</td>" +
                        "<td>" + nazione + "</td>" +
                        "<td>" + comune + "</td>" +
                        "<td>" + dataInizio + "</td>" +
                        "<td>" + dataFine + "</td></tr>"


                    $('#tabellaCollaborazioni').append('<tr id="riga' + id + '" role="row">' + row + '</tr>');
                    refreshCollaborazioni();
                }

            },
            error: function (error) {
                $('#errorModalTitle').text("<spring:message code="creaColloborazione" javaScriptEscape="true" text="Crea collaborazione"/>");
                $('#errorModalMessage').text("<spring:message code="selezionareImpresaValidaDaCollegare" javaScriptEscape="true" text="Selezionare una impresa valida da collegare oppure compilare i campi sottostanti"/>");
                $('#errorModal').modal('show');
            }
        });

    }

    function refreshCollaborazioni() {
        var rowCount = 0;
        $('#tabellaCollaborazioni > tbody  > tr').each(function () {
            $this = $(this);
            var strValue = $this.html();
            if (strValue) {
                rowCount++;
            }
        });
        if (rowCount > 0)
            $("#tabellaCollaborazioni").show();
        else $("#tabellaCollaborazioni").hide();
    }

    function cancellaCollaborazioneShowModal(collaborazione) {
        $('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaCollaborazione(' + collaborazione + ');');
        $('#cancellaCollegamentoModal').modal('show');
    }


    function cancellaCollaborazione(collaborazione) {
        $.ajax({
            type: "POST",
            url: '/vimp/secure/cancellaCollaborazioneProgetto',
            data: {idCollaborazione: collaborazione},
            success: function (result, data) {
                $('#riga' + collaborazione).html('');
                $('#riga' + collaborazione).remove();
                refreshCollaborazioni();
                $('#cancellaCollegamentoModal').modal('hide');
            },
            error: function (response, data) {
                $('#cancellaCollegamentoModal').modal('hide');
                elaboraErrore(response);
            }
        });
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

    function resetForm() {
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
            postData.append('idPlfProgettiProdotti', ${dettaglio.idPlfProgettiProdotti});
            postData.append('allegato', $('#fileAllegato')[0].files[0]);
            postData.append('descrizione', $('#allegaFileDialogText').val());

            $("#uploadModalCloseButton").hide();
            $("#allegaFileDialogButton").hide();

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/vimp/secure/allegaFileProgettoProdotto',
                processData: false,
                contentType: false,
                data: postData,
                success: function (data) {

                    $("#uploadModalCloseButton").show();
                    $('#uploadModal').modal('hide');
                    var id = data.pk;
                    var nomeFile = data.value;
                    var descrizioneFile = data.fields[0];


                    var row = "<td><a href='javascript:cancellaAllegatoShowModal(" + id + ");'> " +
                        "<span class='glyphicon glyphicon-trash'></span></a></td> " +

                        "<td><a href='javascript:editAllegatoDescription(" + id + ");'> " +
                        "<span class='glyphicon glyphicon-pencil'></span></a></td> " +
                        
                        "<td>" + nomeFile + "</td> ";
                    if (descrizioneFile != null) {
                        row += "<td>" + descrizioneFile + "</td> ";
                    } else {
                        row += "<td></td>";
                    }
                    row += "<td><a target='_blank' href='/vimp/progettoProdottoAllegato/" + id + "'><span class='glyphicon glyphicon-folder-open'></span></a></td> ";



                    $('#tabellaAllegati').append('<tr id="rigaAllegato' + id + '" role="row">' + row + '</tr>');
                    //refreshCollegamenti(false);

                },
                error: function (error) {
                    $("#uploadModalCloseButton").show();
                    $('#uploadModal').modal('hide');
                    $('#errorModalTitle').text("<spring:message code="allegaDocumento" javaScriptEscape="true" text="Allega documento"/>");
                    $('#errorModalMessage').text("<spring:message code="erroreNelTrasferimentoDelDocumento" javaScriptEscape="true" text="Errore nel trasferimento del documento."/>");
                    $('#errorModal').modal('show');
                },
                complete: function () {
                    $("#allegaFileDialogButton").show();
                }
            });
        }
    }

    function allegaFileDialog() {
        $('#uploadModal').modal('show');
        $('#allegaFileDialogText').val("");
    }

    function cancellaProgettoProdottoAllegato(idAllegatoParam) {
        $.ajax({
            type: "POST",
            url: '/vimp/secure/cancellaProgettoProdottoAllegato',
            data: {idAllegato: idAllegatoParam},
            success: function (result, data) {
                $('#rigaAllegato' + idAllegatoParam).html('');
                //refreshCollegamenti(false);
                $('#cancellaCollegamentoModal').modal('hide');
            },
            error: function (response, data) {
                $('#cancellaCollegamentoModal').modal('hide');
                elaboraErrore(response);
            }
        });
    }

    function cancellaAllegatoShowModal(idAllegato) {
        $('#cancellaCollegamentoModalButton').attr('onclick', 'javascript:cancellaProgettoProdottoAllegato(' + idAllegato + ');');
        $('#cancellaCollegamentoModal').modal('show');
    }

    function editAllegatoDescription(idProgettiProdottiAllegati) {
        $('#idAllegatoEdit').val(idProgettiProdottiAllegati);

        var descrizione = $('#descrizioneAllegato_' + idProgettiProdottiAllegati).html();
        $('#allegatoDescriptionEdit').val(descrizione);
        $('#editAllegatoModal').modal('show');

    }

    function saveAllegatoDescription() {
        var id = $('#idAllegatoEdit').val();
        var descr = $('#allegatoDescriptionEdit').val();

        var data = {};
        data['idProgettiProdottiAllegati'] = id;
        data['descrizione'] = descr;


        $.ajax({
            type: "POST",
            url: '/vimp/secure/updateProgettiProdottiAllegatoDescription',
            data: data,
            success: function (data) {
                $('#descrizioneAllegato_' + data.idProgettiProdottiAllegati).html(data.descrizione);
            },
            error: function (response) {
                $('#errorModal').modal('show');
                $('#errorModalMessage').html(elaboraErrore(response));
            },
            complete: function () {
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

<!-- BEGIN DETTAGLIO PROGETTO RICERCA-->
<!-- =========================================================================================== -->