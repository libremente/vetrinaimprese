<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="widget" uri="/WEB-INF/widget.tld" %>

<div class="container-fluid">
    <div class="row">
        <c:if test="${modifica}">
        <div class="col">
            <h6>
                <spring:message code="gestione_delegati.nuovo_intestazione"/>
            </h6>
        </div>
        </c:if>

        <div class="col">
            <div class="form-group">
                <label>
                    <spring:message code="gestione_delegati.label_impresa"/>
                </label>
                <c:if test="${fn:length(elencoImprese) gt 0}">
                    <select class="selectpicker" id="idPlfImpresa" data-live-search="true"
                            data-live-search-style="contains">
                        <c:forEach items="${elencoImprese}" var="impresa">
                            <option value="${impresa.idPlfImpresa}" ${impresa.idPlfImpresa eq idPlfImpresa ? 'selected' : ''}>
                                    ${impresa.impresaTranslation.descImpresa}
                            </option>
                        </c:forEach>
                    </select>

                </c:if>

                <c:if test="${fn:length(elencoImprese) eq 1}">
                    <input value="${elencoImprese[0].impresaTranslation.descImpresa}" class="formControl" disabled>
                    <input type="hidden" id="idPlfImpresa" value="${elencoImprese[0].idPlfImpresa}">
                </c:if>

            </div>
        </div>

        <c:if test="${totPagine >1}">
            <div class="row">

                <widget:paginazione
                        numeroRecordPerPagina="${parametriRicerca.numeroRecord}"
                        numeroRisultatoVisibile="true" numeroRecordTotale="${totRecord}"
                        paginaCorrente="${paginaCorrente}" totalePagine="${totPagine}" />
            </div>
        </c:if>

        <div class="col">
            <table id="table-delegati" class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        <spring:message code="gestione_delegati.tabella.nome"/>
                    </th>
                    <th>
                        <spring:message code="gestione_delegati.tabella.codice_fiscale"/>
                    </th>
                    <th>
                        <spring:message code="gestione_delegati.tabella.azioni"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${elencoDelegati}" var="delegato">
                    <tr>
                        <td>${delegato.utente.cognome} ${delegato.utente.nome}
                            <c:if test="${delegato.legaleRappresentante}">
                                <span title="<spring:message code="gestione_delegati.legale_rappresentante"/>">*</span>
                            </c:if>
                        </td>
                        <td>${delegato.utente.codiceFiscale}</td>
                        <td>
                            <c:if test="${modifica and delegato.impresa and not delegato.legaleRappresentante}">
                                <spring:message code="gestione_delegati.link.revoca_impresa" var="linkRevocaImpresa"/>
                                <button class="btn btn-default btn-sm button-revoca-impresa" data-id-delegato="${delegato.utente.idUtente}"
                                   title="${linkRevocaImpresa}">
                                    <span class="glyphicon glyphicon-erase"></span>
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <span>*</span> <spring:message code="gestione_delegati.legale_rappresentante"/>
                    </tr>
                </tfoot>
            </table>
            <c:if test="${modifica}">
                <button id="nuovoDelegatoButton" class="btn btn-primary" style="margin-bottom: 10px">
                    <spring:message code="gestione_delegati.nuovo_button"/>
                </button>
            </c:if>
        </div>

    </div>

</div>

<div class="modal fade" id="revocaDelegatoConfirm" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4><spring:message code="warning"/></h4>
            </div>
            <div class="modal-body">
                <spring:message code="gestione_delegati.conferma_revoca"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <spring:message code="undo"/>
                </button>
                <button id="revocaDelegatoButton" type="button" class="btn btn-primary" data-dismiss="modal">
                    <spring:message code="continue"/>
                </button>
            </div>
        </div>
    </div>
</div>

<script src="${evn_urlRisorseStatiche}/vimp/bootstrap/js/bootstrap.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-select.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wow.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/bootstrap-hover-dropdown.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/icheck.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/owl.carousel.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script>
    (function () {
        var idUtenteSelezionato;

        $(function () {
            $('#idPlfImpresa').change(selezionaImpresa);
            $('#nuovoDelegatoButton').click(formDelegato);
            $('#table-delegati').on('click', '.button-revoca-impresa', confermaRevocaDelagato);
            $('#revocaDelegatoButton').click(revocaDelegato);
        });

        function selezionaImpresa() {
            var idPlfImpresa = $('#idPlfImpresa').val();
            location.assign('/vimp/secure/gestioneDelegati?idPlfImpresa=' + idPlfImpresa);
        }

        function formDelegato() {
            var idPlfImpresa = $('#idPlfImpresa').val();
            location.assign('/vimp/secure/formDelegato?idPlfImpresa=' + idPlfImpresa);
        }

        function confermaRevocaDelagato() {
            idUtenteSelezionato = $(this).data('id-delegato');
            $('#revocaDelegatoConfirm').modal();
        }

        function revocaDelegato() {
            var idPlfImpresa = $('#idPlfImpresa').val();
            location.assign('/vimp/secure/revocaDelegato?idPlfImpresa=' + idPlfImpresa + '&idUtente=' + idUtenteSelezionato);
        }

    })();
</script>