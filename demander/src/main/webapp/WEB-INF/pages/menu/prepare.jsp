<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<jsp:include page="../common/script.jsp"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<script type="text/javascript">
		var FZ = function(a, b) {
			function getFZ() {
				var width = document.documentElement.clientWidth || document.body.clientWidth;
				var fontSize = (a / b) * width;
				return fontSize;
			};
			document.documentElement.style.fontSize = getFZ() + "px";
			document.documentElement.style.minHeight = getFZ() * 18 * 480 / 320 + "px";
			window.onresize = function() {
				setTimeout(function() {
					document.documentElement.style.fontSize = getFZ() + "px";
					document.documentElement.style.minHeight = getFZ() * 18 * 480 / 320 + "px";
				}, 100);
			}
		};
		FZ(20, 360);
	</script>
	<script type="text/javascript">
	$(function(){
		$("#start").click(function(){
			//if(confirm("确定开始吗？")){
				$.post("${url}/menu/start?${openid}");
				WeixinJSBridge.call('closeWindow');
			//}
		});
	});
	</script>
</head>
<title>心理聊天</title>
</head>
<body>
<p></p>
<p>每次咨询50分钟，开始之前请确保你有足够时间</p>
<div>如果你准备好了，请点击匹配</div>
<input type="button" value="开始匹配" id="start">
</body>
</html>