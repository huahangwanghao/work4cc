function getQuetyData() {
	return $("#form_search").serializeJsonObject();
}

$(function(){
	//调用函数，初始化表格
    initTable();
	$("#btn_search").bind("click", initTable);
})
