-- Create the schema if it doesn't exist
-- CREATE SCHEMA IF NOT EXISTS DEMO;

-- Create the DATA_LOG table first
CREATE TABLE IF NOT EXISTS DATA_LOG
(
    DL_ID        NUMERIC      NOT NULL AUTO_INCREMENT,
    DL_UUID      VARCHAR(36)  NOT NULL,
    DL_NAME      VARCHAR(100) NOT NULL,
    CONSTRAINT DATA_LOG_PK PRIMARY KEY (DL_ID)
);