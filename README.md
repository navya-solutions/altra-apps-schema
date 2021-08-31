# Altra Apps Schema

## Requirements

Building the API client library requires:

1. OpenJDK 16+
2. Maven 3.5+
3. Docker

## Installation

To install the application to your local Maven repository, simply execute:

```shell
mvn clean install
```

## Getting Started

1. Go to docker-compose folder and run `docker-compose up` to start docker containers for:
    1. postgres DB
    2. Hasura (graphql-engine)
    3. Mongo DB
    4. Mongo express docker instances
2. run `mvn spring-boot:run` from console to start the application Or use IDE and run
   the `AltraAppsSchemaApplication.java`
3. For MongoDB configuration, use `http://localhost:8081/` to create altra_apps_schema database for application
4. For Hasura configuration, use `http://localhost:8080/console/` to
    1. Connect Database:
        1. Goto Hasura console, Data tab->Connect Database option to configure postgres DB
            1. Database Display Name: <any name>
            2. Data Source Driver: PostgreSQL
            3. Connect Database Via: Database URL
            4. Database URL: postgres://postgres:postgrespassword@postgres:5432/postgres
               ![hasura-data-source-configuration](setup/images/hasura-data-source setup.PNG)
    2. Java application should be started to create DB structure and populate test data
    3. Hasura tables tracking configuration,goto Hasura console, select public schema and press trackAll button
       ![data-tracking-configuration](setup/images/hasura-data-tracking-config.PNG)
    4. Hasura tables relationship tracking configuration
       ![data-relationship-tracking-configuration](setup/images/data-relationship-tracking-configuration.PNG)

When the application runs successfully

1. Test data (defined in `ProjectTestDataLoader.java`) is created in postgresDB
2. Data model Json file is generated in console logs

#### PostgresDB configuration

![Access postgresDB from local server](setup/images/postgres-access-config-settings.PNG)

## Curriculum Data Modeling

### Curriculum data modeling with topicLabels, topics, levels and nested units

In this model, curriculum is defined for the fixed set of topicLabels. TopicLabels are the logical separation for
different topics and levels. Units are defined as combination of topic and level. We can have nested units which can be
connected with nested blocks.
![Curriculum data modeling](setup/images/curriculum-data-modeling-option1.png)

#### json example

```json
{
  "title": "MATHS-NATIONAL_5",
  "childUnits": [
    {
      "title": "Geometry-Easy",
      "childUnits": [],
      "topic": {
        "title": "Geometry",
        "topicLabel": "Section"
      },
      "level": {
        "title": "Easy",
        "topicLabel": "Section"
      }
    },
    {
      "title": "Algebra-Medium",
      "childUnits": [
        {
          "title": "Pythagoras-Easy",
          "childUnits": [],
          "topic": {
            "title": "Pythagoras",
            "topicLabel": "Section"
          },
          "level": {
            "title": "Easy",
            "topicLabel": "Section"
          }
        },
        {
          "title": "Circles-Easy",
          "childUnits": [],
          "topic": {
            "title": "Circles",
            "topicLabel": "Section"
          },
          "level": {
            "title": "Easy",
            "topicLabel": "Section"
          }
        }
      ],
      "topic": {
        "title": "Algebra",
        "topicLabel": "Section"
      },
      "level": {
        "title": "Medium",
        "topicLabel": "Section"
      }
    }
  ],
  "topic": {
    "title": "MATHS",
    "topicLabel": "Subject"
  },
  "level": {
    "title": "NATIONAL_5",
    "topicLabel": "Subject"
  }
}
```

### Curriculum data modeling with nested topics only

In this model, curriculum is defined with nested topics. Each topic has title and label, title defines the topic name
while label defines the logical separation for different topics. We can have nested topics and those topics can be
connected with nested blocks.
![Curriculum data modeling](setup/images/curriculum-data-modeling-option2.png)

#### json example

```json
{
  "topics": [
    {
      "title": "Maths",
      "label": "Subject",
      "hasChildren": true,
      "children": [
        {
          "title": "NATIONAL_5",
          "label": "Section",
          "hasChildren": true,
          "children": [
            {
              "title": "Statistics",
              "label": "Part",
              "hasChildren": true,
              "children": [
                {
                  "title": "Circles",
                  "label": "Key Issue Level",
                  "hasChildren": false,
                  "children": []
                },
                {
                  "title": "Pythagoras",
                  "label": "Key Issue Level",
                  "hasChildren": false,
                  "children": []
                }
              ]
            },
            {
              "title": "Geometry",
              "label": "Part",
              "hasChildren": false,
              "children": []
            },
            {
              "title": "Algebra",
              "label": "Part",
              "hasChildren": false,
              "children": []
            }
          ]
        },
        {
          "title": "HIGHER",
          "label": "Section",
          "hasChildren": false,
          "children": []
        },
        {
          "title": "Avd. HIGHER",
          "label": "Section",
          "hasChildren": false,
          "children": []
        }
      ]
    },
    {
      "title": "History",
      "label": "Subject",
      "hasChildren": false,
      "children": []
    },
    {
      "title": "Science",
      "label": "Subject",
      "hasChildren": false,
      "children": []
    }
  ]
}
```