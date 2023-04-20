package com.twelvet.quartz.server.exception;

import java.io.Serial;

/**
 * @author twelvet
 * @WebSite twelvet.cn
 * @Description: 计划策略异常
 */
public class TaskException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	private final Code code;

	public TaskException(String msg, Code code) {
		this(msg, code, null);
	}

	public TaskException(String msg, Code code, Exception nestedEx) {
		super(msg, nestedEx);
		this.code = code;
	}

	public Code getCode() {
		return code;
	}

	public enum Code {

		TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE

	}

}
