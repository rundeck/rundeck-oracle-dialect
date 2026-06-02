package org.rundeck.hibernate;

import org.hibernate.resource.jdbc.spi.StatementInspector;

import java.util.regex.Pattern;

/**
 * Strips DISTINCT from all SELECT statements before they reach Oracle.
 *
 * Oracle rejects SELECT DISTINCT when any selected column is a CLOB or BLOB (ORA-22848).
 * Hibernate's HQL DISTINCT maps to SQL DISTINCT by default, which triggers this Oracle
 * restriction on entity queries that include large-object columns like workflow_json.
 *
 * This inspector rewrites "SELECT DISTINCT" → "SELECT", delegating deduplication to
 * Hibernate's in-memory result transformer, which is the same behavior as setting
 * hibernate.query.passDistinctThrough=false per query.
 */
public class OracleDistinctStatementInspector implements StatementInspector {

    private static final Pattern SELECT_DISTINCT =
            Pattern.compile("(?i)\\bselect\\s+distinct\\b");

    @Override
    public String inspect(String sql) {
        if (sql == null) return null;
        return SELECT_DISTINCT.matcher(sql).replaceAll("select");
    }
}
