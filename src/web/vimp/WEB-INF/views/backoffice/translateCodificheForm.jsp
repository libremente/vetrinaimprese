<!-- =========================================================================================== -->
<!-- BEGIN PARAMETRI RICERCA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="localized_msg" hidden="true">
	<spring:message code="translate_codifiche.error_save" text="La modifica non è andata a buon fine. Controlla i dati e riprova." var="error_save"/>
	<spring:message code="translate_codifiche.success" text="Salvataggio avvenuto con successo." var="success"/>
	<spring:message code="translate_codifiche.success_delete" text="Eliminazione avvenuta con successo." var="successDelete"/>
	<spring:message code="translate_codifiche.error_duplicated_fields" javaScriptEscape="true" var="duplicateError"/>
	
	<spring:message code="messaggioNotDeleteCodifica" text="La cancellazione di questa denominazione non puo' essere effettuata in quanto utilizzata in {1} servizi." var="messaggioNotDeleteCodifica_message"/>
	<spring:message code="messaggioUpdateServiceCodifica" text="Questa denominazione e' utilizzata in {1} servizi. L'aggiornamento comporta la modifica dove e' utilizzata. Vuoi continuare?" var="messaggioUpdateServiceCodifica_message"/>
	
	
	<span id="CODICE"><spring:message code="translate_codifiche.code" javaScriptEscape="true"/></span>
	<span id="DESCRIZIONE"><spring:message code="translate_codifiche.description" javaScriptEscape="true"/></span>

