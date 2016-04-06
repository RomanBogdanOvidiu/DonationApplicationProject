<%@ page import="com.users.model.User"%>
<%@ page import="com.users.model.Client"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>

	<form:form method="post"
		action="${pageContext.request.contextPath}/client"
		modelAttribute="client" cssClass="form-horizontal">

		<label class="control-label" for="FirstName"><strong>FirstName</strong></label>
		<div class="controls">
			<form:input path="firstName" cssClass="span3" cssErrorClass="error">$(client.firstName) </form:input>
		</div>

		<label class="control-label" for="LastName"><strong>LastName</strong></label>
		<div class="controls">
			<form:input path="lastName" cssClass="span3" cssErrorClass="error">$(client.lastName)</form:input>

		</div>

		<label class="control-label" for="CNP"><strong>CNP</strong></label>
		<div class="controls">
			<form:input path="cnp" cssClass="span3" cssErrorClass="error" />
		</div>

		<label class="control-label" for="Address"><strong>Address</strong></label>
		<div class="controls">
			<form:input path="address" cssClass="span3" cssErrorClass="error" />
		</div>
		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/client"></a>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

	</form:form>




</body>
</html>