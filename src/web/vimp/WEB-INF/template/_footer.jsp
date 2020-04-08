<!-- =========================================================================================== -->
<!-- BEGIN FOOTER -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="footer-area">
	<div class=" footer">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-6 wow fadeInRight animated">
					<div class="single-footer">
						<h4><spring:message code="footer.who_are_us" text="Chi siamo"/></h4>
						<div class="footer-title-line"></div>
						<div>
							<img height="50" src="${evn_urlRisorseStatiche}/vimp/assets/img/GE-Logo-ComuneGenova.png" class="footer-img"
								alt="" class="wow pulse" data-wow-delay="1s" >
						</div>
						<ul class="footer-adress">
							<li class="address-title"><spring:message code="footer.genova_city" text="Comune di Genova"/></li>
							<li class="address-content">Comune di Genova - Palazzo Tursi</li>
							<li class="address-content">Via Garibaldi 9 - 16124 GENOVA</li>
							<li class="address-content">C.F / P. Iva 00856930102</li>
						</ul>
						<div>
							<img src="${evn_urlRisorseStatiche}/vimp/assets/img/ponmetro.png"
								alt="" class="wow pulse" data-wow-delay="1s" width ="380" >
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 wow fadeInRight animated">
					<div class="single-footer">
						<h4>Links</h4>
						<div class="footer-title-line"></div>
						<ul class="footer-menu">
							<li><a href="/vimp/contact"><spring:message code="footer.contact_us" text="Contattaci"/></a></li>
							<li><a href="/vimp/termini"><spring:message code="footer.terms_of_service" text="Termini"/></a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-4 col-sm-6 wow fadeInRight animated">
					<div class="single-footer news-letter">
						<h4>Social</h4>
						<div class="footer-title-line"></div>
						
						<!-- 
						<p>Puoi scegliere di ricevere gli aggiornamenti con una
							semplice iscrizione gratuita alla nostra newletter.</p>
						<form>
							<div class="input-group">
								<input class="form-control" type="text"
									placeholder="E-mail ... "> <span
									class="input-group-btn">
									<button class="btn btn-primary subscribe" type="button">
										<i class="pe-7s-paper-plane pe-2x"></i>
									</button>
								</span>
							</div>
						</form>
						-->
						<div class="social">
							<ul>
								<li><a class="wow fadeInUp animated social-icons"
									href="https://twitter.com/comunedigenova"><i
										class="fa fa-twitter"></i></a></li>
								<li><a class="wow fadeInUp animated social-icons"
									href="https://www.facebook.com/Comune.di.Genova/"
									data-wow-delay="0.2s"><i class="fa fa-facebook "></i></a></li>
								<li><a class="wow fadeInUp animated social-icons"
									href="https://www.instagram.com/genovamorethanthis/?hl=it"
									data-wow-delay="0.4s"><i class="fa fa-instagram"></i></a></li>

								<li><a class="wow fadeInUp animated social-icons"
									href="https://it.linkedin.com/company/comune-di-genova"
									data-wow-delay="0.5s"><i class="fa fa-linkedin"></i></a></li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-copy text-center">
		<div class="container">
			<div class="row">
				<div class="pull-left">
					<span>V. <b>1.0.63</b> 2020-03-27 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(C) <a href="http://www.pro-logic.it">Interlogic
							S.r.l.</a> , All rights reserved 2020
					</span>
				</div>
				<div class="bottom-menu pull-right">
					<ul>
						<li><a class="wow fadeInUp animated" href="http://www.pro-logic.it/"
							data-wow-delay="0.2s">Home</a></li>
						<li><a class="wow fadeInUp animated" href="http://www.pro-logic.it/#contatti"
							data-wow-delay="0.3s">Contact</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<script src="${evn_urlRisorseStatiche}/vimp/assets/js/cookieconsent.min.js"></script>
<script>

$(document).ready(function() {
	
	window.cookieconsent.initialise({
		palette: {
			popup: {
				background: '#000'
			},
			button: {
				background: '#f1d600'
			}
		},
		cookie: {
			path: '/vimp'
		},
		content: {
			message: "<spring:message code='cookie_message'/>",
			dismiss: '<spring:message code="cookie_allow"/>',
			link: '<spring:message code="cookie_link"/>',
			href: '/vimp/cookies'
		},
		blacklistPage: [/.*\/cookies\/?.*/]
	});
	
})
</script>


<!-- END FOOTER -->
<!-- =========================================================================================== -->