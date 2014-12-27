package shopping.basic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import shopping.util.OperationResult;
import shopping.util.error.RequestParamError;

import com.alibaba.fastjson.JSON;

/**
 * @author songjz Dec 22, 2014
 */
public class BasicController {

  /**
   * 验证请求参数, 如果表单验证有错误则返回403拒绝访问.
   */
  protected void validateRequestParams(Errors errors, HttpServletResponse response) {
    if (errors.hasErrors()) {
      List<FieldError> list = errors.getFieldErrors();
      List<RequestParamError> paramErrorList = new ArrayList<RequestParamError>(list.size());
      for (FieldError error : list) {
        Object value = error.getRejectedValue();
        if (value != null) {
          paramErrorList.add(new RequestParamError(error.getField(), value.toString()));
        }
      }
      try {
        response.getWriter().print(OperationResult.fail("表单错误 : " + JSON.toJSONString(list)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
