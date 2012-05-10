package com.othelle.core.filter;

import java.util.Collection;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/21/11
 */
public interface Filter<Type> {
    public List<Type> filter(List<Type> filter);

    public Collection<Type> filter(Collection<Type> filter);
}
