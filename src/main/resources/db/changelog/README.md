schema manual changes needed after exporting using ___mvn liquibase:generateChangeLog___
1. Change all  "id" BIGINT --> "id" SERIAL
2.  Remove "CREATE SEQUENCE"