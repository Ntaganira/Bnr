ALTER TABLE applications
ADD COLUMN institution_type VARCHAR(255);

ALTER TABLE applications
ADD COLUMN registration_number VARCHAR(255);

ALTER TABLE applications
ADD COLUMN business_description VARCHAR(2000);