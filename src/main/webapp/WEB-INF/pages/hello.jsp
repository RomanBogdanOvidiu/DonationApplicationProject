<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>



	<sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_ADMIN')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<a href="${pageContext.request.contextPath}/client">Create new
			Client</a>

		<a href="${pageContext.request.contextPath}/clientlist">Edit/Remove/View
			Client</a>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>

	</sec:authorize>
	
	<sec:authorize
		access="!hasRole('ROLE_USER') && !hasRole('ROLE_ADMIN') ">
		<a href="${pageContext.request.contextPath}/login">Login</a>

	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN') ">
		<a href="${pageContext.request.contextPath}/signUp">Sign Up</a>
 		<a href='${pageContext.request.contextPath}/admin'
        class="btn btn-primary">Admin page</a>
	</sec:authorize>



</body>
</html>