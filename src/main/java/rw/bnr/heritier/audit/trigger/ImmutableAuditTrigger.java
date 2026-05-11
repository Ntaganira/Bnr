/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ImmutableAuditTrigger.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Prevents modification of audit records
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.audit.trigger;

import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;

public class ImmutableAuditTrigger implements Trigger {

    @Override
    public void init(
            Connection conn,
            String schemaName,
            String triggerName,
            String tableName,
            boolean before,
            int type
    ) {
    }

    @Override
    public void fire(
            Connection conn,
            Object[] oldRow,
            Object[] newRow
    ) throws SQLException {

        throw new SQLException(
                "Audit logs are immutable and cannot be modified"
        );
    }

    @Override
    public void close() {
    }

    @Override
    public void remove() {
    }

}