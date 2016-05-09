<!DOCTYPE html>
<html>
<head>




<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href='${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css'>
<script type="text/javascript"
	src='${pageContext.request.contextPath}/js/jquery-1.7.1.min.js'></script>
<title>${param.title}</title>
<style type="text/css">
html, body {
	background-color: #eee;
}

.container>footer p {
	text-align: center; /* center align it with the container */
}

.container {
	width: 820px;
}

/* The white background content wrapper */
.content {
	background-color: #fff;
	padding: 20px;
	margin: 0 -20px;
	-webkit-border-radius: 0 0 6px 6px;
	-moz-border-radius: 0 0 6px 6px;
	border-radius: 0 0 6px 6px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
}
/* Page header tweaks */
.page-header {
	background-color: #f5f5f5;
	padding: 20px 20px 10px;
	margin: -20px -20px 20px;
}

.row {
	padding: 20px;
}
</style>
</head>




<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>


	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href='${pageContext.request.contextPath}/'>Hospital Application</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a
							href='${pageContext.request.contextPath}/'>Home</a></li>
						<li><a href='${pageContext.request.contextPath}/'>Poate ceva</a></li>
						<li><a href='${pageContext.request.contextPath}/'>Poate altceva</a></li>
						<li><a href='${pageContext.request.contextPath}/logout'>Log
								out</a></li>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="./">Default <span
								class="sr-only">(current)</span></a></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>



		<div class="content">
			<div class="page-header">
				
			</div>
			<div class="row">${param.body}</div>
		</div>

	</div>
</body>
</html>