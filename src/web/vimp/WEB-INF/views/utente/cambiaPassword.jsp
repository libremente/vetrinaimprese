<!-- =========================================================================================== -->
<!-- BEGIN CAMBIA PASSWORD -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



 		<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title"><spring:message code="goodmorning" text="Buongiorno"/> : <span class="orange strong">${utente.nome} ${utente.cognome}</span></h1>               
                    </div>
                </div>
            </div>
        </div>
        <!-- End page header -->


        <!-- property area -->

		
        <div class="content-area submit-property">&nbsp;
           <div class="container">

            
                <div class="clearfix" > 
                    <div class="wizard-container"> 

                        <div class="wizard-card ct-wizard-orange" id="wizardProperty">
                        	 <form action="" method="">
                        	 <div class="wizard-header">
                                    <h3>
                                        <b><spring:message code="update.uppercase" text="AGGIORNA"/></b> <spring:message code="cambia.password.your_password" text="LA TUA PASSWORD"/> <br>
										<small><spring:message code="cambia.password.updates_are_sent_via_email" text="Gli aggiornamenti sono inviati via e-mail"/></small>
									</h3>
									<hr>
                            </div>

                            <div class="clear">

                                <div class="col-sm-10 col-sm-offset-1">
                                    <div class="form-group">
                                        <label><spring:message code="cambia.password.old_password" text="Password precedente"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label>
										<input name="oldPassword" type="password" class="form-control" placeholder="">                          
                                    </div>
                                    <div class="form-group">
                                        <label><spring:message code="cambia.password.new_password" text="Password nuova"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label>
                                        <input name="newPassword" type="password" class="form-control" placeholder="">
                                    </div>
                                    <div class="form-group">
                                        <label><spring:message code="cambia.password.confirm_old_password" text="Conferma nuova password"/> <small>(<spring:message code="required" text="richiesto"/>)</small></label>
										<input name="confirmpassword" type="password" class="form-control" placeholder="">
                                    </div> 
                                    <hr>
	                            
                                </div>
                                
                            </div>
                            <div class="wizard-footer">
                            
                            
                            	<div class="col-sm-10 col-sm-offset-1">
                                    <div class="pull-right">
	                                        <input type='button' class='btn btn-finish btn-primary pull-right' name='updatePassword' value='Aggiorna' />
	                                </div>
	
	                                <div class="clearfix"></div>        
	                                    <hr>
                                </div>
                            </div>	
                            </form>
                            
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


<!-- END CAMBIA PASSWORD-->
<!-- =========================================================================================== -->