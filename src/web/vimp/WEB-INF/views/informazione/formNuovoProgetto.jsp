<!-- =========================================================================================== -->
<!-- BEGIN NUOVO PROGETTO RICERCA-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message var="selectTheProjectTypeTitleAttr" code="form.nuovo.progetto.selectTheProjectTypeTitleAttr"
                text="Seleziona il tipo progetto"/>
<spring:message var="selectTheAreaTitleAttr" code="form.nuovo.progetto.selectTheAreaTitleAttr"
                text="Seleziona il settore"/>
<spring:message var="selectTheProjectStatusTitleAttr" code="form.nuovo.progetto.selectTheProjectStatusTitleAttr"
                text="Seleziona lo stato del progetto"/>
<spring:message var="selectTipologyTitleAttr" code="form.nuovo.progetto.selectTipologyTitleAttr"
                text="Seleziona la tipologia"/>
<spring:message var="nameTitleAttr" code="name" text="Nome"/>
<spring:message var="descriptionTitleAttr" code="description" text="Descrizione"/>

<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title"><spring:message code="form.nuovo.progetto.insert_new_project"
                                                       text="Inserisci nuovo progetto prodotto"/></h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->

<!-- property area -->
<div class="content-area submit-property">&nbsp;
    <div class="container">


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

        <c:choose>
            <c:when test="${!utente.isWriter(parametriRicerca.tipoInformazione)}">
                <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">&times;
                    </button>
                    <strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message
                        code="operation_not_permitted" text="Operazione non permessa"/>.
                </div>
            </c:when>
            <c:otherwise>

                <div class="clearfix">
                    <div class="wizard-container">


                        <div class="wizard-card ct-wizard-orange" id="wizardInsert">


                            <form:form id="frmDettaglio" method="POST" action="/vimp/secure/salvaProgetto" role="form"
                                       modelAttribute="dettaglio" class="safe-reload">


                                <input id="imageData" name="imageData" type="hidden">
                                <form:hidden id="progettoOrigine" path="progettoOrigine"/>


                                <div class="wizard-header">
                                    <h3>
                                        <b><spring:message code="insert.uppercase" text="INSERISCI"/></b>
                                        <spring:message code="form.nuovo.progetto.a_new_project"
                                                        text="NUOVO PROGETTO PRODOTTO"/> <br>
                                        <small> <spring:message code="promote_visibility"/></small>
                                    </h3>
                                </div>

                                <ul>
                                    <li><a href="#step1" data-toggle="tab"><spring:message code="first_step"
                                                                                           text="Passo 1"/> </a></li>
                                    <li><a href="#step2" data-toggle="tab"><spring:message code="second_step"
                                                                                           text="Passo 2"/> </a></li>
                                    <li><a href="#step3" data-toggle="tab"><spring:message code="third_step"
                                                                                           text="Passo 3"/> </a></li>
                                    <li><a href="#step4" data-toggle="tab"><spring:message code="finish"
                                                                                           text="Finito"/> </a></li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane" id="step1">
                                        <div class="row p-b-15  ">
                                            <h4 class="info-text"><spring:message code="start_with_basic_data"
                                                                                  text="Iniziamo con i dati di base (con validazione)"/></h4>
                                            <div class="col-sm-4 col-sm-offset-1">
                                                <div class="picture-container">
                                                    <div class="picture">
                                                        <img src="${evn_urlRisorseStatiche}/vimp/assets/img/default-property.jpg"
                                                             class="picture-src" id="wizardPicturePreview" title=""/>
                                                        <input type="file" id="wizard-picture" accept=".jpg,.jpeg,.png">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label><spring:message code="form.dettaglio.progetto.project_name"
                                                                           text="Nome progetto"/>
                                                        <small>(<spring:message code="required"
                                                                                text="richiesto"/>)</small></label>
                                                    <input id="nomeProgettoProdotto"
                                                           name="progettiProdottiTranslation.nomeProgettoProdotto"
                                                           type="text" class="form-control"
                                                           placeholder=""
                                                           value="${dettaglio.progettiProdottiTranslation.nomeProgettoProdotto}"
                                                           path="nomeProgettoProdotto" maxlength="400">
                                                </div>
                                                <div class="form-group">
                                                    <label><spring:message code="form.dettaglio.progetto.type"
                                                                           text="Tipo"/> : <small>(<spring:message
                                                            code="required" text="richiesto"/>)</small></label>
                                                    <form:select id="selectTipo" name="selectTipo"
                                                                 title="${selectTheProjectTypeTitleAttr}"
                                                                 data-live-search="true"
                                                                 data-live-search-style="contains"
                                                                 path="plfTTipoProgettiProdotti.id"
                                                                 cssClass="selectpicker">
                                                        <form:options items="${tipoProgettoList}"/>
                                                    </form:select>
                                                </div>


                                                <div class="form-group">
                                                    <div id="labelSelectImpresa">
                                                        <label><spring:message code="common_texts.enterprise" text="Impresa"/> :</label>
                                                        <form:select id="selectImpresa"
                                                                     title="${selectTheCompanyLinkedToTheNewsTitleAttr}" data-live-search="true"
                                                                     data-live-search-style="contains"
                                                                     path="plfImpresa.idPlfImpresa" cssClass="selectpicker">
                                                            <form:options items="${impresaList}" />
                                                        </form:select>
                                                    </div>
                                                </div>

                                                <div class="form-group" hidden>
                                                    <label id="descProgettoLabel"
                                                           name="descProgettoLabel"><spring:message code="description"
                                                                                                    text="Descrizione"/>
                                                        <small>(<spring:message code="required"
                                                                                text="richiesto"/>)</small></label>
                                                    <input id="descProgetto"
                                                           name="progettiProdottiTranslation.descProgetto" type="text"
                                                           class="form-control"
                                                           placeholder=""
                                                           value="${dettaglio.progettiProdottiTranslation.descProgetto}"
                                                           path="descProgetto" maxlength="3990">
                                                </div>
                                                <div class="form-group" hidden><!-- hidden -->
                                                    <div class="checkbox">
                                                        <label class="center homecheckbox"> <input
                                                                id="checkboxProgettoOrigine"
                                                                name="checkboxProgettoOrigine"
                                                                type="checkbox"/> <strong>&nbsp;<spring:message
                                                                code="form.dettaglio.progetto.origin_project"
                                                                text="Progetto origine"/><small>&nbsp;(<spring:message
                                                                code="form.dettaglio.progetto.origin_project.info"/>...)
                                                        </small></strong>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br>

                                    </div>
                                    <!--  End step 1 -->

                                    <div class="tab-pane" id="step2">
                                        <h4 class="info-text"><spring:message
                                                code="form.nuovo.progetto.product_project_features"
                                                text="Caratteristiche progetto prodotto"/></h4>
                                        <div class="row">
                                            <div class="safe-col">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label id="descrizioneLabel"><spring:message code="form.dettaglio.progetto.topic"/>
                                                            : <small>(<spring:message code="required"/>)</small></label>
                                                        <textarea rows="4" id="descrizione"
                                                                  name="progettiProdottiTranslation.descrizione"
                                                                  class="form-control"
                                                                  maxlength="3990">${dettaglio.progettiProdottiTranslation.descrizione}</textarea>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group" id="obiettivi">
                                                        <label><spring:message code="form.dettaglio.progetto.targets"
                                                                               text="Obiettivi"/> : </label>
                                                        <textarea rows="4" id="obiettivi_txt"
                                                                  name="progettiProdottiTranslation.obiettivi"
                                                                  class="form-control"
                                                                  maxlength="1000">${dettaglio.progettiProdottiTranslation.obiettivi}</textarea>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="form.dettaglio.progetto.area"
                                                                               text="Settore"/>
                                                            : <small>(<spring:message code="required"/>)</small></label>
                                                        <div id="settoreTecnologie">
                                                            <form:select id="selectSettoreTecnologia" title="${selectTheAreaTitleAttr}" multiple="true"
                                                                         data-live-search="true" data-live-search-style="contains"
                                                                         path="elencoSettoreTecnologie"
                                                                         cssClass="selectpicker">
                                                                <form:options items="${listaSettoriTecnologie}" itemValue="id" itemLabel="descrizione"/>
                                                            </form:select>
                                                        </div>
                                                        <div id="settoreProgetto">
                                                            <form:select id="selectSettoreProgetto" title="${selectTheAreaTitleAttr}" multiple="true"
                                                                         data-live-search="true" data-live-search-style="contains"
                                                                         path="elencoSettoreProgettiProdotti"
                                                                         cssClass="selectpicker">
                                                                <form:options items="${listaSettoriProgetto}"  itemValue="id" itemLabel="descrizione"/>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group" hidden>
                                                <label><spring:message code="form.dettaglio.progetto.requirements"
                                                                       text="Requisiti"/>:</label>
                                                <textarea rows="4" name="progettiProdottiTranslation.descRequisiti"
                                                          class="form-control"
                                                          maxlength="3990">${dettaglio.progettiProdottiTranslation.descRequisiti}</textarea>
                                            </div>
                                            <div class="form-group" hidden>
                                                <label><spring:message code="form.dettaglio.progetto.finality"
                                                                       text="Finalit&agrave;"/>:</label>
                                                <textarea rows="4" name="progettiProdottiTranslation.descFinalita"
                                                          class="form-control"
                                                          maxlength="3990">${dettaglio.progettiProdottiTranslation.descFinalita}</textarea>
                                            </div>
                                            <div class="form-group" hidden>
                                                <label><spring:message code="results" text="Risultati"/>:</label>
                                                <textarea rows="4" name="progettiProdottiTranslation.descRisultati"
                                                          class="form-control"
                                                          maxlength="3990">${dettaglio.progettiProdottiTranslation.descRisultati}</textarea>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group col-md-12">
                                                    <label id="caratteristicheTecnicheLabel"
                                                           name="caratteristicheTecnicheLabel"><spring:message code="form.dettaglio.progetto.caratteristiche_tecniche"/></label>
                                                    <textarea name="progettiProdottiTranslation.caratteristicheTecniche" id="caratteristicheTecniche"
                                                              class="form-control" maxlength="3990">${dettaglio.progettiProdottiTranslation.caratteristicheTecniche}</textarea>
                                                </div>
                                            </div>


                                        </div>
                                    </div>
                                    <!-- End step 2 -->

                                    <div class="tab-pane" id="step3">
                                        <h4 class="info-text"><spring:message
                                                code="form.nuovo.progetto.employment_economic_and_contact_values"
                                                text="Valori occupazionali, economici e contatti"/></h4>
                                            <%-- <div class="col-sm-12"> --%>
                                        <div class="col-sm-6" hidden>
                                            <div class="form-group">
                                                <label><spring:message code="form.dettaglio.progetto.economic_value"
                                                                       text="Valore economico"/> : </label>
                                                <input name="numValoreEconomico" id="numValoreEconomico"
                                                       type="text" class="form-control" placeholder=""
                                                       path="numValoreEconomico"
                                                       value="<fmt:formatNumber value="${dettaglio.numValoreEconomico}" pattern="###,###,###,###,##0.00" />"
                                                       maxlength="18">
                                            </div>
                                        </div>
                                        <div class="col-sm-6" id="numDurataBox">
                                            <div class="form-group">
                                                <label><spring:message code="duration" text="Durata"/> : <small>
                                                    (<spring:message code="days" text="giorni"/>)</small></label>
                                                <input name="numDurata" id="numDurata"
                                                       type="number" class="form-control" placeholder=""
                                                       value="${dettaglio.numDurata}" min="0" max="99999999999999999">
                                            </div>
                                        </div>
                                            <%-- </div> --%>
                                            <%-- <div class="col-sm-12"><p>&nbsp;</p></div> --%>

                                            <%-- <div class="col-sm-12">  --%>
                                        <div class="col-sm-6" id="selectStatoBox">
                                            <div class="form-group">
                                                <label for="sito"><spring:message
                                                        code="form.dettaglio.progetto.project_status"
                                                        text="Stato progetto"/> :</label>
                                                <form:select id="selectStato"
                                                             title="${selectTheProjectStatusTitleAttr}"
                                                             data-live-search="true"
                                                             data-live-search-style="contains"
                                                             path="plfTStatoProgetto.id"
                                                             cssClass="selectpicker">
                                                    <form:options items="${statoProgettoList}"/>
                                                </form:select>
                                            </div>

                                        </div>
                                        <div class="col-sm-6" id="dataScadenzaBox">
                                            <div class="form-group">
                                                <label for="sito"><spring:message
                                                        code="form.dettaglio.progetto.expiry_date"
                                                        text="Data scadenza"/> :</label>

                                                <fmt:formatDate value="${dettaglio.dataScadenza}"
                                                                type="date"
                                                                pattern="dd/MM/yyyy"
                                                                var="dataScadenzaFormatted"/>
                                                <div class="input-group date" data-provide="datepicker"
                                                     data-date-format="dd/mm/yyyy" data-date-language="${env_locale}">
                                                    <input id="dataScadenza" name="dataScadenza" type="text"
                                                           class="form-control" value="${dataScadenzaFormatted}"
                                                           path="dataScadenza" autocomplete="off">
                                                    <div class="input-group-addon"><span
                                                            class="glyphicon glyphicon-th"></span></div>
                                                </div>

                                            </div>

                                        </div>
                                        <div class="col-sm-6" id="selectTipologiaBox">
                                            <div class="form-group">
                                                <label><spring:message code="form.dettaglio.progetto.tipology"
                                                                       text="Tipologia"/> : </label> <small>
                                                (<spring:message code="required"/>)</small>
                                                <form:select id="selectTipologia" name="plfTTipologiaProgetto.id"
                                                             title="${selectTipologyTitleAttr}" data-live-search="true"
                                                             data-live-search-style="contains"
                                                             path="plfTTipologiaProgetto.id"
                                                             cssClass="selectpicker">
                                                    <form:options items="${tipologiaProgettoList}"/>
                                                </form:select>
                                            </div>
                                        </div>

                                        <div class="col-sm-6" id="descContattiBox">
                                            <div class="form-group">
                                                <label><spring:message code="form.contatti.contacts_title"
                                                                       text="Contatti"/> :</label>
                                                <textarea rows="4" name="progettiProdottiTranslation.descContatti"
                                                          class="form-control"
                                                          maxlength="1000">${dettaglio.progettiProdottiTranslation.descContatti}</textarea>
                                            </div>

                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="sito"><spring:message code="web_site" text="Sito web"/> :</label>
                                                <input name="descSito" id="sito"
                                                       type="text" class="form-control" placeholder=""
                                                       value="${dettaglio.descSito}" path="descSito" maxlength="1000">
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div class="checkbox form-grid-checkbox">
                                                <label>
                                                    <input name="pubblicato" type="checkbox"/>
                                                    <strong>&nbsp;<spring:message code="form.dettaglio.informazione.published"/>&nbsp;</strong>
                                                </label>
                                            </div>
                                        </div>


                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label>
                                                    <strong>&nbsp;<spring:message code="form.dettaglio.informazione.attachment"/>&nbsp;</strong>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-sm-12" style="margin-bottom: 2%">
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
                                                <input name="progettiProdottiTranslation.descTag"
                                                       value="${dettaglio.progettiProdottiTranslation.descTag}" id="selectedTags" hidden>
                                                <div class="col-sm-12" id="tagsBox">
                                                    <div class="form-group">
                                                        <label>Tag</label><br>
                                                        <small><spring:message
                                                                code="form.dettaglio.impresa.tag_guide"/></small><br>
                                                        <c:forEach items="${allTags}" var="tag">
                                                            <div class="col-md-2 col-sm-2 element-tag tag-interactive"
                                                                 onclick="toggleTagSelection(this)">${tag}</div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                 --%>
                                            <br>
                                        </div>


                                    </div>
                                    <!--  End step 3 -->


                                    <div class="tab-pane" id="step4">
                                        <h4 class="info-text"><spring:message code="finish_and_send"
                                                                              text="Concludi e Invia"/></h4>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-10">
                                                    <p>
                                                        <label><strong><spring:message
                                                                code="form.richiesta.accreditamento.terms"
                                                                text="Termini e Condizioni"/></strong></label>
                                                        <spring:message code="form.nuova.news.impresa.terms.text"
                                                                        text="Ai sensi dell'art. 13 del D. Lgs. n. 196/2003 denominato \"Codice in materia di protezione dei dati personali\" informiamo che: Le informazioni immesse, nel rispetto della normativa sopra richiamata e degli obblighi di riservatezza, verranno utilizzate esclusivamente per le finalit&agrave; esposte e in conformit&agrave; allo scopo di condivisione della piattaforma di lavoro e formazione. Tali informazioni saranno trattate e conservate con strumenti informatici e non saranno comunicati ad altri soggetti, n&eacute; saranno oggetto di diffusione: verranno utilizzate nell'ambito specifico ed esclusivamente per le finalit&agrave; condivise."/>
                                                    </p>

                                                    <div class="checkbox">
                                                        <label>
                                                            <input id="checkboxAccept" name="checkboxAccept"
                                                                   type="checkbox"/> <strong>&nbsp;<spring:message
                                                                code="accept" text="Accetto"/>&nbsp;</strong>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--  End step 4 -->

                                </div>

                                <div class="wizard-footer">

                                    <div class="pull-right">
                                        <button type="button" class='btn btn-next btn-primary' name='next'
                                                value='Prossimo'>&nbsp;&nbsp;<spring:message code="next1"
                                                                                             text="Prossimo"/>&nbsp;&nbsp;
                                        </button>
                                        <button type="button" id="saveButton"
                                                class='btn btn-finish btn-primary'
                                                onClick="javascript:aggiorna(event);"
                                                value='Finito'>&nbsp;&nbsp;<spring:message code="finish" text="Finito"/>&nbsp;&nbsp;
                                        </button>
                                    </div>
                                    <div class="pull-left">
                                        <button type="button" class='btn btn-previous btn-default' name='previous'
                                                value='Precedente'>&nbsp;&nbsp;<spring:message code="back1"
                                                                                               text="Precedente"/>&nbsp;&nbsp;
                                        </button>
                                        <button class='btn btn-default' type="button" onClick="javascript:resetForm();">
                                            <spring:message code="common_texts.reset" text="ANNULLA"/></button>
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
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="undo"/></button>
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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript"
        charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript"
        charset="UTF-8"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script type="text/javascript"
        src="${evn_urlRisorseStatiche}/vimp/assets/js/summernote-bs4.js"></script>
