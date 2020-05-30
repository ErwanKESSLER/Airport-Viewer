package com.PPII.GUI;

import com.PPII.database.CreateDataBase;
import com.PPII.entities.*;
import com.PPII.exports.SelectInterestingData;
import com.PPII.repository.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SelectGui implements Initializable {
    @FXML
    private TableView table;
    @FXML
    private TextField input;

    private AirportRepository saveAirport;
    private RouteRepository saveRoute;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void fillAirportTable(AirportRepository airportRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn id = new TableColumn<Airport, String>("Id");
        id.setCellValueFactory(new PropertyValueFactory<Airport, String>("id"));
        TableColumn name = new TableColumn<Airport, String>("name");
        name.setCellValueFactory(new PropertyValueFactory<Airport, String>("name"));
        TableColumn iata = new TableColumn<Airport, String>("iata");
        iata.setCellValueFactory(new PropertyValueFactory<Airport, String>("iata"));
        TableColumn icao = new TableColumn<Airport, String>("icao");
        icao.setCellValueFactory(new PropertyValueFactory<Airport, String>("icao"));
        TableColumn latitude = new TableColumn<Airport, String>("latitude");
        latitude.setCellValueFactory(new PropertyValueFactory<Airport, String>("latitude"));
        TableColumn longitude = new TableColumn<Airport, String>("longitude");
        longitude.setCellValueFactory(new PropertyValueFactory<Airport, String>("longitude"));
        TableColumn altitude = new TableColumn<Airport, String>("altitude");
        altitude.setCellValueFactory(new PropertyValueFactory<Airport, String>("altitude"));
        TableColumn cityId = new TableColumn<Airport, String>("cityId");
        cityId.setCellValueFactory(new PropertyValueFactory<Airport, String>("cityId"));
        table.getColumns().addAll(id, name, iata, icao, latitude, longitude, altitude, cityId);

        table.getItems().addAll(airportRepository.getList());
        saveAirport = airportRepository;
    }


    private void fillRouteTable(RouteRepository routeRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn routeId = new TableColumn<Route, String>("routeId");
        routeId.setCellValueFactory(new PropertyValueFactory<Route, String>("routeId"));
        TableColumn airlineId = new TableColumn<Route, String>("airlineId");
        airlineId.setCellValueFactory(new PropertyValueFactory<Route, String>("airlineId"));
        TableColumn sourceAirportId = new TableColumn<Route, String>("sourceAirportId");
        sourceAirportId.setCellValueFactory(new PropertyValueFactory<Route, String>("sourceAirportId"));
        TableColumn destinationAirportId = new TableColumn<Route, String>("destinationAirportId");
        destinationAirportId.setCellValueFactory(new PropertyValueFactory<Route, String>("destinationAirportId"));
        TableColumn escales = new TableColumn<Route, String>("escales");
        escales.setCellValueFactory(new PropertyValueFactory<Route, String>("escales"));
        TableColumn codeshare = new TableColumn<Route, String>("codeshare");
        codeshare.setCellValueFactory(new PropertyValueFactory<Route, String>("codeshare"));
        TableColumn equipmentId = new TableColumn<Route, String>("equipementId");
        equipmentId.setCellValueFactory(new PropertyValueFactory<Route, String>("equipementId"));

        table.getColumns().addAll(routeId, airlineId, sourceAirportId, destinationAirportId, escales, codeshare, equipmentId);

        table.getItems().addAll(routeRepository.getList());
        saveRoute = routeRepository;
    }

    private void fillCityTable(LocalizationRepository localizationRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn cityId = new TableColumn<Localization, String>("cityId");
        cityId.setCellValueFactory(new PropertyValueFactory<Localization, String>("cityId"));
        TableColumn cityName = new TableColumn<Localization, String>("cityName");
        cityName.setCellValueFactory(new PropertyValueFactory<Localization, String>("cityName"));
        TableColumn countryId = new TableColumn<Localization, String>("countryId");
        countryId.setCellValueFactory(new PropertyValueFactory<Localization, String>("countryId"));
        TableColumn timezone = new TableColumn<Localization, String>("timezone");
        timezone.setCellValueFactory(new PropertyValueFactory<Localization, String>("timezone"));

        table.getColumns().addAll(cityId, cityName, countryId, timezone);
        table.getItems().addAll(localizationRepository.getList());
    }

    private void fillPlanesTable(PlaneRepository planeRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn planeId = new TableColumn<Plane, String>("planeId");
        planeId.setCellValueFactory(new PropertyValueFactory<Plane, String>("planeId"));
        TableColumn name = new TableColumn<Plane, String>("name");
        name.setCellValueFactory(new PropertyValueFactory<Plane, String>("name"));
        TableColumn iata = new TableColumn<Plane, String>("iata");
        iata.setCellValueFactory(new PropertyValueFactory<Plane, String>("iata"));
        TableColumn icao = new TableColumn<Plane, String>("icao");
        icao.setCellValueFactory(new PropertyValueFactory<Plane, String>("icao"));

        table.getColumns().addAll(planeId, name, iata, icao);

        table.getItems().addAll(planeRepository.getList());
    }

    private void fillFNtable(FlightNumberRepository flightNumberRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn id = new TableColumn<FlightNumber, String>("id");
        id.setCellValueFactory(new PropertyValueFactory<FlightNumber, String>("id"));
        TableColumn flightNumber = new TableColumn<FlightNumber, String>("flightNumber");
        flightNumber.setCellValueFactory(new PropertyValueFactory<FlightNumber, String>("flightNumber"));
        TableColumn airlineId = new TableColumn<FlightNumber, String>("airlineId");
        airlineId.setCellValueFactory(new PropertyValueFactory<Plane, String>("airlineId"));
        TableColumn routeId = new TableColumn<FlightNumber, String>("routeId");
        routeId.setCellValueFactory(new PropertyValueFactory<FlightNumber, String>("routeId"));

        table.getColumns().addAll(id, flightNumber, airlineId, routeId);

        table.getItems().addAll(flightNumberRepository.getList());
    }

    private void fillAirlineTable(AirlineRepository airlineRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn id = new TableColumn<Airline, String>("id");
        id.setCellValueFactory(new PropertyValueFactory<Airline, String>("id"));
        TableColumn name = new TableColumn<Airline, String>("name");
        name.setCellValueFactory(new PropertyValueFactory<Airline, String>("name"));
        TableColumn iata = new TableColumn<Airline, String>("iata");
        iata.setCellValueFactory(new PropertyValueFactory<Airline, String>("iata"));
        TableColumn icao = new TableColumn<Airline, String>("icao");
        icao.setCellValueFactory(new PropertyValueFactory<Airline, String>("icao"));
        TableColumn callsign = new TableColumn<Airline, String>("callsign");
        callsign.setCellValueFactory(new PropertyValueFactory<Airline, String>("callsign"));
        TableColumn countryId = new TableColumn<Airline, String>("countryId");
        countryId.setCellValueFactory(new PropertyValueFactory<Airline, String>("countryId"));
        TableColumn active = new TableColumn<Airline, String>("active");
        active.setCellValueFactory(new PropertyValueFactory<Airline, String>("active"));

        table.getColumns().addAll(id, name, iata, icao, callsign, countryId, active);
        table.getItems().addAll(airlineRepository.getList());
    }

    private void fillCountryTable(CountryRepository countryRepository) {
        table.getColumns().clear();
        table.setEditable(true);
        TableColumn countryId = new TableColumn<Countries, String>("countryId");
        countryId.setCellValueFactory(new PropertyValueFactory<Countries, String>("countryId"));
        TableColumn countryName = new TableColumn<Countries, String>("countryName");
        countryName.setCellValueFactory(new PropertyValueFactory<Countries, String>("countryName"));
        TableColumn countryRealName = new TableColumn<Countries, String>("countryRealName");
        countryRealName.setCellValueFactory(new PropertyValueFactory<Countries, String>("countryRealName"));
        TableColumn latitude = new TableColumn<Countries, String>("latitude");
        latitude.setCellValueFactory(new PropertyValueFactory<Countries, String>("latitude"));
        TableColumn longitude = new TableColumn<Countries, String>("longitude");
        longitude.setCellValueFactory(new PropertyValueFactory<Countries, String>("longitude"));


        table.getColumns().addAll(countryId, countryName, countryRealName, latitude, longitude);
        table.getItems().addAll(countryRepository.getList());
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void clearTable(ActionEvent event) {
        table.getItems().clear();
        table.getColumns().clear();
    }

    //aiport
    @FXML
    private void getAirportId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                AirportRepository airportRepository = new AirportRepository(db.getDb());
                airportRepository.findById(Integer.parseInt(text));
                fillAirportTable(airportRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllAiport(ActionEvent event) {
        CreateDataBase db = new CreateDataBase(false);
        AirportRepository airportRepository = new AirportRepository(db.getDb());
        airportRepository.findAll();
        table.getItems().clear();
        fillAirportTable(airportRepository);
        db.getDb().closeConnection();
    }

    @FXML
    private void getAiportCountry(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            CreateDataBase db = new CreateDataBase(false);
            AirportRepository airportRepository = new AirportRepository(db.getDb());
            airportRepository.findByCountry(text);
            table.getItems().clear();
            fillAirportTable(airportRepository);
            db.getDb().closeConnection();

        }
    }

    @FXML
    private void getAirportCity(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            CreateDataBase db = new CreateDataBase(false);
            AirportRepository airportRepository = new AirportRepository(db.getDb());
            airportRepository.findByCity(text);
            table.getItems().clear();
            fillAirportTable(airportRepository);
            db.getDb().closeConnection();

        }
    }

    @FXML
    private void getAirportArea(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            String[] points = text.split(";");
            for (int i = 0; i < 4; i++) {
                if (!isDouble(points[i])) {
                    return;
                }
            }

            CreateDataBase db = new CreateDataBase(false);
            AirportRepository airportRepository = new AirportRepository(db.getDb());
            airportRepository.findByArea(Double.parseDouble(points[0]), Double.parseDouble(points[1]), Double.parseDouble(points[2]), Double.parseDouble(points[3]));
            table.getItems().clear();
            fillAirportTable(airportRepository);
            db.getDb().closeConnection();
        }
    }


    @FXML
    private void getAirportRadius(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            String[] points = text.split(";");
            for (int i = 0; i < 3; i++) {
                if (!isDouble(points[i])) {
                    return;
                }
            }
            CreateDataBase db = new CreateDataBase(false);
            AirportRepository airportRepository = new AirportRepository(db.getDb());
            airportRepository.findByRadius(Double.parseDouble(points[0]), Double.parseDouble(points[1]), Double.parseDouble(points[2]));
            table.getItems().clear();
            fillAirportTable(airportRepository);
            db.getDb().closeConnection();
        }
    }


    //route
    @FXML
    private void getRouteId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                RouteRepository routeRepository = new RouteRepository(db.getDb());
                routeRepository.findById(Integer.parseInt(text));
                fillRouteTable(routeRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllRoute(ActionEvent event) {

        CreateDataBase db = new CreateDataBase(false);
        RouteRepository routeRepository = new RouteRepository(db.getDb());
        routeRepository.findAll();
        table.getItems().clear();
        fillRouteTable(routeRepository);
        db.getDb().closeConnection();

    }

    @FXML
    private void getSourceAirport(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                RouteRepository routeRepository = new RouteRepository(db.getDb());
                routeRepository.findBySourceAirport(Integer.parseInt(text));
                table.getItems().clear();
                fillRouteTable(routeRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getDestinationAirport(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                RouteRepository routeRepository = new RouteRepository(db.getDb());
                routeRepository.findByDestinationAirport(Integer.parseInt(text));
                table.getItems().clear();
                fillRouteTable(routeRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getRouteAirline(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                RouteRepository routeRepository = new RouteRepository(db.getDb());
                routeRepository.findByAirline(Integer.parseInt(text));
                table.getItems().clear();
                fillRouteTable(routeRepository);
                db.getDb().closeConnection();
            }
        }
    }

    //airline
    @FXML
    private void getAirlineId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                AirlineRepository airlineRepository = new AirlineRepository(db.getDb());
                airlineRepository.findById(Integer.parseInt(text));
                fillAirlineTable(airlineRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllAirline(ActionEvent event) {
        CreateDataBase db = new CreateDataBase(false);
        AirlineRepository airlineRepository = new AirlineRepository(db.getDb());
        airlineRepository.findAll();
        table.getItems().clear();
        fillAirlineTable(airlineRepository);
        db.getDb().closeConnection();
    }

    //country
    @FXML
    private void getCountryId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (text.length() == 3) {
                CreateDataBase db = new CreateDataBase(false);
                CountryRepository countryRepository = new CountryRepository(db.getDb());
                countryRepository.findById(text);
                fillCountryTable(countryRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllCountry(ActionEvent event) {
        CreateDataBase db = new CreateDataBase(false);
        CountryRepository countryRepository = new CountryRepository(db.getDb());
        countryRepository.findAll();
        fillCountryTable(countryRepository);
        db.getDb().closeConnection();

    }

    //planes
    @FXML
    private void getPlanesId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                EquipmentListRepository equipmentListRepository = new EquipmentListRepository(db.getDb());
                equipmentListRepository.findById(Integer.parseInt(text));
                table.getItems().clear();
                for (EquipmentList planeIds : equipmentListRepository.getList()) {
                    for (Integer plane : planeIds.getPlaneIds()) {
                        PlaneRepository planeRepository = new PlaneRepository(db.getDb());
                        planeRepository.findById(plane);
                        fillPlanesTable(planeRepository);
                    }
                }
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllPlanes(ActionEvent event) {
        table.getItems().clear();
        CreateDataBase db = new CreateDataBase(false);
        PlaneRepository planeRepository = new PlaneRepository(db.getDb());
        planeRepository.findAll();
        fillPlanesTable(planeRepository);
        db.getDb().closeConnection();
    }

    //cities
    @FXML
    private void getCitiesId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                LocalizationRepository localizationRepository = new LocalizationRepository(db.getDb());
                localizationRepository.findById(Integer.parseInt(text));
                fillCityTable(localizationRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllcities(ActionEvent event) {
        table.getItems().clear();
        CreateDataBase db = new CreateDataBase(false);
        LocalizationRepository localizationRepository = new LocalizationRepository(db.getDb());
        localizationRepository.findAll();
        fillCityTable(localizationRepository);
        db.getDb().closeConnection();
    }

    @FXML
    private void getFNid(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                FlightNumberRepository flightNumberRepository = new FlightNumberRepository(db.getDb());
                flightNumberRepository.findById(Integer.parseInt(text));
                fillFNtable(flightNumberRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getAllFN(ActionEvent event) {
        table.getItems().clear();
        CreateDataBase db = new CreateDataBase(false);
        FlightNumberRepository flightNumberRepository = new FlightNumberRepository(db.getDb());
        flightNumberRepository.findAll();
        fillFNtable(flightNumberRepository);
        db.getDb().closeConnection();
    }

    @FXML
    private void getFNbyAirline(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                table.getItems().clear();
                CreateDataBase db = new CreateDataBase(false);
                FlightNumberRepository flightNumberRepository = new FlightNumberRepository(db.getDb());
                flightNumberRepository.findByAirline(Integer.parseInt(text));
                fillFNtable(flightNumberRepository);
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void getRoutesId(ActionEvent event) {
        String text = input.getText();
        if (!text.equals("")) {
            if (isInteger(text)) {
                CreateDataBase db = new CreateDataBase(false);
                RouteListeRepository routeListeRepository = new RouteListeRepository(db.getDb());
                routeListeRepository.findById(Integer.parseInt(text));
                table.getItems().clear();
                for (RouteListe routes : routeListeRepository.getList()) {
                    for (Integer plane : routes.getAirportId()) {
                        AirportRepository airport = new AirportRepository(db.getDb());
                        airport.findById(plane);
                        fillAirportTable(airport);
                    }
                }
                db.getDb().closeConnection();
            }
        }
    }

    @FXML
    private void searchRoute() {
        String text = input.getText();
        if (!text.equals("")) {
            String[] cities = text.split(";");
            if (cities.length == 2) {
                table.getItems().clear();
                CreateDataBase db = new CreateDataBase(false);
                SelectInterestingData search = new SelectInterestingData(cities[0], cities[1]);
                RouteRepository routeRepository = search.possibleRoute(db.getDb());
                fillRouteTable(routeRepository);
                db.getDb().closeConnection();
            }
        }
    }

    public RouteRepository getSaveRoute(){
        return this.saveRoute;
    }

    public AirportRepository getSaveAirport() {
        return saveAirport;
    }
}
