CREATE TABLE IF NOT EXISTS EMPLOYEE(
    employee_id BIGINT NOT NULL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS TIME_CARD(
    time_card_uuid UUID NOT NULL PRIMARY KEY,
    is_approved BOOLEAN,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES EMPLOYEE(employee_id)
);

CREATE TABLE IF NOT EXISTS TIME_CARD_ENTRY(
    time_card_entry_uuid UUID NOT NULL PRIMARY KEY,
    start_day_time BIGINT,
    end_day_time BIGINT,
    time_card_uuid UUID,
    FOREIGN KEY (time_card_uuid) REFERENCES TIME_CARD(time_card_uuid)
);
