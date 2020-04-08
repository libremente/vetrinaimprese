<%-- --%>
<!-- =========================================================================================== -->
<!-- BEGIN DETTAGLIO PACCHETTO -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message var="selectMacroareaPacchettoTitleAttr" code="form.dettaglio.pacchetto.selectMacroareaPacchettoTitleAttr"/>
<spring:message var="selectModalitaErogazioneTitleAttr" code="form.dettaglio.pacchetto.selectModalitaErogazioneTitleAttr"/>

<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">
                    <b><spring:message code="form.dettaglio.pacchetti.title"/></b> ${utils.truncateString(dettaglio.pacchettoServiziTranslation.titolo,100)}
                </h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->

<form:form id="frmDettaglio" method="POST"
           action="/vimp/secure/salvaPacchettoServizi" role="form"
           modelAttribute="dettaglio" class="safe-reload">

    <form:hidden id="idPacchettoServizi" path="idPacchettoServizi" />
    <form:hidden id="pacchettoServiziTranslation.idPacchettoServizi" path="pacchettoServiziTranslation.idPacchettoServizi"/>
    <form:hidden id="dataInizio" path="dataInizio" />
    <form:hidden id="utenteCreazione.idUtente" path="utenteCreazione.idUtente" />
    <form:hidden id="utenteCreazione.ruolo.idRuolo" path="utenteCreazione.ruolo.idRuolo" />
    <form:hidden id="utenteCreazione.nome" path="utenteCreazione.nome" />
    <form:hidden id="utenteCreazione.cognome" path="utenteCreazione.cognome" />
    <form:hidden id="dataCreazione" path="dataCreazione"/>

    <input id="imageData" name="imageData" type="hidden">

    <div class="content-area user-profiel">

    <div class="container">

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
                    <strong><spring:message code="warning"/></strong> ${warningMessage}
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
                                                    src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_pacchetti_servizi.jpg"
                                                    class="picture-src" id="wizardPicturePreview" title="" />
                                            <c:if test="${modifica}">
                                                <input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6" ${not modifica and empty dettaglio.pacchettoServiziTranslation.titolo ? 'hidden' :''}>
                            <div class="form-group">
                                <br /> <label><spring:message code="form.dettaglio.pacchetto.titolo" /> :
                                <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="richiesto"/>)</small>
                                </c:if>
                            </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input name="pacchettoServiziTranslation.titolo" type="text" class="form-control"
                                               placeholder="" value="${dettaglio.pacchettoServiziTranslation.titolo}" path="titolo"
                                               maxlength="400">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.pacchettoServiziTranslation.titolo}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="col-sm-6" ${not modifica and empty dettaglio.pacchettoServiziTranslation.titolo ? 'hidden' :''}>
                            <div class="form-group">
                                <label><spring:message code="description" text="Descrizione"/> : </label>
                                <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="richiesto"/>)</small>
                                </c:if>
                                <c:choose>
                                    <c:when test="${modifica}">
											<textarea rows="4" name="pacchettoServiziTranslation.descrizione" class="form-control"
                                                      maxlength="3390">${dettaglio.pacchettoServiziTranslation.descrizione}</textarea>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.pacchettoServiziTranslation.descrizione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>
                    </div>
                    <div class="safe-col">
                        <div class="col-sm-6" ${not modifica ? 'hidden' :''}> <%-- Non visibile in dettaglio --%>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.pacchetto.macroarea" /> </label>
                                <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="richiesto"/>)</small>
                                </c:if>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <form:select id="selectMacroarea"
                                                     title="${selectMacroareaPacchettoTitleAttr}"
                                                     data-live-search="true" data-live-search-style="contains"
                                                     path="macroarea.id"
                                                     cssClass="selectpicker">
                                            <form:options items="${macroareeList}" />
                                        </form:select>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.macroarea.descrizione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>

                        <div class="col-sm-6" ${not modifica and empty dettaglio.modalitaErogazione ? 'hidden' :''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.pacchetto.modalita_erogazione" /> </label>
                                <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="richiesto"/>)</small>
                                </c:if>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <form:select id="selectModalitaErogazione"
                                                     title="${selectModalitaErogazioneTitleAttr}"
                                                     data-live-search="true" data-live-search-style="contains"
                                                     path="modalitaErogazione.id"
                                                     cssClass="selectpicker">
                                            <form:options items="${modalitaErogazioneList}" />
                                        </form:select>
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.modalitaErogazione.descrizione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>

                        <div class="col-sm-6" ${not modifica ? 'hidden' :''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.pacchetto.note1" /> </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input id="note1" name="pacchettoServiziTranslation.note1" type="text"
                                               class="form-control" placeholder=""
                                               value="${dettaglio.pacchettoServiziTranslation.note1}" path="note1"
                                               maxlength="400">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.pacchettoServiziTranslation.note1}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>

                        <div class="col-sm-6" ${not modifica ? 'hidden' :''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.pacchetto.note2" /> </label>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input id="note2" name="pacchettoServiziTranslation.note2" type="text"
                                               class="form-control" placeholder=""
                                               value="${dettaglio.pacchettoServiziTranslation.note2}" path="note2"
                                               maxlength="400">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.pacchettoServiziTranslation.note2}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>

                        <div class="col-sm-6" ${not modifica and empty dettaglio.luogoErogazione ? 'hidden' :''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.pacchetto.luogo_erogazione" /> </label>
                                <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="richiesto"/>)</small>
                                </c:if>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input id="luogoErogazione" name="luogoErogazione" type="text"
                                               class="form-control" placeholder=""
                                               value="${dettaglio.luogoErogazione}" path="luogoErogazione"
                                               maxlength="400">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.luogoErogazione}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>

                        <div class="col-sm-6" ${not modifica and empty dettaglio.contatti ? 'hidden' :''}>
                            <div class="form-group">
                                <label><spring:message code="form.dettaglio.pacchetto.contatti" /> </label>
                                <c:if test="${modifica}">
                                    <small>(<spring:message code="required" text="richiesto"/>)</small>
                                </c:if>
                                <c:choose>
                                    <c:when test="${modifica}">
                                        <input id="contatti" name="contatti" type="text"
                                               class="form-control" placeholder=""
                                               value="${dettaglio.contatti}" path="contatti"
                                               maxlength="400">
                                    </c:when>
                                    <c:otherwise>
                                        <br>
                                        <label><b>${dettaglio.contatti}</b></label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>


                        <c:if test="${modifica}">
                            <div class="col-sm-12">
                                <h4 class="info-text"> <spring:message code="common_texts.services" /> </h4>
                                <div class="container-fluid">

                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label><b><spring:message code="form.dettaglio.pacchetto.servizi_utente" /></b> </label>
                                                <form:select id="selectServiziUtente"
                                                             title="${selectServiziUtenteTitleAttr}"
                                                             data-live-search="true" data-live-search-style="contains"
                                                             cssClass="selectpicker" path="">
                                                    <form:options items="${serviziUtenteList}" />
                                                </form:select>
                                                <div id="serviziUtenteCollegati" hidden></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="pull-right">
                                                    <button type="button" id="collegaServiziUtenteButton"
                                                            class='btn btn-primary'
                                                            onClick="javascript:collegaServiziUtente();"><spring:message code="form.dettaglio.pacchetto.connect" /></button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <br /> <br /> <br />
                                                <div class="table-responsive">
                                                    <table id="tabellaServiziUtente"
                                                           class="table table-condensed bordered1 tablesorter">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th data-toggle="tooltip" data-placement="top"
                                                                title="${serviceTitleAttr}"><spring:message code="common_texts.service" /></th>
                                                            <th></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label><b><spring:message code="form.dettaglio.pacchetto.servizi_general" /></b> </label>
                                                <form:select id="selectServiziGeneral"
                                                             title="${selectServiziGeneralTitleAttr}"
                                                             data-live-search="true" data-live-search-style="contains"
                                                             cssClass="selectpicker" path="">
                                                    <form:options items="${serviziGeneralList}" />
                                                </form:select>
                                                <div id="serviziGeneralCollegati" hidden></div>
                                            </div>
                                            <div class="form-group">
                                                <div class="pull-right">
                                                    <button type="button" id="collegaServiziGeneralButton"
                                                            class='btn btn-primary'
                                                            onClick="javascript:collegaServiziGeneral();"><spring:message code="form.dettaglio.pacchetto.connect" /></button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <br /> <br /> <br />
                                                <div class="table-responsive">
                                                    <table id="tabellaServiziGeneral"
                                                           class="table table-condensed bordered1 tablesorter">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th data-toggle="tooltip" data-placement="top"
                                                                title="${serviceTitleAttr}"><spring:message code="common_texts.service" /></th>
                                                            <th></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>

                            </div>
                        </c:if>
                        <c:if test="${not modifica}">
                            <div class="col-sm-12">
                                <h4 class="info-text"> <spring:message code="common_texts.services" /> </h4>
                                <div class="container-fluid">

                                    <div class="row">
                                        <div class="col-sm-12">

                                            <div class="form-group">
                                                <br /> <br /> <br />
                                                <div class="table-responsive">
                                                    <table class="table table-condensed bordered1 tablesorter">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th data-toggle="tooltip" data-placement="top"
                                                                title="${serviceTitleAttr}"><spring:message code="common_texts.service" /></th>
                                                            <th data-toggle="tooltip" data-placement="top" title="${newsTitleAttr}"><spring:message code="link" text="Link"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${dettaglio.servizi}" var="servizio">
                                                            <tr>
                                                                <td></td>
                                                                <td>${servizio.denominazioneCalcolata}</td>
                                                                <td><a
                                                                        href="/vimp/servizi/${servizio.idServizi}"><span
                                                                        class="glyphicon glyphicon-folder-open"></span></a></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
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
                                <div class="col-sm-12" ${!modifica && empty dettaglio.pacchettoServiziTranslation.descTag ? 'hidden' : ''}>
                                    <div class="form-group">
                                        <input name="pacchettoServiziTranslation.descTag"
                                               value="${dettaglio.pacchettoServiziTranslation.descTag}" id="selectedTags"
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
                        

						<div class="col-sm-12">
                        	&nbsp;
                        </div>
                        
                        <div class="safe-col">
						
                            <div class="col-sm-6" ${empty dettaglio.utenteCreazione ? 'hidden' :''}>
                            
                            	<c:choose>
                                	<c:when test="${dettaglio.utenteCreazione.isStakeholder()}">
                                		<label><spring:message code="form.dettaglio.pacchetto.utente_creazione" /> </label>
                                	</c:when>
                                	<c:otherwise>
                                		<label><spring:message code="form.dettaglio.pacchetto.utente_creazione_admin" /> </label>
                                	</c:otherwise>
                                </c:choose>
                            
                                <label><b>${dettaglio.utenteCreazione.cognome} ${dettaglio.utenteCreazione.nome}</b></label>
                            </div>
                            <div class="col-sm-6" ${empty dettaglio.dataCreazione ? 'hidden' :''}>
                                <label><spring:message code="form.dettaglio.pacchetto.data_creazione" /> </label>
                                <label><b><fmt:formatDate value="${dettaglio.dataCreazione}" pattern="dd/MM/yyyy"/></b></label>
                            </div>

                            <div class="col-sm-6" ${empty dettaglio.utenteUltimaModifica ? 'hidden' :''}>
                            	
                            	<c:choose>
                                	<c:when test="${dettaglio.utenteUltimaModifica.isStakeholder()}">
                                		<label><spring:message code="form.dettaglio.pacchetto.utente_ultima_modifica" /> </label>
                                	</c:when>
                                	<c:otherwise>
                                		<label><spring:message code="form.dettaglio.pacchetto.utente_ultima_modifica_admin" /> </label>
                                	</c:otherwise>
                                </c:choose>
                            
                                <label><b>${dettaglio.utenteUltimaModifica.cognome} ${dettaglio.utenteUltimaModifica.nome}</b></label>
                            </div>
                            <div class="col-sm-6" ${empty dettaglio.dataUltimaModifica ? 'hidden' :''}>
                                <label><spring:message code="form.dettaglio.pacchetto.data_ultima_modifica" /> </label>
                                <label><b><fmt:formatDate value="${dettaglio.dataUltimaModifica}" pattern="dd/MM/yyyy"/></b></label>
                            </div>

						<div class="col-sm-12">
                        	&nbsp;
                        </div>
                        
                            <div class="col-sm-6" ${not modifica ? 'hidden' :''}>
                                <div class="checkbox">
                                    <label>
                                        <input id="pubblicato" name="pubblicato" type="checkbox"/>
                                        <strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
                                    </label>
                                </div>
                            </div>

                        </div>
                        
                        <div class="col-sm-12">
                        	&nbsp;
                        </div>

                        


                        <c:if test="${modifica}">
                            <div class="col-sm-12">
                                <div class="col-sm-6">&nbsp;</div>
                                <div class="col-sm-6">&nbsp;</div>


                                <div class="col-sm-12">
                                    <div class="col-sm-6 save-btns">

                                        <a href="#" class="btn btn-finish btn-primary"
                                           data-toggle="modal" data-target="#chiudiModal"><spring:message code="form.dettaglio.pacchetto.cancella"/></a>
                                        <button class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" /></button>
                                    </div>
                                    <div class="col-sm-6 save-btns">
                                        <button id="saveButton"
                                                class='btn btn-finish btn-primary pull-right'
                                                onClick="javascript:aggiorna(event);" value='APPLICA MODIFICHE'><spring:message code="common_texts.apply_changes" /></button>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>

                <div class="modal fade" id="chiudiModal" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">

                        <!-- Modal -->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel"><spring:message code="form.dettaglio.pacchetto.deletion"/></h4>
                            </div>
                            <div class="modal-body">
                                <strong><spring:message code="warning" text="Attenzione!"/> </strong>
                                <spring:message code="form.dettaglio.pacchetto.deletion_text"/>
                                    ${dettaglio.pacchettoServiziTranslation.titolo}?<br>
                            </div>
                            <div class="modal-footer">
                                <a type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo.uppercase" /></a>
                                <button type="button" class="btn btn-primary"
                                        onClick="javascript:cancella();"><spring:message code="delete.uppercase"/></button>
                            </div>
                        </div>

                    </div>
                </div>

            </div>


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

