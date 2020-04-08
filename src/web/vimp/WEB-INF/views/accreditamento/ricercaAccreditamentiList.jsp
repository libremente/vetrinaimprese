<!-- =========================================================================================== -->
<!-- BEGIN TABELLA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="/WEB-INF/widget.tld" prefix="widget"%>

<spring:message var="requestDateTitleAttr" code="ricerca.accreditamento.form.request_date" text="Data richiesta"/>
<spring:message var="ragSocTitleAttr" code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/>
<spring:message var="fiscalCodeTitleAttr" code="common_texts.fiscal_code" text="Codice fiscale"/>
<spring:message var="accreditamentoIvaTitleAttr" code="form.richiesta.accreditamento.iva" text="Partita IVA"/>
<spring:message var="enterpriseStatusTitleAttr" code="form.dettaglio.accreditamento.enterprise_status" text="Stato impresa"/>
<spring:message var="checksTitleAttr" code="ricerca.accreditamenti.list.checks" text="Controlli"/>
<spring:message var="requestStatusTitleAttr" code="form.dettaglio.accreditamento.request_status" text="Stato richiesta"/>
<spring:message var="" code="" text="Seleziona lo stato dell'impresa dell'impresa"/>
<spring:message var="thereAreActiveFiltersTitleAttr" code="ricerca.impresa.form.thereAreActiveFiltersTitleAttr" text="Ci sono dei filtri attivi"/>

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
							
							<th style="width: 8%;" data-toggle="tooltip" data-placement="top"
								title="${requestDateTitleAttr}"><spring:message code="form.dettaglio.accreditamento.request_date" text="Data richiesta"/></th>
							
							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('ragioneSociale')}">
								<th data-toggle="tooltip" data-placement="top"
									title="${ragSocTitleAttr}"><spring:message code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/></th>
							</c:if>

							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('codiceFiscale')}">
								<th data-toggle="tooltip" data-placement="top"
									title="${fiscalCodeTitleAttr}"><spring:message code="common_texts.fiscal_code" text="Codice fiscale"/></th>
							</c:if>

							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('partitaIva')}">
								<th data-toggle="tooltip" data-placement="top"
									title="${accreditamentoIvaTitleAttr}"><spring:message code="form.richiesta.accreditamento.iva" text="Partita IVA"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('descStatoImpresa')}">
								<th data-toggle="tooltip" data-placement="top" title="${enterpriseStatusTitleAttr}"><spring:message code="form.richiesta.accreditamento.enterprise_status" text="Stato impresa"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('descControlliRichiesta')}">
								<th data-toggle="tooltip" data-placement="top" title="${checksTitleAttr}"><spring:message code="ricerca.accreditamenti.list.checks" text="Controlli"/></th>
							</c:if>


							<c:if
								test="${parametriRicerca.isColonnaVisualizzabile('descStatoRichiesta')}">
								<th data-toggle="tooltip" data-placement="top" title="${requestStatusTitleAttr}"><spring:message code="form.dettaglio.accreditamento.request_status" text="Stato richiesta"/></th>
							</c:if>

						</tr>
					</thead>
					<tbody>

						<c:forEach items="${lista}" var="riga">
							<tr id='riga${riga.idRichiestaAccreditamento}'>

								<td><a href="/vimp/secure/dettaglioAccreditamento/${riga.idRichiestaAccreditamento}"> 
									<span class="pe-7s-flag"></span>
								</a></td>


								<td><a href="/vimp/secure/dettaglioAccreditamento/${riga.idRichiestaAccreditamento}"> 
									${riga.dataRichiesta}
								</a></td>

								
								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('ragioneSociale')}">
									<td>${riga.ragioneSociale}</td>
								</c:if>


								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('codiceFiscale')}">
									<td>${riga.codiceFiscale}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('partitaIva')}">
									<td>${riga.partitaIva}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('descStatoImpresa')}">
									<td>${riga.descStatoImpresa}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('descControlliRichiesta')}">
									<td>${riga.descControlliRichiesta}</td>
								</c:if>

								<c:if
									test="${parametriRicerca.isColonnaVisualizzabile('descStatoRichiesta')}">
									<td>${riga.descStatoRichiesta}</td>
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
					sortable : false,
					condensed : true,
					bordered : true
				});
			})
		</script>





	</c:otherwise>
</c:choose>





<!-- END TABELLA -->
<!-- =========================================================================================== -->