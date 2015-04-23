<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<title>user</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script type="text/javascript" src="static/js/jquery1.11.1.js"></script>
<script>
	$(function(){
		//添加操作
		$('#addOrg').click(function(){
			var data = $('#addForm').serialize();
			$.ajax({
				type:"post",
				url:"org/insertOrg.json",
				data:data,
				success:function(data){
					if(1 == data.status){
						alert('操作成功');
					}else{
						alert('操作失败');
					}
				},
				error:function(){alert('操作成功');}        
			});
		});
	})
</script>
</head>
<body>
<form id="addForm">
<div>
	上级部门代码：<input type="text" name="parentPath" id="parentPath" />
	名称：<input type="text" name="name" /><br>
	描述：<input type="text" name="description" />
</div>
<input type="button" id="addOrg" value="提交">
</form>
</body>
</html>
