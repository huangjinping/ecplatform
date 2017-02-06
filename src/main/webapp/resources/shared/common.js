// 设置日期空间的默认显示格式为 yyyy-MM-dd
$.fn.datebox.defaults.formatter = function(date){
	return dateFormat(date, '-');
};

// 添加startWith
String.prototype.startWith=function(str) {     
  var reg=new RegExp("^"+str);     
  return reg.test(this);        
};

// 添加endWith方法
String.prototype.endWith=function(str){     
  var reg=new RegExp(str+"$");     
  return reg.test(this);        
};

/**
 * 初始化
 */
$(function() {
    // 改变验证控件初始化验证为鼠标离开时
    enableValidate(false);

    $.extend($.fn.validatebox.defaults.rules, {
        // 密码验证
        equals : {
            validator : function(value, param) {
                return value == $(param[0]).val();
            },
            message : '两次输入的密码不一致！'
        },
        // 最小长度
        minLength : {
            validator : function(value, param) {
                return value.length >= param[0];
            },
            message : '密码至少输入6位以上字符！'
        },
        // 时间控件验证
        dateTime : {
            validator : function(value, param) {
                var start = $(param[0]).datebox('getValue');
                var end = $(param[1]).datebox('getValue');
                if (start != '' && end != '') {
                    var strSeparator = "-"; // 日期分隔符
                    var oDate1;
                    var oDate2;

                    oDate1 = start.split(strSeparator);
                    oDate2 = end.split(strSeparator);
                    var strDateS = new Date(oDate1[0], oDate1[1] - 1, oDate1[2]);
                    var strDateE = new Date(oDate2[0], oDate2[1] - 1, oDate2[2]);
                    return strDateE >= strDateS;
                }
                return true;
            },
            message : '结束时间不能小于开始时间，请重新选择！'
        }
    });
});

/**
 * 设置页面初始化后是否验证表单
 * @param enable true:开启验证 false：关闭验证
 */
function enableValidate(enable){
    if(enable){
        $('input.easyui-validatebox,input.easyui-numberbox').validatebox('enableValidation');
    }else{
        // 改变验证控件初始化验证为鼠标离开时
        $('input.easyui-validatebox,input.easyui-numberbox').validatebox('disableValidation').focus(function() {
            $(this).validatebox('enableValidation');
        }).blur(function() {
            $(this).validatebox('validate');
        });
    }
}

/**
 * 遮罩层
 */
var Mask = function (config) {
	config = config || {};
	var msg = config.maskMsg || MyLocal.Mask_default_loadingMsg;
	var mask = $('<div id="maskDiv" class="datagrid-mask"></div>');
    var msgMask = $('<div class="datagrid-mask-msg"></div>');
	mask.css({
		display:'none',
		width:'100%',
		zIndex:'9998', 
		height:$(window).height()
	})
	.appendTo('body'); 
	msgMask.html(msg)
	.appendTo('body')
	.css({
		display:'none',
		zIndex:'9999',
		left:($(document.body).outerWidth(true) - 190) / 2,
		top:($(window).height() - 45) / 2
	});
    this.show = function () {	
    	if (!mask || !msgMask) {
    		return;
    	}
		mask.css({display:'block'});
		msgMask.css({display:'block'});
		return this;
	};
	this.hide = function() {
		mask.remove(); 
		msgMask.remove();
	};
};

/**
 * 页面加载遮罩层
 */
var LoadingPageMask = function (config) {
	config = config || {};
	var msg = config.maskMsg || MyLocal.LoadingPageMask_default_loadingMsg;
	var mask = $('<div id="loadingPageMask" class="datagrid-mask"></div>');
    var msgMask = $('<div class="datagrid-mask-msg"></div>');
	mask.css({
		display:'none',
		width:'100%',
		zIndex:'9998', 
		height:$(window).height()
	})
	.appendTo('body'); 
	msgMask.html(msg)
	.appendTo('body')
	.css({
		display:'none',
		zIndex:'9999',
		left:($(document.body).outerWidth(true) - 190) / 2,
		top:($(window).height() - 45) / 2
	});
    this.show = function () {	
    	if (!mask || !msgMask) {
    		return;
    	}
		mask.css({display:'block'});
		msgMask.css({display:'block'});
		return this;
	};
	this.hide = function() {
		mask.remove(); 
		msgMask.remove();
	};
};

/**
 * 路径
 * 获取contextPath + requestURI的完整路径。
 * @param path
 * @returns
 */
function getUrl(path) {
	return contextPath + path;
}

/**
 * 消息类
 */
var YF = {};
YF.Msg = Msg = (function() {
	/** 所有的消息提示均接受两种类型的参数。
	 *  1. Msg.alert('Title', 'Msg');
	 *  2. var config = {title: 'Title', msg: 'Msg'}
	 *     Msg.alert(config);
	 */
	var Message = function() {
		var getConfig = function(args, defTitle) {
			var cfg = new Object();
			cfg.title = defTitle || MyLocal.Msg.msg_default_title;
			cfg.msg = '';
			if (args.length == 0) {
				return cfg;
			}
			if (args.length == 1) {
				var constructor = args[0].constructor;
				if (constructor == Object) {
					return args[0];
				}
				if (constructor == String) {
					cfg.msg = args[0];
					return cfg;
				}
				return cfg;
			}
			cfg.title = args[0];
			cfg.msg = args[1];
			return cfg;
		};
		this.alert = function() {
			var config = getConfig(arguments, MyLocal.Msg.msg_alert_title);
			$.messager.alert(config.title, config.msg);
		};
		this.info = function() {
			var config = getConfig(arguments, MyLocal.Msg.msg_info_title);
			$.messager.alert(config.title, config.msg, 'info');
		};
		this.warning = function() {
			var config = getConfig(arguments, MyLocal.Msg.msg_warning_title);
			$.messager.alert(config.title, config.msg, 'warning');
		};
		this.error = function() {
			var config = getConfig(arguments, MyLocal.Msg.msg_error_title);
			$.messager.alert(config.title, config.msg, 'error');
		};
		this.question = function() {
			var config = getConfig(arguments, MyLocal.Msg.msg_question_title);
			$.messager.alert(config.title, config.msg, 'question');
		};
	};
	return new Message();
})();

