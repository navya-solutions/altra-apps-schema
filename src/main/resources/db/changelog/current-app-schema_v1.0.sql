-- liquibase formatted sql

-- changeset a316916:1631699988721-1
CREATE TABLE "block_aud" ("id" SERIAL NOT NULL, "rev" INTEGER NOT NULL, "revtype" SMALLINT, "voting_minus_count" BIGINT, "archived" BOOLEAN, "block" JSONB, "block_type" VARCHAR(255), "created_time" BIGINT, "has_public_access" BOOLEAN, "last_edited_time" BIGINT, "ref_block_pid" VARCHAR(255), "url" VARCHAR(255), "voting_plus_count" BIGINT, CONSTRAINT "block_aud_pkey" PRIMARY KEY ("id", "rev"));

-- changeset a316916:1631699988721-2
CREATE TABLE "block" ("id" SERIAL NOT NULL, "voting_minus_count" BIGINT, "archived" BOOLEAN NOT NULL, "block" JSONB, "block_type" VARCHAR(255), "created_time" BIGINT, "has_public_access" BOOLEAN NOT NULL, "last_edited_time" BIGINT, "ref_block_pid" VARCHAR(255), "url" VARCHAR(255), "voting_plus_count" BIGINT, "language_id" BIGINT, "topic_id" BIGINT, CONSTRAINT "block_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-3
CREATE TABLE "block_tag" ("id" SERIAL NOT NULL, "topic_ref_pid" VARCHAR(255), "topic_title" VARCHAR(255), "block_id" BIGINT, CONSTRAINT "block_tag_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-4
CREATE TABLE "block_change_request" ("id" SERIAL NOT NULL, "change_description" VARCHAR(255), "change_request_block_type" VARCHAR(255), "change_request_status_type" VARCHAR(255), "change_request_type" VARCHAR(255), "ref_id" VARCHAR(255), "user_ref_id" VARCHAR(255), "block_id" BIGINT, CONSTRAINT "block_change_request_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-5
CREATE TABLE "language" ("id" SERIAL NOT NULL, "short_code" VARCHAR(255), "title" VARCHAR(255), CONSTRAINT "language_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-6
CREATE TABLE "topic" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "has_children" BOOLEAN NOT NULL, "label" VARCHAR(255), "same_as" VARCHAR(255), "similar_to" VARCHAR(255), "title" VARCHAR(255), "topic_unit_title" VARCHAR(255), "curriculum_id" BIGINT, "parent_id" BIGINT, "topic_label_id" BIGINT, CONSTRAINT "topic_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-7
CREATE TABLE "country" ("id" SERIAL NOT NULL, "iso3short_code" VARCHAR(255), "name" VARCHAR(255), CONSTRAINT "country_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-8
CREATE TABLE "curriculum" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "has_public_access" BOOLEAN NOT NULL, "ref_curriculum_pid" VARCHAR(255), "short_title" VARCHAR(255), "title" VARCHAR(255), "country_id" BIGINT, "institution_id" BIGINT, CONSTRAINT "curriculum_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-9
CREATE TABLE "institution" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255), "ref_id" VARCHAR(255), CONSTRAINT "institution_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-10
CREATE TABLE "topic_label" ("id" SERIAL NOT NULL, "sequence" INTEGER, "title" VARCHAR(255), "curriculum_id" BIGINT, CONSTRAINT "topic_label_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-11
CREATE TABLE "role" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255), CONSTRAINT "role_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-12
CREATE TABLE "users" ("id" SERIAL NOT NULL, "display_name" VARCHAR(255), "name" VARCHAR(255), "subscription_id" BIGINT NOT NULL, "institution_id" BIGINT, "role_id" BIGINT NOT NULL, CONSTRAINT "users_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-13
CREATE TABLE "language_aud" ("id" SERIAL NOT NULL, "rev" INTEGER NOT NULL, "revtype" SMALLINT, "short_code" VARCHAR(255), "title" VARCHAR(255), CONSTRAINT "language_aud_pkey" PRIMARY KEY ("id", "rev"));

-- changeset a316916:1631699988721-14
CREATE TABLE "revinfo" ("rev" INTEGER NOT NULL, "revtstmp" BIGINT, CONSTRAINT "revinfo_pkey" PRIMARY KEY ("rev"));

-- changeset a316916:1631699988721-15
CREATE TABLE "tag" ("id" SERIAL NOT NULL, "name" VARCHAR(255), "level_interests_id" BIGINT, "topic_interests_id" BIGINT, CONSTRAINT "tag_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-16
CREATE TABLE "subscription_type" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255), CONSTRAINT "subscription_type_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-17
ALTER TABLE "block_change_request" ADD CONSTRAINT "fka7lpq8r7bt3r8keu9duwasss" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-18
ALTER TABLE "block_tag" ADD CONSTRAINT "block_block_tag" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-19
ALTER TABLE "curriculum" ADD CONSTRAINT "curriculum_country" FOREIGN KEY ("country_id") REFERENCES "country" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-20
ALTER TABLE "topic" ADD CONSTRAINT "curriculum_topic" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-21
ALTER TABLE "topic_label" ADD CONSTRAINT "curriculum_topic_label" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-22
ALTER TABLE "users" ADD CONSTRAINT "fk4qu1gr772nnf6ve5af002rwya" FOREIGN KEY ("role_id") REFERENCES "role" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-23
ALTER TABLE "users" ADD CONSTRAINT "fkes3l5tviwmnu2d0gy350kdfht" FOREIGN KEY ("institution_id") REFERENCES "institution" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-24
ALTER TABLE "block_aud" ADD CONSTRAINT "fkndsyvqjax1wwk4gb3xjlilc6o" FOREIGN KEY ("rev") REFERENCES "revinfo" ("rev") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-25
ALTER TABLE "block" ADD CONSTRAINT "block_language" FOREIGN KEY ("language_id") REFERENCES "language" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-26
ALTER TABLE "block" ADD CONSTRAINT "block_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-27
ALTER TABLE "topic" ADD CONSTRAINT "fke679b6a4edyobvprraj7mjoih" FOREIGN KEY ("topic_label_id") REFERENCES "topic_label" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-28
ALTER TABLE "topic" ADD CONSTRAINT "fkipo6144dvb6o1rb1rgo88ut4d" FOREIGN KEY ("parent_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-29
CREATE TABLE "curriculum_change_request" ("id" SERIAL NOT NULL, "change_description" VARCHAR(255), "change_request_object_type" VARCHAR(255), "change_request_status_type" VARCHAR(255), "change_request_type" VARCHAR(255), "ref_id" VARCHAR(255), "user_ref_id" VARCHAR(255), "curriculum_id" BIGINT, CONSTRAINT "curriculum_change_request_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1631699988721-30
ALTER TABLE "curriculum_change_request" ADD CONSTRAINT "curriculum_change_request" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-31
ALTER TABLE "curriculum" ADD CONSTRAINT "curriculum_institution" FOREIGN KEY ("institution_id") REFERENCES "institution" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-32
ALTER TABLE "users" ADD CONSTRAINT "fklyno40pkyawttif0n60i04772" FOREIGN KEY ("subscription_id") REFERENCES "subscription_type" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-33
ALTER TABLE "language_aud" ADD CONSTRAINT "fkadkpre8edt1b93q10c2us67in" FOREIGN KEY ("rev") REFERENCES "revinfo" ("rev") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-34
ALTER TABLE "tag" ADD CONSTRAINT "fkeyprhq1kxbxqna036txwl3fyc" FOREIGN KEY ("topic_interests_id") REFERENCES "tag" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1631699988721-35
ALTER TABLE "tag" ADD CONSTRAINT "fkrhdmb044knqxknwjjfgeqnxcy" FOREIGN KEY ("level_interests_id") REFERENCES "tag" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;