# Changelog

All notable changes to this project will be documented in this file.

## [1.2.2] - 2021-07-21

### Changed

- Change Postgres version to 42.2.23.

### Fixed

- Add globally trusted CAs to truststore.

## [1.2.1] - 2021-06-29

### Added

- Add timestamp to route error log messages.

### Changed

- Change Camel Spring Boot version to 3.11.0.

## [1.2.0] - 2021-06-28

### Added

- Log errors during route execution to the Configuration Manager.

### Changed

- Change Spring Boot version to 2.5.2.

## [1.1.1] - 2021-06-18

### Changed

- Change Postgres version to 42.2.22.

### Fixed 

- Remove custom connection manager for HTTP component, to allow using HTTP URLs.

## [1.1.0] - 2021-06-14

### Added

- Add custom truststore for Camel HTTP component (configurable via `application.properties`).

### Changed

- Change Spring Boot version to 2.5.1.
- Change Postgres version to 42.2.21.

### Removed

- RollingFileAppender in `log4j2.xml`.

## [1.0.1] - 2021-06-09

### Changed

- Change Camel Spring Boot version to 3.10.0.
- Change Spring Boot version to 2.5.0.

## [1.0.0] - 2021-04-30

### Added

- Setup a basic Spring Boot application.
- Integrate Camel Springboot starter and component starters (HTTP, SQL, Paho, File) v3.9.0.
- Provide REST endpoint for adding and removing Camel routes and Spring beans.
- Add basic authentication for the backend API.
- Provide example routes.
