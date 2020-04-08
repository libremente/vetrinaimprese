<!-- =========================================================================================== -->
<!-- BEGIN TABELLA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="/WEB-INF/widget.tld" prefix="widget"%>

<spring:message var="ragSocTitleAttr" code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/>
<spring:message var="fiscalCodeTitleAttr" code="common_texts.fiscal_code" text="Codice fiscale"/>
<spring:message var="partitaIvaTitleAttr" code="form.richiesta.accreditamento.iva" text="Partita IVA"/>
<spring:message var="settoreTitleAttr" code="ricerca.impresa.form.settore" text="Settore"/>
<spring:message var="districtTitleAttr" code="ricerca.impresa.form.district" text="Provincia"/>
<spring:message var="cityTitleAttr" code="common_texts.city" text="Comune"/>
<spring:message var="enterpriseStatusTitleAttr" code="form.dettaglio.accreditamento.enterprise_status" text="Stato impresa"/>

<hr />
<c:if test="${totPagine >1}">
	<div class="row">

		<widget:paginazione
			numeroRecordPerPagina="${parametriRicerca.numeroRecord}"
			numeroRisultatoVisibile="true" numeroRecordTotale="${totRecord}"
			paginaCorrente="${paginaCorrente}" totalePagine="${totPagine}" />
	</div>
</c:if>

<c:choose>
	<c:when test="${!utente.isBackoffice()}">
	<div class="alert alert-warning alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="operation_not_permitted" text="Operazione non permessa"/>.
	</div>
	</c:when>
	<c:when test="${empty lista}">
		<div class="alert alert-warning alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="the_search_did_not_produce_any_results" text="La ricerca non ha prodotto nessun risultato"/>.
		</div>
	</c:when>
	<c:otherwise>

		<div class="row">

			<div class="table-responsive">
				<table id="tabella"
					class="table table-condensed bordered1 tablesorter">
					<thead>
						<tr>

							<th></th>

							<th data-toggle="tooltip" data-placement="top"
								title="${ragSocTitleAttr}"><spring:message code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/></th>

							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('codiceFiscale')}">
								<th data-toggle="tooltip" data-placement="top"
									title="${fiscalCodeTitleAttr}"><spring:message code="form.richiesta.accreditamento.codice_fiscale" text="Codice fiscale"/></th>
							</c:if>

							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('partitaIva')}">
								<th data-toggle="tooltip" data-placement="top"
									title="${partitaIvaTitleAttr}"><spring:message code="form.richiesta.accreditamento.iva" text="Partita IVA"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('settore')}">
								<th data-toggle="tooltip" data-placement="top" title="${settoreTitleAttr}"><spring:message code="ricerca.impresa.form.settore" text="Settore"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('provincia')}">
								<th data-toggle="tooltip" data-placement="top" title="${districtTitleAttr}"><spring:message code="common_texts.province" text="Provincia"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('comune')}">
								<th data-toggle="tooltip" data-placement="top" title="${cityTitleAttr}"><spring:message code="common_texts.city" text="Comune"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('statoImpresa')}">
								<th data-toggle="tooltip" data-placement="top"
									title="${enterpriseStatusTitleAttr}"><spring:message code="form.richiesta.accreditamento.enterprise_status" text="Stato impresa"/></th>
							</c:if>


						</tr>
					</thead>
					<tbody>

						<c:forEach items="${lista}" var="riga">
							<tr id='riga${riga.idImpresa}'>



								<td><a href="/vimp/impresa/${riga.idImpresa}"> <c:choose>
											
											<c:when test="${riga.idStatoImpresa == 1 || riga.idStatoImpresa == 2}">
												<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/icona_spinf_off_azienda.png" height="20" width="20"></span>
											</c:when>

											<c:when test="${riga.idStatoImpresa == 3 || riga.idStatoImpresa == 6}">
												<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/startup_lista_risultati.png" height="20" width="20"></span>
											</c:when>

											<c:when test="${riga.idStatoImpresa == 4 || riga.idStatoImpresa == 5}">
												<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/pmi_lista_risultati.png" height="20" width="20"></span>
											</c:when>
											
											<c:when test="${riga.idStatoImpresa == 7}">
												<span><img src="${evn_urlRisorseStatiche}/vimp/assets/img/ic/grandi_imprese_lista_risultati.png" height="20" width="20"></span>
											</c:when>
										</c:choose>
								</a></td>



								<td>${riga.ragioneSociale}</td>


								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('codiceFiscale')}">
									<td>${riga.codiceFiscale}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('partitaIva')}">
									<td>${riga.partitaIva}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('settore')}">
									<td>${riga.settore}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('provincia')}">
									<td>${riga.provincia}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('comune')}">
									<td>${riga.comune}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('statoImpresa')}">
									<td>${riga.statoImpresa}</td>
								</c:if>

							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

			<c:if test="${totPagine >1}">
				<widget:paginazione
					numeroRecordPerPagina="${parametriRicerca.numeroRecord}"
					numeroRisultatoVisibile="true" numeroRecordTotale="${totRecord}"
					paginaCorrente="${paginaCorrente}" totalePagine="${totPagine}" />
			</c:if>

		</div>

		<!-- Scripts -->




		<script>
			$(document).ready(function() {
				$("#tabella").tablecloth({
					theme : "default",
					striped : true,
					sortable : true,
					condensed : true,
					bordered : true
				});
			})
		</script>





	</c:otherwise>
</c:choose>





<!-- END TABELLA -->
<!-- =========================================================================================== -->