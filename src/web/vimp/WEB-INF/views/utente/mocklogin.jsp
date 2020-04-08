<!-- =========================================================================================== -->
<!-- =========================================================================================== -->
<!-- BEGIN PAGINA -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="/WEB-INF/widget.tld" prefix="widget"%>

<spring:message var="surnameTitleAttr" code="surname" text="Cognome"/>
<spring:message var="nameTitleAttr" code="name" text="Nome"/>
<spring:message var="sendEmailTitleAttr" code="utente.listaview.sendEmailTitleAttr" text="Invia e-mail"/>
<spring:message var="roleTitleAttr" code="common_texts.role" text="Ruolo"/>

<div class="row">
&nbsp;
</div>

<div class="alert alert-danger fade in">
	<h4>&nbsp;</h4>
	<h3>MOCK LOGIN</h3>
	<h4>&nbsp;</h4>



	<div class="row">
		<div class="proerty-th">

			<div class="col-sm-12">
				<div class="col-sm-12">
					<div class="table-responsive">
						<table id="tabella"
							class="table table-condensed bordered1 tablesorter">
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

										<td><a href="/vimp/mocklogin/${riga.idUtente}"> <span
												class="glyphicon glyphicon-user"></span>
										</a></td>

										<td><a href="/vimp/mocklogin/${riga.idUtente}"> <span>${riga.cognome}</span>
										</a></td>

										<td><a href="/vimp/mocklogin/${riga.idUtente}"> <span>${riga.nome}</span>
										</a></td>

										<td><a href="mailto:${riga.email}"> <span>${riga.email}</span>
										</a></td>

										<td><a href="/vimp/mocklogin/${riga.idUtente}"> <span>${riga.ruolo.descRuolo}</span>
										</a></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>



</div>
<!-- END PAGINA -->
<!-- =========================================================================================== -->
