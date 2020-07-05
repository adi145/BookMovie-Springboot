package com.spapr.bookshow.factory;

import com.spapr.bookshow.Constants.ResponseConstant;
import com.spapr.bookshow.controller.response.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseFactory {

    public ResponseFactory() {
        //default constructor
    }

    public ResponseEntity success() {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseObject.setMessage(ResponseConstant.SUCCESS_CODE);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity success(Object data, String message) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseObject.setMessage(message);
        responseObject.setData(data);
        log.info("========== Response ========== {}", responseObject);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setMessage(errorCode);
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, Object data) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setMessage(errorCode);
        responseObject.setData(data);
        return new ResponseEntity(responseObject, httpStatus);

    }
}
