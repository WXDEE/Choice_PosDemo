<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/student/save.do">
		<table>
			<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>教师编号</td>
			<td>年龄</td>
			<td>性别</td>
			</tr>
			<tbody>
			<td> <input type="text" name="sid"> </td>
			<td> <input type="text" name="sname"> </td>
			<td> <input type="text" name="tid"> </td>
			<td> <input type="text" name="age"> </td>
			<td> <input type="text" name="sex"> </td>
			</tbody>
			
		</table>
		<input type="submit">
	</form>
</body>
</html>