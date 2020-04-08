<!-- =========================================================================================== -->
<!-- BEGIN PARAMETRI RICERCA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title"><spring:message code="form.contatti.contacts_title" text="Contatti"/></h1>               
                    </div>
                </div>
            </div>
        </div>
        <!-- End page header -->

        <!-- property area -->
        <div class="content-area recent-property padding-top-40" style="background-color: #FFF;">
            <div class="container">  
                <div class="row">
                    <div class="col-md-8 col-md-offset-2"> 
                        <div class="" id="contact1">                        
                            <div class="row">
                                <div class="col-sm-4">
                                    <h3><i class="fa fa-map-marker"></i> <spring:message code="form.contatti.address" text="Indirizzo"/></h3>
                                    <p>Via Garibaldi 9
                                        <br>16124 Genova 
                                        <br>
                                        <strong>Italia</strong>
                                    </p>
                                </div>
                                <!-- /.col-sm-4 -->
                                <div class="col-sm-4">
                                    <h3><i class="fa fa-phone"></i> <spring:message code="form.contatti.call_center" text="Call center"/></h3>
                                    <p class="text-muted"><spring:message code="form.contatti.call_center.text" text="Questo è un numero verde che potete chiamare in caso di necessità "/>.</p>
                                    <p><strong>+39 010 557111</strong></p>
                                </div>
                                <!-- /.col-sm-4 -->
                                <div class="col-sm-4">
                                    <h3><i class="fa fa-envelope"></i> <spring:message code="form.contatti.technical_support" text="Supporto tecnico"/></h3>
                                    <p class="text-muted"><spring:message code="form.contatti.technical_support.text" text="Potete scriverci un email per eventuale supporto"/></p>
                                    <ul>
                                        <li><strong><a href="mailto:">comunegenova@postemailcertificata.it</a></strong>   </li>
                                    </ul>
                                </div>
                                <!-- /.col-sm-4 -->
                            </div>
                            <!-- /.row -->
                            <hr>
                            <div id="map"></div>
                            <hr>
                            <h2><spring:message code="form.contatti.contacts_title" text="Contatti"/></h2>
                            <form:form id="frmDettaglio" method="POST"
								action="/vimp/mailContattaci" role="form"
								modelAttribute="dettaglio">

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="cognome"><spring:message code="surname" text="Cognome"/></label>
                                            <input type="text" class="form-control" id="cognome" path="cognome" name="cognome">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="nome"><spring:message code="name" text="Nome"/></label>
                                            <input type="text" class="form-control" id="nome" path="nome" name="nome">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="email"><spring:message code="mail" text="Email"/></label>
                                            <input type="text" class="form-control" id="email" path="email" name="email">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="oggetto"><spring:message code="subject" text="Oggetto"/></label>
                                            <input type="text" class="form-control" id="oggetto" path="oggetto" name="oggetto">
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="messaggio"><spring:message code="text" text="Testo"/></label>
                                            <textarea id="messaggio" path="messaggio" name="messaggio" class="form-control"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 text-center">
                                        <button type="submit" class="btn btn-primary"
                                        onClick="javascript:inviaMail(event);" ><i class="fa fa-envelope-o"></i> <spring:message code="send_message" text="Invia messaggio"/></button>
                                    </div>
                                </div>
                                <!-- /.row -->
                           </form:form>
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
        
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false&key=${mapsApiKey}"></script>
        
        
        
        <script src="${evn_urlRisorseStatiche}/vimp/assets/js/gmaps.js"></script>        
        <script src="${evn_urlRisorseStatiche}/vimp/assets/js/gmaps.init.js"></script>

        <script src="${evn_urlRisorseStatiche}/vimp/assets/js/main.js"></script>
        
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery.validate.min.js"></script>
	

   
<script>


	$(document).ready(
			function() {


				$('#frmDettaglio').validate({
			        rules: 
				        {
			        	cognome: "required",
			        	email: "required",
			        	oggetto: "required",
			        	messaggio: "required"
			        },
			        messages: {
			        	cognome: "<spring:message code="contattiFormDigitareNome" javaScriptEscape="true" text="Digitare il cognome"/>",
			        	email: "<spring:message code="contattiFormSpecificareMail" javaScriptEscape="true" text="Specificare un indirizzo di posta elettronica valido"/>",
			        	oggetto: "<spring:message code="contattiFormDigitareOggetto" javaScriptEscape="true" text="Digitare l'oggetto"/>",
			        	messaggio: "<spring:message code="contattiFormDigitareMessaggio" javaScriptEscape="true" text="Digitare il messaggio"/>",
			        }
			    });

			});	

	function inviaMail(event) {
		event.preventDefault();
		$("#frmDettaglio").submit();
	}
	
</script>

<!-- END PARAMETRI RICERCA -->
<!-- =========================================================================================== -->