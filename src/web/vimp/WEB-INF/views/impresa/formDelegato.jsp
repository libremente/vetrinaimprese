<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
    button:disabled {
        color: graytext;
    }
</style>

<form:form modelAttribute="formDelegato" action="/vimp/secure/salvaDelegato" cssClass="safe-form">

    <input type="hidden" id="idPlfImpresa" name="idPlfImpresa" value="${impresa.idPlfImpresa}">

    <div class="container-fluid">

        <c:if test="${!empty errorMessage}">
            <div class="alert alert-danger fade in">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;
                </button>
                <p>
                    <strong><spring:message code="${errorMessage}"/></strong>
                </p>
            </div>
        </c:if>

        <div class="row">
            <div class="col">
                <h1 class="page-title-small">
                    <b><spring:message code="form_delegato.header"/></b>
                </h1>
                <h6>
                    <spring:message code="form_delegato.sub_header"/> ${impresa.impresaTranslation.descImpresa}
                </h6>
            </div>
        </div>

        <div class="row">
            <div class="col">

                <div class="form-group">
                    <label>
                        <spring:message code="form_delegato.nome"/>
                    </label>
                    <form:input path="nome" class="form-control"/>
                </div>

                <div class="form-group">
                    <label>
                        <spring:message code="form_delegato.cognome"/>
                    </label>
                    <form:input path="cognome" class="form-control"/>
                </div>

                <div class="form-group">
                    <label>
                        <spring:message code="form_delegato.codice_fiscale"/>
                    </label>
                    <form:input path="codiceFiscale" class="form-control"/>
                </div>

                <div class="form-group">
                    <label>
                        <spring:message code="form_delegato.email"/>
                    </label>
                    <form:input path="email" class="form-control"/>
                </div>

            </div>

            <div class="col" style="margin-bottom: 15px">
                <button class="btn btn-primary">
                    <spring:message code="form_delegato.salva_nuovo"/>
                </button>
                <button id="cercaEsistenteButton" type="button" class="btn btn-primary">
                    <spring:message code="form_delegato.cerca_esistente"/>
                </button>
                <button type="button" onClick="javascript:clean(event);" class="btn btn-warning">
                    <spring:message code="form_delegato.annulla"/>
                </button>
            </div>

        </div>


    </div>

    <div class="modal fade" id="cercaDelegatoDialog" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 id="cercaDelegatoDialogHeader"></h4>
                </div>
                <div id="cercaDelegatoDialogBody" class="modal-body"></div>
                <div class="modal-footer">
                    <button id="cercaDelegatoDialogButton" type="button" class="btn btn-default" >
                        <spring:message code="continue"/>
                    </button>
                    <button type="button" class="btn btn-warning" data-dismiss="modal">
                        <spring:message code="undo"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

</form:form>

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
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js"></script>

<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/wizard.js"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>

