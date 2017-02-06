var MyLocal = {
	BaseMainPaeg : {
		list : {
			onLoadError_parseResponse_successMsg : '数据返回成功。',
			onLoadError_parseResponse_failureMsg : '获取数据异常。您可以尝试刷新或关闭后再重新打开列表界面。',
			onLoadSuccess_exceptionOrNodataMsg : '系统异常，获取数据失败。'
		},
		noSelectedRowMsg : {
			def : '请选择要操作的行。', // 默认
			u   : '请选择要修改的行！', // 修改
			d   : '请选择要删除的行！', // 删除
			s   : '请选择要查看的行！'  // 查看
		},
		openAddFormWindow_title : '新增',
		openUpdateFormWindow_title : '修改',
		save_form_parseResponse_successMsg : '保存成功。',
		save_form_parseResponse_failureMsg : '保存失败。',
		showDetail_title : '详情',
		delete_confirm_title : '删除',
		delete_confirm_msg : '您确定要删除所选中的数据行吗?'
	},
	
	Msg : {
		msg_default_title: '提示',
		msg_alert_title : '提示',
		msg_info_title : '提示',
		msg_warning_title : '警告',
		msg_error_title : '错误',
		msg_question_title : '问题',
	},
	
	MyTool : {
		parseResponse : {
			default_respNullMsg : '系统响应异常，未接收到响应数据。操作失败。',
			default_respExceptionMsg : '返回数据格式错误，解析失败。',
			default_operateSuccessMsg : '操作成功。',
			default_operateErrorMsg : '操作失败。',
		},
		doLoginFailure : {
			loginFailureMsg : '登陆失效，请重新登陆!'
		}
		
	},
	
	Mask_default_loadingMsg : '正在处理，请稍候......',
	
	LoadingPageMask_default_loadingMsg : '页面加载中......'
};