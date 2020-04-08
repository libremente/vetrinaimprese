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
		$("#frmParametri").get(0).setAttribute('action', '/vimp/homeSearch');
		$('#paginaCorrente').val(numeroPagina);
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


<div class="content-area home-area-1 recent-property" style="padding-bottom: 0px;">
	<div class="container home-form-container">

		<div class="col-md-6 row home-search-row">
			<div class="search-bar col-md-10 col-sm-5 p0">
				<input type="text" class="form-control home-form-control" placeholder="<spring:message code="search" text="Cerca"/>"
					   value="${parametriRicerca.textRicerca}" id="shownText" onchange="setTextRicerca()">
			</div>

			<%--<div class="">--%>
				<button class="btn search-btn col-md-2 col-sm-1 p0" type="button" onclick="cambiaPagina(0); return false;">
					<i class="fa fa-search"></i>
				</button>
			<%--</div>--%>
		</div>

		<form:form id="frmParametri" method="get" action="/vimp/homeSearch"
			role="form" modelAttribute="parametriRicerca">

			<form:hidden id="textRicerca" path="textRicerca" value="${parametriRicerca.textRicerca}"/>

			<form:hidden id="paginaCorrente" path="paginaCorrente" />
			<form:hidden id="numeroRecord" path="numeroRecord" />
			<form:hidden id="tipoInformazione" path="tipoInformazione" />


			<form:hidden id="findStartup" path="findStartup" />
			<form:hidden id="findPmi" path="findPmi" />
			<form:hidden id="findSpinoff" path="findSpinoff" />
			<form:hidden id="findGrandi" path="findGrandi" />



			<form:hidden id="findIncubatori" path="findIncubatori" />

			<form:hidden id="findServizi" path="findServizi" />
			<form:hidden id="findPacchettiServizi" path="findPacchettiServizi" />


			<form:hidden id="findProdotti" path="findProdotti" />
			<form:hidden id="findTecnologie" path="findTecnologie" />
			<form:hidden id="findInnovazione" path="findInnovazione" />
			<form:hidden id="findProgetti" path="findProgetti" />

			<form:hidden id="findNewsEvidenza" path="findNewsEvidenza"/>

			<c:choose>
				<c:when test="${parametriRicerca.tipoInformazione == 1}">
					<div class="col-md-6 col-sm-12" >

						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox"> <input
										id="checkboxStartup" name="checkboxStartup" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/startup_lista_risultati.png" height="30" width="30">
									</span>--%>
										Start up
									</strong>
								</label>
							</div>
						</div>
						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox"> <input
										id="checkboxPmi" name="checkboxPmi" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/pmi_lista_risultati.png" height="30" width="30">
									</span>--%>
										PMI
									</strong>
								</label>
							</div>
						</div>
						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox"> <input
										id="checkboxSpinoff" name="checkboxSpinoff" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/icona_spinf_off_azienda.png" height="30" width="30">
									</span>--%>
										Spin off
									</strong>
								</label>
							</div>
						</div>
						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox" style="padding-left: 0px;"> <input
										id="checkboxGrandi" name="checkboxGrandi" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/grandi_imprese_lista_risultati.png" height="30" width="30">
									</span>--%>
										<spring:message code="home.form.bigEnterprises" text="Grandi imp."/>
									</strong>
								</label>
							</div>
						</div>

					</div>
				</c:when>
				
				
				<c:when test="${parametriRicerca.tipoInformazione == 2}">
					<div class="col-sm-6" >
						<div class="col-sm-6 col-md-6">
							<div class="checkbox">
								<input id="checkboxIncubatore" name="checkboxIncubatore" type="checkbox" />
								<strong class="check-home-form"><spring:message code="home.incubatori.stakeholder"/>
								</strong>
							</div>
						</div>
					</div>
				</c:when>

				<c:when test="${parametriRicerca.tipoInformazione == 3}">

					<div class="col-sm-6" >

						<div class="col-sm-4 col-md-4">
							<div class="checkbox">
								<input id="checkboxServizi" name="checkboxServizi" type="checkbox" />
								<strong class="check-home-form"><spring:message code="servizi.degli.stakeholder"/>
								</strong>
							</div>
						</div>
						<div class="col-sm-4 col-md-4">
							<div class="checkbox">
								<input id="checkboxPacchettiServizi" name="checkboxPacchettiServizi" type="checkbox" />
								<strong class="check-home-form">
									<span><spring:message code="pacchetti.degli.stakeholder"/>
								</strong>
							</div>
						</div>

						<c:if test="${utente.isBackoffice() || utente.isStakeholder()}">
							<div class="col-sm-4 wow fadeInDown" data-wow-delay="0.9s">
								<button class="navbar-btn nav-button login " type="button"
										onclick="location.href='/vimp/secure/newElement/7';"
										data-wow-delay="0.8s"><spring:message code="home.new.service_bundle"/></button>
							</div>
						</c:if>
					</div>
				</c:when>

				<c:when test="${parametriRicerca.tipoInformazione == 4}">

					<div class="col-sm-6" >

						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox"> <input
										id="checkboxProgetti" name="checkboxProgetti" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/icona_progetti.png" height="30" width="30">
									</span>--%>
										<spring:message code="common_texts.projects" text="Progetti"/>
									</strong>
								</label>
							</div>
						</div>
						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox"> <input
										id="checkboxTecnologie" name="checkboxTecnologie" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/icona_tecnologie.png" height="30" width="30">
									</span>--%>
										<spring:message code="home.form.technologies" text="Tecnol."/>
									</strong>
								</label>
							</div>
						</div>
						<div class="col-sm-3 col-md-3 home-form-single-check-section">
							<div class="checkbox">
								<label class="center homecheckbox" style="padding-left: 0px;"> <input
										id="checkboxProdotti" name="checkboxProdotti" type="checkbox" />
									<strong class="check-home-form">
									<%--<span>
										<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/icona_prodotti.png" height="30" width="30">
									</span>--%>
										<spring:message code="common_texts.products" text="Prodotti"/>
									</strong>
								</label>
							</div>
						</div>

					</div>
				</c:when>

				<c:when test="${parametriRicerca.tipoInformazione == 6}">

					<div class="col-md-6">
						<label>
							<input type="checkbox" id="evidenzaPortaleCheck"/>
							<strong>&nbsp;<spring:message code="home.form.news.evidence"/>&nbsp;</strong>
						</label>
					</div>
				</c:when>

				<c:otherwise>
					<div class="col-md-6 search-label-container">
						<span class="home-form-label-bungee"><spring:message code="home.form.fai_crescere"/></span>
						<span class="home-form-label-regular"><spring:message code="home.form.your_idea"/></span>
					</div>
				</c:otherwise>
			</c:choose>
		</form:form>
	</div>
