# --- !Ups

create table "suppliers" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR(254) NOT NULL,"desc" VARCHAR(254) NOT NULL);

create table "room" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"room" VARCHAR(254) NOT NULL);
create table "message" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"message" VARCHAR(254) NOT NULL, "date" TIMESTAMP NOT NULL, "room_id" INT NOT NULL);


create table "event" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"desc" VARCHAR(254) NOT NULL,"date_init" VARCHAR(254) NOT NULL,"date_end" VARCHAR(254) NOT NULL);


create table "participation" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR(254) NOT NULL,"last_name" VARCHAR(254) NOT NULL,"rut" VARCHAR(254) NOT NULL,"event_id" INTEGER(50) ,"disp_id" INTEGER(50),"career" VARCHAR(254) NOT NULL,"date" VARCHAR(254) NOT NULL,"sender" VARCHAR(254) NOT NULL,"tui_id" INTEGER(50) NOT NULL,"folio" INTEGER(50), "participated" INTEGER(1) NOT NULL );
create table "user" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"tui_id" INTEGER(50) NOT NULL,"name" VARCHAR(254) NOT NULL,"last_name" VARCHAR(254) NOT NULL,"rut" VARCHAR(254) NOT NULL,"career" VARCHAR(254) NOT NULL);


# --- !Downs
;
drop table "suppliers";

drop table "room";

drop table "message";

drop table "participation";
drop table "event";
drop table "users";