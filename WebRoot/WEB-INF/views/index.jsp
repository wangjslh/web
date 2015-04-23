<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>index</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="static/js/jquery1.11.1.js"></script>
<script>
	function operateRecord(){
		var data = $("#form").serialize();
		$.ajax({
			type:"post", 
			url:"user/insertUser",
			data:data,
			dataType:"json", 
			success:function (data) {
				if(data.status == 1){
					alert("操作成功");
				}else{
					alert("操作失败");
				}
			}
		});
	}
	
	function getRecords(){
		var data = $("#form").serialize();
		$.ajax({
			type:"get", 
			url:"user/listXmLObjs",
			data:data,
			dataType:"xml", 
			success:function (data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
<form id="form">
	用户名：<input type="text" name="account" />
	<input type="hidden" name="password" value="111111" />
	<input type="hidden" name="gender" value="1" />
	<input type="hidden" name="birthday" value="2015-01-01" />
	<input type="button" value="插入" onclick="operateRecord()" />
	<input type="button" value="获取" onclick="getRecords()" />
</form>
</html>
