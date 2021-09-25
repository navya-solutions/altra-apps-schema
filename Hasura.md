# Hasura's configuration and usages

### Step to configure the GraphQL Engine from the scratch

1. Install Hasura CLI

```shell
npm install --global hasura-cli
```

2. Create hasura project

```shell
hasura init
```

3. If the database is created from hasura console, use this command to sync Hasura GraphQL engine metadata( i.e.
   database, action, events etc.)

```shell
hasura md export
```

Other metadata available Commands:

```shell
apply         Apply Hasura metadata on a database
clear         Clear Hasura GraphQL engine metadata on the database
diff          (PREVIEW) Show a highlighted diff of Hasura metadata
export        Export Hasura GraphQL engine metadata from the database
inconsistency Manage inconsistent objects in Hasura metadata
reload        Reload Hasura GraphQL engine metadata on the database
```

4.

NOTE! Currently, we are using a Java project for defining, creating, maintaining and migrating project database schema.
We will investigate and migrated to Java project solution to hasura CLI databases and its migration feature  
Manual changes in the liquibase schema:

schema manual changes needed after exporting using ___mvn liquibase:generateChangeLog___
- Change all  "id" BIGINT --> "id" SERIAL 
- Remove "CREATE SEQUENCE"

5. Create migration manually

```shell
hasura migrate create altra --database-name altra-db
```

This command will create up and down migration SQL files in the migrations directory.

add the SQL statement to the up.sql file and apply the migration by running:

```shell
hasura migrate apply
```

6. Go to hasura console and apply the table and relationships tracking manually.

7. Export GraphQL engine metadata from the database to local repo:

```shell
hasura md export
```

### Step to apply the project changes to GraphQL Engine

1. Install Hasura and configure the Database connection

```shell
DisplayName: altra-db
datasoruce driver: POstgreSQL
database URL :postgres://postgres:postgrespassword@postgres:5432/postgres
```

2. Apply the migration by running:

```shell
hasura migrate apply
```

3. Go to hasura console and apply the table and relationships tracking manually.
