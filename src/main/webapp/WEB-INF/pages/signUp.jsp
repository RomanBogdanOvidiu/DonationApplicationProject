<%@ page import="com.users.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>


<body>
	<div align="center">

		<form:form method="post"
			action="${pageContext.request.contextPath}/signUp"
			modelAttribute="user" cssClass="form-horizontal">

			<label class="control-label" for="username"><strong>Username</strong></label>
			<div class="controls">
				<form:input path="username" cssClass="span3" cssErrorClass="error" />

			</div>

			<label class="control-label" for="password"><strong>password</strong></label>
			<div class="controls">
				<form:input path="password" type="password" cssClass="span3"
					cssErrorClass="error" />
			</div>

			<label class="control-label" for="firstName"><strong>FirstName</strong></label>
			<div class="controls">
				<form:input path="firstName" cssClass="span3" cssErrorClass="error" />

			</div>
			<label class="control-label" for="lastName"><strong>LastName</strong></label>
			<div class="controls">
				<form:input path="lastName" cssClass="span3" cssErrorClass="error" />

			</div>

			<label class="control-label" for="contactInfo"><strong>Contact
					Information</strong></label>
			<div class="controls">
				<form:input path="contactInfo" cssClass="span3"
					cssErrorClass="error" />

			</div>

			<label class="control-label" for="email"><strong>Email</strong></label>
			<div class="controls">
				<form:input path="email" cssClass="span3" cssErrorClass="error" />

			</div>

			<input type="submit" class="btn" value="Submit">&nbsp; <a
				href="${pageContext.request.contextPath}/signUp"></a>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />


		</form:form>


	</div>
	<a href='${pageContext.request.contextPath}/' class="btn btn-info">Back</a>
</body>
</html>