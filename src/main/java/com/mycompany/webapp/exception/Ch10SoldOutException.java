package com.mycompany.webapp.exception;

// 사용자 커스텀 예외
// try-catch가 필요 없는 RuntimeException으로 만들었다.
public class Ch10SoldOutException extends RuntimeException {
	public Ch10SoldOutException() {
		super("품절");
	}
	
	public Ch10SoldOutException(String message) {
		super(message);
	}
}
