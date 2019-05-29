CREATE TABLE "user"
(
	"id" SERIAL NOT NULL,
	"username"	VARCHAR(50) NOT NULL,
	"email"	VARCHAR(512) NOT NULL,
	"password"	VARCHAR(50) NOT NULL,
	"first_name" VARCHAR(20) NOT NULL,
	"last_name"  VARCHAR(20) NOT NULL,
	"city"	VARCHAR(20) NOT NULL,
	"phone" VARCHAR(11) NOT NULL,
	"is_active"	BOOLEAN NOT NULL,
	CONSTRAINT pk_user_id PRIMARY KEY ("id"),
	CONSTRAINT uk_email UNIQUE ("email"),
	CONSTRAINT uk_phone UNIQUE ("phone")
);

CREATE TABLE "book"
(
	"id" SERIAL NOT NULL,
	"name"	VARCHAR(50) NOT NULL,
	"autor"	VARCHAR(50) DEFAULT 'Unknow',
	"genre"	VARCHAR(50) NOT NULL,
	"year"  DATE NOT NULL,
	"description" VARCHAR(50) DEFAULT 'Not provaided',
	CONSTRAINT pk_book_id PRIMARY KEY ("id")
);

CREATE TABLE "announce_board"
(
  "id" SERIAL NOT NULL,
  "user_id" INTEGER NOT NULL,
  "book_id" INTEGER NOT NULL,
  "announce_timestamp" TIMESTAMP ,
  CONSTRAINT pk_announce_board_id PRIMARY KEY ("id"),
  CONSTRAINT fk_user_id FOREIGN KEY("user_id") 
  REFERENCES "user"("id"),
  CONSTRAINT fk_book_id FOREIGN KEY("book_id") 
  REFERENCES "book"("id")
);