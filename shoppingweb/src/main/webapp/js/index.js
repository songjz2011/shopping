$(document).ready(function() {
	initValidateForm();
	$("#submitButton").on("click", formSubmit);
});

/** 初始化验证form */
function initValidateForm() {
	$("#form").validate({
		rules : {
			name : "required",
			password : {
				required : true,
				minlength : 5
			}
		},
		messages : {
			name : "请输入账户",
			password : {
				required : "请输入密码",
				minlength : jQuery.validator.format("密码不能小于{0}个字 符")
			}
		}
	});
}

/** form提交 */
function formSubmit() {
	var options = {
		beforeSubmit : function(arr, $form, options) {
			$("#form").valid();
		},
		success : formSubmitResponse,
		url : "users/login",
		type : "post",
		dataType : "json",
	};
	$("#form").ajaxForm(options);
}

/**
 * form提交回调
 */
function formSubmitResponse(data) {
	alert(data);
	alert("提交成功");
}
