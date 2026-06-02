package org.rundeck.hibernate;

import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;
import org.hibernate.boot.spi.SessionFactoryBuilderImplementor;

/**
 * Hibernate SPI that registers {@link OracleDistinctStatementInspector} when
 * {@link RundeckOracleDialect} is active.
 *
 * Discovered via Java service loader from META-INF/services — no changes to
 * Rundeck's application.groovy or rundeck-config.properties are required.
 */
public class OracleSessionFactoryBuilderFactory implements SessionFactoryBuilderFactory {

    @Override
    public SessionFactoryBuilder getSessionFactoryBuilder(
            MetadataImplementor metadata,
            SessionFactoryBuilderImplementor defaultBuilder) {
        if (metadata.getDatabase().getDialect() instanceof RundeckOracleDialect) {
            defaultBuilder.applyStatementInspector(new OracleDistinctStatementInspector());
        }
        return defaultBuilder;
    }
}
