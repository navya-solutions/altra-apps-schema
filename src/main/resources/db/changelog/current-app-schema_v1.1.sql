-- liquibase formatted sql

-- changeset a316916:1632834502008-1
CREATE TABLE "block" ("id" SERIAL NOT NULL, "archived" BOOLEAN NOT NULL, "block" JSONB NOT NULL, "created_time" BIGINT, "last_edited_time" BIGINT, "publicly_accessible" BOOLEAN NOT NULL, "ref_block_id" VARCHAR(255), "type" VARCHAR(255) NOT NULL, "url" VARCHAR(255), "language_id" BIGINT, "topic_id" BIGINT, CONSTRAINT "block_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-2
CREATE TABLE "block_tag" ("id" SERIAL NOT NULL, "block_id" BIGINT, "topic_id" BIGINT, CONSTRAINT "block_tag_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-3
CREATE TABLE "block_change_request" ("id" SERIAL NOT NULL, "block_type" VARCHAR(255) NOT NULL, "change_description" TEXT NOT NULL, "comment" VARCHAR(255), "created_time" BIGINT NOT NULL, "ref_object_id" VARCHAR(255) NOT NULL, "status" VARCHAR(255), "type" VARCHAR(255), "block_id" BIGINT, "user_id" BIGINT NOT NULL, CONSTRAINT "block_change_request_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-4
CREATE TABLE "language" ("id" SERIAL NOT NULL, "code" VARCHAR(255) NOT NULL, "name" VARCHAR(255) NOT NULL, CONSTRAINT "language_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-5
CREATE TABLE "block_revision" ("id" SERIAL NOT NULL, "block" JSONB NOT NULL, "revision_at" BIGINT NOT NULL, "revision_no" INTEGER NOT NULL, CONSTRAINT "block_revision_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-6
CREATE TABLE "topic" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "has_children" BOOLEAN NOT NULL, "title" VARCHAR(255) NOT NULL, "topic_unit_title" VARCHAR(255) NOT NULL, "curriculum_id" BIGINT, "parent_id" BIGINT, "topic_label_id" BIGINT, CONSTRAINT "topic_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-7
CREATE TABLE "vote" ("id" SERIAL NOT NULL, "voting_minus" BIGINT, "user_comment" VARCHAR(255), "voting_plus" BIGINT, "block_id" BIGINT, "user_id" BIGINT, CONSTRAINT "vote_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-8
CREATE TABLE "country" ("id" SERIAL NOT NULL, "code" VARCHAR(255) NOT NULL, "name" VARCHAR(255) NOT NULL, CONSTRAINT "country_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-9
CREATE TABLE "curriculum" ("id" SERIAL NOT NULL, "created_time" BIGINT, "description" TEXT, "last_edited_time" BIGINT, "name" VARCHAR(255) NOT NULL, "publicly_accessible" BOOLEAN NOT NULL, "ref_curriculum_id" VARCHAR(255), "short_title" VARCHAR(255) NOT NULL, "country_id" BIGINT, "institution_id" BIGINT, CONSTRAINT "curriculum_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-10
CREATE TABLE "institution" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255) NOT NULL, "ref_id" VARCHAR(255), CONSTRAINT "institution_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-11
CREATE TABLE "topic_label" ("id" SERIAL NOT NULL, "order_id" INTEGER NOT NULL, "title" VARCHAR(255) NOT NULL, "curriculum_id" BIGINT, CONSTRAINT "topic_label_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-12
CREATE TABLE "identical_topic" ("id" SERIAL NOT NULL, "type" VARCHAR(255) NOT NULL, "topic_id" BIGINT, CONSTRAINT "identical_topic_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-13
CREATE TABLE "users" ("id" SERIAL NOT NULL, "avatar_url" VARCHAR(255), "bio_data" TEXT, "created_time" BIGINT, "display_name" VARCHAR(255), "email" VARCHAR(255) NOT NULL, "last_edited_time" BIGINT, "name" VARCHAR(255), "phone_number" VARCHAR(255), "subscription_id" BIGINT, "institution_id" BIGINT, "role_id" BIGINT, CONSTRAINT "users_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-14
CREATE TABLE "role" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255) NOT NULL, CONSTRAINT "role_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-15
CREATE TABLE "subscription" ("id" SERIAL NOT NULL, "description" VARCHAR(255), "discount" FLOAT8 NOT NULL, "monthly_price" FLOAT8 NOT NULL, "quarterly_price" FLOAT8 NOT NULL, "type" VARCHAR(255) NOT NULL, "yearly_price" FLOAT8 NOT NULL, CONSTRAINT "subscription_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-16
CREATE TABLE "tag" ("id" SERIAL NOT NULL, "block_id" BIGINT, "curriculum_id" BIGINT, "topic_id" BIGINT, "user_interests_id" BIGINT, CONSTRAINT "tag_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-17
CREATE TABLE "user_comment" ("id" SERIAL NOT NULL, "comment" VARCHAR(255) NOT NULL, "created_time" BIGINT NOT NULL, "block_cr_id" BIGINT, "curriculum_cr_id" BIGINT, "user_id" BIGINT NOT NULL, CONSTRAINT "user_comment_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-18
CREATE TABLE "user_curriculum_access" ("id" SERIAL NOT NULL, "has_active" BOOLEAN NOT NULL, "has_verified" BOOLEAN NOT NULL, "curriculum_id" BIGINT, "user_curriculum_access_id" BIGINT, CONSTRAINT "user_curriculum_access_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-19
CREATE TABLE "user_invitation" ("id" SERIAL NOT NULL, "created_time" BIGINT, "curriculum_ids" VARCHAR(255), "invited_user_email" VARCHAR(255) NOT NULL, "topic_ids" VARCHAR(255), "user_invitation_id" BIGINT, CONSTRAINT "user_invitation_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-20
CREATE TABLE "user_topic_access" ("id" SERIAL NOT NULL, "has_active" BOOLEAN NOT NULL, "has_verified" BOOLEAN NOT NULL, "topic_id" BIGINT, "user_topic_access_id" BIGINT, CONSTRAINT "user_topic_access_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-21
ALTER TABLE "vote" ADD CONSTRAINT "block_vote" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-22
ALTER TABLE "block_change_request" ADD CONSTRAINT "fka7lpq8r7bt3r8keu9duwasss" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-23
ALTER TABLE "tag" ADD CONSTRAINT "user_block_tag" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-24
ALTER TABLE "block_tag" ADD CONSTRAINT "block_block_tag" FOREIGN KEY ("block_id") REFERENCES "block" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-25
ALTER TABLE "user_comment" ADD CONSTRAINT "user_comment_block_change_request" FOREIGN KEY ("block_cr_id") REFERENCES "block_change_request" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-26
ALTER TABLE "language" ADD CONSTRAINT "uk_5h2eb4yggd9jjo1x9kd594t02" UNIQUE ("code");

-- changeset a316916:1632834502008-27
ALTER TABLE "language" ADD CONSTRAINT "uk_g8hr207ijpxlwu10pewyo65gv" UNIQUE ("name");

-- changeset a316916:1632834502008-28
ALTER TABLE "user_topic_access" ADD CONSTRAINT "user_topic_access_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-29
ALTER TABLE "tag" ADD CONSTRAINT "user_topic_tag" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-30
ALTER TABLE "curriculum" ADD CONSTRAINT "curriculum_country" FOREIGN KEY ("country_id") REFERENCES "country" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-31
ALTER TABLE "topic" ADD CONSTRAINT "curriculum_topic" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-32
ALTER TABLE "topic_label" ADD CONSTRAINT "curriculum_topic_label" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-33
ALTER TABLE "user_curriculum_access" ADD CONSTRAINT "user_curriculum_access_curriculum" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-34
ALTER TABLE "tag" ADD CONSTRAINT "user_curriculum_tag" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-35
ALTER TABLE "users" ADD CONSTRAINT "user_institution" FOREIGN KEY ("institution_id") REFERENCES "institution" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-36
ALTER TABLE "topic" ADD CONSTRAINT "topic_topiclabel" FOREIGN KEY ("topic_label_id") REFERENCES "topic_label" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-37
ALTER TABLE "identical_topic" ADD CONSTRAINT "topic_identical_to_another_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-38
ALTER TABLE "users" ADD CONSTRAINT "uk_6dotkott2kjsp8vw4d0m25fb7" UNIQUE ("email");

-- changeset a316916:1632834502008-39
ALTER TABLE "users" ADD CONSTRAINT "user_role" FOREIGN KEY ("role_id") REFERENCES "role" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-40
ALTER TABLE "users" ADD CONSTRAINT "user_subscription" FOREIGN KEY ("subscription_id") REFERENCES "subscription" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-41
ALTER TABLE "tag" ADD CONSTRAINT "user_interests" FOREIGN KEY ("user_interests_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-42
ALTER TABLE "user_comment" ADD CONSTRAINT "user_comment_change_request" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-43
ALTER TABLE "user_curriculum_access" ADD CONSTRAINT "user_curriculum_access_user" FOREIGN KEY ("user_curriculum_access_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-44
ALTER TABLE "user_invitation" ADD CONSTRAINT "user_user_invitation" FOREIGN KEY ("user_invitation_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-45
ALTER TABLE "user_topic_access" ADD CONSTRAINT "user_topic_access_user" FOREIGN KEY ("user_topic_access_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-46
ALTER TABLE "block" ADD CONSTRAINT "block_language" FOREIGN KEY ("language_id") REFERENCES "language" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-47
ALTER TABLE "block" ADD CONSTRAINT "block_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-48
ALTER TABLE "block_tag" ADD CONSTRAINT "block_tag_topic" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-49
ALTER TABLE "block_change_request" ADD CONSTRAINT "user_block_change_request" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-50
ALTER TABLE "topic" ADD CONSTRAINT "fkipo6144dvb6o1rb1rgo88ut4d" FOREIGN KEY ("parent_id") REFERENCES "topic" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-51
ALTER TABLE "vote" ADD CONSTRAINT "user_vote" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-52
CREATE TABLE "curriculum_change_request" ("id" SERIAL NOT NULL, "change_description" TEXT NOT NULL, "comment" VARCHAR(255), "created_time" BIGINT NOT NULL, "object_type" VARCHAR(255) NOT NULL, "ref_object_id" INTEGER NOT NULL, "status" VARCHAR(255) NOT NULL, "type" VARCHAR(255) NOT NULL, "curriculum_id" BIGINT, "user_id" BIGINT NOT NULL, CONSTRAINT "curriculum_change_request_pkey" PRIMARY KEY ("id"));

-- changeset a316916:1632834502008-53
ALTER TABLE "curriculum_change_request" ADD CONSTRAINT "curriculum_change_request" FOREIGN KEY ("curriculum_id") REFERENCES "curriculum" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-54
ALTER TABLE "curriculum" ADD CONSTRAINT "curriculum_institution" FOREIGN KEY ("institution_id") REFERENCES "institution" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-55
ALTER TABLE "curriculum_change_request" ADD CONSTRAINT "requester_user_curriculum_change_request" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset a316916:1632834502008-56
ALTER TABLE "user_comment" ADD CONSTRAINT "user_comment_curriculum_change_request" FOREIGN KEY ("curriculum_cr_id") REFERENCES "curriculum_change_request" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;