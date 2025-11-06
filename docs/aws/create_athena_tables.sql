CREATE EXTERNAL TABLE IF NOT EXISTS hydration_db.hydration_runs (
    run_number string,
    created_at string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
WITH SERDEPROPERTIES (
    'field.delim' = ','
)
STORED AS INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION 's3://spring-hydration-bucket/hydration-runs/'
TBLPROPERTIES (
    'classification' = 'csv',
    'skip.header.line.count' = '1'
);

CREATE EXTERNAL TABLE IF NOT EXISTS hydration_db.advisor_contracts (
    id string,
    first_name string,
    last_name string,
    license_number string,
    start_date string,
    end_date string,
    run_number string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
WITH SERDEPROPERTIES (
    'field.delim' = ','
)
STORED AS INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION 's3://spring-hydration-bucket/advisor-contracts/'
TBLPROPERTIES (
    'classification' = 'csv',
    'skip.header.line.count' = '1'
);

CREATE EXTERNAL TABLE IF NOT EXISTS hydration_db.firm_contracts (
    id string,
    name string,
    registration_number string,
    tax_id string,
    start_date string,
    end_date string,
    run_number string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
WITH SERDEPROPERTIES (
    'field.delim' = ','
)
STORED AS INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION 's3://spring-hydration-bucket/firm-contracts/'
TBLPROPERTIES (
    'classification' = 'csv',
    'skip.header.line.count' = '1'
);