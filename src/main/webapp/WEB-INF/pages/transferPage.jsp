<%@ page import="com.users.model.Consultation"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>

<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>


	<form:form method="post"
		action="${pageContext.request.contextPath}/client/account/transfer"
		modelAttribute="transfer" cssClass="form-horizontal">


		<label class="control-label" for="amount"><strong>Amount you want to transfer:</strong></label>
		<div class="controls">
			<form:input path="amount" cssClass="span3" cssErrorClass="error" />
		</div>

		<label class="control-label" for="destAccountNo"><strong>Destination account:</strong></label>
		<div class="controls">
			<form:input path="destAccountNo" cssClass="span3" cssErrorClass="error" />
		</div>
		
	<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/client/account/transfer"></a>
			
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		
		<form:hidden path="clientId" />
			
		<form:hidden path="srcAccountNo" />
		
	
			
		

	</form:form>
	
</body>
</html>