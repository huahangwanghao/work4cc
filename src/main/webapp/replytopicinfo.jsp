<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论帖子及回复评论</title>
	<style type="text/css">
		textarea,#wrapper{
			width:500px;
		}
		textarea{
			padding:3px 5px;
		}
		#replytopicForm .td_1{
			padding:0;
		}
	</style>
</head>
<body>
	<div class="info_div">
		<br/>
		<form method="post" id="replytopicForm" enctype="multipart/form-data">
			<table class="t_biaodan" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td class="td_1" width="80"><span id="labeltopiccontent">帖子内容</span>：</td>
					<td class="td_2">
						<textarea rows="9" id="topiccontent" name="topiccontent" class="required" maxlength="200" placeholder="输入评论内容(不超过200字)"></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_1">图片：</td>
					<td class="td_2">
						<div id="wrapper" style="float: left;">
					        <div id="container">
					            <!-- 头部，相册选择和格式选择 -->
					            <div id="uploader">
					                <div class="queueList">
					                    <div id="dndArea" class="placeholder">
					                        <div id="filePicker"></div>
					                        <p>或将照片拖到这里，最多可选9张</p>
					                    </div>
					                </div>
					                <div class="statusBar" style="display:none;">
					                    <div class="progress">
					                        <span class="text">0%</span>
					                        <span class="percentage"></span>
					                    </div>
					                    <div class="info"></div>
					                    <div class="btns">
					                        <div id="filePicker2"></div>
					                        <div class="uploadBtn">上传图片</div>
					                    </div>
					                </div>
					            </div>
					        </div>
						</div>
					</td>
				</tr>
				<tr height="50px">
					<td colspan="2" style="padding-top:20px;">
						<input type="hidden" id="topicid" name="topicid"/>
						<input type="hidden" id="replytopicid" name="replytopicid"/>
						<input type="hidden" id="bbsuserid" name="bbsuserid"/>
						<button id="save_replytopic" class="btn_1 btn-orange" type="button" style="margin-left: 170px;">回复</button>
						&nbsp;&nbsp;
						<button id="return_reply" class="btn_1 btn-orange" type="button" style="margin-left: 100px;">返回</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function () {
	delCookie("topicimgs");
})
	$("#save_replytopic").click(function(){
		var objForm = $("#replytopicForm");
		if (!$.check4Submit(objForm)) {
			return;
		}
		var file = uploader.getFiles();
		var topicimgs;
		if(file!=null && file!="" && file.length > 0){
			var imgarr = getCookie("topicimgs");
	        if(imgarr == null || imgarr == ''){
	        	alert("请先上传图片！");
				return;
	        } else {
	        	topicimgs = imgarr.split(",");
	        	topicimgs = '{"topicimgs":['+topicimgs+']}';
	        }
	     }
		showloading();
		$.ajax({
			url : "reply/addreplytopic.do",
			type : "post",
			dataType : "json",
			cache: false,
         	data : {
         		"bbsuserid":$("#replytopicForm #bbsuserid").val(),
         		"topicid":$("#replytopicForm #topicid").val(),
         		"answerreplytopicid":$("#replytopicForm #replytopicid").val(),
         		"topiccontent":$("#replytopicForm #topiccontent").val(),
         		"topicimg":topicimgs
         	},
         	success: function (data){
				alert(data.restr);
           		if (data.recode == 0){
           			delCookie("topicimgs");
					//隐藏upload div
					$("#replytopicdiv").removeAttr("class");
					$("#replytopicdiv").attr("class","webuploader-element-invisible");
					$("#searchForm").submit();
				} else {
					hideloading();
				}
        	},
        	error : function(data) {
        		alert("操作失败！");
        		hideloading();
		    }
       	});
	})
	//返回处理
	$("#return_reply").click(function(){
		$("#masklayer").hide();
		clearuploadfiles();
		//重置表单元素
		$("#replytopicForm")[0].reset();
		$("#replytopicForm #topicid").val("");
		$("#replytopicForm #replytopicid").val("");
		//隐藏upload div
		$("#replytopicdiv").removeAttr("class");
		$("#replytopicdiv").attr("class","webuploader-element-invisible");
	});
	
	//清空选择的图片以及已经上传的图片
	function clearuploadfiles(){
		var file = uploader.getFiles();
		for(var i=0;i<file.length;i++){
			uploader.removeFile(file,true);
		}
		var imgarr = getCookie("topicimgs");
		var replyimgs;
		if(imgarr != null && imgarr != ''){
        	replyimgs = '{"topicimgs":['+imgarr.split(",")+']}';
        }
        delCookie("topicimgs");
		$.ajax({
			url : "reply/delreplyimg.do",
			type : "post",
			dataType : "json",
			cache: false,
         	data : { "replyimgs":replyimgs },
         	success: function (data){
        	},
        	error : function(data) {
		    }
       	});
	}
	
	function onFileSelected(event,id) {
		var filesize = event.target.files.length;
		if(filesize > 0){
			var imgtag = document.getElementById(id);
			imgtag.src = "/css/images/wait.jpg";
			
			var selectedFile = event.target.files[0];
		 	var reader = new FileReader();
		 	
			imgtag.title = selectedFile.name;
	
		    reader.onload = function(event) {
		        imgtag.src = event.target.result;
		    };
		    reader.readAsDataURL(selectedFile);
		}
	}
</script>
</html>