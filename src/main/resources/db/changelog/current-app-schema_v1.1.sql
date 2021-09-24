-- liquibase formatted sql

-- changeset a316916:1632500366289-1
CREATE TABLE "block" ("id" SERIAL NOT NULL, "archived" BOOLEAN NOT NULL, "block" JSONB, "created_time" BIGINT, "last_edited_time" BIGINT, "publicly_accessible" BOOLEAN NOT NULL, "ref_block_id" VARCHAR(255), "type" VARCHAR(255), "url" VARCHAR(255), "language_id" BIGINT, "topic_id" BIGINT, CONSTRAINT "block_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-2
CREATE TABLE "block_tag" ("id" SERIAL NOT NULL, "block_id" BIGINT, "topic_id" BIGINT, CONSTRAINT "block_tag_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-3
CREATE TABLE "block_change_request" ("id" SERIAL NOT NULL, "block_type" VARCHAR(255), "change_description" TEXT, "comment" VARCHAR(255), "ref_object_id" VARCHAR(255), "status" VARCHAR(255), "type" VARCHAR(255), "block_id" BIGINT, "user_id" BIGINT NOT NULL, CONSTRAINT "block_change_request_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-4
CREATE TABLE "language" ("id" SERIAL NOT NULL, "code" VARCHAR(255) NOT NULL, "name" VARCHAR(255) NOT NULL, CONSTRAINT "language_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-5
CREATE TABLE "block_revision" ("id" SERIAL NOT NULL, "block" JSONB, "revision_at" BIGINT, "revision_no" INTEGER, CONSTRAINT "block_revision_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-6
CREATE TABLE "topic" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "has_children" BOOLEAN NOT NULL, "title" VARCHAR(255), "topic_unit_title" VARCHAR(255), "curriculum_id" BIGINT, "parent_id" BIGINT, "topic_label_id" BIGINT, CONSTRAINT "topic_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-7
CREATE TABLE "vote" ("id" SERIAL NOT NULL, "voting_minus_count" BIGINT, "user_comment" VARCHAR(255), "voting_plus_count" BIGINT, "block_id" BIGINT, "user_id" BIGINT, CONSTRAINT "vote_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-8
CREATE TABLE "country" ("id" SERIAL NOT NULL, "code" VARCHAR(255) NOT NULL, "name" VARCHAR(255) NOT NULL, CONSTRAINT "country_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-9
CREATE TABLE "curriculum" ("id" SERIAL NOT NULL, "created_time" BIGINT, "description" TEXT, "last_edited_time" BIGINT, "name" VARCHAR(255) NOT NULL, "publicly_accessible" BOOLEAN NOT NULL, "ref_curriculum_id" VARCHAR(255), "short_title" VARCHAR(255) NOT NULL, "country_id" BIGINT, "institution_id" BIGINT, CONSTRAINT "curriculum_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-10
CREATE TABLE "institution" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255) NOT NULL, "ref_id" VARCHAR(255), CONSTRAINT "institution_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-11
CREATE TABLE "topic_label" ("id" SERIAL NOT NULL, "order_id" INTEGER NOT NULL, "title" VARCHAR(255) NOT NULL, "curriculum_id" BIGINT, CONSTRAINT "topic_label_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-12
CREATE TABLE "identical_topic" ("id" SERIAL NOT NULL, "type" VARCHAR(255) NOT NULL, "topic_id" BIGINT, CONSTRAINT "identical_topic_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-13
CREATE TABLE "role" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255) NOT NULL, CONSTRAINT "role_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-14
CREATE TABLE "subscription" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "discount" FLOAT8 NOT NULL, "monthly_price" FLOAT8 NOT NULL, "quarterly_price" FLOAT8 NOT NULL, "type" VARCHAR(255) NOT NULL, "yearly_price" FLOAT8 NOT NULL, CONSTRAINT "subscription_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-15
CREATE TABLE "tag" ("id" SERIAL NOT NULL, "block_id" BIGINT, "curriculum_id" BIGINT, "topic_id" BIGINT, "user_interests_id" BIGINT, CONSTRAINT "tag_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-16
CREATE TABLE "users" ("id" SERIAL NOT NULL, "avatar_url" VARCHAR(255), "bio_data" TEXT, "created_time" BIGINT, "display_name" VARCHAR(255), "email" VARCHAR(255), "last_edited_time" BIGINT, "name" VARCHAR(255), "phone_number" VARCHAR(255), "subscription_id" BIGINT, "institution_id" BIGINT, "role_id" BIGINT, CONSTRAINT "users_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-17
ALTER TABLE "vote" ADD CONSTRAINT "block_vote" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-18
ALTER TABLE "block_change_request" ADD CONSTRAINT "fka7lpq8r7bt3r8keu9duwasss" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-19
ALTER TABLE "tag" ADD CONSTRAINT "user_block_tag" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-20
ALTER TABLE "block_tag" ADD CONSTRAINT "block_block_tag" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-21
ALTER TABLE "tag" ADD CONSTRAINT "user_topic_tag" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-22
ALTER TABLE "curriculum" ADD CONSTRAINT "curriculum_country" FOREIGN KEY ("country_id") REFERENCES "country" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-23
ALTER TABLE "topic" ADD CONSTRAINT "curriculum_topic" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-24
ALTER TABLE "topic_label" ADD CONSTRAINT "curriculum_topic_label" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-25
ALTER TABLE "tag" ADD CONSTRAINT "user_curriculum_tag" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-26
ALTER TABLE "users" ADD CONSTRAINT "user_institution" FOREIGN KEY ("institution_id") REFERENCES "institution" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-27
ALTER TABLE "topic" ADD CONSTRAINT "topic_topiclabel" FOREIGN KEY ("topic_label_id") REFERENCES "topic_label" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-28
ALTER TABLE "identical_topic" ADD CONSTRAINT "topic_identical_to_another_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-29
ALTER TABLE "users" ADD CONSTRAINT "user_role" FOREIGN KEY ("role_id") REFERENCES "role" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-30
ALTER TABLE "users" ADD CONSTRAINT "user_subscription" FOREIGN KEY ("subscription_id") REFERENCES "subscription" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-31
ALTER TABLE "block" ADD CONSTRAINT "block_language" FOREIGN KEY ("language_id") REFERENCES "language" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-32
ALTER TABLE "block" ADD CONSTRAINT "block_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-33
ALTER TABLE "block_tag" ADD CONSTRAINT "block_tag_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-34
ALTER TABLE "block_change_request" ADD CONSTRAINT "user_block_change_request" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-35
ALTER TABLE "topic" ADD CONSTRAINT "fkipo6144dvb6o1rb1rgo88ut4d" FOREIGN KEY ("parent_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-36
ALTER TABLE "vote" ADD CONSTRAINT "user_vote" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-37
CREATE TABLE "curriculum_change_request" ("id" SERIAL NOT NULL, "change_description" TEXT, "comment" VARCHAR(255), "object_type" VARCHAR(255) NOT NULL, "ref_object_id" INTEGER NOT NULL, "status" VARCHAR(255), "type" VARCHAR(255), "curriculum_id" BIGINT, "user_id" BIGINT NOT NULL, CONSTRAINT "curriculum_change_request_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632500366289-38
ALTER TABLE "curriculum_change_request" ADD CONSTRAINT "curriculum_change_request" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-39
ALTER TABLE "curriculum" ADD CONSTRAINT "curriculum_institution" FOREIGN KEY ("institution_id") REFERENCES "institution" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-40
ALTER TABLE "tag" ADD CONSTRAINT "user_interests" FOREIGN KEY ("user_interests_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632500366289-41
ALTER TABLE "curriculum_change_request" ADD CONSTRAINT "user_curriculum_change_request" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

