<!-- =========================================================================================== -->
<!-- BEGIN RICERCA -->

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
		$("#frmparametriRicerca").get(0).setAttribute('action', '/vimp/secure/homePersonal');
		$('#paginaCorrente').val(numeroPagina);
		$("#frmparametriRicerca").submit();
	}

	function setCambiaPagina(e) {
		if(!e)
			e = window.event;

		if(e.keyCode === 13) {
			cambiaPagina(0);
		}
	}

	function setTextRicerca() {
		var text = $('#shownText').val();
		$('#textRicerca').val(text);
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
				document.getElementById('frmparametriRicerca').submit();
			};

			recognition.onerror = function(e) {
				recognition.stop();
			}

		}
	}
</script>


<div class="content-area home-area-1 recent-property" style="padding-bottom: 0px;">
	<div class="container home-form-container">
	
		<div class="row">
			<div class="col-md-10 col-md-offset-1 col-sm-12 text-center homemt" style="padding-bottom: 30px; padding-top: 0px;">
				<span class="my-info-label-bungee"><spring:message code="home.personal.list.my_info" text="Le Mie informazioni"/></span>
			</div>
		</div>
	

		<div class="col-md-6 row home-search-row">
			<div class="search-bar col-md-10 col-sm-5 p0">
				<input type="text" class="form-control home-form-control" placeholder="<spring:message code="search" text="Cerca"/>"
					   value="${parametriRicerca.textRicerca}" id="shownText" onchange="setTextRicerca()">
			</div>

			
				<button class="btn search-btn col-md-2 col-sm-1 p0" type="button" onclick="cambiaPagina(0); return false;">
					<i class="fa fa-search"></i>
				</button>
			
		</div>

		<form:form id="frmparametriRicerca" method="get" action="/vimp/secure/homePersonal"
			role="form" modelAttribute="parametriRicerca">

			<form:hidden id="textRicerca" path="textRicerca" value="${parametriRicerca.textRicerca}"/>

			<form:hidden id="paginaCorrente" path="paginaCorrente" />
			<form:hidden id="numeroRecord" path="numeroRecord" />

			<form:hidden id="findServizi" path="findServizi" />
			<form:hidden id="findPacchetti" path="findPacchetti" />
			<form:hidden id="findProgetti" path="findProgetti" />
			<form:hidden id="findProdotti" path="findProdotti" />
			<form:hidden id="findTecnologie" path="findTecnologie" />
			<form:hidden id="findNews" path="findNews" />
			<form:hidden id="findScadute" path="findScadute" />

			<div class="col-md-6 col-sm-12" >

				<c:if test="${utente.isStakeholder()}">
					<div class="col-sm-2 col-md-2 home-form-single-check-section">
						<div class="checkbox">
							<label class="center homecheckbox"> <input
									id="checkboxServizi" name="checkboxServizi" type="checkbox" />
								<strong class="check-home-form">
									<spring:message code="home.personal.services" text="Servizi"/>
								</strong>
							</label>
						</div>
					</div>
					<div class="col-sm-2 col-md-2 home-form-single-check-section">
						<div class="checkbox">
							<label class="center homecheckbox"> <input
									id="checkboxPacchetti" name="checkboxPacchetti" type="checkbox" />
								<strong class="check-home-form">
									<spring:message code="home.personal.package" text="Pacchetti"/>
								</strong>
							</label>
						</div>
					</div>
				</c:if>
				<div class="col-sm-2 col-md-2 home-form-single-check-section">
					<div class="checkbox">
						<label class="center homecheckbox"> <input
								id="checkboxProgetti" name="checkboxProgetti" type="checkbox" />
							<strong class="check-home-form">
								<spring:message code="home.personal.project.filter" text="Progetti"/>
							</strong>
						</label>
					</div>
				</div>
				<div class="col-sm-2 col-md-2 home-form-single-check-section">
					<div class="checkbox">
						<label class="center homecheckbox"> <input
								id="checkboxProdotti" name="checkboxProdotti" type="checkbox" />
							<strong class="check-home-form">
								<spring:message code="home.personal.product.filter" text="Prodotti"/>
							</strong>
						</label>
					</div>
				</div>
				<div class="col-sm-2 col-md-2 home-form-single-check-section">
					<div class="checkbox">
						<label class="center homecheckbox"> <input
								id="checkboxTecnologie" name="checkboxTecnologie" type="checkbox" />
							<strong class="check-home-form">
								<spring:message code="home.personal.tecnologies.filter" text="Tecnologie"/>
							</strong>
						</label>
					</div>
				</div>
				<div class="col-sm-2 col-md-2 home-form-single-check-section">
					<div class="checkbox">
						<label class="center homecheckbox"> <input
								id="checkboxNews" name="checkboxNews" type="checkbox" />
							<strong class="check-home-form">
								<spring:message code="home.personal.news" text="News"/>
							</strong>
						</label>
					</div>
				</div>

			</div>
			
			
			<div class="col-md-6 col-sm-12" >
				<div class="checkbox" style="margin-top: -1px;margin-left: -40px;">
					<label class="homecheckbox"> <input
							id="checkboxScadute" name="checkboxScadute" type="checkbox" />
						<strong class="check-home-form">
							<spring:message code="home.personal.expired" text="Visualizza info scadute"/>
						</strong>
					</label>
				</div>
			</div>
			
		</form:form>
	</div>
