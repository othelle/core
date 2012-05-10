package com.othelle.core.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/21/11
 */
public abstract class BaseFilter<Type> implements Filter<Type> {
    @Override
    public List<Type> filter(List<Type> filter) {
        return (List<Type>) filter(((Collection<Type>) filter));
    }

    @Override
    public Collection<Type> filter(Collection<Type> filter) {
        ArrayList<Type> result = new ArrayList<Type>();

        for (Type item : filter) {
            if (filter(item)) {
                result.add(item);
            }
        }

        return result;
    }

    protected abstract boolean filter(Type item);
}
