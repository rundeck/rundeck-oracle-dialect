package org.rundeck.hibernate;

import org.hibernate.dialect.Oracle12cDialect;

import java.sql.Types;

/**
 * Custom Oracle dialect that fixes various issues with Oracle support in Hibernate.
 */
public class RundeckOracleDialect
        extends Oracle12cDialect
{
    public RundeckOracleDialect() {
        super();
        // Oracle cannot use CLOB/BLOB columns in SELECT DISTINCT queries (ORA-22848).
        // Disabling passDistinctThrough makes Hibernate deduplicate results in memory
        // instead of emitting SQL DISTINCT, avoiding this Oracle restriction.
        getDefaultProperties().setProperty("hibernate.query.passDistinctThrough", "false");
    }

    @Override
    protected void registerLargeObjectTypeMappings() {
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");

        registerColumnType(Types.BINARY, 2000, "raw($l)");
        registerColumnType(Types.BINARY, "blob");

        registerColumnType(Types.VARBINARY, 2000, "raw($l)");
        registerColumnType(Types.VARBINARY, "blob");

        registerColumnType(Types.LONGVARCHAR, "clob");
        registerColumnType(Types.LONGVARBINARY, "blob");
    }

    @Override
    protected void registerCharacterTypeMappings() {
        super.registerCharacterTypeMappings();
        registerColumnType(Types.VARCHAR, "varchar($l)");
    }
}