<script>
var validator;

    (function () {

        $(function () {
            /* Document Ready */
            validator = $("#formDelegato").validate(
                {
                	errorPlacement: function(error, element) {
				        if(element.parent('.input-group').length) {
				            error.insertAfter(element.parent());
				        } else {
				            error.insertAfter(element);
				        }
				    },
                    rules: {
                        nome: 'required',
                        cognome: 'required',
                        codiceFiscale: {
                            required: true,
                            minlength: 16,
                            maxlength: 16
                        },
                        email: {
                            required: true,
                            email: true
                        }
                    },
                    messages: {
                        nome: '<spring:message code="form_delegato.nome_required" javaScriptEscape="true"/>',
                        cognome: '<spring:message code="form_delegato.cognome_required" javaScriptEscape="true"/>',
                        codiceFiscale: {
                            required: '<spring:message code="form_delegato.codice_fiscale_required" javaScriptEscape="true"/>',
                            minlength: '<spring:message code="form_delegato.codice_fiscale_length" javaScriptEscape="true"/>',
                            maxlength: '<spring:message code="form_delegato.codice_fiscale_length" javaScriptEscape="true"/>'
                        } ,
                        email: {
                            required: '<spring:message code="form_delegato.email_required" javaScriptEscape="true"/>',
                            email: '<spring:message code="form_delegato.email_format" javaScriptEscape="true"/>'
                        }
                    }
                }
            );

            $('#cercaEsistenteButton').click(cercaEsitente);

        });

        /*
         * Tasto ricerca
         * */
        function cercaEsitente() {
            var form = $('#formDelegato').serializeArray();
            var data = {};
            form.forEach(function (input) {
                if (!isBlank(input.value)) {
                    data[input.name] = input.value;
                }
            });
            initDialog();
            $.ajax({
                url: '/vimp/secure/cercaDelegato',
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                statusCode: {
                    200: dialogNuovaCorrispondenza,
                    204: dialogCorrispondenzaMancante,
                    417: dialogMultiCorrispondenza,
                    418: dialogAssociato
                }
            });
        }

        /* Dialog */

        function initDialog() {
            $('#cercaDelegatoDialogHeader').text('<spring:message code="in_progress"/>');
            $('#cercaDelegatoDialogBody').html('<img src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif"/>');
            $('#cercaDelegatoDialogButton').attr('disabled', true);
            $('#cercaDelegatoDialog').modal();
        }

        function dialogNuovaCorrispondenza(response) {
            var delegato = $('<table></table>').addClass('table table-striped');
            addRow(delegato,'<spring:message code="form_delegato.cognome" javaScriptEscape="true"/>',response.cognome);
            addRow(delegato,'<spring:message code="form_delegato.nome" javaScriptEscape="true"/>',response.nome);
            addRow(delegato,'<spring:message code="form_delegato.codice_fiscale" javaScriptEscape="true"/>',response.codiceFiscale);
            addRow(delegato,'<spring:message code="form_delegato.email" javaScriptEscape="true"/>',response.email);

            $('#cercaDelegatoDialogHeader').text('<spring:message code="form_delegato.nuova_associazione"/>');
            $('#cercaDelegatoDialogBody').html('<spring:message code="form_delegato.nuova_associazione_text"/>').append(delegato);
            $('#cercaDelegatoDialogButton').attr('disabled', false);
            $('#cercaDelegatoDialogButton').click(function () {
                associaDelegato(response.idUtente);
            });
        }

        function dialogCorrispondenzaMancante() {
            $('#cercaDelegatoDialogHeader').text('<spring:message code="warning"/>');
            $('#cercaDelegatoDialogBody').text('<spring:message code="form_delegato.nessuna_corrisondenza" javaScriptEscape="true"/>');
        }

        function dialogMultiCorrispondenza() {
            $('#cercaDelegatoDialogHeader').text('<spring:message code="warning"/>');
            $('#cercaDelegatoDialogBody').text('<spring:message code="form_delegato.multi_corrisondenza" javaScriptEscape="true"/>');

        }

        function dialogAssociato() {
            $('#cercaDelegatoDialogHeader').text('<spring:message code="warning"/>');
            $('#cercaDelegatoDialogBody').text('<spring:message code="form_delegato.delegato_associato" javaScriptEscape="true"/>');
        }

        /* REST */

        function associaDelegato(idUtente) {
            $('#cercaDelegatoDialogBody').prepend('<img src="${evn_urlRisorseStatiche}/vimp/assets/img/loading.gif"/>');
            var idPlfImpresa = $('#idPlfImpresa').val();
            $.ajax({
                url: '/vimp/secure/associaDelegato/'+idUtente+'/'+idPlfImpresa,
                type: 'GET',
                success: function () {
                    location.assign('/vimp/secure/gestioneDelegati?idPlfImpresa='+idPlfImpresa)
                },
                error: function () {
                    $('#cercaDelegatoDialogButton').attr('disabled', true);
                    $('#cercaDelegatoDialogBody').text('<spring:message code="form_delegato.associazione_errore" javaScriptEscape="true"/>');
                }
            })
        }

        /* Utilità */

        function addRow(tableNode,key,val){
            var row = $('<tr></tr>');
            row.append($('<th></th>').text(key));
            row.append($('<td></td>').text(val));
            tableNode.append(row);
        }

        function isBlank(s) {
            return !s || s.trim() === '';
        }


        

    }());

    function clean(event)
    {
    	event.preventDefault();
    	validator.resetForm();
    	$("#formDelegato").trigger("reset");
    	validator.resetForm();
    	
    }

</script>