</form:form>

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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"  type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js" type="text/javascript"></script>

<script>

    var uploadImage = false;
    var checkServiziDuplicati = false;
    var serviziUtenteCollegati = [];
    var serviziGeneralCollegati = [];

    $(document).ready(
        function() {

            if (${modifica})
                $('#frmDettaglio').addClass('safe-reload');
            else
                $('#frmDettaglio').removeClass('safe-reload');

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
                rules: {
                    'pacchettoServiziTranslation.titolo': 'required',
                    'pacchettoServiziTranslation.descrizione' : 'required',
                    'macroarea.id': 'required',
                    'luogoErogazione': 'required',
                    'contatti': 'required',
                },
                messages: {
                    'pacchettoServiziTranslation.titolo' : '<spring:message code="pacchettoFormNuovoInserireNome" javaScriptEscape="true" />',
                    'pacchettoServiziTranslation.descrizione' : '<spring:message code="pacchettoFormNuovoInserireDescrizione" javaScriptEscape="true" />',
                    'macroarea.id': '<spring:message code="pacchettoFormNuovoSelectMacroarea" javaScriptEscape="true" />',
                    'luogoErogazione': '<spring:message code="pacchettoFormNuovoInserireLuogoErogazione" javaScriptEscape="true" />',
                    'contatti': '<spring:message code="pacchettoFormNuovoInserireContatti" javaScriptEscape="true" />'
                }
            });

            if(${dettaglio.pubblicato} === true) {
                $('input[name="pubblicato"]').iCheck('check');
            }

            setupServizi();
            displayTabelleServizi();
            

            $('#frmDettaglio').submit(function(e){
                e.preventDefault();
                if($('#frmDettaglio').valid()){
                    if(!validateServizi()){
                        errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="form.dettaglio.pacchetto.due_servizi" javaScriptEscape="true" />')
                        return false;
                    }

                    if(!validateServiziUtente() && ${not utente.isBackoffice()}){
                        errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="form.dettaglio.pacchetto.obbligo_servizi_utente" javaScriptEscape="true"/>');
                        return false;
                    }

                    validateServiziDuplicati(function () {
                        $('#frmDettaglio').unbind('submit').submit();
                    });
                }
            })

        });

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
        $('#frmDettaglio').attr('action', '/vimp/secure/cancellaPacchettoServizi');
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

    function setupServizi() {
        var idServizi = ${dettaglio.elencoServizi};
        idServizi.forEach(function (idServizio) {
            var isServizioUtente =$('#selectServiziUtente option[value='+idServizio+']').length>0;
            var isServizioGeneral = $('#selectServiziGeneral option[value='+idServizio+']').length>0;
            if(isServizioUtente){
                collegaServiziUtenteAux(idServizio+'',$('#selectServiziUtente option[value='+idServizio+']').text());
            } else if(isServizioGeneral){
                collegaServiziGeneralAux(idServizio+'',$('#selectServiziGeneral option[value='+idServizio+']').text())
            }
        });

    }

    function collegaServiziUtente() {
        var id = $('#selectServiziUtente option:selected').val();
        var denominazione = $('#selectServiziUtente option:selected').text();
        collegaServiziUtenteAux(id,denominazione);
    }

    function collegaServiziUtenteAux(id,denominazione){
        if(id!='')
            if(serviziUtenteCollegati.indexOf(id)==-1){
                serviziUtenteCollegati.push(id);
                displayTabelleServizi();
                var linkCancellazione = $('<a onclick="javascript:scollegaServizioUtente('+id+')"><span class="glyphicon glyphicon-trash"></span></a>');
                var nuovaRiga = $('<tr></tr>').attr('id','servizio-utente-'+id)
                    .append($('<td></td>').append(linkCancellazione))
                    .append($('<td></td>').text(denominazione));
                $('#tabellaServiziUtente > tbody').append(nuovaRiga);

                $('<input>', {
                    type: 'hidden',
                    id: 'elencoServizi',
                    name: 'elencoServizi',
                    value: id
                }).appendTo('#serviziUtenteCollegati');


            } else {
                errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="pacchettoFormNuovoServizioSelezionato" javaScriptEscape="true"/>');
            }
    }

    function scollegaServizioUtente(id) {
        serviziUtenteCollegati.splice(serviziUtenteCollegati.indexOf(id),1);
        $('#servizio-utente-'+id).remove();
        $('input[name=elencoServizi][value='+id+']').remove();
        displayTabelleServizi();
    }

    function collegaServiziGeneral() {
        var id = $('#selectServiziGeneral option:selected').val();
        var denominazione = $('#selectServiziGeneral option:selected').text();
        collegaServiziGeneralAux(id,denominazione);
    }

    function collegaServiziGeneralAux(id, denominazione) {
        if(id!='')
            if(serviziGeneralCollegati.indexOf(id)==-1){
                serviziGeneralCollegati.push(id);
                displayTabelleServizi();
                var linkCancellazione = $('<a onclick="javascript:scollegaServizioGeneral('+id+')"><span class="glyphicon glyphicon-trash"></span></a>');
                var nuovaRiga = $('<tr></tr>').attr('id','servizio-general-'+id)
                    .append($('<td></td>').append(linkCancellazione))
                    .append($('<td></td>').text(denominazione));
                $('#tabellaServiziGeneral > tbody').append(nuovaRiga);

                $('<input>', {
                    type: 'hidden',
                    id: 'elencoServizi',
                    name: 'elencoServizi',
                    value: id
                }).appendTo('#serviziGeneralCollegati');


            } else {
                errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="pacchettoFormNuovoServizioSelezionato" javaScriptEscape="true"/>');
            }
    }

    function scollegaServizioGeneral(id) {
        serviziGeneralCollegati.splice(serviziGeneralCollegati.indexOf(id),1);
        $('#servizio-general-'+id).remove();
        $('input[name=elencoServizi][value='+id+']').remove();
        displayTabelleServizi();
    }

    function validateServizi(){
        return serviziUtenteCollegati.length + serviziGeneralCollegati.length >= 2;
    }

    function validateServiziUtente() {
        return serviziUtenteCollegati.length >=1;
    }

    function validateServiziDuplicati(success) {
        var idServizi = arrayMerge(serviziUtenteCollegati, serviziGeneralCollegati);

        var obj = { idPacchetto: ${dettaglio.idPacchettoServizi},
            servizi: idServizi
        };

        $.ajax({
            url: '/vimp/secure/checkPacchettoServizi',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            dataType: 'json',
            statusCode: {
                200: function(){
                    checkServiziDuplicati= true;
                    success();
                },
                418: function () {
                    checkServiziDuplicati = false;
                    errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="form.dettaglio.pacchetto.servizi_duplicati" />')
                },
            }
        });

    }

    function displayTabelleServizi(){
        if(serviziUtenteCollegati.length==0){
            $('#tabellaServiziUtente').hide();
        } else {
            $('#tabellaServiziUtente').show();
        }
        if(serviziGeneralCollegati.length==0){
            $('#tabellaServiziGeneral').hide();
        } else {
            $('#tabellaServiziGeneral').show();
        }
    }

    function errorModal(title, message){
        $('#errorModalTitle').text(title);
        $('#errorModalMessage').text(message);
        $('#errorModal').modal('show');
    }

    function arrayMerge(arr1,arr2){
        var result = [];
        for(var i = 0; i<arr1.length; i++){
            result.push(arr1[i]);
        }
        for(var i = 0; i<arr2.length; i++){
            result.push(arr2[i]);
        }
        return result;
    }

</script>

<!-- END DETTAGLIO PACCHETTO -->
<!-- =========================================================================================== -->