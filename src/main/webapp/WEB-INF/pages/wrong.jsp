<%@ page import="com.users.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>
<body>
	Wrong input! Please complete all the fields with the desired data
	properly. Make sure you did not forget to complete any of the fields!

	<a href="${pageContext.request.contextPath}/">Back to main page</a>

</body>
</html>