package com.othelle.core.dao;

/**
 * User: v.vlasov
 * Date: 12/14/11
 */
public interface Scheme<ProviderType> {
    void createScheme(ProviderType provider);

    void updateScheme(ProviderType provider, int oldVersion, int newVersion);
}
