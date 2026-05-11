INSERT INTO applications (
    institution_name,
    application_number,
    status,
    applicant_id,
    submitted_at,
    version
)
VALUES
(
    'Kigali Commercial Bank',
    'APP-2026-001',
    'SUBMITTED',
    4,
    CURRENT_TIMESTAMP,
    0
),

(
    'Rwanda Capital Finance',
    'APP-2026-002',
    'UNDER_REVIEW',
    4,
    CURRENT_TIMESTAMP,
    0
);