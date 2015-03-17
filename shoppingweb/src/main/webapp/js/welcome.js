$(document).ready(function() {
	var user_info = $.cookie("userName") + "<span class='caret'></span>";
	$("#loginuser_a").html(user_info);

	$("#logout_a").on("click", user_logout);
});

/**
 * 用户退出
 */
function user_logout() {
	$.ajax({
		dataType : "json",
		url : "users/logout",
		success : function() {
			window.location.href = "./login.html";
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			alert(GlobalMessgege.request_error);
		}
	});
}