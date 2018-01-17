/**
 * ajax请求
 * @param url：请求地址
 * @param data：请求参数
 * @param contentType：内容类型1：json传输，2：表单字符串传输，3：文件类型传输
 * @param handleSuccess：回调函数
 */
function handleAjaxSimple(url, data, contentType, callback) {
	var reqContentType = "";
	switch (contentType) {
	case 1:
		reqContentType = "application/json";
		break;
	case 2:
		reqContentType = "application/x-www-form-urlencoded";
		break;
	case 3:
		reqContentType = "multipart/form-data";
		break;
	default:
		reqContentType = "application/json";
		break;
	}
	$.ajax({
		type : "post",
		url : url,
		data : data,
		dataType : "json",
		contentType : reqContentType,
		async : true,
		cache : false,
		error : function() {
			$.fn.modalAlert("请求失败！", "error");
		},
		success : function(json) {
			return callback(json);
			//handleSuccess(json);
		}
	});
}

function handleAjaxAdvance(type, url, data, dataType, contentType, async, cache,handleSuccess) {
	$.ajax({
		type : type,
		url : url,
		data : data,
		dataType : dataType,
		contentType : contentType,
		async : async,
		cache : cache,
		error : function() {
			$.fn.modalAlert("请求失败！", "error");
		},
		success : function(json) {
			handleSuccess(json);
		}
	});
}

$.fn.serializeJsonObject = function(obj) {
	if (obj == undefined || obj == '')
		obj = {};
	var formarray = this.serializeArray();
	$.each(formarray, function() {
		if (obj[this.name]) {
			if (!obj[this.name].push) {
				obj[this.name] = [ obj[this.name] ];
			}
			obj[this.name].push(this.value || '');
		} else {
			obj[this.name] = this.value || '';
		}
	});
	return obj;
};
