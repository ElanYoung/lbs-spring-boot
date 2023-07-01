package com.starimmortal.lbs.exception;

/**
 * 腾讯位置服务异常
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
public class LbsClientException extends RuntimeException {

	public LbsClientException() {
		super();
	}

	public LbsClientException(String message) {
		super(message);
	}

	public LbsClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public LbsClientException(Throwable cause) {
		super(cause);
	}

	protected LbsClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
