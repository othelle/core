package com.othelle.core.dao;

import java.util.Collection;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/14/11
 */
public interface Dao<Type> {
    Type find(long id);

    List<Type> list();

    long save(Type item);

    long save(Type item, boolean recursive);

    long[] save(Type... item);

    long remove(long id);

    long remove(Type item);

    long[] remove(Type... item);

    long[] remove(Collection<Type> item);

    void resetCache();

    void removeAll();

    List<Type> list(String column, Object value);

    List<Type> list(String[] columns, Object[] values);
}
