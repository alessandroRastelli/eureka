<%@ include file="layout/header.jsp"%>

<div class="container">

	<div id="search-form">

		<div id="brand">
			<a href="search.do">
			<img alt="" class="img-responsive center-block"
				src="assets/images/eureka.png"></a>
		</div>

		<form>
			<div class="row">
				<div class="col-xs-6 col-xs-offset-3">

					<div class="input-group">
						<input type="text" class="form-control input-lg"
							placeholder="Cosa vuoi cercare oggi?" id="search-input"  autocomplete="off"> 
						
						<span class="input-group-btn">
							<button class="btn btn-primary btn-lg" type="submit" id="search-button">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span> Cerca
								</button>
						</span>
					</div>
					<div id="suggested-search">
						Forse cercavi <span class="words"><a href="#">Prova</a></span>
					</div>
						<!-- /input-group -->
				</div>

				<div class="col-xs-6"></div>
			</div>
		</form>
		
			<div class="row">
				<div class="col-xs-6 col-xs-offset-3">
		
					<div id="stats"></div>
					<div id="results">
						<!-- qui ci vanno i risultati -->
					</div>
					<div id="more-like-this" class="well">
						<h4>Ricerche correlate a "<span class="query"></span>"</h4>
						<div class="row">
							<div class="col-md-6 left">
								<ul class="words"></ul>	
							</div>
							<div class="col-md-6 right">
								<ul class="words"></ul>	
							</div>
						</div>
						
						<!-- qui ci vanno le ricerche correlate -->
					</div>
				</div>
			</div>
			
			<nav class="text-center">
			  <div id="pagination">
			  	<!-- qui ci vanno i numeri di pagina -->
			  </div>
			</nav>
	</div>


</div>
<!-- /.container -->

<%@ include file="layout/footer.jsp"%>