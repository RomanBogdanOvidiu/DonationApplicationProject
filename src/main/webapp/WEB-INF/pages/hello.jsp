<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"></c:import>
<html>
<body>
	<div align="center">
		<h1>Welcome !</h1>
	</div>
	
		<sec:authorize access="hasRole('ROLE_DOCTOR')">
		<div align="center">
		
				<h2>
					User : ${pageContext.request.userPrincipal.name} 
				</h2>
		
			<a href="${pageContext.request.contextPath}/clientlist"
				class="btn btn-primary">Patient List</a>
				<a href="${pageContext.request.contextPath}/notification"
				class="btn btn-primary">Notification List</a>
		</div>
		
	

	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_SECRETARY')">
		<div align="center">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>
					User : ${pageContext.request.userPrincipal.name}
				</h2>
			</c:if>
			<a href="${pageContext.request.contextPath}/clientlist"
				class="btn btn-primary">Patient List</a>
		</div>
		
	

	</sec:authorize>


	<sec:authorize access="hasRole('ROLE_ADMIN') ">
		<div align="center">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>
					User : ${pageContext.request.userPrincipal.name}
				</h2>
			</c:if>
		</div>
		<p>&nbsp;</p>
		<div align="center">
			<a href='${pageContext.request.contextPath}/admin'
				class="btn btn-primary">Admin Page</a>
		</div>
	</sec:authorize>
	
	
	


</body>
</html>