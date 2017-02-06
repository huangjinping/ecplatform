/* **************************************************************
 *
 * 文件名称：ValidResult.java
 *
 * 包含类名：cn.cooperlink.framework.core.validator.ValidResult
 * 创建日期：2014年5月14日
 * 创建作者：潘云峰
 * 版权声明：Copyright 2014 北京酷博灵科信息科技有限公司 保留所有权利。
 *
 * **************************************************************/
package cn.cooperlink.framework.core.validator;
/**
 * 验证结果类。
 *
 * 创建日期：2014年5月14日
 * 创建作者：潘云峰
 */
public class ValidResult {
	
	public static final ValidResult SUCCESS = new ValidResult(true, "");

	private String msgCode;
	
	private Object[] arguments;
	
	/** 成功 */
	private boolean success;
	
	/** 消息 */
	private String msg;
	
	public ValidResult(boolean success) {
		this.success = success;
	}
	
	public ValidResult(boolean success, String msgCode, 
			Object... arguments) {
		this.success = success;
		this.msgCode = msgCode;
		this.arguments = arguments;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}
	
	public ValidResult msg(String msg) {
		if (msgCode != null) {
			throw new RuntimeException("非法操作！当前实例化对象采用消息编码取值，"
					+ "不能手动设置消息内容。");
		}
		this.msg = msg;
		return this;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public Object[] getArguments() {
		return arguments;
	}

}
