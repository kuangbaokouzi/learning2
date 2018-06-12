$(document).ready(function() {
	$('#idCard').textbox({
		label : 'ID Card:',
		iconCls : 'icon-man',
		width : 300,
		prompt : 'please input idcard...',
		required : true
	});
	$('#password').textbox({
		label : 'Password:',
		iconCls : 'icon-lock',
		type : 'password',
		width : 300,
		required : true
	});

	$('#_easyui_textbox_input1').bind('keyup', function() {
		var idCard = $(this).val();
		if (!idCard)
			return;
		$.ajax({
			url : "UserServlet",
			type : 'post',
			data : {
				tp : 'isIdCardExists',
				idCard : idCard
			},
			dataType : 'json',// 预期服务器响应的数据格式是json
			beforeSend : function(jqXHR, settings) {
				console.log('before ajax sent', jqXHR, settings);
			},
			success : function(data) {
				console.log('ajax sent successfully');
				if (!data.msg) {
					$('#idCard_err_msg').show();
				} else {
					$('#idCard_err_msg').hide();
				}
			},
			dataFilter : function(data, type) {
				console.log('dataFilter', data, type);
				return data;
			},
			complete : function(jqXHR, textStatus) {
				console.log('complete', textStatus);
			}
		});
	});
});

// 表单提交
function loginSubmit() {
	$('#form').form('submit', {
		onSubmit : function() { // 提交动作发生时，回调的函数
			var isValid = $(this).form('validate');
			if (!isValid) {
				$.messager.progress('close'); // 验证失败，隐藏进度条
			}
			// 当form验证通过，利用jq的方式进行传统的表单提交
			console.log($('#form').serialize());
			$('#form').submit();
			return false;
		}
	});
}