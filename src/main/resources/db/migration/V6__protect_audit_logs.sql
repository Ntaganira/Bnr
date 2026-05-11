CREATE TRIGGER prevent_audit_update
BEFORE UPDATE
ON audit_logs
FOR EACH ROW
CALL "rw.bnr.heritier.audit.trigger.ImmutableAuditTrigger";