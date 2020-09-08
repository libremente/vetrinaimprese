<!-- =========================================================================================== -->
<!-- BEGIN NUOVO PACCHETTO -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:message var="selectMacroareaPacchettoTitleAttr" code="form.dettaglio.pacchetto.selectMacroareaPacchettoTitleAttr"/>
<spring:message var="selectServiziUtenteTitleAttr" code="form.dettaglio.pacchetto.selectServiziUtenteTitleAttr"/>
<spring:message var="selectServiziGeneralTitleAttr" code="form.dettaglio.pacchetto.selectServiziGeneralTitleAttr"/>

<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title"><spring:message code="form.nuovo.pacchetto.insert_new_pacchetto"/></h1>
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
                    <strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="operation_not_permitted" />.
                </div>
            </c:when>
            <c:otherwise>

                <div class="clearfix">
                    <div class="wizard-container">

                        <div class="wizard-card ct-wizard-orange" id="wizardInsert">

                            <form:form id="frmDettaglio" method="POST"
                                       action="/vimp/secure/salvaPacchettoServizi" role="form"
                                       modelAttribute="dettaglio" class="safe-reload">

                                <input id="imageData" name="imageData" type="hidden">

                                <div class="wizard-header">
                                    <h3>
                                        <b><spring:message code="insert.uppercase" /></b> <spring:message code="form.nuovo.pacchetto.new_pacchetto" /> <br> <small>
                                        <spring:message code="promote_visibility" /></small>
                                    </h3>
                                </div>

                                <ul>
                                    <li><a href="#step1" data-toggle="tab"><spring:message code="first_step" /> </a></li>
                                    <li><a href="#step2" data-toggle="tab"><spring:message code="second_step" /> </a></li>
                                    <li><a href="#step3" data-toggle="tab"><spring:message code="third_step" /> </a></li>
                                    <li><a href="#step4" data-toggle="tab"><spring:message code="finish" /> </a></li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane" id="step1">
                                        <div class="row p-b-15  ">
                                            <h4 class="info-text"><spring:message code="start_with_basic_data"/></h4>
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
                                                    <label><spring:message code="form.dettaglio.pacchetto.pacchetto_name" /> : <small>(<spring:message code="required" />)</small></label>
                                                    <input id="titolo" name="pacchettoServiziTranslation.titolo" type="text"
                                                           class="form-control" placeholder=""
                                                           value="${dettaglio.pacchettoServiziTranslation.titolo}" path="titolo"
                                                           maxlength="400">
                                                </div>


                                                <div class="form-group">
                                                    <label><spring:message code="description"/> : <small>(<spring:message code="required"/>)</small></label>
                                                    <textarea name="pacchettoServiziTranslation.descrizione" id="descrizione" class="form-control"  maxlength="3990">${dettaglio.pacchettoServiziTranslation.descrizione}</textarea>
                                                </div>

                                                <div class="form-group">
                                                    <label><spring:message code="form.dettaglio.pacchetto.macroarea" /> : <small>(<spring:message code="required" />)</small></label>
                                                    <form:select id="selectMacroarea"
                                                                 title="${selectMacroareaPacchettoTitleAttr}"
                                                                 data-live-search="true" data-live-search-style="contains"
                                                                 path="macroarea.id"
                                                                 cssClass="selectpicker">
                                                        <form:options items="${macroareeList}" />
                                                    </form:select>
                                                </div>

                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                    <!--  End step 1 -->

                                    <div class="tab-pane" id="step2">
                                        <h4 class="info-text"><spring:message code="common_texts.features" /></h4>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="form.dettaglio.pacchetto.note1" /> </label>
                                                        <input id="note1" name="pacchettoServiziTranslation.note1" type="text"
                                                               class="form-control" placeholder=""
                                                               value="${dettaglio.pacchettoServiziTranslation.note1}" path="note1"
                                                               maxlength="400">
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="form.dettaglio.pacchetto.note2" /> </label>
                                                        <input id="note2" name="pacchettoServiziTranslation.note2" type="text"
                                                               class="form-control" placeholder=""
                                                               value="${dettaglio.pacchettoServiziTranslation.note2}" path="note2"
                                                               maxlength="400">
                                                    </div>
                                                </div>

                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="form.dettaglio.pacchetto.luogo_erogazione" /> : <small>(<spring:message code="required" />)</small></label>
                                                        <input id="luogoErogazione" name="luogoErogazione" type="text"
                                                               class="form-control" placeholder=""
                                                               value="${dettaglio.luogoErogazione}" path="luogoErogazione"
                                                               maxlength="400">
                                                    </div>
                                                </div>

                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><spring:message code="form.dettaglio.pacchetto.contatti" /> : <small>(<spring:message code="required" />)</small> </label>
                                                        <input id="contatti" name="contatti" type="text"
                                                               class="form-control" placeholder=""
                                                               value="${dettaglio.contatti}" path="contatti"
                                                               maxlength="400">
                                                    </div>
                                                </div>
                                                
                                                
                                                
                                                <div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="date" text="Data"/> :<small>(<spring:message code="required" text="richiesto"/>)</small></label>
														<fmt:formatDate value="${dettaglio.dataInizio}" type="date" pattern="dd/MM/yyyy" var="dataInizioFormatted" />
														<div class="input-group date" data-provide="datepicker" data-date-language="${env_locale}"
															 data-date-format="dd/mm/yyyy" >
															<input name="dataInizio" type="text" class="form-control" id="dataInizio"
																   value="${dataInizioFormatted}" path="dataInizio" autocomplete="off">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label><spring:message code="end_date" text="Data fine"/> :</label>
														<fmt:formatDate value="${dettaglio.dataFine}" type="date"
																		pattern="dd/MM/yyyy" var="dataFineFormatted" />
														<div class="input-group date" data-provide="datepicker" data-date-language="${env_locale}"
															 data-date-format="dd/mm/yyyy">
															<input name="dataFine" type="text" class="form-control" id="dataFine" autocomplete="off"
																   value="${dataFineFormatted}" path="dataFine" >
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div>

                                            </div>
                                        </div>
                                    </div>

                                    <!-- End step 2 -->

                                    <div class="tab-pane" id="step3">
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

                                            <div class="row">
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
                                                        <%--
                                                        <div class="form-group">
                                                            <input name="pacchettoServiziTranslation.descTag"
                                                                   value="${dettaglio.pacchettoServiziTranslation.descTag}" id="selectedTags"
                                                                   hidden="true">
                                                            <label>Tag </label><br>
                                                            <small><spring:message code="form.dettaglio.impresa.tag_guide"/></small><br>
                                                            <c:forEach items="${allTags}" var="tag">
                                                                <div class="col-md-2 col-sm-2 element-tag tag-interactive"
                                                                     onclick="toggleTagSelection(this)">${tag}</div>
                                                            </c:forEach>
                                                        </div>
                                                        --%>
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
                                        </div>
                                    </div>
                                        <%--END step 4--%>

                                    <div class="tab-pane" id="step4">
                                        <h4 class="info-text"> <spring:message code="finish_and_send" /> </h4>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-10">
                                                    <p>
                                                        <label><strong><spring:message code="form.richiesta.accreditamento.terms" /></strong></label>
                                                        <spring:message code="form.nuovo.pacchetto.terms.text" />
                                                    </p>

                                                    <div class="checkbox">
                                                        <label>
                                                            <input id="checkboxAccept" name="checkboxAccept" type="checkbox" /> <strong>&nbsp;<spring:message code="accept" />&nbsp;</strong>
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
                                        <button type="button" class='btn btn-next btn-primary' name='next' value='Prossimo'>&nbsp;&nbsp;<spring:message code="next1"/>&nbsp;&nbsp;</button>
                                        <button type="button" id="saveButton" class='btn btn-finish btn-primary'
                                                onClick="javascript:aggiorna(event);" value='Finito'>&nbsp;&nbsp;<spring:message code="finish"/>&nbsp;&nbsp;</button>
                                    </div>
                                    <div class="pull-left">
                                        <button type="button" class='btn btn-previous btn-default' name='previous' >&nbsp;&nbsp;<spring:message code="back1" />&nbsp;&nbsp;</button>
                                        <button class='btn btn-default' type="button" onClick="javascript:resetForm();"><spring:message code="common_texts.reset" /></button>
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

