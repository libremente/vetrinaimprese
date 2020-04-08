<!-- =========================================================================================== -->
<!-- BEGIN PARAMETRI RICERCA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- CSS Styles -->
<style>
.speech {
	border: 1px solid #DDD;
	width: 300px;
	padding: 0;
	margin: 0
}

.speech input {
	border: 0;
	width: 240px;
	display: inline-block;
	height: 30px;
}

.speech img {
	float: right;
	width: 40px
}
</style>

<script>
	function cambiaPagina(numeroPagina) {
		$("#frmParametri").get(0).setAttribute('action', '/vimp/elencoUtenti');
		$("#paginaCorrente").val(numeroPagina);
		$("#frmParametri").submit();
	}

	function startDictation() {

		if (window.hasOwnProperty('webkitSpeechRecognition')) {

			var recognition = new webkitSpeechRecognition();

			recognition.continuous = false;
			recognition.interimResults = false;

			recognition.lang = "it-IT";
			recognition.start();

			recognition.onresult = function(e) {
				document.getElementById('textRicerca').value = e.results[0][0].transcript;
				recognition.stop();
				document.getElementById('frmParametri').submit();
			};

			recognition.onerror = function(e) {
				recognition.stop();
			}

		}
	}
</script>



<form:form id="frmParametri" method="get" action="/vimp/elencoUtenti"
	role="form" modelAttribute="parametriRicerca">


	<form:hidden id="paginaCorrente" path="paginaCorrente" />
	<form:hidden id="numeroRecord" path="numeroRecord" />

	<c:choose>
		<c:when test="${!utente.isAdministrator()}">
			<div class="alert alert-warning alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="operation_not_permitted" text="Operazione non permessa"/>.
			</div>
		</c:when>
		<c:otherwise>

			<div class="page-head">
				<div class="container">
					<div class="row">
						<div class="page-head-content">
							<h1 class="page-title">
								<b><spring:message code="form.parametri.user_list" text="ELENCO UTENTI"/></b>
							</h1>
						</div>
					</div>
				</div>
			</div>



			<div class="content-area home-area-1 recent-property"
				style="padding-bottom: 0px; padding-top: 20px;">
				<div class="container">
					<div class="row">
						<div class="proerty-th">
							<div class="col-sm-12 col-md-6 p0">

								<div class="">
									<div class="col-md-10 col-sm-8">
										<input type="text" class="form-control" placeholder="<spring:message code="search" text="Cerca"/>"
											name="textRicerca" id="textRicerca"
											value="${parametriRicerca.textRicerca}">
									</div>
									<!--  <img name="speekRicerca" id="speekRicerca" onclick="startDictation()" src="${evn_urlRisorseStatiche}/vimp/assets/img/mic.gif" /> -->
									<button class="btn search-btn" type="submit">
										<i class="fa fa-search"></i>
									</button>
								</div>

								
							</div>
							<div class="pull-right center" style="margin-top:6px;">
									<a class="navbar-btn nav-button login"
										onclick="location.href='/vimp/utente/0'" data-wow-delay="0.7s"><spring:message code="menu.new" text="Nuovo"/></a>
								</div>
						</div>


					</div>
				</div>
			</div>

		</c:otherwise>
	</c:choose>

</form:form>



<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->