var MyTool = {};
(function() {
	/**
	 * 打印对象的第一层 key, value
	 */
	MyTool.objToString = function (obj) {
		var s = '';
		for (var property in obj) {
			s = s + '\n '+property +': ' + obj[property] ;
		}
		return s;
	};
	
	/** 
	 * 获取当前日期字符串
	 * 默认为 ‘-’ 分隔
	 * @returns
	 */
	MyTool.nowDate = function(separator) {
		separator = separator || '-';
		return this.dateFormat(new Date(), separator);
	};
	
	/**
	 * 日期格式化 yyyy-MM-dd
	 * 将日期格式化 为年月日
	 * @param date	日期
	 * @param separator 年月日分隔符
	 * @returns
	 */
	MyTool.dateFormat = function(date, separator) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m > 9 ? m : '0' + m;
		var d = date.getDate();
		d = d > 9 ? d : '0' + d;
		return y + separator + m + separator + d;
	};
	
	/**
	 * 处理响应，返回状态 （成功 - true, 失败 - false）
	 * 
	 * @param response
	 * 			解析参数，格式要求：
	 * 					必须为 {success:true, msg:''....} 的json字符串或json格式。
	 * @param noShowAlertMsg
	 * @param other: 参数对象,可选属性
	 * 					successMsg[默认的成功提示信息], 
	 * 					failureMsg[默认的失败提示信息], 
	 * 					respNullMsg[响应内容为空异常提示信息],
	 * 					noShowAlertMsg[不弹出成功、失败消息提示框，仅返回true 或 false]
	 * @returns
	 */
	MyTool.parseResponse = function(response, other) {
		other = other || {};
		if (!response) {
			YF.Msg.error((other.respNullMsg || 
					MyLocal.MyTool.parseResponse.default_respNullMsg));
			return false;
		}
		var result = undefined;
		var constructor = response.constructor;
		if (constructor == Object) {
			result = response;
		} else if (constructor == String) {
			try {
			    result = eval('('+response+')');
			} catch(e) {
				YF.Msg.error(MyLocal.MyTool.parseResponse.default_respExceptionMsg);
				return false;
			};
		} else {
			YF.Msg.error(MyLocal.MyTool.parseResponse.default_respExceptionMsg);
			return false;
		}
		// 首先检查登录身份信息是否超时
		if(!this.doLoginFailure(result)) {
            return false;
        }
		// 登录验证成功后选择不弹出message信息
	    if (other.noShowAlertMsg) {
	    	return result.success;
	    }
	    // 登录验证成功后显示message信息
		var msg = '';
	    if (result.success) {
	    	if (other.successMsg) {
	    		msg = other.successMsg;
	    	} else {
	    		msg = result.msg || MyLocal.MyTool.parseResponse.default_operateSuccessMsg;
	    	}
//	    	if (result.msg) {
//	    	    msg = result.msg;
//	    	} else {
//	    	    msg = other.successMsg || MyLocal.MyTool.parseResponse.default_operateSuccessMsg;
//	    	}
	    	YF.Msg.info(msg);
	    	return true;
	    } else {
	    	if (other.failureMsg) {
	    		msg = other.failureMsg;
	    	} else {
	    		msg = result.msg || MyLocal.MyTool.parseResponse.default_operateErrorMsg;
	    	};
//	    	if (result.msg) {
//	    	    msg = result.msg;
//	    	} else {
//	    	    msg = other.failureMsg || MyLocal.MyTool.parseResponse.default_operateErrorMsg;
//	    	};
	    	YF.Msg.error(msg);
	        return false;
	    }
	};
	
	/**
	 * 获取最部的window对象
	 * 
	 * @param win
	 * @returns
	 */
	MyTool.getOuterWindow = function() {
		var top = window.top;
		var self = window.self;
		if (top != self) {
			return top;
		}
	};
	
	/**
	 * 登录失效校验
	 * parseResponse 方法中已经调用了此方法。
	 * 所以如果调用了parseResponse方法，就不需要再调此方法了。
	 */
	MyTool.doLoginFailure = function(result) {
		if (!result) {
			return true;
		}
		if (result.constructor == String) {
			try {
			    result = eval('('+result+')');
			} catch(e) {
				return true;
			};
		}
		if (result.notLoggedIn == 'yes') {
			var me = this;
			$.messager.alert(MyLocal.Msg.msg_warning_title,
				MyLocal.MyTool.doLoginFailure.loginFailureMsg,
				'warning',function(){
					var win = me.getOuterWindow();
					win.location.href=getUrl('/login');
				}
			);
			return false;
		}
		return true;
	};
})();


