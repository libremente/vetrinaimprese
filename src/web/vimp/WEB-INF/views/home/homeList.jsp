<!-- =========================================================================================== -->
<!-- BEGIN TABELLA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="/WEB-INF/widget.tld" prefix="widget"%>

<spring:message var="bigBusinessUppercaseTitleAttr" code="home.list.big_business.uppercase" text="GRANDI IMPRESE"/>
<spring:message var="servicesUppercaseTitleAttr" code="common_texts.services.uppercase" text="SERVIZI"/>
<spring:message var="standardServicesUppercaseTitleAttr" code="home.list.standard_services.uppercase" text="SERVIZI STANDARD"/>
<spring:message var="projectsUppercaseTitleAttr" code="home.list.projects.uppercase" text="PROGETTI"/>
<spring:message var="productsUppercaseTitleAttr" code="home.list.products.uppercase" text="PRODOTTI"/>
<spring:message var="technologiesUppercaseTitleAttr" code="home.list.technologies.uppercase" text="TECNOLOGIE"/>
<spring:message var="innovationsUppercaseTitleAttr" code="home.list.innovations.uppercase" text="INNOVAZIONI"/>

<c:if test="${!empty msg_infoElencoProcedimenti}">
	<div class="alert alert-info alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		${msg_infoElencoProcedimenti}
	</div>
</c:if>

<hr class="homehr"/>



