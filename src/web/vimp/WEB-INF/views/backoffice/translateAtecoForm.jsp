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
	<span id="DESCRIZIONE"><spring:message code="translate_ateco.descrizione" javaScriptEscape="true"/></span>
</div>
<c:choose>
	<c:when test="${!utente.isBackoffice()}">
		&nbsp;
	</c:when>
	<c:otherwise> 
    <div class="content-area submit-property safe-reload">&nbsp;
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
           	<div class="container">
				<div id="frmParametri">
			 		<div class="content-area submit-property">&nbsp;
					     <div class="container" id="form_section">
					     <div class="center">
				        	<button type="button" class="btn btn-default" onclick="showTables()">
				        		<spring:message code="translate_codifiche.show_tables"/>
			        		</button>
			        		<button type='button' class='btn btn-default' style='margin:10px 0' onclick='emptyForm()'>
			        			<spring:message code='translate_codifiche.new_encoding'/>
			        		</button>
			        	</div>
						 <div class="center">
							 <h4 id="selected_table_label">ATECO</h4>
						 </div>
							<div class="form-group">
								<input type="text" id="inputCercaCodifica" placeholder="Cerca codifica" class="form-control form-input" style="margin:10px 0; width: 95%" onkeyup="searchCodificheBtns()" />			
								<div id="codificheBtns" class="col-md-6 float-left">
               					  	<img src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif"/>  
								</div>
								<form id="formCodifiche" class="col-md-6 float-right">
									<div class="clearfix" id="form_container" hidden="true"> 
                    					<div class="wizard-container">          					
                    						<c:forEach items="${languages}" var="lang">
		                    					<div id="form_${lang}"  class="wizard-card ct-wizard-orange" style="padding: 10px; margin-bottom: 10px;">
			                    					<div class="wizard-header">
						                                <img class="loading_codifica" src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif" hidden="true"/>	
					                                    <h3><img id="imgFlag_${lang}"/></h3>  	             			                               
						                            </div>
						                            <input id="id_${lang}" name="id_${lang}" class="form-input" value="" hidden="true"></input>
						                            
													<label><spring:message code="translate_ateco.activity"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
														<small> <spring:message code="max_length"/>: 19</small>
													</label>
													<input id="attivita_${lang}" name="attivita_${lang}" type="number" class="form-control form-input" value="">
													
													<label><spring:message code="translate_ateco.codifica"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
														<small> <spring:message code="max_length"/>: 19</small>
													</label>
													<input id="codifica_${lang}" name="codifica_${lang}" type="number" class="form-control form-input" value="">
													
													<label><spring:message code="translate_ateco.fonte"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
														<small> <spring:message code="max_length"/>: 2</small>
													</label>
													<input id="fonte_${lang}" name="fonte_${lang}" type="text" class="form-control form-input" value="">
													
													<label><spring:message code="translate_ateco.sezione"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
														<small> <spring:message code="max_length"/>: 1</small>
													</label>
													<input id="sezione_${lang}" name="sezione_${lang}" type="text" class="form-control form-input" value="">
													
													<label><spring:message code="translate_ateco.descrizione"/>: 
														<c:if test="${lang == defaultLocale}">
															<small>(<spring:message code="required"/>)</small>
														</c:if>
														<small> <spring:message code="max_length"/>: 400</small>
													</label>
													<input id="descrizione_${lang}" name="descrizione_${lang}" type="text" class="form-control form-input" value="">					
												</div> 
											</c:forEach>
											<button type="submit" class="btn btn-default float-right"><spring:message code="save" text="Salva"/></button>
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

var globalTempCodificheArray = [];
var globalFilteredCodificheArray = [];