<script	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.it_IT.js" type="text/javascript" charset="UTF-8"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/bootstrap-datepicker.en_US.js" type="text/javascript" charset="UTF-8"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/summernote-bs4.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/lang/summernote-it-IT.js"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/checkModify.js"></script>

<script>
    var uploadImage = false;
	var summernoteValidator;
	var summernoteForm;
	var summernoteElement;
    var checkServiziDuplicati = false;
    var serviziUtenteCollegati = [];
    var serviziGeneralCollegati = [];

    $(function () {


        $('.datepicker').datepicker({
            format : 'yyyy-mm-dd',
            viewformat : 'dd/mm/yyyy',
            language : '${env_locale}'
        });

        $('#wizard-picture').change(function() {
            uploadImage = true;
        });

        $('#descrizione').summernote({
			onChange: function (contents, $editable) {
				summernoteElement.val(summernoteElement.summernote('isEmpty') ? '' : contents);
				summernoteValidator.element(summernoteElement);
			},
			lang: '${env_locale}',
			placeholder: '<spring:message code="pacchettoFormNuovaTextPlaceholder" text="Descrizione" />',
			tabsize: 2,
			height: 150,
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New'],
			toolbar: [
				['style', ['bold', 'italic', 'underline', 'clear']],
				['fontname', ['fontname']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['height', ['height']]
			]
		});

		summernoteForm = $('#frmDettaglio');
		summernoteElement = $('.summernote');

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
                } else if(index == 4){
                    return validateFourthStep();
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

        displayTabelleServizi();
        setCheckInsert();

    });

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

    function validateFirstStep() {


    	summernoteValidator = $('#frmDettaglio').validate({
			ignore: ':hidden:not(.validate),hidden:not(.summernote),.note-editable.card-block',
			errorPlacement: function(error, element) {
				if(element.parent('.input-group').length) {
					error.insertAfter(element.parent());
				} else {
					error.insertAfter(element);
				}
			},
            rules: {
                'pacchettoServiziTranslation.titolo': 'required',
                'macroarea.id': 'required',
                'luogoErogazione': 'required',
                'contatti': 'required',
                checkboxAccept: 'required',
                dataInizio: 'required',
				dataFine: "checkDate"
            },
            messages: {
                'pacchettoServiziTranslation.titolo' : '<spring:message code="pacchettoFormNuovoInserireNome" javaScriptEscape="true" />',
                'macroarea.id': '<spring:message code="pacchettoFormNuovoSelectMacroarea" javaScriptEscape="true" />',
                'luogoErogazione': '<spring:message code="pacchettoFormNuovoInserireLuogoErogazione" javaScriptEscape="true" />',
                'contatti': '<spring:message code="pacchettoFormNuovoInserireContatti" javaScriptEscape="true" />',
                checkboxAccept: '<spring:message code="perProcedereConRegistrazioneAccettareTermini" javaScriptEscape="true" />',
                dataInizio: '<spring:message code="pacchettoInserireLaData" javaScriptEscape="true" />',
				dataFine: '<spring:message code="erroreDateNews" javaScriptEscape="true" />'
            }
        });



    	var test = $('#descrizione').val();
		if(test.length < 1) {
			$('#frmDettaglio').validate().showErrors({
				'pacchettoServiziTranslation.descrizione': '<spring:message code="pacchettoFormNuovoInserireDescrizione" javaScriptEscape="true" />'
			});
			return false;
		}
		
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

        if(!validateServizi()){
            errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="form.dettaglio.pacchetto.due_servizi" javaScriptEscape="true" />')
            return false;
        }

        if(!validateServiziUtente() && ${not utente.isBackoffice()}){
            errorModal('<spring:message code="warning" javaScriptEscape="true"/>','<spring:message code="form.dettaglio.pacchetto.obbligo_servizi_utente" javaScriptEscape="true"/>');
            return false;
        }

        validateServiziDuplicati();

        $('#frmDettaglio').validate({
            rules: {
            },
            messages: {
            }
        });

        if (!$('#frmDettaglio').valid() || !checkServiziDuplicati) {
            return false;
        }

        return true;
    }

    function validateFourthStep() {
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
        window.location.replace(originalUrl); <%-- Usare questo se ${refreshRelativeUrl} arriva dall'handler
        location.reload(true); // Usare questo nl caso non si voglia usare ${refreshRelativeUrl} --%>
    }

    function collegaServiziUtente() {
        var id = $('#selectServiziUtente option:selected').val();
        var denominazione = $('#selectServiziUtente option:selected').text();
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

    function validateServiziDuplicati() {
        var idServizi = arrayMerge(serviziUtenteCollegati, serviziGeneralCollegati);

        var obj = { servizi: idServizi };

        $.ajax({
            url: '/vimp/secure/checkPacchettoServizi',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            dataType: 'json',
            statusCode: {
                200: function(){
                    checkServiziDuplicati= true;
                    $('#wizardInsert').bootstrapWizard('next');
                    $('#frmDettaglio').validate().resetForm();
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


<style>
    #checkboxAccept-error {
        width: 1000px;
        max-width: 1000px;
        margin-top: 25px;
    }
</style>


<!-- END NUOVO PACCHETTO -->
<!-- =========================================================================================== -->