</div>
<c:choose>
	<c:when test="${!utente.isBackoffice() && !utente.isStakeholder() && !utente.isImpresa() }">
		&nbsp;
	</c:when>
	<c:otherwise> 
    <div class="content-area submit-property safe-reload">&nbsp;
	    <div class="row tables_section">
	        <h2 class="center"><small><spring:message code="translate_codifiche.title" text="Traduzione codifiche"/></small></h2> 
	        <div class="center"><spring:message code="translate_codifiche.select_table"/></div>     
	    </div>
	    <div id="success_div" class="alert alert-success fade in" hidden="true">
			<button type="button" class="close" onclick="hideSuccessMessage()"
				aria-hidden="true">&times;</button>
			<p><span id="success_message"></span></p>
		</div>
	        	
		<div id="warning_div" class="alert alert-warning fade in" hidden="true">
			<button type="button" class="close" onclick="hideWarningMessage()"
				aria-hidden="true">&times;</button> 
			<p>
				<strong><spring:message code="warning"></spring:message></strong> 
				<span id="warning_message"></span>
			</p>
		</div>
		
		<div id="alert_div" class="alert alert-danger fade in" hidden="true">
			<button type="button" class="close" onclick="hideAlertMessage()"
				aria-hidden="true">&times;</button>
			<p>
				<strong><spring:message code="error"></spring:message></strong>
				<span id="error_message"></span>
			</p>
		</div>
           	<div>
				<div id="frmParametri">
			 		<div class="content-area submit-property">&nbsp;
            			<div class="container tables_section">
            			<input id="selected_table" value="" hidden="true"/>
            				<ul>
	            				<c:forEach items="${tables}" var="table">
						            	<li class="col-md-4">
							            	<button type="button" class="btn" onclick="searchCodifiche('${table.originalName}', '${languages}', '${table.isFieldCodeVisible}')">
												${table.showName}
											</button>
					            		</li>
							     </c:forEach>
						     </ul>
					     </div>
					     <div class="container" id="form_section"   hidden="true">
					     <div class="center">
				        	<button type="button" class="btn btn-default" onclick="showTables()">
				        		<spring:message code="translate_codifiche.show_tables"/>
			        		</button>
			        		<button type='button' class='btn btn-default' style='margin:10px 0' onclick='newEncoding()'>
			        			<spring:message code='translate_codifiche.new_encoding'/>
			        		</button>
			        	</div>
                         <div class="center">
                             <h4 id="selected_table_label"></h4>
                         </div>
							<div class="form-group">		
								<input type="text" id="inputCercaCodifica" placeholder="Cerca codifica" class="form-control form-input" style="margin:10px 0; width: 95%" onkeyup="searchCodificheBtns()" />		
								<div id="codificheBtns" class="col-md-6 float-left">
               					  	<img src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif"/>  
								</div>
								<form id="formCodifiche" name="formCodifiche" class="col-md-6 float-right">
									<div class="clearfix" id="form_container" hidden="true"> 
                    					<div class="wizard-container">          					
                    						<c:forEach items="${languages}" var="lang">
		                    					<div id="form_${lang}" class="wizard-card ct-wizard-orange" id="wizardInsert" style="padding: 10px; margin-bottom: 10px;" >
			                    					<div class="wizard-header">
			                    						<img class="loading_codifica" src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif" hidden="true"/>	
					                                    <h3><img id="imgFlag_${lang}"/></h3>
						                                             			                               
						                            </div>
						                            <input id="id_${lang}" name="id_${lang}" class="form-input" value="" hidden="true"/>
						                            <label id="codiceLabel" name="codiceLabel"><spring:message code="translate_codifiche.code" text="Codice"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
														<small> <spring:message code="max_length"/>: 100</small>
													</label>
													<input id="codice_${lang}" name="codice_${lang}" type="text" class="form-control form-input" value="">
													<div>
														<label><spring:message code="translate_codifiche.description" text="Descrizione"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
															<small> <spring:message code="max_length"/>: 255</small>
														</label>
														<input id="descrizione_${lang}" name="descrizione_${lang}" type="text" class="form-control form-input" value="">
													</div>
													<div class="extension_div" hidden="true">
													<label><spring:message code="translate_codifiche.extension" text="Estensione"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
													</label>
													<input id="estensione_${lang}" name="estensione_${lang}" type="text" class="form-control form-input" value="">
													</div>						
												</div>
											</c:forEach>
											<button type="button" class="btn btn-danger float-left" id="delete_btn" onclick="confirmDelete()" disabled>
												<spring:message code="translate_codifiche.delete_encoding" text="Elimina codifica"/>
											</button>
											<button type="button" class="btn btn-default float-right" id="save_btn" onclick="confirmUpdate()">
												<spring:message code="save" text="Salva"/>
											</button>
											
										</div>
									</div>
								</form>
							</div>
           				</div>
      				</div>
   				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div class="modal-body">
				<spring:message code="messaggioDeleteCodifica"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onClick="javascript:disableCodifica();"><spring:message code="continue"/></button>			
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="confirmSimpleDelete" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div class="modal-body">
				<spring:message code="messaggioSimpleDeleteCodifica"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onClick="javascript:disableCodifica();"><spring:message code="continue"/></button>			
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="messageNotDelete" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div id='messageNotDeleteText' class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="confirmUpdate" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div class="modal-body">
				<spring:message code="messaggioUpdateCodifica"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onClick="javascript:updateCodifica();"><spring:message code="continue"/></button>			
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="confirmServiceUpdate" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div id='confirmServiceUpdateText' class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onClick="javascript:updateCodifica();"><spring:message code="continue"/></button>			
			</div>
		</div>
	</div>
</div>


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery-1.10.2.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/bootstrap/js/bootstrap.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-select.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/owl.carousel.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wow.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/icheck.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-editable.js"></script>	

<script>

var validator;

$(function () {
    $('#formDelegato').submit(submitForm);
    setImgFlags();
    hideWholeForm('${selectedLocale}');
});

function hideWholeForm(selectedLang) {

	var langs = toParsedLanguages('${languages}');
	var baseSrc = '${evn_urlRisorseStatiche}/vimp/assets/img/flags/';

	for(var x = 0; x < langs.length; x++) {
		if(langs[x] !== selectedLang) {
			$('#form_' + langs[x]).hide();
		}
	}
	
}

