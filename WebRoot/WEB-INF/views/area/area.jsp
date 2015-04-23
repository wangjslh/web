<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type = request.getParameter("type");
	request.setAttribute("type",type); 
%>
<script type="text/javascript" src="static/js/jquery1.11.1.js"></script>
<script>
	var PIECELENGTH = 2;
	function setOptions(flag, objs){
		if('city' == flag){
			$('#city').children(':gt(0)').remove();
			$('#area').children(':gt(0)').remove();
			var provinceCode = $('#province').prop('value');
			$.each(objs, function(k, v){
				if(PIECELENGTH * 2 == v.code.length && provinceCode == v.code.substr(0, PIECELENGTH)){
					$('#city').append('<option value="' + v.code + '">' + v.name + '</option>');
				}
			});
		}else if('area' == flag){
			$('#area').children(':gt(0)').remove();
			var cityCode = $('#city').prop('value');
			$.each(objs, function(k, v){
				if(PIECELENGTH * 3 == v.code.length && cityCode == v.code.substr(0, PIECELENGTH * 2)){
					$('#area').append('<option value="' + v.code + '">' + v.name + '</option>');
				}
			});
		}
	}
	$(function(){
		//省份改变事件
		<c:if test="${!(type < 2)}">
		$('#province').change(function(){
			var path = $(this).prop('value')
			if($('#province').data(path) == undefined){
				$.ajax({
					type:"post",
					url:"area/listDescendants",
					data:{"parentPath":path},
					success:function(data){
						if(1 == data.status){
							$('#province').data(path, data.data);
							setOptions('city', $('#province').data(path));
						}
					}
				});
			}else{
				setOptions('city', $('#province').data(path));
			}
		});
		</c:if>
		
		//市改变事件
		<c:if test="${!(type < 3)}">
		$('#city').change(function(){
			var path = $('#province').prop('value');
			setOptions('area', $('#province').data(path));
		});
		</c:if>
	})
</script>
<div>
	<span>
	省：
	<select id="province">
		<option value="">请选择</option>
	</select>
	<script>
		$.ajax({
			type:"post",
			url:"area/listProvinces",
			success:function(data){
				if(1 == data.status){
					$.each(data.data, function(k, v){
						$('#province').append('<option value="' + v.code + '">' + v.name + '</option>');
					});
				}
			}
		});
	</script>
	</span>
	<span style="<c:if test='${type < 2}'>display:none</c:if>">
	市：
	<select id="city">
		<option value="">请选择</option>
	</select>
	</span>
	<span style="<c:if test='${type < 3}'>display:none</c:if>">
	区：
	<select id="area">
		<option value="">请选择</option>
	</select>
	</span>
</div>
