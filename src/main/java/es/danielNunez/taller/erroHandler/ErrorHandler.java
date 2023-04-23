package es.danielNunez.taller.erroHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends Throwable{

	private String type;
	private String title;
	private int status;
	private String detail;
	
	public ErrorHandler(String name, String title2, int status2, String detail2) {
		// TODO Auto-generated constructor stub
	}

	public ErrorHandler() {
		// TODO Auto-generated constructor stub
	}

	public ErrorHandler(ErrorHandler res) {
		// TODO Auto-generated constructor stub
	}

	@ExceptionHandler( Exception.class)
	protected ErrorHandler handleDefaultError(Exception e) throws ErrorHandler{
		ErrorHandler res = new ErrorHandler();
		res.type = e.getClass().getName();
		res.title = "Unexpected error";
		res.status = 400;
		res.detail = "An unexpected error ocurred";
		
		throw new ErrorHandler(res);
		//throw new ErrorHandler(ResponseEntity<>(HttpStatus.BAD_REQUEST)).body(e.getClass().getName(), res.title, res.status, res.detail));
	}
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	protected ResponseEntity<String> nonValidFields(String msg){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
	}

	private ResponseEntity<Object> handleExceptionInternal(Runtime ex, ErrorHandler res, HttpHeaders httpHeaders,
			HttpStatus conflict) {
		// 
		return new ResponseEntity<Object>(res , conflict);
	}
	
//	protected ResponseEntity<ErrorHandler> validateFields(Runtime ex, WebRequest req) {
//		ErrorHandler res = new ErrorHandler();
//		res.type = ex.getClass().getName();
//		res.title = "Unexpected error";
//		res.status = 400;
//		res.detail = "An unexpected error ocurred";
//		
//		
//		return new ResponseEntity<ErrorHandler>(res, HttpStatus.BAD_REQUEST);
//	}
	
}
