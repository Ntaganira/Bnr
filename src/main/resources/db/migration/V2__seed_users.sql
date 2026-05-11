INSERT INTO users (
    full_name,
    email,
    password,
    role,
    enabled
)
VALUES
(
    'System Administrator',
    'admin@nbr.rw',
    '$2a$10$LAXqT7nqXfo/kQyxqPb.u.kyH40tBT5FqryxE53f6rMpuvgVg584q',
    'ADMIN',
    true
),

(
    'Senior Reviewer',
    'reviewer@nbr.rw',
    '$2a$10$LAXqT7nqXfo/kQyxqPb.u.kyH40tBT5FqryxE53f6rMpuvgVg584q',
    'REVIEWER',
    true
),

(
    'Chief Approver',
    'approver@nbr.rw',
    '$2a$10$LAXqT7nqXfo/kQyxqPb.u.kyH40tBT5FqryxE53f6rMpuvgVg584q',
    'APPROVER',
    true
),

(
    'Bank Applicant',
    'applicant@nbr.rw',
    '$2a$10$LAXqT7nqXfo/kQyxqPb.u.kyH40tBT5FqryxE53f6rMpuvgVg584q',
    'APPLICANT',
    true
);