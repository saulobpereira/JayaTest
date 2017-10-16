<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Jaya Test</title>
		
	</head>
	<body>
	
		<div class="row">
			<div class="col-md-3">
				<div class="panel panel-default">
				  <div class="panel-body">
				    Your list is empty
				  </div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>Friends Checkins</h2>
			</div>
		</div>
		<div class="row">
			<g:each var="friend" in="${friends}">
				<div class="col-md-3">
					<div class="panel panel-default">
					  <div class="panel-body">
					  <div>
					  		<img src="${friend?.photoURL }" alt="${friend?.name }" class="img-circle">
						    ${friend.recentVenue.name }
					  		<button type="button" class="btn btn-primary btn-md pull-right" title="Add to Wish List"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
					  	</div>
					  	<div>
					  		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
							<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox">
									<g:each var="i" in="${ (0..<friend.recentVenue.photos.size()) }">
										<div class="item ${i!=0?:'active'}">
											<img src="${friend.recentVenue.photos[i] }" width="100%" alt="...">
											<div class="carousel-caption"></div>
										</div>

									</g:each>
								</div>
								<!-- Controls -->
							  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
							    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							    <span class="sr-only">Previous</span>
							  </a>
							  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
							    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							    <span class="sr-only">Next</span>
							  </a>
							</div>
					  	</div>
					  	
					  </div>
					</div>
				</div>
			</g:each>
		
		
		</div>
		
	</body>
</html>
