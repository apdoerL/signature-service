package org.apdoer.signature.service.code;

import java.util.*;

/**
 * 异常错误码枚举   0400-0499：服务响应 0500-0699：请求参数验证 0700-0899：业务验证
 */
public enum ExceptionCode {
	/**
	 * 0400-0499：服务响应
	 */
	/*成功*/
	SUCCESS( 0, "success" ),

	/*失败*/
	FAIL( 101050400, "fail" ),

	/*请求超时*/
	REQUEST_TIMEOUT( 101050401, "request timeout" ),

	/*未知异常*/
	UNKNOWN_EXCEPTION_CODE( 101050402, "unknown exception code" ),

	/*渠道服务发送失败*/
	CHANNEL_SERVICE_DELIVERY_FAILURE( 101050403, "channel service delivery failure" ),

	/*无权限用户*/
	UNAUTHORIZED_USERS( 101050404, "unauthorized users" ),

	/**
	 * 0500-0699：请求参数验证
	 */

	/**
	 * 0700-0899：业务验证
	 */
	/*签名错误*/
	SIGNATURE_INVALID( 101050701, "signature invalid" );


	private int codeNo;

	private String codeName;

	private static Map<Integer, String> map = new HashMap<Integer, String>();

	private static List<ExceptionCode> list = new ArrayList<ExceptionCode>();

	static {
		for (ExceptionCode status : ExceptionCode.values()) {
			map.put(status.getCodeNo(), status.getCodeName());
		}
		list.addAll(Arrays.asList(ExceptionCode.values()));
	}

	public int getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(int codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	private ExceptionCode(int codeNo, String codeName) {
		this.codeNo = codeNo;
		this.codeName = codeName;
	}

	/**
	 * 返回map类型形式
	 *
	 * @return
	 */
	public static Map<Integer, String> getMap() {
		return map;
	}

	/**
	 * 返回list类型形式
	 *
	 * @return
	 */
	public static List<ExceptionCode> getList() {
		return list;
	}

	/**
	 * 根据code获取枚举类型
	 *
	 * @param codeNo
	 * @return
	 */
	public static ExceptionCode getCategory(int codeNo) {
		for (ExceptionCode status : list) {
			if (status.getCodeNo() == codeNo) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 根据code获取枚举类型中文名称
	 *
	 * @param codeNo
	 * @return
	 */
	public static String getName(int codeNo) {
		for (ExceptionCode status : list) {
			if (status.getCodeNo() == codeNo) {
				return status.getCodeName();
			}
		}
		return null;
	}
}
