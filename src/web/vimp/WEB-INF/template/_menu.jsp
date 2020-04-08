<!-- =========================================================================================== -->
<!-- BEGIN MENU -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div id="preloader">
	<div id="status">&nbsp;</div>
</div>
<!-- Body content -->
<div class="header-connect">

	<div class="container header-container">
		<div class="navbar-header col-md-11">
			<div class="header-body">
			
				<a href="/vimp/home">
					<span class="header-title">VETRINA</span>
					<br>
					<span class="header-title">IMPRESE</span>
				</a>
				
				<div class="menu-card" hidden="true">
					<span><small><b>idee nuove per</b></small></span>
					<span>imprese</span>
					<span>INNOVATIVE</span>
					<a href="/vimp/home"><span>Scopri di pi&ugrave;</span></a>
				</div>
			</div>
		</div>
		<div class="dropdown ddown-menu-style col-md-1">
			<button class="btn btn-secondary dropdown-toggle change-lang-btn" type="button" id="dropdownMenuButton"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="setOriginalUrl('${refreshRelativeUrl}')">
	    		<img alt="" src="/vimp/local_resources/vimp/assets/img/flags/${env_locale}.png"/>
	  		</button>
			<div class="dropdown-menu dropdown-style" aria-labelledby="dropdownMenuButton">
				<input id="selectedLang" value="${env_locale}" hidden="true"/>
				<c:forEach items="${env_locales_opt}" var="opt">
					<div class="dropdown-item flag-btn" data-toggle="modal" onclick="setLang('${opt.key}')" >
						<img alt="" src="${opt.value}"/>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
		
</div>
<!--End top header -->
<div id="nav" class="navbar navbar-default " data-spy="affix"
	data-offset-top="0.07">

	<div class="container header-container">
		<div class="navbar-header">

			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navigation">
				<span class="sr-only">Menu</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="/vimp/home">
				<span class="navbar-title"><spring:message code="footer.genova_city" text="Comune di Genova"/></span>
			</a>
		</div>

		<div class="collapse navbar-collapse yamm" id="navigation">
			<div class="button navbar-right">
				<c:choose>
					<c:when test="${!utente.isVisitor()}">

						<ul class="main-nav nav navbar-nav navbar-right nav-user-icon" align="center"
							style="margin-top: -25px;">
							<li class="dropdown ymm-sw " data-wow-delay="0.1s"><a
								href="#" class="dropdown-toggle user-account-toggle" data-toggle="dropdown"
								data-hover="dropdown" data-delay="200"> <span
									class="glyphicon glyphicon-user" style="font-size: 30px;"></span>
									<br /> <small class="small-reducer">${utente.getAlias()}</small>
							</a>
								<ul class="dropdown-menu navbar-nav" style="text-align: center">
									<c:if
										test="${utente.isAdministrator() || utente.isBackoffice()}">
										<li><a href="/vimp/secure/accreditamento"><spring:message code="menu.accreditation" text="Accreditamento"/></a></li>
									</c:if>
									<c:if test="${utente.isVisitorLogin() || utente.isImpresa()}">
										<li><a href="/vimp/secure/richiestaAccreditamento"><spring:message code="menu.accreditate" text="Accreditati"/></a></li>
									</c:if>
									<c:if test="${utente.isImpresa() || utente.isStakeholder()}">
										<li><a href="/vimp/secure/homePersonal"><spring:message code="menu.my_info" text="Le mie info"/></a></li>
									</c:if>
									<c:if
										test="${utente.isAdministrator() || utente.isBackoffice()}">
										<li><a href="/vimp/secure/ricercaImpresa"><spring:message code="menu.extended_search" text="Ricerca estesa"/></a></li>
									</c:if>
									<c:if
										test="${utente.isAdministrator() || utente.isBackoffice()}">
										<li><a href="/vimp/secure/batchlog"><spring:message code="menu.alignment_log" text="Log allineamento"/></a></li>
									</c:if>
									<c:if
										test="${utente.isAdministrator() || utente.isBackoffice() || utente.isImpresa() || utente.isStakeholder()}">
										<li><a href="/vimp/secure/translateCodifiche"><spring:message code="menu.encoding_translations" text="Traduzione codifiche"/></a></li>
									</c:if>
									<!-- 
									<c:if
										test="${utente.isAdministrator() || utente.isBackoffice()}">
										<li><a href="/vimp/secure/gestioneRuoli"><spring:message code="menu.management_roles" text="Gestione ruoli"/></a></li>
									</c:if>
									 -->
									 
									<c:if test="${utente.isImpresa() or utente.isStakeholder()}">
										<li><a href="/vimp/secure/gestioneDelegati"><spring:message code="menu.management_delegates"/></a></li>
									</c:if>
									<li><a href="/vimp/logout"> <span
											class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;<spring:message code="exit" text="Esci"/>
									</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<form:form id="frmLogin" method="POST" action="/vimp/login"
							role="form">
							<input type="hidden" name="currentCall" id="currentCall"
								value="${currentCall}">
							<button class="nav-button wow bounceInRight login icon-user-menu"
									data-wow-delay="0.45s"><span class="glyphicon glyphicon-user" style="color: white;"></span></button>
						</form:form>
					</c:otherwise>
				</c:choose>
			</div>



			<ul class="main-nav nav navbar-nav navbar-float-rigth">
				<li class="wow fadeInDown" data-wow-delay="0.2s">
					<c:choose>
						<c:when test="${parametriRicerca.tipoInformazione == 1}">
							<a class="active " align="center" href="/vimp/home/1"><spring:message code="menu.enterprises" text="Imprese"/></a>
						</c:when>
						<c:otherwise>
							<a class="" align="center" href="/vimp/home/1"><spring:message code="menu.enterprises" text="Imprese"/></a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="web-only">|</li>
				<li class="wow fadeInDown" data-wow-delay="0.3s"><c:choose>
						<c:when test="${parametriRicerca.tipoInformazione == 2}">
							<a class="active " align="center" href="/vimp/home/2"><spring:message code="menu.stakeholder" text="Stakeholder"/></a>
						</c:when>
						<c:otherwise>
							<a class="" align="center" href="/vimp/home/2"><spring:message code="menu.stakeholder" text="Stakeholder"/></a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="web-only">|</li>
				<li class="wow fadeInDown" data-wow-delay="0.4s"><c:choose>
						<c:when test="${parametriRicerca.tipoInformazione == 3}">
							<a class="active " align="center" href="/vimp/home/3"><spring:message code="menu.services"/></a>
						</c:when>
						<c:otherwise>
							<a class="" align="center" href="/vimp/home/3"><spring:message code="menu.services"/></a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="web-only">|</li>
				<li class="wow fadeInDown" data-wow-delay="0.6s" title="<spring:message code="menu.projects_products_technologies_title"/>"><c:choose>
						<c:when test="${parametriRicerca.tipoInformazione == 4}">
							<a class="active " align="center" href="/vimp/home/4"><spring:message code="menu.projects_products_technologies"/></a> <!-- Progetti&nbsp;Prodotti&nbsp;Tecnologie -->
						</c:when>
						<c:otherwise>
							<a class="" align="center" href="/vimp/home/4"><spring:message code="menu.projects_products_technologies"/></a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="web-only">|</li>
				<li class="wow fadeInDown" data-wow-delay="0.7s"><c:choose>
						<c:when test="${parametriRicerca.tipoInformazione == 5}">
							<a class="active " align="center" href="/vimp/home/5"><spring:message code="menu.opportunities"/></a>
						</c:when>
						<c:otherwise>
							<a class="" align="center" href="/vimp/home/5"><spring:message code="menu.opportunities"/></a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="web-only">|</li>
				<li class="wow fadeInDown" data-wow-delay="0.8s"><c:choose>
						<c:when test="${parametriRicerca.tipoInformazione == 6}">
							<a class="active " align="center" href="/vimp/home/6"><spring:message code="menu.news" text="News"/></a>
						</c:when>
						<c:otherwise>
							<a class="" align="center" href="/vimp/home/6"><spring:message code="menu.news" text="News"/></a>
						</c:otherwise>
					</c:choose>
				</li>

				<c:if
					test="${parametriRicerca.tipoInformazione > 0 && parametriRicerca.numeroInformazione < 0 && utente.isWriter(parametriRicerca.tipoInformazione)}">
					<li class="wow fadeInDown" data-wow-delay="0.9s">
						<button class="navbar-btn nav-button login " style="margin: 0 5px;"
							onclick="location.href='/vimp/secure/newElement/${parametriRicerca.tipoInformazione}';"
							data-wow-delay="0.8s">
							<c:choose>
								<c:when test="${parametriRicerca.tipoInformazione eq 3}">
									<spring:message code="menu.new_service"/>
								</c:when>
								<c:otherwise>
									<spring:message code="menu.new"/>
								</c:otherwise>
							</c:choose>
						</button>
					</li>
				</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</div>