function setImgFlags() {

	var langs = toParsedLanguages('${languages}');
	var baseSrc = '${evn_urlRisorseStatiche}/vimp/assets/img/flags/';

	for(var x = 0; x < langs.length; x++) {
		var splitted = langs[x].split('_');
		var finalStr = splitted[0].toLowerCase() + '_' + splitted[1];
		$('#imgFlag_' + langs[x]).attr('src', baseSrc + finalStr + '.png');
	}
	
}

function showTables() {
	hideWarningMessage();
	hideAlertMessage();
	hideSuccessMessage();
    $('#selected_table_label').html('');
	$('#form_section').hide();
	$('.tables_section').show();
	$('#tables_btn').hide();
	emptyForm();
	$('#selecte_table').val('');
	$('#codificheBtns').html('');
}

function newEncoding() {
	hideWarningMessage();
	hideAlertMessage();
	hideSuccessMessage();
	if (validator) validator.resetForm();
	emptyForm();
}

function hideTables() {
	$('#form_section').show();
	$('.tables_section').hide();
	$('#tables_btn').show();
}

var globalTempCodificheArray = [];
var globalFilteredCodificheArray = [];
var globalIsFieldCodeVisible = false;

function setTableNameLabel(tableName) {
    var finalString = tableName.replace('PLF_T_', '');
    finalString = finalString.replace(/_/g, ' ');
    $('#selected_table_label').html(finalString);
}

function searchCodifiche(tableName, languages, isFieldCodeVisible) {


	globalIsFieldCodeVisible = (isFieldCodeVisible === 'TRUE');

	var languagesParsed = toParsedLanguages(languages);
	for(var i = 0; i < languagesParsed.length; i++) {
		var tLang = languagesParsed[i];
		if (globalIsFieldCodeVisible)
		{
			$('#codiceLabel').show();
			$('#codice_' + tLang).show();
		}
		else
		{
			$('#codiceLabel').hide();
			$('#codice_' + tLang).hide();
		}
	}
	
	setTableNameLabel(tableName);
	$('#selected_table').val(tableName);
	var splitted = window.location.href.split('/vimp/');

	if(tableName === 'PLF_T_ATECO') {
		window.location.href = splitted[0] + '/vimp/secure/translateAteco';
	} else {
		hideTables();
		emptyForm(tableName);
		
		if(tableName === 'PLF_T_TIPO_ALLEGATO') {
			$('.extension_div').show();
		}
		onLoadBtns();
		
		var url = splitted[0] + '/vimp/secure/elencoCodifiche.json?table=' + tableName;
		$.ajax({
		  url: url,
		  context: document.body
		}).done(function(res) {
			showForm();
			globalTempCodificheArray = res[tableName];
			populateCodificheButtons(res[tableName], languages);
			$('.rigaCodifica').removeClass('trCodificheFormSelected');
		});
	}
}

function populateCodificheButtons(lista, languages, startIndex) {

	$('#formCodifiche').validate().resetForm();

	if(!startIndex) {startIndex = 0;}
	$('#codificheBtns').html(setCodificheBtnHtml(lista, 5, startIndex));
	$('#tr'+$('#id_' + '${defaultLocale}').val()).addClass('trCodificheFormSelected');
}

