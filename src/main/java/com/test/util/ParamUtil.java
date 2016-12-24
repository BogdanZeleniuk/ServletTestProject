package com.test.util;

public class ParamUtil {

    private static final int DEFAULT_LIMIT = 10000;
    private static final String DEFAULT_TEXT = "";
    private static final boolean DEFAULT_INCLUDE_META_DATA = false;

    private boolean isFigure(String param){
        return param.trim().matches("\\d+$");
    }
    private boolean isBoolean(String param){return ("true".equals(param) || "false".equals(param));}

    public String getTextParam(String q){
        return (q != null && !q.trim().isEmpty()) ? q : DEFAULT_TEXT;
    }

    public int getLimitParam(String limit){
        return (limit !=null && !limit.trim().isEmpty() && isFigure(limit)) ? Integer.parseInt(limit.trim()) : DEFAULT_LIMIT;
    }

    public int getLengthParam(String length){
        return (length != null && !length.trim().isEmpty() && isFigure(length)) ? Integer.parseInt(length.trim()) : -1;
    }

    public boolean includeMetaData(String defaultMetaData){
        return (defaultMetaData != null && isBoolean(defaultMetaData)) ? Boolean.parseBoolean(defaultMetaData) : DEFAULT_INCLUDE_META_DATA;
    }
}