<!-- End of nav bar -->

<div class="modal fade" id="changeLangConfirm" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4><spring:message code="warning"/></h4>
			</div>
			<div class="modal-body">
				<spring:message code="messaggioConfermaReload"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="undo"/></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onClick="javascript:refreshWithLocale();"><spring:message code="continue"/></button>			
			</div>
		</div>
	</div>
</div>


<!-- END MENU -->
<script src="${evn_urlRisorseStatiche}/vimp/assets/js/jquery-1.10.2.min.js"></script>
<script>

var originalUrl;

function setOriginalUrl(relativeUrl) {
	if(relativeUrl) {
		var baseUrl = window.location.href.split('/vimp/')[0];
		originalUrl = baseUrl + '/vimp/' + relativeUrl;
	} else {
		originalUrl = undefined;
	}
}

function setLang(locale) {


	if (locale)
	{
		if (locale == 'IT' || locale == 'it')
			locale = 'it_IT';
	}

	var ok = true;
	$('#selectedLang').val(locale);

	if($('.safe-reload').length >0){
		$('#changeLangConfirm').modal();
		ok = false;
	}
	
	if(!ok){
		return;
	} else {
		 refreshWithLocale();
	}	
}


function refreshWithLocale() {
	var locale = $('#selectedLang').val();

	if (locale)
	{
		if (locale == 'IT' || locale == 'it')
			locale = 'it_IT';
	}
	
	var url = !originalUrl ? window.location.href : originalUrl;

	if(url.indexOf('lang=') === -1) {
		
		if(url.indexOf('?') === -1) {
			url += '?lang=' + locale;
		} else {
			url += '&lang=' + locale;
		}
		
	} else {
		var splitted = url.split('lang=');
		url = splitted[0] + 'lang=' + locale;
	}

	window.location.replace(url);
};

$(document).ready(function() {
	$('#locale_select').val('${env_locale}');
});


</script>

<!-- =========================================================================================== -->