</div>


<%-- <script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery-1.10.2.min.js"></script> --%>
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


		// STAKEHOLDER

		if (${parametriRicerca.findIncubatori == 'S'})
			$('#checkboxIncubatore').iCheck('check');
		else 
			$('#checkboxIncubatore').iCheck('uncheck');
		$('#checkboxIncubatore').on('ifChecked', function(event) {
			$("#findIncubatori").val("S");
		});
		$('#checkboxIncubatore').on('ifUnchecked', function(event) {
			$("#findIncubatori").val("N");
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

		if (${parametriRicerca.findInnovazione == 'S'})
			$('#checkboxInnovazione').iCheck('check');
		else 
			$('#checkboxInnovazione').iCheck('uncheck');
		$('#checkboxInnovazione').on('ifChecked', function(event) {
			$("#findInnovazione").val("S");
		});
		$('#checkboxInnovazione').on('ifUnchecked', function(event) {
			$("#findInnovazione").val("N");
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

		// NEWS

		if (${parametriRicerca.findNewsEvidenza == 'S'})
			$('#evidenzaPortaleCheck').iCheck('check');
		else
			$('#evidenzaPortaleCheck').iCheck('uncheck');

		$('#evidenzaPortaleCheck').on('ifChecked', function(event) {
			$("#findNewsEvidenza").val("S");
		});
		$('#evidenzaPortaleCheck').on('ifUnchecked', function(event) {
			$("#findNewsEvidenza").val("N");
		});
		

	})

</script>



<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->