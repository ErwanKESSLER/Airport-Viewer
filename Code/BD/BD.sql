CREATE TABLE `Airports`
(
  `id` number(5) PRIMARY KEY,
  `Name` varchar2(200),
  `IATA` varchar2(3),
  `ICAO` varchar2(4),
  `latitude` number(*,15),
  `longitude` number(*,15),
  `altitude` number,
  `CityId` number NOT NULL
);

CREATE TABLE `Localization`
(
  `CityId` number PRIMARY KEY,
  `CityName` varchar2(200),
  `CountryId` varchar2(3) NOT NULL,
  `Timezone` number(*,5)
);

CREATE TABLE `Country`
(
  `CountryId` varchar2(3) PRIMARY KEY,
  `CoutryName` varchar2(200)
);

CREATE TABLE `Airline`
(
  `id` integer PRIMARY KEY,
  `Name` varchar2(200),
  `Alias` varchar2(100),
  `IATA` varchar2(2),
  `ICAO` varchar2(3),
  `Callsign` varchar2(200),
  `CountryId` varchar2(3),
  `Active` varchar2(1)
);

CREATE TABLE `Route`
(
  `RouteId` number PRIMARY KEY,
  `AirlineId` number(5) NOT NULL,
  `SourceAirportId` number(5) NOT NULL,
  `DestinationAirportId` number(5) NOT NULL,
  `Escales` number,
  `Codeshare` varchar2(1),
  `EquipmentsId` number
);

CREATE TABLE `FlightNumberIATA`
(
  `IATA` varchar2(3) PRIMARY KEY,
  `FlightNumber` varchar2(10),
  `RouteId` number UNIQUE NOT NULL
);

CREATE TABLE `FlightNumberICAO`
(
  `ICAO` varchar2(4) PRIMARY KEY,
  `FlightNumber` varchar2(10),
  `RouteId` number UNIQUE NOT NULL
);

CREATE TABLE `planes`
(
  `planeID` number PRIMARY KEY,
  `IATA` varchar2(3),
  `ICAO` varchar2(4),
  `Name` varchar2(200)
);

CREATE TABLE `RouteList`
(
  `RouteId` number PRIMARY KEY,
  `AirportId` number(5),
  `RouteIdNext` number UNIQUE NOT NULL
);

CREATE TABLE `EquipmentList`
(
  `EquipmentId` number PRIMARY KEY,
  `planeID` number NOT NULL,
  `EquipmentNext` number UNIQUE NOT NULL
);

ALTER TABLE `Airports` ADD FOREIGN KEY (`CityId`) REFERENCES `Localization` (`CityId`);

ALTER TABLE `Localization` ADD FOREIGN KEY (`CountryId`) REFERENCES `Country` (`CountryId`);

ALTER TABLE `Airline` ADD FOREIGN KEY (`CountryId`) REFERENCES `Country` (`CountryId`);

ALTER TABLE `FlightNumberIATA` ADD FOREIGN KEY (`IATA`) REFERENCES `Airline` (`IATA`);

ALTER TABLE `FlightNumberICAO` ADD FOREIGN KEY (`ICAO`) REFERENCES `Airline` (`ICAO`);

ALTER TABLE `Route` ADD FOREIGN KEY (`AirlineId`) REFERENCES `Airline` (`id`);

ALTER TABLE `EquipmentList` ADD FOREIGN KEY (`EquipmentId`) REFERENCES `Route` (`EquipmentsId`);

ALTER TABLE `EquipmentList` ADD FOREIGN KEY (`EquipmentId`) REFERENCES `EquipmentList` (`EquipmentNext`);

ALTER TABLE `Route` ADD FOREIGN KEY (`SourceAirportId`) REFERENCES `Airports` (`id`);

ALTER TABLE `Route` ADD FOREIGN KEY (`DestinationAirportId`) REFERENCES `Airports` (`id`);

ALTER TABLE `RouteList` ADD FOREIGN KEY (`AirportId`) REFERENCES `Airports` (`id`);

ALTER TABLE `FlightNumberIATA` ADD FOREIGN KEY (`RouteId`) REFERENCES `RouteList` (`RouteId`);

ALTER TABLE `FlightNumberICAO` ADD FOREIGN KEY (`RouteId`) REFERENCES `RouteList` (`RouteId`);

ALTER TABLE `RouteList` ADD FOREIGN KEY (`RouteIdNext`) REFERENCES `RouteList` (`RouteId`);

ALTER TABLE `Route` ADD FOREIGN KEY (`RouteId`) REFERENCES `FlightNumberIATA` (`RouteId`);

ALTER TABLE `Route` ADD FOREIGN KEY (`RouteId`) REFERENCES `FlightNumberICAO` (`RouteId`);

ALTER TABLE `EquipmentList` ADD FOREIGN KEY (`planeID`) REFERENCES `planes` (`planeID`);
