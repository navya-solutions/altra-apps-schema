schema manual changes needed after exporting using ___mvn liquibase:generateChangeLog___
1. Change all  "id" BIGINT --> "id" SERIAL
2. Change "block" JSON --> "block" JSONB
3  Remove "CREATE SEQUENCE"