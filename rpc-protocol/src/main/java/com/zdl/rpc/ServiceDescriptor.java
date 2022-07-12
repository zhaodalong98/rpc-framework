package com.zdl.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description: 表示服务
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDescriptor {

    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClass = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClass.length];
        for(int i = 0; i < parameterClass.length; i++){
            parameterTypes[i] = parameterClass[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor)o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    public String toString(){
        return "clazz="+clazz+",method=" + method+ ",returnType=" + returnType +
                ",parameterTypes="+ Arrays.toString(parameterTypes);
    }
}
