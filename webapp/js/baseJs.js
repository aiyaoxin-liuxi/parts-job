$(document).ready(function() {

	$.getUrlParam = function(name) {
		if(name != ''){
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		} else {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
		
	}
});

// 正整数验证
function isPositiveInteger(s) {
	var re = /^[0-9]+$/;
	return !re.test(s);
}
// 正数允许两位小数验证
function isPositiveDouble(s) {
	var re = /^[0-9]+([.]{1}[0-9]{1,2})?$/;
	return !re.test(s);
}
// 特殊字符验证
function specialStr(s) {
	var re = /^[^\s]{1}[^-_\~!@#\$%\^&\*\.\{\}<>\?\\\/\'\"]*$/;
	return !re.test(s);
}
// 特殊字符验证(日期的，可以有-)
function specialDate(s) {
	var re = /^[^\s]{1}[^_\~!@#\$%\^&\*\.\{\}<>\?\\\/\'\"]*$/;
	return !re.test(s);
}
// 特殊字符验证(地图经纬度的，可以有.)
function specialMap(s) {
	var re = /^[^\s]{1}[^_\~!@#\$%\^&\*\{\}<>\?\\\/\'\"]*$/;
	return !re.test(s);
}
// 特殊字符验证(可以空格)
function special_s(s) {
	var re = /[`~!@#\$%\^\&\*\(\)_\+<>\?:"\{\},\.\\\/;'\[\]]/;
	return !re.test(s);
}
// 超长验证
function overLengthStr(s, length) {
	return s.length > length;
}
// 包含空格验证
function checkTextSpace(s) {
	var re = /(^\s+)|(\s+$)/g;
	return re.test(s);
}
// 固话+手机号验证+非法字符
function checkPhoneAndMobile(s) {
	var res = false;

	if (checkMobile(s)) {
		res = checkPhone(s)
	}
	return res;
}
// 手机号验证
function checkMobile(s) {
	var re = /^((13[0-9])|(15[0-9])|147|(17[0-9])|(18[0-9]))[0-9]{8}$/;
	return !re.test(s);
}
// 固话验证010-65246953
function checkPhone(s) {
	var re = /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/;
	return !re.test(s);
}
// 不能为空
function isNullStr(s) {
	return s == '' ? true : false;
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt)){
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o){
		if (new RegExp("(" + k + ")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
