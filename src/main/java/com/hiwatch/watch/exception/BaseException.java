package com.hiwatch.watch.exception;


/**
 * Description:
 * @author w77996
 * @date 2017年7月4日 下午2:09:56
 */
public class BaseException extends RuntimeException{


	private static final long serialVersionUID = -8510855161645724753L;
	
	protected int code;
	
	protected String msg;
	
	public static final BaseException DB_INSERT_RESULT_0 = new BaseException(10040001,"数据库操作insert返回0");
	
	public static final BaseException DB_UPDATE_RESULT_0 = new BaseException(10040002,"数据库操作update返回0");
	
	public static final BaseException DB_SELECTIONE_RESULT_0 = new BaseException(10040003,"数据库操作selectone返回0");
	
	public static final BaseException DB_LIST_IS_NULL = new BaseException(10040004,"数据库list返回0");
	
	public static final BaseException TOKEN_IS_ILLICIT = new BaseException(10040005,"Token 验证不通过");
	
	public static final BaseException SESSION_IS_OUT_TIME = new BaseException(10040006,"会话超时");
	
	public static final BaseException DB_GET_SEQ_NEWX_VALUE_ERROR = new BaseException(10040007,"序列生成超时");
	
	public BaseException(int code,String msgFomat,Object...objects ){
		super(String.format(msgFomat, objects));
		this.code = code;
		this.msg = String.format(msgFomat, objects);
	}
	
	public BaseException(){
		
	}
	
	public BaseException(String message,Throwable cause){
		super(message, cause);
	}
	
	public BaseException(String message){
		super(message);
	}
	
	public BaseException(Throwable cause){
		super(cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BaseException newInstence(String msgFomat,Object... objects){
		return new BaseException(this.code, msgFomat, objects);
	}
}
