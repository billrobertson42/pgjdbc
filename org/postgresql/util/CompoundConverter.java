package org.postgresql.util;

import java.sql.SQLException;
import org.postgresql.core.BaseConnection;
import org.postgresql.core.Encoding;

public interface CompoundConverter<T> {

    T fromBytes(byte[] bytes, Encoding encoding) throws SQLException;
    T fromString(String stringRepresentation);
    
    byte[] toBytes(T value, Encoding encoding) throws SQLException;
    String toString(T value);
 
    int getOid(BaseConnection connection) throws SQLException;
    boolean canConvert(Object value);
}