function setCodificheBtnHtml(itemArray, numberToDisplay, startItem){
	if(!numberToDisplay) {numberToDisplay = 5;}
	if(!startItem) {startItem = 0;}
	var tableName = $('#selected_table').val();
	var howManyPages = 0;
    finalHtml = "<div class='table-responsive'><table id='tableCodifiche' class='table table-condensed bordered1'><thead>";
    if(itemArray.length < numberToDisplay) {
    	numberToDisplay = itemArray.length;
    }

    if (globalIsFieldCodeVisible)
    	finalHtml += '<th><spring:message code="translate_codifiche.code" text="Codice"/></th>';
    	
    finalHtml += '<th><spring:message code="translate_codifiche.description" text="Descrizione"/></th>';
    if(tableName == 'PLF_T_TIPO_ALLEGATO'){
    	finalHtml += '<th><spring:message code="translate_codifiche.extension" text="Estensione"/></th>';
    }
    finalHtml += "</thead><tbody>";
	for(var i = 0; i < numberToDisplay; i++){
		if((i + startItem + 1) <= (itemArray.length)){
			var descrizione = itemArray[i+startItem].DESCRIZIONE;
			var finalDesc = descrizione;
			finalHtml += "<tr id='tr" + itemArray[i+startItem].ID + "' class='rigaCodifica' onclick='onCodificaChoosen(" + itemArray[i+startItem].ID + ", " + JSON.stringify('${languages}') + ")'>";
			if (globalIsFieldCodeVisible)
				finalHtml += "<td> " + itemArray[i+startItem].CODICE + "</td>";
			finalHtml += "<td>" + finalDesc + "</td>" + ((tableName == 'PLF_T_TIPO_ALLEGATO') ? '<td>'+itemArray[i+startItem].ESTENSIONE+'</td>' : '') + "</tr>";
		}
	}
	finalHtml += "</tbody></table></div>";
	howManyPages = (itemArray.length > numberToDisplay) ? (itemArray.length/numberToDisplay)+1 : itemArray.length/numberToDisplay;
	globalFilteredCodificheArray = itemArray;
	finalHtml += '<div class="row paginazione"><ul class="pagination">';
	if(startItem != 0) {
		finalHtml += "<li><a href='javascript:changePage(" + (startItem-numberToDisplay) + ")'>&laquo;</a></li>";
	}else{
		finalHtml += "<li><a href='javascript:changePage(" + 0 + ")' style='pointer-events:none;cursor:default;background-color:#e8e8e8;'>&laquo;</a></li>";
	}
	for(var i = 1; i <= howManyPages; i++){
		if((i-1)*numberToDisplay == startItem){
			finalHtml += "<li  class='active'><a href='javascript:changePage(" + (i-1)*numberToDisplay + ")'>"+i+"</a></li>";
		}else{
			finalHtml += "<li><a href='javascript:changePage(" + (i-1)*numberToDisplay + ")'>"+i+"</a></li>";
		}
	}
	if(itemArray[startItem+numberToDisplay]){
		finalHtml += "<li><a href='javascript:changePage(" + (startItem+numberToDisplay) + ")'>&raquo;</a></li>";
	}else{
		finalHtml += "<li><a href='javascript:changePage(" + (((howManyPages-1)*numberToDisplay)-1) + ")' style='pointer-events:none;cursor:default;background-color:#e8e8e8;'>&raquo;</a></li>";
	}
	finalHtml += '</ul></div>';
	return finalHtml;
}

function changePage(startIndex){
	populateCodificheButtons(globalFilteredCodificheArray, '${languages}', startIndex);
}

function searchCodificheBtns(){
	var filteredList = [];
	if ($("#inputCercaCodifica").val() !== '') {
        filteredList = globalTempCodificheArray.filter(
            function(item) {
				return item['DESCRIZIONE'].toLowerCase().indexOf($("#inputCercaCodifica").val().toLowerCase()) >= 0
			}
        );
        populateCodificheButtons(filteredList, '${languages}');
    } else {
    	populateCodificheButtons(globalTempCodificheArray, '${languages}');
    }
	hideSuccessMessage();
	hideAlertMessage();
}

function onCodificaChoosen(idCodifica, languages) {
	var parsedLanguages = toParsedLanguages(languages);
	var tableName = $('#selected_table').val();
	var splitted = window.location.href.split('/vimp/');
	var url = splitted[0] + '/vimp/secure/translatedCodificheByid.json?table=' + tableName + '&id='+ idCodifica;
	$('.rigaCodifica').removeClass('trCodificheFormSelected');
	$('#tr'+idCodifica).addClass('trCodificheFormSelected');
	onLoadCodifica();
	$.ajax({
	  url: url,
	  context: document.body
	}).done(function(res) {
		setCodificaInForm(res, parsedLanguages);
		stopLoadCodifica();
	});
	hideSuccessMessage();
	hideWarningMessage();
	hideAlertMessage();
	if (validator) validator.resetForm();
}

