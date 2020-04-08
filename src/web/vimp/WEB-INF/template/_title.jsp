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
<!-- =========================================================================================== -->