<script type="text/javascript"
        src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/summernote-it-IT.js"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/checkModify.js"></script>

<script>
    var uploadImage = false;

    $(document).ready(
        function () {

            $('#selectTipo').on('change', function () {

                if ($('#selectTipo').val() == 2 || $('#selectTipo').val() == 3) {
                    $('#descProgettoLabel').html('<spring:message code="description" javaScriptEscape="true" text="Descrizione"/> <small>(<spring:message code="required" javaScriptEscape="true" text="richiesto"/>)</small>');
                    $('#selectTipologia').prop('disabled', true);
                    $('#numDurata').prop('disabled', true);
                    $('#dataScadenza').prop('disabled', true);
                } else {
                    $('#descProgettoLabel').html('<spring:message code="description" javaScriptEscape="true" text="Descrizione"/>');
                    $('#descProgetto').prop('disabled', false);
                    $('#numDurata').prop('disabled', false);
                    $('#dataScadenza').prop('disabled', false);
                    $('#selectTipologia').prop('disabled', false);
                }

                if ($('#selectTipo').val() == 1) {
                    $('#numDurataBox').show();
                    $('#selectStatoBox').show();
                    $('#dataScadenzaBox').show();
                    $('#selectTipologiaBox').show();
                    $('#descContattiBox').show();
                    $('#obiettivi').show();
                } else {
                    $('#numDurataBox').hide();
                    $('#selectStatoBox').hide();
                    $('#dataScadenzaBox').hide();
                    $('#selectTipologiaBox').hide();
                    $('#descContattiBox').hide();
                    $('#obiettivi').hide();
                }

                if($('#selectTipo').val() == 3){
                    $('#settoreTecnologie').show();
                    $('#settoreProgetto').hide();
                } else {
                    $('#settoreTecnologie').hide();
                    $('#settoreProgetto').show();
                }

                /*
                if ($('#selectTipo').val() == 1)
                {
                    $( '#descProgetto').val('');
                    $( '#descProgetto').prop( 'disabled', true );
                }
                else
                    $( '#descProgetto').prop( 'disabled', false );
                */
            });

            if (${dettaglio.progettoOrigine == 'S'})
                $('#checkboxProgettoOrigine').iCheck('check');
            else
                $('#checkboxProgettoOrigine').iCheck('uncheck');
            $('#checkboxProgettoOrigine').on('ifChecked', function (event) {
                $('#progettoOrigine').val('S');
            });
            $('#checkboxProgettoOrigine').on('ifUnchecked', function (event) {
                $('#progettoOrigine').val('N');
            });


            $('#numValoreEconomico').keydown(function (event) {


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

                if ($(this).val().indexOf('.') !== -1 && event.keyCode == 190)
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
                    } else if (index == 4) {
                        return validateFourthStep();
                    } //etc.

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

            setValidation();

            setCheckInsert();

        });

    function setValidation() {
        $('#frmDettaglio').validate({
            errorPlacement: function(error, element) {
                if(element.parent('.input-group').length) {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            },
            rules: {
                'progettiProdottiTranslation.nomeProgettoProdotto': 'required',
                'plfTTipoProgettiProdotti.id': 'required',
                'progettiProdottiTranslation.descrizione' : 'required',
                checkboxAccept: 'required'
            },
            messages: {
                'progettiProdottiTranslation.nomeProgettoProdotto': '<spring:message code="progettoFormNuovoInserisciNome" javaScriptEscape="true" />',
                'progettiProdottiTranslation.descrizione' : '<spring:message code="form.nuovo.progetto.insert_description" javaScriptEscape="true" />',
                'plfTTipoProgettiProdotti.id': '<spring:message code="progettoFormNuovoInserisciTipo" javaScriptEscape="true" />',
                checkboxAccept: '<spring:message code="perProcedereConRegistrazioneAccettareTermini" javaScriptEscape="true" />',
                numDurata: '<spring:message code="form.progetti.prodotti.max_durata" javaScriptEscape="true"/> 99999999999999999'
            }
        });
    }

    function aggiorna(event) {
    	removeCheckInsert();
        event.preventDefault();
        if (uploadImage) {

            var fileSize = $('#wizardPicturePreview').attr('src').length;
            if (fileSize > 1572864) {
                alert('<spring:message code="immagineTroppoGrandeMax1.5M" javaScriptEscape="true" text="Attenzione! Immagine troppo grande (max 1.5 MB)"/>');
                return;
            }

            $('#imageData').val($('#wizardPicturePreview').attr('src'));
            $('#frmDettaglio').submit();
        } else {
            $('#imageData').val('');
            $('#frmDettaglio').submit();
        }
    }


    function formatNumber(number, c, d, t) {
        c = isNaN(c = Math.abs(c)) ? 2 : c;
        d = d == undefined ? '.' : d;
        t = t == undefined ? ',' : t;
        s = number < 0 ? '-' : '';
        i = String(parseInt(number = Math.abs(Number(number) || 0).toFixed(c)));
        j = (j = i.length) > 3 ? j % 3 : 0;
        var ret = s + (j ? i.substr(0, j) + t : '')
            + i.substr(j).replace(/(\d{3})(?=\d)/g, '$1' + t)
            + (c ? d + Math.abs(number - i).toFixed(c).slice(2) : '');
        return ret;
    }

    function getCurrentYear() {
        return (new Date()).getFullYear();
    }


    function validateFirstStep() {

        let form = $('#frmDettaglio');
        let validateSettings = form.validate().settings;

        if ($('#selectTipo').val() == 1 || $('#selectTipo').val() == 2) {

            validateSettings.rules['elencoSettoreProgettiProdotti'] = 'required';
            validateSettings.messages['elencoSettoreProgettiProdotti'] = '<spring:message code="form.progetti.prodotti.settore_required" javaScriptEscape="true"/>';

            if($('#selectTipo').val() == 1) {
                validateSettings.rules['plfTTipologiaProgetto.id'] = 'required';
                validateSettings.messages['plfTTipologiaProgetto.id'] = '<spring:message code="form.progetti.prodotti.tipologia_required" javaScriptEscape="true"/>';
            }

            delete validateSettings.rules['elencoSettoreTecnologie'];
            delete validateSettings.messages['elencoSettoreTecnologie'];

        } else if ($('#selectTipo').val() == 3) {

            validateSettings.rules['elencoSettoreTecnologie'] = 'required';
            validateSettings.messages['elencoSettoreTecnologie'] = '<spring:message code="form.progetti.prodotti.settore_required" javaScriptEscape="true"/>';

            delete validateSettings.rules['elencoSettoreProgettiProdotti'];
            delete validateSettings.messages['elencoSettoreProgettiProdotti'];
            delete validateSettings.rules['plfTTipologiaProgetto.id'];
            delete validateSettings.messages['plfTTipologiaProgetto.id'];
        }

        return form.valid();
    }


    function validateSecondStep() {
        return $('#frmDettaglio').valid();
    }


    function validateThirdStep() {
        $('#frmDettaglio').validate({
            rules: {},
            messages: {}
        });

        if (!$('#frmDettaglio').valid()) {
            return false;
        }

        return true;
    }


    function validateFourthStep() {
        $('#frmDettaglio').validate({
            rules: {},
            messages: {}
        });

        if (!$('#frmDettaglio').valid()) {
            return false;
        }

        return true;
    }

    function resetForm() {
        $('#resetFormConfirm').modal();
    }

    function reloadPage() {
        var baseUrl = window.location.href.split('/vimp')[0];
        originalUrl = baseUrl + '/vimp' + '${refreshRelativeUrl}';
        window.location.replace(originalUrl); // Usare questo se ${refreshRelativeUrl} arriva dall'handler
        //location.reload(true); // Usare questo nel caso non si voglia usare ${refreshRelativeUrl}
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


<style>
    #checkboxAccept-error {
        width: 1000px;
        max-width: 1000px;
        margin-top: 25px;
    }
</style>


<style>
    #checkboxProgettoOrigine-error {
        width: 1000px;
        max-width: 1000px;
        margin-top: 25px;
    }
</style>

<!-- END NUOVO PROGETTO RICERCA-->
<!-- =========================================================================================== -->