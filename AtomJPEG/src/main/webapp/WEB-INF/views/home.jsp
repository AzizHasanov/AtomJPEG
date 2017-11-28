<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Welcome to AtomJPEG!</h1>
	<h2>(only .jpeg for now)</h2>
	<P>The time on the server is ${serverTime}.</P>
	<a href="http://localhost:8080/atomjpeg/main">Go to main </a>
	<br />
	<img src="<c:url value ="/images/org/crawled_0.jpg" />" >
	<br />
	<img height="600" weight="700"
		src="${pageContext.request.contextPath}/resources/images/org/Love.png" />
		
</body>
</html>