$(document).ready(function() {
	var tableName = '${tableName}';
	var splitted = window.location.href.split('/vimp/');
	var url = splitted[0] + '/vimp/secure/elencoCodifiche.json?table=' + tableName;
	var languages = '${languages}';
	$.ajax({
	  url: url,
	  context: document.body
	}).done(function(res) {
		$('#form_container').show();
		globalTempCodificheArray = res[tableName];
		populateCodificheButtons(res[tableName], languages);
	});

	setImgFlags();
    hideWholeForm('${selectedLocale}');
    setDynamicValidation();
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
	var splitted = window.location.href.split('/vimp/');
	window.location.href = splitted[0] + '/vimp/secure/translateCodifiche';
}

function populateCodificheButtons(lista, languages, startIndex) {
	if(!startIndex) {startIndex = 0;}
	$('#codificheBtns').html(setCodificheBtnHtml(lista, 35, startIndex));
	$('#tr'+$('#id_' + '${defaultLocale}').val()).addClass('trCodificheFormSelected');
}

function setCodificheBtnHtml(itemArray, numberToDisplay, startItem){
	if(!numberToDisplay) {numberToDisplay = 35;}
	if(!startItem) {startItem = 0;}

	var tableName = '${tableName}';
	var howManyPages = 0;
    finalHtml = "<div class='table-responsive'><table id='tableCodifiche' class='table table-condensed bordered1'><thead>";
    if(itemArray.length < numberToDisplay) {
    	numberToDisplay = itemArray.length;
    }
    finalHtml += '<th><spring:message code="translate_ateco.activity" text="AttivitÃ "/></th>';
    finalHtml += '<th><spring:message code="translate_ateco.codifica" text="Codifica"/></th>';
    finalHtml += '<th><spring:message code="translate_ateco.fonte" text="Fonte"/></th>';
    finalHtml += '<th><spring:message code="translate_ateco.sezione" text="Sezione"/></th>';
    finalHtml += '<th><spring:message code="translate_ateco.descrizione" text="Descrizione"/></th>';
    finalHtml += "</thead><tbody>";
	for(var i = 0; i < numberToDisplay; i++){
		if((i + startItem + 1) <= (itemArray.length)){
			var descrizione = itemArray[i+startItem].DESCRIZIONE;
			var shortDesc = descrizione.substring(0, 40);
			var finalDesc = descrizione.length > 40 ? shortDesc + '...' : descrizione;
			finalHtml += "<tr id='tr" + itemArray[i+startItem].ID_PLF_T_ATECO + "' class='rigaCodifica' onclick='onCodificaChoosen(" + itemArray[i+startItem].ID_PLF_T_ATECO + ", " + JSON.stringify('${languages}') + ")'><td> " + itemArray[i+startItem].C_ATTIVITA + "</td><td>" + itemArray[i+startItem].T_CODIFICA + "</td><td>" + itemArray[i+startItem].C_FONTE + "</td><td>" + itemArray[i+startItem].SEZIONE + "</td><td>" + itemArray[i+startItem].DESCRIZIONE + "</td></tr>";
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
		});
        populateCodificheButtons(filteredList, '${languages}');
    } else {
    	populateCodificheButtons(globalTempCodificheArray, '${languages}');
    }
	hideSuccessMessage();
	hideAlertMessage();
}

function onCodificaChoosen(idCodifica, languages) {
	var parsedLanguages = toParsedLanguages(languages);
	var tableName = 'PLF_T_ATECO';
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
	var table = tableName ? tableName : '${tableName}';

	if(table !== 'PLF_T_TIPO_ALLEGATO') {
		$('.extension_div').hide();
	}
	$('.rigaCodifica').removeClass('trCodificheFormSelected');
}

function setCodificaInForm(codifiche, languages) {

	for(var i = 0; i < languages.length; i++) {
		var tLang = languages[i];

		$('#delete_btn').removeAttr('disabled');

		$('#id_' + tLang).val(codifiche[tLang].ID_PLF_T_ATECO);
		$('#attivita_' + tLang).val(codifiche[tLang].C_ATTIVITA);
		$('#codifica_' + tLang).val(codifiche[tLang].T_CODIFICA);
		$('#fonte_' + tLang).val(codifiche[tLang].C_FONTE);
		$('#sezione_' + tLang).val(codifiche[tLang].SEZIONE);
		$('#descrizione_' + tLang).val(codifiche[tLang].DESCRIZIONE);
	}

	hideAlertMessage();
	hideSuccessMessage();
	setDynamicValidation();
}

