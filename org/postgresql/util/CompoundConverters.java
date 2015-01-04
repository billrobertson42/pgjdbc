package org.postgresql.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class CompoundConverters {

    private static final ConcurrentHashMap<String, CompoundConverter> converters = new ConcurrentHashMap<String, CompoundConverter>();

    static {
        put("hstore", new HStoreConverter());
    }
    
    public static synchronized CompoundConverter put(String pgTypeName, CompoundConverter converter) {
        return converters.put(pgTypeName, converter);
    }
    
    public static CompoundConverter get(String pgTypeName, Object value) {
        if(pgTypeName != null) {
            CompoundConverter converter = converters.get(pgTypeName);
            return converter.canConvert(value) ? converter : null;
        }
        else {
            Iterator<CompoundConverter> iter = converters.values().iterator();
            while(iter.hasNext()) {
                CompoundConverter converter = iter.next();
                if(converter.canConvert(value)) {
                    return converter;
                }
            }
        }
        return null;
    }
    
    public static CompoundConverter get(String pgTypeName) {
        return converters.get(pgTypeName);
    }
    
}