<!-- property area -->
<div class="content-area home-area-1 recent-property"
	style="padding-bottom: 55px;">
	<div class="container">


		<c:choose>
			<c:when test="${empty lista}">
			
				<c:choose>
					<c:when test="${(parametriRicerca.tipoInformazione == 1 && parametriRicerca.findStartup == 'N' && parametriRicerca.findPmi == 'N' && parametriRicerca.findSpinoff == 'N' && parametriRicerca.findGrandi == 'N') ||
									(parametriRicerca.tipoInformazione == 3 && parametriRicerca.findServizi == 'N' && parametriRicerca.findPacchettiServizi == 'N')}">
						<div class="alert alert-warning alert-dismissable" style="margin-top: 35px;">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong><spring:message code="warning" text="Attenzione"/></strong>
								<spring:message code="home.list.no_search_filter" text="Occorre impostare almeno un filtro di selezione."></spring:message>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-warning alert-dismissable" style="margin-top: 35px;">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong><spring:message code="warning" text="Attenzione"/></strong>
								<spring:message code="home.list.no_results" text="La ricerca non ha prodotto nessun risultato."></spring:message>
						</div>
					</c:otherwise>
				</c:choose>
					
			</c:when>

			<c:otherwise>

				<c:if test="${empty parametriRicerca.textRicerca && !widgetPaginazione}">
					<div class="row">
						<div class="col-md-10 col-md-offset-1 col-sm-12 text-center homemt">
							<p class="homemt-content"><spring:message code="home.list.top_news"></spring:message></p>
						</div>
					</div>
				</c:if>



				<c:if test="${widgetPaginazione}">
					<widget:paginazione
						numeroRecordPerPagina="${parametriRicerca.numeroRecord}"
						numeroRisultatoVisibile="true" numeroRecordTotale="${totRecord}"
						paginaCorrente="${paginaCorrente}" totalePagine="${totPagine}" />
				</c:if>

				<div class="row">
					<div class="proerty-th">

						<c:forEach items="${lista}" var="riga">

							<div class="col-sm-6 col-md-3 p0">
								<div class="box-two proerty-item">

									<div class="item-thumb">
										<c:choose>
											<c:when test="${riga.idTipoInformazione == 1}">
												<!-- <h6>&nbsp;&nbsp;IMPRESA</h6> -->
												<h6 class="card-title">
													<span class="card-title-left-text"><spring:message code="home.list.enterprise" text="Impresa"/></span>
													
													<c:choose>
														<c:when test="${riga.dataCancellazione != null}">
															<span class="card-title-right-text"><label class="label-scaduto-small"><spring:message code="deleted" text="Cancellata"/></label></span>
														</c:when>
														<c:otherwise>
															<span class="card-title-right-text">
																<c:choose>
												    				<c:when test="${riga.stato == 1 || riga.stato == 2}">
																		Spin off
																	</c:when>
																	<c:when test="${riga.stato == 3 || riga.stato == 6}">
																		Start up
																	</c:when>
																	<c:when test="${riga.stato == 4 || riga.stato == 5}">
																		PMI
																	</c:when>
																	<c:when test="${riga.stato == 7}">
																		<spring:message code="home.form.bigEnterprises"/>
																	</c:when>
																</c:choose>
															</span>
														</c:otherwise>
													</c:choose>
												</h6>
													<a href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}">
													<c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_imprese.jpg">
														</c:otherwise>
													</c:choose>
												</a>
											</c:when>

											<c:when test="${riga.idTipoInformazione == 2}">
												<h6 class="card-title">&nbsp;&nbsp;<spring:message code="home.list.stakeholder" text="Stakeholder"/></h6>
												<a href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}">
													<c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_stakeholder.jpg">
														</c:otherwise>
													</c:choose>
												</a>
											</c:when>

											<c:when test="${riga.idTipoInformazione == 3}">
												<h6 class="card-title">
													<span class="card-title-left-text"><spring:message code="home.list.services" text="Servizi"/></span>
													<c:if test="${riga.isScaduto()}">
														<span class="card-title-right-text"><label class="label-scaduto-small"><spring:message code="expired" text="Scaduto"/></label></span>
													</c:if>
												</h6>
												<a href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}"><c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_servizi.jpg">
														</c:otherwise>
													</c:choose> </a>
											</c:when>
											
											
											<c:when test="${riga.idTipoInformazione == 7}">
												<h6 class="card-title">
													<span class="card-title-left-text"><spring:message code="home.list.pacchetto_servizi" text="Pacchetto servizi"/></span>
													<c:if test="${riga.isScaduto()}">
														<span class="card-title-right-text"><label class="label-scaduto-small"><spring:message code="expired" text="Scaduto"/></label></span>
													</c:if>
												</h6>
												<a href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}"><c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_servizi.jpg">
														</c:otherwise>
													</c:choose> </a>
											</c:when>

											<c:when test="${riga.idTipoInformazione == 4}">
												<h6 class="card-title">
													<c:choose>
														<c:when test="${riga.stato == 1}">
															<span class="card-title-left-text"><spring:message code="home.list.project" text="Progetto"/></span>
														</c:when>
														<c:when test="${riga.stato == 2}">
															<span class="card-title-left-text"><spring:message code="home.list.product" text="Prodotto"/></span>
														</c:when>
														<c:when test="${riga.stato == 3}">
															<span class="card-title-left-text"><spring:message code="home.list.technology" text="Tecnologia"/></span>
														</c:when>
														<c:when test="${riga.stato == 4}">
															<span class="card-title-left-text"><spring:message code="home.list.innovation" text="Innvazione"/></span>
														</c:when>
													</c:choose>
													<c:if test="${riga.isScaduto()}">
														<span class="card-title-right-text"><label class="label-scaduto-small"><spring:message code="expired" text="Scaduto"/></label></span>
													</c:if>
												</h6>
												<a
													href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}"><c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${riga.stato == 1}">
																	<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_progetti.jpg">
																</c:when>
																<c:when test="${riga.stato == 2}">
																	<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_prodotto.jpg">
																</c:when>
																<c:when test="${riga.stato == 3}">
																	<img src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_tecnologie.jpg">
																</c:when>
															</c:choose>
														</c:otherwise>
													</c:choose> </a>
											</c:when>


											<c:when test="${riga.idTipoInformazione == 5}">
												<h6 class="card-title">
													<span class="card-title-left-text"><spring:message code="home.list.opportunity" text="Opportunita"/></span>
													<c:if test="${riga.isScaduto()}">
														<span class="card-title-right-text"><label class="label-scaduto-small"><spring:message code="expired" text="Scaduto"/></label></span>
													</c:if>
												</h6>
												<a
													href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}"><c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<img
																src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_opportunita.jpg">
														</c:otherwise>
													</c:choose> </a>
											</c:when>


											<c:when test="${riga.idTipoInformazione == 6}">
												<h6 class="card-title">
													<span class="card-title-left-text"><spring:message code="home.list.news" text="News"/></span>
													<c:if test="${riga.isScaduto()}">
														<span class="card-title-right-text"><label class="label-scaduto-small"><spring:message code="expired" text="Scaduto"/></label></span>
													</c:if>
												</h6>
												<a
													href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}"><c:choose>
														<c:when test="${!empty riga.immagine}">
															<img src="data:image/jpg;base64,${riga.getImageString()}">
														</c:when>
														<c:otherwise>
															<img
																src="${evn_urlRisorseStatiche}/vimp/assets/img/GE_news.jpg">
														</c:otherwise>
													</c:choose> </a>
											</c:when>
										</c:choose>
									</div>


									<div class="item-entry overflow">
										<table>
										  <tr>
										    <td>
										    	<h5>
													<a class="card-ellipsis"
														href="/vimp/detail/${riga.idTipoInformazione}/${riga.idInformazione}"
														title="${riga.titolo}">${riga.titolo}
													</a>
												</h5>
											</td> 
										  </tr>
										</table>
										<%--<div class="dot-hr"></div>--%>

										<c:choose>
											<c:when test="${riga.idTipoInformazione == 3}">
												<span class="pull-left card-detail-ellipsis" title='${riga.prima}'>
													<c:choose>
														<c:when test="${!(empty riga.prima)}">
															${riga.prima}
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${!(empty riga.seconda)}">
															<br>
															${riga.seconda}
														</c:when>
														<c:otherwise>
															<br>&nbsp;
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${!(empty riga.terza)}">
															<br>
															${riga.terza}
														</c:when>
														<c:otherwise>
															<br>&nbsp;
														</c:otherwise>
													</c:choose>
												</span>
											</c:when>
											<c:otherwise>
												<span class="pull-left" title="">
													<c:choose>
														<c:when test="${!(empty riga.prima)}">
															<span class="card-single-ellipsis" title='${riga.prima}'>
															${riga.prima}
															</span>
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${!(empty riga.seconda)}">
															<span class="card-single-ellipsis" title='${riga.seconda}'>
															${riga.seconda}
															</span>
														</c:when>
														<c:otherwise>
															<br>&nbsp;
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${!(empty riga.terza)}">
															<span class="card-single-ellipsis" title="${riga.terza}">
															${riga.terza}
															</span>
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
												</span>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- fine riga -->


				<c:if test="${widgetPaginazione}">
					<widget:paginazione
						numeroRecordPerPagina="${parametriRicerca.numeroRecord}"
						numeroRisultatoVisibile="true" numeroRecordTotale="${totRecord}"
						paginaCorrente="${paginaCorrente}" totalePagine="${totPagine}" />
				</c:if>
			</c:otherwise>
		</c:choose>


		<!-- Count area -->
		<div class="count-area">
			<div class="container">
				<div class="row">
					<div
						class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
						<!-- /.feature title -->
						<h2 class="count-title-label"><spring:message code="home.list.our_datas" text="I nostri dati"/></h2>
					</div>
				</div>
				<div class="row">

					<div
						class="col-md-12 col-xs-12 percent-blocks m-main"
						data-waypoint-scroll="true">
						<div class="row">
							<div class="col-sm-2 col-xs-5">
								<div class="count-item">
									<div class="count-item-circle counter-background-top">
										<div class="chart" data-percent="0">
											<h2 class="percent" id="counterProdotti">0</h2>
										</div>
										<%--<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/CERCHIO.png" height="100" width="100"></span>--%>
									</div>
									<h5 class="count-label"><spring:message code="home.list.products_technologies" text="Prodotti e tecnologie"/></h5>
								</div>
							</div>
							<div class="col-sm-2 col-xs-5">
								<div class="count-item">
									<div class="count-item-circle counter-background-right">
										<div class="chart" data-percent="0">
											<h2 class="percent" id="counterStartup">0</h2>
										</div>
										<%--<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/CERCHIO.png" height="100" width="100"></span>--%>
									</div>
									<h5 class="count-label"><spring:message code="home.list.innovative_startups" text="Start up innovative"/></h5>
								</div>
							</div>
							<div class="col-sm-2 col-xs-5">
								<div class="count-item">
									<div class="count-item-circle counter-background-bottom">
										<div class="chart" data-percent="0">
											<h2 class="percent" id="counterPmi">0</h2>
										</div>
										<%--<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/CERCHIO.png" height="100" width="100"></span>--%>
									</div>
									<h5 class="count-label"><spring:message code="home.list.innovative_PMI" text="PMI innovative"/></h5>
								</div>
							</div>
							
							
							<div class="col-sm-2 col-xs-5">
								<div class="count-item">
									<div class="count-item-circle counter-background-top">
										<div class="chart" data-percent="0">
											<h2 class="percent" id="counterSpinoff">0</h2>
										</div>
										<%--<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/CERCHIO.png" height="100" width="100"></span>--%>
									</div>
									<h5 class="count-label"><spring:message code="home.list.spinoff" text="Spin off"/></h5>
								</div>
							</div>
							
							<div class="col-sm-2 col-xs-5">
								<div class="count-item">
									<div class="count-item-circle counter-background-left">
										<div class="chart" data-percent="0">
											<h2 class="percent" id="counterProgetti">0</h2>
										</div>
										<%--<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/CERCHIO.png" height="100" width="100"></span>--%>
									</div>
									<h5 class="count-label"><spring:message code="home.list.research_projects" text="Progetti di ricerca"/></h5>
								</div>
							</div>
							
							<div class="col-sm-2 col-xs-5">
								<div class="count-item">
									<div class="count-item-circle counter-background-right">
										<div class="chart" data-percent="0">
											<h2 class="percent" id="counterGrandi">0</h2>
										</div>
										<%--<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/CERCHIO.png" height="100" width="100"></span>--%>
									</div>
									<h5 class="count-label"><spring:message code="home.list.big_business" text="Grandi imprese"/></h5>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




