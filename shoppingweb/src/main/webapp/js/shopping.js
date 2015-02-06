/**
 * 公用js
 */

/**
 * 验证返回json数据，true，返回正确数据，false，错误数据 参数为：请求返回的数据
 */
function _validateResponseJsonData(data) {
	if (data == null) {
		return false;
	}
	var typeData = typeof (data);
	if (typeData == "undefined" || typeData != 'object') {
		alert(GlobalMessgege.request_error);
		return false;
	}
	if (data.result == "FAIL") {
		var msg = data.msg;
		var typeMsg = typeof (msg);
		if (typeMsg == "undefined" || (typeMsg == "string" && msg != "")) {
			alert(msg);
		}
		return false;
	}
	return true;
}

/**
 * 获取请求返回的业务数据, 参数为：请求返回的数据
 */
function _getResponseBizJsonData(data) {

}