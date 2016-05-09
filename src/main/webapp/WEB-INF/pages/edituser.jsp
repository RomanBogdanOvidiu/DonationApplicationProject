<%@ page import="com.users.model.Consultation"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> </c:import>


<html>
<body>
	<form:form method="post"
		action="${pageContext.request.contextPath}/admin/edituser"
		modelAttribute="user" cssClass="form-horizontal">


		<label class="control-label" for="firstName"><strong>First Name</strong></label>
		<div class="controls">
			<form:input path="firstName" cssClass="span3" cssErrorClass="error" />
			<label class="control-label" for="lastName"><strong>Last Name</strong></label>
		<div class="controls">
			<form:input path="lastName" cssClass="span3" cssErrorClass="error" />
		
		</div>

		

		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/admin/edituser"></a>
			
			
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

	
		<form:hidden path="username" />
		<form:hidden path="password" />

	</form:form>
	
</body>
</html>