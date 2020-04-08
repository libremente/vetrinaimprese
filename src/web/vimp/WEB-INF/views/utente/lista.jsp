<!-- =========================================================================================== -->
<!-- BEGIN TABELLA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="/WEB-INF/widget.tld" prefix="widget"%>

<spring:message var="surnameTitleAttr" code="surname" text="Cognome"/>
<spring:message var="nameTitleAttr" code="name" text="Nome"/>
<spring:message var="sendEmailTitleAttr" code="utente.listaview.sendEmailTitleAttr" text="Invia e-mail"/>
<spring:message var="roleTitleAttr" code="common_texts.role" text="Ruolo"/>




<c:if test="${!empty msg_infoElencoProcedimenti}">
	<div class="alert alert-info alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		${msg_infoElencoProcedimenti}
	</div>
</c:if>

<hr />




<!-- property area -->
<div class="content-area home-area-1 recent-property"
	style="padding-bottom: 55px;">
	<div class="container">


		<c:choose>
			<c:when test="${empty lista}">
				<div class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code="warning" text="Attenzione!"/></strong> <spring:message code="messaggioNessunRisultato" text="La ricerca non ha prodotto nessun risultato"/>.
				</div>
			</c:when>

			<c:otherwise>

				<c:if test="${widgetPaginazione}">
					<widget:paginazione
						numeroRecordPerPagina="${parametriRicerca.numeroRecord}"
						numeroRisultatoVisibile="true" numeroRecordTotale="${totRecord}"
						paginaCorrente="${paginaCorrente}" totalePagine="${totPagine}" />
				</c:if>

				<div class="row">
					<div class="proerty-th">
					
						 <div class="col-sm-12">
					         <div class="col-sm-12">
					            <div class="table-responsive">
					               <table id="tabella" class="table table-condensed bordered1 tablesorter">
					                  <thead>
					                     <tr>
					                        <th></th>
					                        <th data-toggle="tooltip" data-placement="top" title="${surnameTitleAttr}"><spring:message code="surname" text="Cognome"/></th>
					                        <th data-toggle="tooltip" data-placement="top" title="${nameTitleAttr}"><spring:message code="name" text="Nome"/></th>
					                        <th data-toggle="tooltip" data-placement="top" title="${sendEmailTitleAttr}"><spring:message code="mail" text="Email"/></th>
					                        <th data-toggle="tooltip" data-placement="top" title="${roleTitleAttr}"><spring:message code="common_texts.role" text="Ruolo"/></th>
					                     </tr>
					                  </thead>
					                  <tbody>
					                  
					                  
					                     <c:forEach items="${lista}" var="riga">
					                        <tr id='riga${riga.idUtente}'>
					                        
					                           <td><a href="/vimp/utente/${riga.idUtente}">
					                              <span class="glyphicon glyphicon-user"></span>
					                              </a>
					                           </td>
					                           
					                           <td><a href="/vimp/utente/${riga.idUtente}">
					                              <span>${riga.cognome}</span>
					                              </a>
					                           </td>
					                           
					                           <td><a href="/vimp/utente/${riga.idUtente}">
					                              <span>${riga.nome}</span>
					                              </a>
					                           </td>
					                           
					                          
					                           
					                           <td><a href="mailto:${riga.email}">
					                              <span>${riga.email}</span>
					                              </a>
					                           </td>
					                           
					                            <td><a href="/vimp/utente/${riga.idUtente}">
					                              <span>${riga.ruolo.descRuolo}</span>
					                              </a>
					                           </td>
											   
					                        </tr>
					                     </c:forEach>
					                  </tbody>
					               </table>
					            </div>
					         </div>
					      </div>
						
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
				<a type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="close" text="Chiudi"/></a>
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
					<strong>&nbsp;<spring:message code="operazioneInCorso" text="operazione in corso"/>...</strong>
				</div>
			</div>
		</div>
	</div>
</div>


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery-1.10.2.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/bootstrap/js/bootstrap.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-select.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-hover-dropdown.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/easypiechart.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.easypiechart.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/owl.carousel.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wow.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/icheck.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/price-range.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.tablecloth.js"></script>
<script type="text/javascript" src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-editable.js"></script>


<script>

$(document).ready(function() {
		var totPagineVar = "${totPagine}";
		var paginaCorrenteVar = "${paginaCorrente}";
		$('#top-pagination').pagination({
			currentPage : paginaCorrenteVar,
			pages : totPagineVar,
			prevText : 'Indietro',
			nextText : 'Avanti',
			cssStyle : 'light-theme',
			onPageClick : function(pageNumber, event) {
				cambiaPagina(pageNumber);
			},
		});

		$('#bottom-pagination').pagination({
			currentPage : paginaCorrenteVar,
			pages : totPagineVar,
			prevText : 'Indietro',
			nextText : 'Avanti',
			cssStyle : 'light-theme',
			onPageClick : function(pageNumber, event) {
				cambiaPagina(pageNumber);
			},
		});
		// TABELLA
		$("#tabella").tablecloth({
			theme : "default",
			striped : true,
			sortable : true,
			condensed : true,
			bordered : true
		});
	})
</script>

<!-- END TABELLA -->
<!-- =========================================================================================== -->