function toParsedLanguages(stringified){
	var parsed = stringified.replace(' ', '');
	parsed = parsed.replace('[', '');
	parsed = parsed.replace(']', '');
	parsed = parsed.split(',');
	return parsed;
}

function emptyForm(tableName) {
	$('.form-input').val('');
	$('#delete_btn').attr('disabled', 'true');
	var table = tableName ? tableName : $('#selected_table').val();

	if(table !== 'PLF_T_TIPO_ALLEGATO') {
		$('.extension_div').hide();
	}
	$('.rigaCodifica').removeClass('trCodificheFormSelected');
}

function setCodificaInForm(codifiche, languages) {

	for(var i = 0; i < languages.length; i++) {
		var tLang = languages[i];
		var tableName = $('#selected_table').val();

		$('#delete_btn').removeAttr('disabled');
		
		$('#id_' + tLang).val(codifiche[tLang].ID);
		$('#descrizione_' + tLang).val(codifiche[tLang].DESCRIZIONE);


		$('#codice_' + tLang).val(codifiche[tLang].CODICE);
		if (globalIsFieldCodeVisible)
		{
			$('#codiceLabel').show();
			$('#codice_' + tLang).show();
		}
		else
		{
			$('#codiceLabel').hide();
			$('#codice_' + tLang).hide();
		}

		if(tableName === 'PLF_T_TIPO_ALLEGATO'){
			$('#estensione_' + tLang).val(codifiche[tLang].ESTENSIONE);
		}
	}

	setDynamicValidation();
}

function setDynamicValidation() {

	var defaultLocale = '${defaultLocale}';
	var langs = toParsedLanguages('${languages}');
	var tableName = $('#selected_table').val();

	var dynamicRules = {};
	var dynamicMessages = {};

	var maxCodeLength = 100;
	var maxDescLength = 255;

	for(var x = 0; x < langs.length; x++) {
		var tempLang = langs[x];
		var tempCodice = 'codice_' + tempLang;
		var tempDesc = 'descrizione_' + tempLang;

		if (globalIsFieldCodeVisible)
			dynamicRules[tempCodice] = { maxlength: maxCodeLength };
		
		dynamicRules[tempDesc] = { maxlength: maxDescLength };

		if(tableName === 'PLF_T_TIPO_ALLEGATO'){

			var tempExt = 'estensione_' + tempLang;

			dynamicRules[tempExt] = { maxlength: maxDescLength };
		}

		if(tempLang === defaultLocale) {

			if (globalIsFieldCodeVisible)
				dynamicRules[tempCodice]['required'] = true;
			
			dynamicRules[tempDesc]['required'] = true;

			if(tableName === 'PLF_T_TIPO_ALLEGATO'){

				dynamicRules[tempExt]['required'] = true;
			}
					
		}

		

		if (globalIsFieldCodeVisible)
		{
			dynamicMessages[tempCodice] = {
				required: '<spring:message code="translate_codifiche.italian_required" javaScriptEscape="true"/>',
				maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxCodeLength + ')'
			};
		}
		dynamicMessages[tempDesc] = {
			required: '<spring:message code="translate_codifiche.italian_required_desc" javaScriptEscape="true"/>',
			maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxDescLength + ')'
		};

		if(tableName === 'PLF_T_TIPO_ALLEGATO'){

			var tempExt = 'estensione_' + tempLang;

			dynamicMessages[tempExt] = {
				required: '<spring:message code="translate_codifiche.italian_required_ext" javaScriptEscape="true"/>',
				maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxCodeLength + ')'
			};

		}

	}
	
	validator = $('#formCodifiche').validate(
        {
        	errorPlacement: function(error, element) {
		        if(element.parent('.input-group').length) {
		            error.insertAfter(element.parent());
		        } else {
		            error.insertAfter(element);
		        }
		    },
            rules: dynamicRules,
            messages: dynamicMessages,
			submitHandler: function() { submitForm(); }
        }
    );
}

