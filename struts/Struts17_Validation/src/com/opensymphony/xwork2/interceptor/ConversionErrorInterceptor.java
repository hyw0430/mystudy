/*
 * Copyright 2002-2007,2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensymphony.xwork2.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.conversion.impl.XWorkConverter;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;

public class ConversionErrorInterceptor extends AbstractInterceptor {

    public static final String ORIGINAL_PROPERTY_OVERRIDE = "original.property.override";

    protected Object getOverrideExpr(ActionInvocation invocation, Object value) {
        return escape(value);
    }

    protected String escape(Object value) {
        return "\"" + StringEscapeUtils.escapeJava(String.valueOf(value)) + "\"";
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionContext invocationContext = invocation.getInvocationContext();
        Map<String, Object> conversionErrors = invocationContext.getConversionErrors();
        ValueStack stack = invocationContext.getValueStack();

        HashMap<Object, Object> fakie = null;

        for (Map.Entry<String, Object> entry : conversionErrors.entrySet()) {
            String propertyName = entry.getKey();
            Object value = entry.getValue();

            if (shouldAddError(propertyName, value)) {
                String message = XWorkConverter.getConversionErrorMessage(propertyName, stack);

                Object action = invocation.getAction();
                if (action instanceof ValidationAware) {
                    ValidationAware va = (ValidationAware) action;
                    va.addFieldError(propertyName, message);
                }

                if (fakie == null) {
                    fakie = new HashMap<Object, Object>();
                }

                fakie.put(propertyName, getOverrideExpr(invocation, value));
            }
        }

        if (fakie != null) {
            // if there were some errors, put the original (fake) values in place right before the result
            stack.getContext().put(ORIGINAL_PROPERTY_OVERRIDE, fakie);
            invocation.addPreResultListener(new PreResultListener() {
                public void beforeResult(ActionInvocation invocation, String resultCode) {
                    Map<Object, Object> fakie = (Map<Object, Object>) invocation.getInvocationContext().get(ORIGINAL_PROPERTY_OVERRIDE);

                    if (fakie != null) {
                        invocation.getStack().setExprOverrides(fakie);
                    }
                }
            });
        }
        
		//目标：检查目标Action类中是否有错误信息，如果有则return input
		//1.获取目标Action对象
		Object action = invocation.getAction();
		
		//2.检测目标Action对象是否实现了ValidationAware接口
		if(action instanceof ValidationAware) {
			//3.如果实现了ValidationAware接口，则转换类型
			ValidationAware va = (ValidationAware) action;
			
			//4.检测目标Action中是否被添加了错误消息
			if(va.hasErrors()) {
				//5.如果添加了错误消息，则return input
				return "input";
			}
			
		}
        
        
        return invocation.invoke();
    }

    protected boolean shouldAddError(String propertyName, Object value) {
        return true;
    }
}
