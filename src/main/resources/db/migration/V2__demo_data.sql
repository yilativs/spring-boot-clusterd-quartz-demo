CREATE SCHEMA demo;

CREATE TABLE demo.execution (
    date_time timestamp without time zone,
    scheduler_name character varying(200) NOT NULL,
    scheduler_instance_id character varying(200) NOT NULL,
    job_group character varying(200) NOT NULL,
    job_name character varying(200) NOT NULL,
    PRIMARY KEY (date_time, scheduler_name, scheduler_instance_id,job_group, job_name)
);