</div>


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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script>
	$(document).ready(function() {

		$('#shownText').keypress( function(e) {
			if(e.keyCode === 13) {
				setTextRicerca();
				cambiaPagina(0);
			}
		})

		if (${parametriRicerca.findServizi == 'S'})
			$('#checkboxServizi').iCheck('check');
		else 
			$('#checkboxServizi').iCheck('uncheck');
		$('#checkboxServizi').on('ifChecked', function(event) {
			$("#findServizi").val("S");
		});
		$('#checkboxServizi').on('ifUnchecked', function(event) {
			$("#findServizi").val("N");
		});

		if (${parametriRicerca.findPacchetti == 'S'})
			$('#checkboxPacchetti').iCheck('check');
		else 
			$('#checkboxPacchetti').iCheck('uncheck');
		$('#checkboxPacchetti').on('ifChecked', function(event) {
			$("#findPacchetti").val("S");
		});
		$('#checkboxPacchetti').on('ifUnchecked', function(event) {
			$("#findPacchetti").val("N");
		});

		if (${parametriRicerca.findProgetti == 'S'})
			$('#checkboxProgetti').iCheck('check');
		else 
			$('#checkboxProgetti').iCheck('uncheck');
		$('#checkboxProgetti').on('ifChecked', function(event) {
			$("#findProgetti").val("S");
		});
		$('#checkboxProgetti').on('ifUnchecked', function(event) {
			$("#findProgetti").val("N");
		});

		if (${parametriRicerca.findProdotti == 'S'})
			$('#checkboxProdotti').iCheck('check');
		else 
			$('#checkboxProdotti').iCheck('uncheck');
		$('#checkboxProdotti').on('ifChecked', function(event) {
			$("#findProdotti").val("S");
		});
		$('#checkboxProdotti').on('ifUnchecked', function(event) {
			$("#findProdotti").val("N");
		});

		if (${parametriRicerca.findTecnologie == 'S'})
			$('#checkboxTecnologie').iCheck('check');
		else 
			$('#checkboxTecnologie').iCheck('uncheck');
		$('#checkboxTecnologie').on('ifChecked', function(event) {
			$("#findTecnologie").val("S");
		});
		$('#checkboxTecnologie').on('ifUnchecked', function(event) {
			$("#findTecnologie").val("N");
		});

		if (${parametriRicerca.findNews == 'S'})
			$('#checkboxNews').iCheck('check');
		else 
			$('#checkboxNews').iCheck('uncheck');
		$('#checkboxNews').on('ifChecked', function(event) {
			$("#findNews").val("S");
		});
		$('#checkboxNews').on('ifUnchecked', function(event) {
			$("#findNews").val("N");
		});

		if (${parametriRicerca.findScadute == 'S'})
			$('#checkboxScadute').iCheck('check');
		else 
			$('#checkboxScadute').iCheck('uncheck');
		$('#checkboxScadute').on('ifChecked', function(event) {
			$("#findScadute").val("S");
		});
		$('#checkboxScadute').on('ifUnchecked', function(event) {
			$("#findScadute").val("N");
		});
		
	})

</script>



<!-- END  RICERCA -->
<!-- =========================================================================================== -->