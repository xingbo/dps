package com.dps.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * <pre>
 * 
 * </pre>
 *
 */
public class BaseController implements MessageSourceAware {

	private static final  Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected MessageSource     messageSource;

    public Map<String, Object> pagedResult(List<?> results, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("results", results);
        map.put("rows", count);
        return map;
    }

    public Map<String, Object> fail() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        return map;
    }

    public Map<String, Object> fail(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        String message = messageSource.getMessage(code, null, null, null);
        if (message == null) {
            message = code;
        }
        map.put("msg", message);
        return map;
    }

    public Map<String, Object> fail(String code, Object[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        String message = messageSource.getMessage(code, args, "Fail!", null);
        map.put("msg", message);
        return map;
    }

    public Map<String, Object> data(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        return map;
    }

    public Map<String, Object> data(String name, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, data);
        return map;
    }

    public Map<String, Object> success() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
