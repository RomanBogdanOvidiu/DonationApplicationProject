<%@ page import="com.users.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<body>
	<form:form method="post"
		action="${pageContext.request.contextPath}/signUp"
		modelAttribute="user" cssClass="form-horizontal">

		<label class="control-label" for="username"><strong>username</strong></label>
		<div class="controls">
			<form:input path="username" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="firstName"><strong>FirstName</strong></label>
		<div class="controls">
			<form:input path="firstName" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="lastName"><strong>LastName</strong></label>
		<div class="controls">
			<form:input path="lastName" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="password"><strong>password</strong></label>
		<div class="controls">
			<form:input path="password" type="password" cssClass="span3"
				cssErrorClass="error" />
		</div>


		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/signUp"></a>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />



	</form:form>


</body>
</html>