<!-- Modal -->
<div class="modal fade" id="errorStateModal" tabindex="-1" role="dialog"
	aria-labelledby="errorStateLabel" aria-hidden="true">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="error" text="Errore"/></h4>
			</div>
			<div class="modal-body">
				<!-- Vuoi eliminare la ditta?  -->
			</div>
			<div class="modal-footer">
				<a type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="close" text="Chiudi"/></a>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="infoStateModal" tabindex="-1" role="dialog"
	aria-labelledby="infoStateLabel" aria-hidden="true">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="close" text="Chiudi"/></span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="info" text="Info"/></h4>
			</div>
			<div class="modal-body">
				<!-- Vuoi eliminare la ditta?  -->
			</div>
			<div class="modal-footer">
				<a type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="close"></spring:message></a>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="waiting-modal" tabindex="-1" role="dialog"
	aria-labelledby="waiting" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="box_waiting">
					<img src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif">
					<strong>&nbsp;<spring:message code="in_progress" text="operazione in corso..."/></strong>
				</div>
			</div>
		</div>
	</div>
</div>






<script>

function strip(html)
{
   var tmp = document.createElement("DIV");
   tmp.innerHTML = html;
   return tmp.textContent || tmp.innerText || "";
}

$(document).ready(function () {
setTimeout(function () {
        $('#counterProdotti').text('0');
        $('#counterStartup').text('0');
        $('#counterPmi').text('0');
        $('#counterSpinoff').text('0');
        $('#counterProgetti').text('0');
        $('#counterGrandi').text('0');
        
        setInterval(function () {
            var curval = parseInt($('#counterProdotti').text());
            var curval1 = parseInt($('#counterStartup').text());
            var curval2 = parseInt($('#counterPmi').text());
            var curval3 = parseInt($('#counterSpinoff').text());
            var curval4 = parseInt($('#counterProgetti').text());
            var curval5 = parseInt($('#counterGrandi').text());
            if (curval <= ${prodottiRegistrati} -1) {
                $('#counterProdotti').text(curval + 1);
            }
            if (curval1 <= ${startupRegistrate}-1) {
                $('#counterStartup').text(curval1 + 1);
            }
            if (curval2 <= ${pmiRegistrate}-1) {
                $('#counterPmi').text(curval2 + 1);
            }
            if (curval3 <= ${spinoffRegistrati}-1) {
                $('#counterSpinoff').text(curval3 + 1);
            }
            if (curval4 <= ${progettiRegistrati}-1) {
                $('#counterProgetti').text(curval4 + 1);
            }
            if (curval5 <= ${grandiRegistrati}-1) {
                $('#counterGrandi').text(curval5 + 1);
            }
            
        }, 2);
    }, 500);
})

$(document).ready(function() {
		var totPagineVar = "${totPagine}";
		var paginaCorrenteVar = "${paginaCorrente}";
})
</script>

<!-- END TABELLA -->
<!-- =========================================================================================== -->