function hideForm() {
	emptyForm();
	$('#form_container').hide();
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

function submitForm(){
	if (hiddenIsValid()) {
		var defaultLocale = '${defaultLocale}';
		var languages = toParsedLanguages('${languages}');
		var tableName = '${tableName}';
		var isNew = false;
		
		var result = {};
		result['table'] = tableName;
		result['data'] = [];
		
	
		for(var i = 0; i < languages.length; i++) {
			var tLang = languages[i];
			var obj = {};
	
			obj['locale'] = tLang;
			obj['codifica'] = {};
			obj['codifica']['ID_PLF_T_ATECO'] = $('#id_' + tLang).val();
			obj['codifica']['C_ATTIVITA'] = $('#attivita_' + tLang).val();
			obj['codifica']['T_CODIFICA'] = $('#codifica_' + tLang).val();
			obj['codifica']['C_FONTE'] = $('#fonte_' + tLang).val();
			obj['codifica']['SEZIONE'] = $('#sezione_' + tLang).val();
			obj['codifica']['DESCRIZIONE'] = $('#descrizione_' + tLang).val();
	
	
			result['data'].push(obj);
	
			if(tLang === defaultLocale && obj['codifica']['ID_PLF_T_ATECO'] === '') {
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

function setDynamicValidation() {

	var defaultLocale = '${defaultLocale}';
	var langs = toParsedLanguages('${languages}');
	var tableName = $('#selected_table').val();

	var dynamicRules = {};
	var dynamicMessages = {};

	var maxAttLength = 19;
	var maxCodLength = 19;
	var maxFonLength = 2;
	var maxSezLength = 1;
	var maxDescLength = 400;

	for(var x = 0; x < langs.length; x++) {
		var tempLang = langs[x];
		var tempAttivita = 'attivita_' + tempLang;
		var tempCod = 'codifica_' + tempLang;
		var tempFonte = 'fonte_' + tempLang;
		var tempSezione = 'sezione_' + tempLang;
		var tempDesc = 'descrizione_' + tempLang;

		dynamicRules[tempAttivita] = { maxlength: maxAttLength };
		dynamicRules[tempCod] = { maxlength: maxCodLength };
		dynamicRules[tempFonte] = { maxlength: maxFonLength };
		dynamicRules[tempSezione] = { maxlength: maxSezLength };
		dynamicRules[tempDesc] = { maxlength: maxDescLength };

		if(tempLang === defaultLocale) {
			
			dynamicRules[tempAttivita]['required'] = true;
			dynamicRules[tempCod]['required'] = true;
			dynamicRules[tempFonte]['required'] = true;
			dynamicRules[tempSezione]['required'] = true;
			dynamicRules[tempDesc]['required'] = true;
					
		}

		dynamicMessages[tempAttivita] = {
			required: '<spring:message code="translate_codifiche.italian_required" javaScriptEscape="true"/>',
			maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxAttLength + ')'
		};
		dynamicMessages[tempCod] = {
			required: '<spring:message code="translate_codifiche.italian_required_desc" javaScriptEscape="true"/>',
			maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxCodLength + ')'
		};
		dynamicMessages[tempFonte] = {
			required: '<spring:message code="translate_codifiche.italian_required" javaScriptEscape="true"/>',
			maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxFonLength + ')'
		};
		dynamicMessages[tempSezione] = {
			required: '<spring:message code="translate_codifiche.italian_required_desc" javaScriptEscape="true"/>',
			maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxSezLength + ')'
		};
		dynamicMessages[tempDesc] = {
			required: '<spring:message code="translate_codifiche.italian_required" javaScriptEscape="true"/>',
			maxlength: '<spring:message code="translate_codifiche.too_long" javaScriptEscape="true"/>' + ' (' + maxDescLength + ')'
		};
	}
	
	$('#formCodifiche').validate(
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

function hiddenIsValid() {
	var defaultLocale = '${defaultLocale}';
	var reqId = $('#id_' + defaultLocale).val();
	var reqAtt = $('#attivita_' + defaultLocale).val();
	var reqCod = $('#codifica_' + defaultLocale).val();
	var reqFon = $('#fonte_' + defaultLocale).val();
	var reqSez = $('#sezione_' + defaultLocale).val();
	var reqDesc = $('#descrizione_' + defaultLocale).val();

	if(reqAtt && reqAtt != '' && reqCod && reqCod != '' && 
			reqFon && reqFon != '' && reqSez && reqSez != '' && reqDesc && reqDesc != '') {
		return true;
	}
	return false;
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
	var tableName = '${tableName}';

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

			window.scrollTo(0, 0);
		},
		error: function () {
			showAlertMessage('${error_save}');
			window.scrollTo(0, 0);
		}
	});
}

function update(toUpdate) {
	var splitted = window.location.href.split('/vimp/');
	var url = splitted[0] + '/vimp/secure/updateCodifiche';
	var tableName = '${tableName}';

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
		},
		error: function () {
			showAlertMessage('${error_save}');
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
	
</script>


<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->