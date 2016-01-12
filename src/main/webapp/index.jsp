<html>
<head>
<script type="text/javascript" src="./jquery-1.10.2.js"></script>
<link rel="stylesheet"
	href="//g.alicdn.com/sj/dpl/1.4.3/css/sui.min.css">
<style type="text/css">
	#left {
		float : left;
		margin-left : 100px; 
	}
	#userinfo {
		text-align : right;
		margin-right : 20px;
		margin-bottom : 20px;
		display: block;
	}

	h2 {
		text-align : center;
		margin-bottom : 20px;
	}

	#right {
		margin-right : 100px; 
		text-align : right;
	}

	#right h4 {
		margin-right : 240px;
	}
	
	.sui-table {
		width: 700px;
		float: right;
		margin-top: 50px;
	}

</style>
</head>
<body>
	<h2>Midware-Exercise!</h2>
	<div>
		<div id="userinfo">
			<h4>Hi,<span id="nick"></span></h4>
		</div>
		<div id="left">
			<div id="tair">
				<h4>Get Tair</h4>
				<div>
					key:<input type="text" id="queryKey"  class="input-thin"/>
					<button id="queryTair" class="sui-btn btn-xlarge btn-primary">Query Tair</button>
				</div>
				<HR
				style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9, strength=10)"
				width="80%" color=#987cb9 SIZE=1 />
				<div>
					<h4>Add Tair</h4>
					key:<input type="text" id="addkey"  class="input-thin"/><br/><br/>
					value:<input	type="text" id="addValue"  class="input-thin"/>
					<button id="addTair" class="sui-btn btn-xlarge btn-primary">Add Tair</button>
				</div>
				<div id=tairContent></div>
			</div>
			<HR
				style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9, strength=10)"
				width="80%" color=#987cb9 SIZE=1 />
			<div id="hsf">
				<h4>API:hsf.HSFDemoService </h4>
				<span>name:</span>
				<input type="text" id="name"  class="input-thin"/>
				<button id="callHSF" class="sui-btn btn-xlarge btn-primary">Call HSF</button><br/> 
				<div id="hsfContent"></div>
			</div>
		</div>
		<div id="right">
			<h4>TDDL</h4>
			<span>SQL:</span><input type="text" id="sql"  class="input-thin"/>
			<button id="querySql" class="sui-btn btn-xlarge btn-primary">query</button>
			<h5>ie:select id,user_id from vfs_dentry where user_id = 3615054028 limit 2</h5>
			<div >
				<table id="tddlContent" class="sui-table"></table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : "http://jbm.daily.taobao.net/loginServlet"
			}).done(function(data) {
				$("#nick").html("");
				$("#nick").html(data);
			}).fail(function() {
				$("#nick").html("");
				$("#nick").html("please login first!");
			});
		});
		
		$("#queryTair").click(function() {
			var queryKey = $("#queryKey").val();
			$.ajax({
				url : "http://jbm.daily.taobao.net/getTairServlet",
				data : {
					key : queryKey
				}
			}).done(function(data) {
				$("#tairContent").html("");
				$("#tairContent").html(data);
			}).fail(function() {
				alert("error");
			});
		});

		$("#addTair").click(function() {
			var addkey = $("#addkey").val();
			var addValue = $("#addValue").val();
			$.ajax({
				url : "http://jbm.daily.taobao.net/addTairServlet",
				data : {
					key : addkey,
					value : addValue
				}
			}).done(function(data) {
				$("#tairContent").html("");
				$("#tairContent").html(data);
			}).fail(function() {
				alert("error");
			});
		});
		
		$("#callHSF").click(function() {
			var name = $("#name").val();
			
			$.ajax({
				url : "http://jbm.daily.taobao.net/HSFConsumerDemoServlet",
				data : {name : name}
			}).done(function(data) {
				$("#hsfContent").html("");
				$("#hsfContent").html(data);
			}).fail(function() {
				alert("error");
			});
		});
		
		$("#querySql").click(function() {
			var sqlString = $("#sql").val();
			
			$.ajax({
				url : "http://jbm.daily.taobao.net/tddlServlet",
				data : {sql : sqlString}
			}).done(function(data) {
				$("#tddlContent").html("");
				var thHtml = "";
				var thArray = [];
				$.each(eval(data), function(i,row) {
					var trHtml = "<tr>";
					$.each(row, function(key,val) {
						if(thArray.indexOf(key) == -1) {
							thArray.push(key);
						}
						trHtml += "<td>" + val + "</td>";
					});
					trHtml += "</tr>" 
					$("#tddlContent").append(trHtml);
				})
				thArray.forEach(function(th) {
					thHtml += "<th>" + th + "</th>";
				});
				$("#tddlContent tr").first().before(thHtml);
			}).fail(function() {
				alert("error");
			});
		});
	</script>
</body>
</html>