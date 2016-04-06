<%@ page import="com.users.model.Account"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<body>
	<form:form method="post"
		action="${pageContext.request.contextPath}/account/"
		modelAttribute="account" cssClass="form-horizontal">


		<label class="control-label" for="amount"><strong>amount</strong></label>
		<div class="controls">
			<form:input path="amount" cssClass="span3" cssErrorClass="error" />
		</div>

		<label class="control-label" for="date"><strong>date</strong></label>
		<div class="controls">
			<form:input path="date" cssClass="span3" cssErrorClass="error" />
		</div>
		


		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/account/${account.client.id}"></a>
			
			
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<form:hidden path="id" />
		

	</form:form>
	
</body>
</html>