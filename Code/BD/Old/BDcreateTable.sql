CREATE TABLE `Airports` 
(
	`id` integer,
	`name` varchar(255),
	`city` varchar(255),
	`country` varchar(255),
	`latitude` double,
	`longitude` double,
	`altitude` double,
	`timezone` double,
	`IATA` varchar(255),
	`ICAO` varchar(255)
);

CREATE TABLE `Airline` 
(
	`id` integer,
	`name` varchar(255),
	`alias` varchar(255),
	`IATA` varchar(255),
	`ICAO` varchar(255),
	`Callsign` varchar(255),
	`Country` varchar(255),
	`flagActivity` varcharacter
);

CREATE TABLE `Route` 
(
	`Airline` varchar(255),
	`AirlineId` integer,
	`sourceAirport` varchar(255),
	`sourceAirportId` integer,
	`destinationAirport` varchar(255),
	`destinationAirportId` integer,
	`shared` varcharacter,
	`equipment` varchar(255),
	`stops` integer
);

CREATE TABLE `planes` 
(
	`name` varchar(255),
	`IATA` varchar(255),
	`ICAO` varchar(255)
);

CREATE TABLE `price` 
(
	`id` integer,
	`date` datetime,
	`sourceAirportId` integer,
	`destinationAirportId` integer,
	`stops` integer,
	`escales` varchar(255),
	`prix` integer,
	`AirlineId` integer,
	`planes` varchar(255)
);

ALTER TABLE `Airline` ADD FOREIGN KEY (`IATA`) REFERENCES `Airports` (`IATA`);

ALTER TABLE `Airline` ADD FOREIGN KEY (`ICAO`) REFERENCES `Airports` (`ICAO`);

ALTER TABLE `Airline` ADD FOREIGN KEY (`id`) REFERENCES `Route` (`AirlineId`);

ALTER TABLE `Airports` ADD FOREIGN KEY (`id`) REFERENCES `Route` (`sourceAirportId`);

ALTER TABLE `Airports` ADD FOREIGN KEY (`id`) REFERENCES `Route` (`destinationAirport`);

ALTER TABLE `planes` ADD FOREIGN KEY (`name`) REFERENCES `Route` (`equipment`);

ALTER TABLE `Airports` ADD FOREIGN KEY (`id`) REFERENCES `price` (`sourceAirportId`);

ALTER TABLE `Airports` ADD FOREIGN KEY (`id`) REFERENCES `price` (`destinationAirportId`);

ALTER TABLE `Airports` ADD FOREIGN KEY (`id`) REFERENCES `price` (`escales`);

ALTER TABLE `Airline` ADD FOREIGN KEY (`id`) REFERENCES `price` (`AirlineId`);

ALTER TABLE `planes` ADD FOREIGN KEY (`name`) REFERENCES `price` (`planes`);

ALTER TABLE `Route` ADD FOREIGN KEY (`sourceAirportId`) REFERENCES `price` (`sourceAirportId`);

ALTER TABLE `Route` ADD FOREIGN KEY (`AirlineId`) REFERENCES `price` (`id`);

ALTER TABLE `Route` ADD FOREIGN KEY (`destinationAirport`) REFERENCES `price` (`destinationAirportId`);
