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
		$("#frmParametri").get(0).setAttribute('action', '/vimp/homePersonal');
		$("#paginaCorrente").val(numeroPagina);
		$("#frmParametri").submit();
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
				document.getElementById('frmParametri').submit();
			};

			recognition.onerror = function(e) {
				recognition.stop();
			}

		}
	}
</script>

<%--PAGINAZIONE NON GESTITA LATO HANDLER
<div class="content-area home-area-1 recent-property"
	 style="background-color: #FCFCFC; padding-bottom: 0px; padding-top: 20px;">
	<div class="container">
		<div class="row">
			<div class="proerty-th">
				<div class="col-sm-12 col-md-12 p0">

					<div class="col-sm-5 col-md-5" style="padding-bottom: 20px;">
						<input type="text" class="form-control" placeholder="<spring:message code="search" text="Cerca"/>"
							   value="${parametriRicerca.textRicerca}" id="shownText" onchange="setTextRicerca()">
					</div>--%>

					<form:form id="frmParametri" method="post" action="/vimp/homePersonal"
						role="form" modelAttribute="parametriRicerca">

						<form:hidden id="textRicerca" path="textRicerca" value="${parametriRicerca.textRicerca}"/>

						<form:hidden id="paginaCorrente" path="paginaCorrente" />
						<form:hidden id="numeroRecord" path="numeroRecord" />
						<form:hidden id="tipoInformazione" path="tipoInformazione" />


						<form:hidden id="findStartup" path="findStartup" />
						<form:hidden id="findPmi" path="findPmi" />
						<form:hidden id="findSpinoff" path="findSpinoff" />
						<form:hidden id="findGrandi" path="findGrandi" />


						<form:hidden id="findServizi" path="findServizi" />
						<form:hidden id="findPacchettiServizi" path="findPacchettiServizi" />


						<form:hidden id="findProdotti" path="findProdotti" />
						<form:hidden id="findTecnologie" path="findTecnologie" />
						<form:hidden id="findInnovazione" path="findInnovazione" />
						<form:hidden id="findProgetti" path="findProgetti" />

					<%-- PAGINAZIONE NON GESTITA LATO HANDLER
					<div class="col-sm-1 col-md-1" style="margin-right: -10px;">
						<button class="btn search-btn" type="button" onclick="cambiaPagina(0); return false;">
							<i class="fa fa-search"></i>
						</button>
					</div>--%>
	
					</form:form>

<%--				</div>
			</div>
		</div>
	</div>
</div>--%>



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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
<script>
	$(document).ready(function() {

		$('#shownText').keypress(function(e) {
			if(e.keyCode === 13) {
				setTextRicerca();
				cambiaPagina(0);
			}
		});

		// IMPRESE

		if (${parametriRicerca.findStartup == 'S'})
			$('#checkboxStartup').iCheck('check');
		else 
			$('#checkboxStartup').iCheck('uncheck');
		$('#checkboxStartup').on('ifChecked', function(event) {
			$("#findStartup").val("S");
		});
		$('#checkboxStartup').on('ifUnchecked', function(event) {
			$("#findStartup").val("N");
		});

		if (${parametriRicerca.findPmi == 'S'})
			$('#checkboxPmi').iCheck('check');
		else 
			$('#checkboxPmi').iCheck('uncheck');
		$('#checkboxPmi').on('ifChecked', function(event) {
			$("#findPmi").val("S");
		});
		$('#checkboxPmi').on('ifUnchecked', function(event) {
			$("#findPmi").val("N");
		});

		if (${parametriRicerca.findSpinoff == 'S'})
			$('#checkboxSpinoff').iCheck('check');
		else 
			$('#checkboxSpinoff').iCheck('uncheck');
		$('#checkboxSpinoff').on('ifChecked', function(event) {
			$("#findSpinoff").val("S");
		});
		$('#checkboxSpinoff').on('ifUnchecked', function(event) {
			$("#findSpinoff").val("N");
		});

		if (${parametriRicerca.findGrandi == 'S'})
			$('#checkboxGrandi').iCheck('check');
		else 
			$('#checkboxGrandi').iCheck('uncheck');
		$('#checkboxGrandi').on('ifChecked', function(event) {
			$("#findGrandi").val("S");
		});
		$('#checkboxGrandi').on('ifUnchecked', function(event) {
			$("#findGrandi").val("N");
		});


		// SERVIZI


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

		if (${parametriRicerca.findPacchettiServizi == 'S'})
			$('#checkboxPacchettiServizi').iCheck('check');
		else
			$('#checkboxPacchettiServizi').iCheck('uncheck');
		$('#checkboxPacchettiServizi').on('ifChecked', function(event) {
			$("#findPacchettiServizi").val("S");
		});
		$('#checkboxPacchettiServizi').on('ifUnchecked', function(event) {
			$("#findPacchettiServizi").val("N");
		});


		// PROGETTI PROGOTTI

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
		

	})
</script>



<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->