function hideForm() {
	emptyForm();
	$('#form_container').hide();
}

function showForm() {
	$('#form_container').show();
	setDynamicValidation();
}

function onLoadBtns() {
	$('#codificheBtns').html('<img src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif"/>');
}

function onLoadCodifica() {
	$('.loading_codifica').show();
}

function stopLoadCodifica() {
	$('.loading_codifica').hide();
}

function hiddenIsValid() {
	var tableName = $('#selected_table').val();
	var defaultLocale = '${defaultLocale}';
	var reqId = $('#id_' + defaultLocale).val();
	var reqCod = $('#codice_' + defaultLocale).val();
	var reqDesc = $('#descrizione_' + defaultLocale).val();


	if (globalIsFieldCodeVisible)
	{
		if(reqCod && reqCod != '' && reqDesc && reqDesc != '') {

			if(tableName === 'PLF_T_TIPO_ALLEGATO') {
				var reqExt = $('#estensione_' + defaultLocale).val();

				if(reqExt && reqExt != '') {
					return true;
				}

				return false;
			}

			return true;
		}
	}
	else
	{
		if(reqDesc && reqDesc != '') {

			if(tableName === 'PLF_T_TIPO_ALLEGATO') {
				var reqExt = $('#estensione_' + defaultLocale).val();

				if(reqExt && reqExt != '') {
					return true;
				}

				return false;
			}

			return true;
		}
	}

	

	return false;

}

function submitForm(){

	if(hiddenIsValid()) {
		var defaultLocale = '${defaultLocale}';
		var languages = toParsedLanguages('${languages}');
		var tableName = $('#selected_table').val();
		var isNew = false;
		
		var result = {};
		result['table'] = tableName;
		result['data'] = [];
		
	
		for(var i = 0; i < languages.length; i++) {
			var tLang = languages[i];
			var obj = {};
	
			obj['locale'] = tLang;
			obj['codifica'] = {};
			obj['codifica']['ID'] = $('#id_' + tLang).val();
			obj['codifica']['DESCRIZIONE'] = $('#descrizione_' + tLang).val();
			obj['codifica']['CODICE'] = $('#codice_' + tLang).val();
	
			if(tableName === 'PLF_T_TIPO_ALLEGATO') {
				obj['codifica']['ESTENSIONE'] = $('#estensione_' + tLang).val();
			}
	
			result['data'].push(obj);
	
			if(tLang === defaultLocale && obj['codifica']['ID'] === '') {
				isNew = true;
			}
		}
	
		if(isNew) {
			save(result);
		} else {
			update(result);
		}
	} else {
		showAlertMessage('<spring:message code="translate_codifiche.default_required"/>')
	}
}

function showWarningMessage(msg) {
	$('#warning_div').show();
	$('#warning_message').html(msg);
}

function hideWarningMessage() {
	$('#warning_div').hide();
	$('#warning_message').html('');
}

function showAlertMessage(msg) {
	$('#alert_div').show();
	$('#error_message').html(msg);
}

function hideAlertMessage() {
	$('#alert_div').hide();
	$('#error_message').html('');
}

function showSuccessMessage(msg) {
	$('#success_div').show();
	$('#success_message').html(msg);
}

function hideSuccessMessage() {
	$('#success_div').hide();
	$('#success_message').html('');
}


function save(toSave) {

	var splitted = window.location.href.split('/vimp/');
	var url = splitted[0] + '/vimp/secure/saveCodifiche';
	var tableName = $('#selected_table').val();

	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify(toSave),
		contentType: "application/json",
		dataType: "json",
		success: function(res) {

			if(res['errorData']) {
				handleDuplicateError(res['errorData']);
			} else {
				showSuccessMessage('${success}');
				emptyForm();
				globalTempCodificheArray = res[tableName];
				populateCodificheButtons(res[tableName], '${languages}');
			}
		},
		error: function () {
			showAlertMessage('${error_save}');
		}
	});
 
}

