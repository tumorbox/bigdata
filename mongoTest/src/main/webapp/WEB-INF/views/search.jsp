<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,spring.data.mongodb.dto.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/mongodb/score/search">
		<h1>�˻��ϱ�</h1>
		�˻��� �ʵ弱��: <select name="field">
			<option value="name">����</option>
			<option value="id">���̵�</option>
			<option value="addr">�ּ�</option>
			<option value="dept">�μ�</option>
			<option value="java">java</option>
			<option value="spring">spring</option>
			<option value="servlet">servlet</option>
			<option value="bonus">bonus</option>

		</select>
		<h3>�˻���:</h3>
		<input type="text" name="value" />
		<input type="submit" value="�˻�"/>
	</form>
</body>
</html>










