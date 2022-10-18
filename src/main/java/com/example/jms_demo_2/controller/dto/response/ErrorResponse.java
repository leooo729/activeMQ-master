package com.example.jms_demo_2.controller.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
public class ErrorResponse {
    private List<Map<String, String>> fieldError;

    private String error;

    // 處理 RequestBody
    public ErrorResponse(MethodArgumentNotValidException e) {

        this.fieldError = new ArrayList<>();

        // 因為未通過基礎檢核的欄位可能不只一個
        // 所以需要呼叫 e.getBindingResult().getFieldErrors() 取得不符合基礎檢核的欄位
        // 再放入 fieldError 中

        e.getBindingResult().getFieldErrors().stream().forEach(m -> {
            Map<String, String> fieldMap = new HashMap<>();

            // 欄位名稱
            fieldMap.put("fiels", m.getField());

            // 錯誤類型
            fieldMap.put("code", m.getCode());

            // 錯誤訊息
            fieldMap.put("message", m.getDefaultMessage());

            fieldError.add(fieldMap);
        });
    }

    // 處理 Query String
    public ErrorResponse(ConstraintViolationException e) {

        this.fieldError = new ArrayList<>();

        // 因為未通過基礎檢核的欄位可能不只一個
        // 所以需要呼叫 e.getConstraintViolations() 取得不符合基礎檢核的欄位
        // 再放入 fieldError 中

        e.getConstraintViolations().stream().forEach(c -> {

            String fieldName = "";
            //LeafNode //nodeList
            for (Path.Node node : c.getPropertyPath()) {
                fieldName = node.getName();
            }

            Map<String, String> map = new HashMap<>();

            // 欄位名稱
            map.put("field", fieldName);

            // 錯誤類型
            map.put("code", c.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());

            // 錯誤訊息
            map.put("message", c.getMessage());

            fieldError.add(map);
        });
    }

    // 處理 Exception
    public ErrorResponse(Exception e) {
        this.error = e.getMessage();
    }

    public List<Map<String, String>> getFieldError() {
        return fieldError;
    }

    public void setFieldError(List<Map<String, String>> fieldError) {
        this.fieldError = fieldError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}

//    private String error;
//
//    private String message;
//
//    private List<FieldError> fieldErrors = new ArrayList<>();
//
//    private List<CheckError> checkErrors = new ArrayList<>();
//
//
//    public ErrorResponse(String error, String message) {
//        this.error = error;
//        this.message = message;
//    }
//
//@Getter
//@Setter
//@RequiredArgsConstructor
//private static class FieldError {
//    private final String field;
//    private final String message;
//}
//
//@Getter
//@Setter
//@RequiredArgsConstructor
//private static class CheckError {
//    private final String message;
//}
//
//    public void addFieldError(String field, String message) {
//        fieldErrors.add(new FieldError(field, message));
//    }
//
//    public void addCheckError(String message) {
//        checkErrors.add(new CheckError(message));
//    }