function update(toUpdate) {

	var splitted = window.location.href.split('/vimp/');
	var url = splitted[0] + '/vimp/secure/updateCodifiche';
	var tableName = $('#selected_table').val();

	$.ajax({
		type: "PUT",
		url: url,
		data: JSON.stringify(toUpdate),
		contentType: "application/json",
		dataType: "json",
		success: function(res) {

			if(res['errorData']) {
				handleDuplicateError(res['errorData']);
			} else {
				showSuccessMessage('${success}');
				emptyForm();
				globalTempCodificheArray = res[tableName];
				populateCodificheButtons(res[tableName], '${languages}');
			}

			window.scrollTo(0, 0);

		},
		error: function () {
			showAlertMessage('${error_save}');
			window.scrollTo(0, 0);
		}
	});
}

function handleDuplicateError(errorData) {
	let errorHtml = '<span>${duplicateError}</span><br><ul>';
	let x = 0;
	let found = true;

	while(found) {
		var error = errorData[0][x];
		if(error) {
			errorHtml += '<li>' + $('#' + error).html() + '</li>';
			x++;
		} else {
			found = false;
		}
	}

	errorHtml += '</ul>';

	showAlertMessage(errorHtml);
}

function disableCodifica() {
	var splitted = window.location.href.split('/vimp/');
	var url = splitted[0] + '/vimp/secure/disableCodifiche';
	var tableName = $('#selected_table').val();
	var languages = toParsedLanguages('${languages}');
	var result = {};
	result['table'] = tableName
	result['data'] = [];

	for(var i = 0; i < languages.length; i++) {
		var tLang = languages[i];
		var obj = {};

		obj['locale'] = tLang;
		obj['codifica'] = {};
		obj['codifica']['ID'] = $('#id_' + tLang).val();

		result['data'].push(obj);
	}

	
	
	$.ajax({
		type: "DELETE",
		url: url,
		data: JSON.stringify(result),
		contentType: "application/json",
		dataType: "json",
		success: function(res) {
			showSuccessMessage('${successDelete}');
			emptyForm();
			globalTempCodificheArray = res[tableName];
			populateCodificheButtons(res[tableName], '${languages}');
		},
		error: function () {
			showAlertMessage('${error_save}');
		}
	});
}

function confirmDelete() {
	var tableName = $('#selected_table').val();
	if (tableName === 'PLF_T_DENOMINAZIONE_SERVIZI')
	{
		var defaultLocale = '${defaultLocale}';
		var reqId = $('#id_' + defaultLocale).val();
		var splitted = window.location.href.split('/vimp/');
		var url = splitted[0] + '/vimp/secure/checkDisableDemServiziCodifiche';
		$.getJSON(url, {
			idDenominazioneServizio : reqId
		}, function(data) {
			if (data > 0)
			{
				// jagooooooooooo
				var message = "${messaggioNotDeleteCodifica_message}";
				message = message.replace("{1}", data);
				$('#messageNotDeleteText').text(message);
				$('#messageNotDelete').modal();
			}
			else $('#confirmSimpleDelete').modal();
		});		
	}
	else {
		$('#confirmDelete').modal();
	}
}

function confirmUpdate() {

	var defaultLocale = '${defaultLocale}';
	var reqId = $('#id_' + defaultLocale).val();

	if (reqId)
	{
		var tableName = $('#selected_table').val();
		if (tableName === 'PLF_T_DENOMINAZIONE_SERVIZI')
		{
			var splitted = window.location.href.split('/vimp/');
			var url = splitted[0] + '/vimp/secure/checkDisableDemServiziCodifiche';
			$.getJSON(url, {
				idDenominazioneServizio : reqId
			}, function(data) {
				var message = "${messaggioUpdateServiceCodifica_message}";
				message = message.replace("{1}", data);
				$('#confirmServiceUpdateText').text(message);
				$('#confirmServiceUpdate').modal();
			});		
		}
		else $('#confirmUpdate').modal();
	}
	else updateCodifica();
}

function updateCodifica() {
	$('#formCodifiche').submit();
}


</script>


<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->