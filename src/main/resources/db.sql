create database if not exists db_infyride;

use db_infyride;

drop table if exists Ride;

create table Ride(
	rideid integer primary key auto_increment,
    pickuplocation varchar(200) not null references Fare(pickuplocation),
    dropLocation varchar(200) not null references Fare(droplocation),
    ridedatetime varchar(50) not null,
    totalfare double not null,
    status varchar(50) not null,
    reasonforcancellation varchar(200) not null default 'NA'
);

drop table Ride;

create table Fare(
	fareid integer primary key,
    pickuplocation varchar(200) not null,
    droplocation varchar(200) not null,
    fare double not null
);

insert into Fare values(1,'New York, New York','Boston, Massachusetts',559.79);
insert into Fare values(2,'Portland, Oregon','Seattle, Washington',222.08);
insert into Fare values(3,'Chicago, Illinois','Detroit, Michigan',315.91);
insert into Fare values(4,'Washington Dc, Washington','Pittsburgh, Pennyslvania',296.79);
insert into Fare values(5,'Atlanta, Georgia','Columbia, South Carolina',215.34);
insert into Fare values(6,'Boston, Massachusetts','New York, New York',294.26);
insert into Fare values(7,'Seattle, Washington','Portland, Oregon',326.76);
insert into Fare values(8,'Detroit, Michigan','Chicago, Illinois',301.10);
insert into Fare values(9,'Pittsburgh, Pennyslvania','Washington Dc, Washington',296.79);
insert into Fare values(10,'Columbia, South Carolina','Atlanta, Georgia',255.02);
insert into Fare values(11,'Albany, New York','Boston, Massachusetts',198.93);
insert into Fare values(12,'Boston, Massachusetts','Albany, New York',236.76);



















