<!-- =========================================================================================== -->
<!-- BEGIN TABELLA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="/WEB-INF/widget.tld" prefix="widget"%>

<spring:message var="enterpriseIdTitleAttr" code="batch.log.enterprise_id" text="Identificativo impresa"/>
<spring:message var="fiscalCodeTitleAttr" code="common_texts.fiscal_code" text="Codice fiscale"/>
<spring:message var="partitaIvaTitleAttr" code="form.richiesta.accreditamento.iva" text="Partita IVA"/>
<spring:message var="ragSocTitleAttr" code="form.dettaglio.accreditamento.rag_soc" text="Ragione sociale"/>
<spring:message var="lastUpdateFromTheChamberOfCommerceBusinessRegistry" code="batch.log.list.batchDateFromTheChamberOfCommerceBusinessRegistryTitleAttr" text="Data ultimo aggiornamento da CCIAA-Anagrafe Imprese"/>
<spring:message var="lastUpdateByCCIAATitleAttr" code="batch.log.list.batchDateByCCIAARegistry" text="Data ultimo aggiornamento da CCIAA-Registro"/>
<spring:message var="batchCheckResultTitleAttr" code="batch.log.list.batchCheckResultTitleAttr" text="Risultato controllo batch"/>

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
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js"
	type="text/javascript"></script>
<script
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script type="text/javascript"
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript"
	src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript"
	src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-editable.js"></script>


<div class="row">
	<h2 class="center">
		<small><spring:message code="batch.log.list" text="Visualizzazzione log allineamento"/></small>
	</h2>
</div>

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
	<c:when test="${empty lista}">
		<div class="alert alert-warning alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
			<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="messaggioNessunRisultato" text="La ricerca non ha prodotto nessun risultato."/>
		</div>
	</c:when>

	<c:otherwise>

		<form:form id="frmParametri" method="post" action="batchlog"
			role="form" modelAttribute="parametriRicerca">
			
			
			
			<form:hidden id="paginaCorrente" path="paginaCorrente" />
			<form:hidden id="numeroRecord" path="numeroRecord" />

			<div class="row">

				<div class="table-responsive">
					<table id="tabella"
						class="table table-condensed bordered1 tablesorter">
						<thead>
							<tr>

								<th data-toggle="tooltip" data-placement="top"
									title="${enterpriseIdTitleAttr}"><spring:message code="batch.log.enterprise_id" text="Identificativo impresa"/></th>

								<th data-toggle="tooltip" data-placement="top"
									title="${fiscalCodeTitleAttr}"><spring:message code="batch.log.codice_fiscale" text="Codice fiscale"/></th>

								<th data-toggle="tooltip" data-placement="top"
									title="${partitaIvaTitleAttr}"><spring:message code="batch.log.iva" text="Partita IVA"/></th>

								<th data-toggle="tooltip" data-placement="top"
									title="${ragSocTitleAttr}"><spring:message code="batch.log.rag_soc" text="Ragione sociale"/></th>

								<th data-toggle="tooltip" data-placement="top"
									title="${lastUpdateFromTheChamberOfCommerceBusinessRegistry}"><spring:message code="batch.log.data_cciaa_anagrafe" text="Data CCIAA-Anagrafe Imprese"/></th>

								<th data-toggle="tooltip" data-placement="top"
									title="${lastUpdateByCCIAATitleAttr}"><spring:message code="batch.log.data_cciaa_registro" text="Data CCIAA-Registro"/></th>

								<th data-toggle="tooltip" data-placement="top"
									title="${batchCheckResultTitleAttr}"><spring:message code="batch.log.controllo" text="Controllo"/></th>


							</tr>
						</thead>
						<tbody>

							<c:forEach items="${lista}" var="riga">
								<tr id='riga${riga.idLog}'>


									<td>${riga.plfImpresa.idPlfImpresa}</td>
									<td>${riga.codiceFiscale}</td>
									<td>${riga.partitaIva}</td>
									<td>${riga.ragioneSociale}</td>
									<td>${riga.dataUltimoAggCciaAnagrafe}</td>
									<td>${riga.dataUltimoAggCciaRegistro}</td>
									<td>${riga.logMessaggi.descrizione}</td>


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
				function cambiaPagina(numeroPagina) {
					$("#frmParametri").get(0)
							.setAttribute('action', 'batchlog');
					$("#paginaCorrente").val(numeroPagina);
					$("#frmParametri").submit();
				}

				function cambiaNumeroRecord(numeroRecord) {
					$("#frmParametri").get(0)
							.setAttribute('action', 'batchlog');
					$("#numeroRecord").val(numeroRecord);
					$("#paginaCorrente").val(1);
					$("#frmParametri").submit();
				}

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




		</form:form>
	</c:otherwise>
</c:choose>



<!-- END TABELLA -->
<!-- =========================================================================================== -->