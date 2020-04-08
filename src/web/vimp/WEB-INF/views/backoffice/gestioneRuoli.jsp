<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="localized_msg" hidden="true">
	<spring:message code="gestione_ruoli.italian_required"
		text="La descrizione del ruolo in italiano è obbligatoria"
		var="italian_required" />
	<spring:message code="gestione_ruoli.error_save"
		text="La modifica non è andata a buon fine. Controlla i dati e riprova."
		var="error_save" />
	<spring:message code="gestione_ruoli.success"
		text="Salvataggio avvenuto con successo." var="success" />
</div>
<c:choose>
	<c:when test="${!utente.isBackoffice()}">
		&nbsp;
	</c:when>
	<c:otherwise>
		<div class="submit-property">
			<div class="row">
				<h2 class="center">
					<small><spring:message code="gestione_ruoli.title"
							text="Gestione ruoli"></spring:message></small>
				</h2>
			</div>
			<div class="container-fluid">
				<div id="success_div" class="alert alert-success fade in"
					hidden="true">
					<button type="button" class="close" onclick="hideSuccessMessage()"
						aria-hidden="true">&times;</button>
					<p>
						<span id="success_message"></span>
					</p>
				</div>
				<div id="warning_div" class="alert alert-warning fade in"
					hidden="true">
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
						<strong><spring:message code="error"></spring:message></strong> <span
							id="error_message"></span>
					</p>
				</div>
			</div>
		</div>
		<div class="content-area submit-property">
			&nbsp;
			<div class="container-fluid">
				<div class="col-xs-12">
					<div class="wizard-card ct-wizard-orange" id="wizardInsert"
						style="padding: 10px; margin-bottom: 10px;">
						<div id="ruoliContainer" class="container-fluid">
							<c:forEach items="${roles}" var="role">
								<div class="col-xs-12" style="overflow-wrap: break-word"">
									<span style="margin-bottom: 10px;">${role.descRuolo}</span>
								</div>
								<div class="col-xs-12">
									<input id="descrizione_${role.idRuolo}" type="text" onclick="resetMessages()"
										class="form-control form-input" value="${role.descRuolo}"
										style="margin-bottom: 10px;">
								</div>
								<div class="col-xs-4" style="margin-bottom: 30px">
									<button type="button" class="btn btn-default"
										style="margin-bottom: 10px;" onclick="salva(${role.idRuolo})">
										<spring:message code="save" text="Salva" />
									</button>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>


<script
	src="${evn_urlRisorseStatiche}/vimp/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-select.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wow.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-hover-dropdown.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/icheck.min.js"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/owl.carousel.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script>
	$(document).ready({});

	function populateRuoliButtons(lista) {
		var finalHtml = '';
			
		for(var i = 0; i < lista.length; i++) {
			var descrizione = lista[i].descRuolo;
			var id = lista[i].idRuolo;

			finalHtml += '<div class="col-xs-12"> <span style="margin-bottom: 10px;">' +
			descrizione + '</span></div><div class="col-xs-4"><input id="descrizione_' + id + '" type="text"' +
			'class="form-control form-input" onclick="resetMessages()" value="' + descrizione +'"style="margin-bottom: 10px;"> </div>' +
			'<div class="col-xs-4">	<button type="button" class="btn btn-default" style="margin-bottom: 10px;"' +
			'onclick="salva(' + id + ')"> <spring:message code="save" text="Salva" /> </button> </div><br>'
		} 

		$('#ruoliContainer').html(finalHtml);
	}

	function salva(idRuolo) {
		var obj = {};
		var descrizione = $('#descrizione_' + idRuolo).val();

		if($.trim(descrizione).length) { 
			obj['ruolo'] = {};
			obj['ruolo']['ID'] = idRuolo;
			obj['ruolo']['DESCRIZIONE'] = descrizione;
			
			update(obj);
		} else {
			showWarningMessage('${italian_required}');
		}
	}

	function update(toUpdate) {
		var splitted = window.location.href.split('/vimp/');
		var url = splitted[0] + '/vimp/secure/updateRuolo';

		$.ajax({
			type : "PUT",
			url : url,
			data : JSON.stringify(toUpdate),
			contentType : "application/json",
			dataType : "json",
			success : function(res) {
				showSuccessMessage('${success}');
				populateRuoliButtons(res)
			},
			error : function() {
				showAlertMessage('${error_save}');
			}
		});
	}

	function resetMessages() {
		hideAlertMessage();
		hideSuccessMessage();
		hideWarningMessage();
	}

	function showWarningMessage(msg) {
		$('#warning_div').show();
		$('#warning_message').html(msg);
		//hideWarnigMessageTimer();
	}

	function hideWarnigMessageTimer(){
		setTimeout(hideWarningMessage, 5000); 
	}

	function hideWarningMessage() {
		$('#warning_div').hide();
		$('#warning_message').html('');
	}
	

	function showAlertMessage(msg) {
		$('#alert_div').show();
		$('#alert_message').html(msg);
		hideAlertMessageTimer();
	}

	function hideAlertMessageTimer(){
		setTimeout(hideAlertMessage, 5000); 
	}

	function hideAlertMessage() {
		$('#alert_div').hide();
		$('#alert_message').html('');
	}
	

	function showSuccessMessage(msg) {
		$('#success_div').show();
		$('#success_message').html(msg);
		hideSuccessMessageTimer();
	}

	function hideSuccessMessageTimer(){
		setTimeout(hideSuccessMessage, 5000); 
	}

	function hideSuccessMessage() {
		$('#success_div').hide();
		$('#success_message').html('');
	}

</script>





