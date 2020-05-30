package com.PPII.GUI;

import com.PPII.database.CreateDataBase;
import com.PPII.database.PopulateDataBase;
import com.PPII.entities.Airport;
import com.PPII.entities.Route;
import com.PPII.exports.JSONexport;
import com.PPII.repository.AirportRepository;
import com.PPII.repository.RouteRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class DatabaseGui implements Initializable {
    @FXML
    private Circle connectLed;
    @FXML
    private Circle createLed;
    @FXML
    private Button connectDatabase;
    @FXML
    private Button createDatabase;
    @FXML
    private ImageView imageView;
    private static final TextArea ta = new TextArea();
    @FXML
    private TextArea console = new TextArea();
    private CreateDataBase db;
    private RouteRepository savedRoute = null;
    private AirportRepository savedAirport = null;
    private SelectGui selectGui = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setConnectLed();
        setCreateLed();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1000);
        URL input = this.getClass().getClassLoader().getResource("equirectangular.png");
        if (input == null) {
            System.out.println("Error loading the image");
            return;
        }
        Image image = new Image(input.toString(), this.imageView.getFitWidth(), this.imageView.getFitHeight(), true, true);
        this.imageView.setImage(image);
    }

    @FXML
    private void test(ActionEvent event) {
        DatabaseGui.println("lollolol");
    }

    @FXML
    private void connect(ActionEvent event) {
        if (!connectDatabase.getText().equals("Disconnect of Database")) {
            connectDatabase.setText("Disconnect of Database");
            connectLed.setFill(Color.GREEN);
            connectLed.setEffect(null);
            Platform.runLater(() -> this.db = new CreateDataBase(false));
        } else {
            setConnectLed();
            this.db.getDb().closeConnection();
            this.db = null;
        }

    }

    @FXML
    private void create(ActionEvent event) {


        if (this.db == null) {
            createDatabase.setText("Waiting for connection");
            createLed.setFill(Color.ORANGE);
            createLed.setEffect(null);
            return;
        }
        if (!createDatabase.getText().equals("Drop Database")) {
            createDatabase.setText("Drop Database");
            createLed.setFill(Color.GREEN);
            createLed.setEffect(null);
            this.db.getDb().closeConnection();
            db = new CreateDataBase(false, true, false, false);
            (new PopulateDataBase(db, false)).run();
            this.db.getDb().closeConnection();
            db = new CreateDataBase(false, false, true, false);
        } else {
            this.db.getDb().closeConnection();
            db = new CreateDataBase(true, false, false, false);
            this.db.getDb().closeConnection();
            DatabaseGui.println("Database Dropped");
            setCreateLed();
        }
    }

    @FXML
    private void generateJSON(ActionEvent event){
        println("Starting the JSON export");
        this.db = new CreateDataBase(false);
        Platform.runLater(() ->new JSONexport(db.getDb()));

        println("JSON export done");
    }

    private void setConnectLed() {
        connectDatabase.setText("Connect to Database");
        Shadow shadow = new Shadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.RED);
        connectLed.setEffect(shadow);
    }

    private void setCreateLed() {
        createDatabase.setText("Create Database");
        Shadow shadow = new Shadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.RED);
        createLed.setEffect(shadow);
    }

    public void update() {
        this.console.setText(ta.getText());
        this.console.setWrapText(true);
    }

    public static void println(String s) {
        Platform.runLater(new Runnable() {//in case you call from other thread
            @Override
            public void run() {
                ta.setText(ta.getText() + s + "\n");
                if (ta.getText().split("\n").length > 10) {

                    String[] temp = ta.getText().split("\n");
                    ta.setText("");
                    for (int i = 10; i > 0; i--) {
                        ta.setText(ta.getText() + temp[temp.length - i] + "\n");
                    }
                }
                System.out.println(s);//for echo if you want
            }
        });


    }

    public static void println(int s) {
        Platform.runLater(new Runnable() {//in case you call from other thread
            @Override
            public void run() {
                ta.setText(ta.getText() + (char) s + "\n");
            }
        });
    }

    @FXML
    private void selectPress(ActionEvent event) throws Exception {
        try {
            URL file = getClass().getClassLoader().getResource("select.fxml");
            if (file == null) {
                throw new Error("Missing the GUI");
            }
            FXMLLoader fxmlLoader = new FXMLLoader(file);
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            this.selectGui = fxmlLoader.getController();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayAirport() {
        if (this.selectGui != null) {

            this.savedAirport = this.selectGui.getSaveAirport();
            URL input = this.getClass().getClassLoader().getResource("equirectangular.png");
            if (input == null) {
                System.out.println("Error loading the image");
                return;
            }
            Image image = new Image(input.toString(), this.imageView.getFitWidth(), this.imageView.getFitHeight(), true, true);
            WritableImage w = new WritableImage((int) image.getWidth(), (int) image.getHeight());
            PixelWriter writer = w.getPixelWriter();

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    //Retrieving the color of the pixel of the loaded image
                    Color color = image.getPixelReader().getColor(x, y);
                    //Setting the color to the writable image
                    writer.setColor(x, y, color.darker());
                }
            }
            for (Airport airport : this.savedAirport.getList()) {
                double xx = Math.cos(Math.toRadians(-1)) * airport.getLongitude() / 180;
                double yy = (airport.getLatitude() + 1) / 90;
                int x = (int) Math.round((xx * 0.997 + 1) * image.getWidth() / 2);
                int y = (int) Math.round((-1 * yy * 0.997 + 1) * image.getHeight() / 2);

                for (int i = -2; i < 2; i++) {
                    for (int j = -2; j < 2; j++) {
                        if (Math.abs(i) + Math.abs(j) <= 2) {
                            if (x + i >= 0 && x + i < image.getWidth() && j + y >= 0 && j + y < image.getHeight()) {
                                writer.setColor(x + i, y + j, Color.RED);
                            }
                        }
                    }
                }

            }
            this.imageView.setImage(w);
        }


    }


    @FXML
    private void displayRoute() {
        if (this.selectGui != null) {

            this.savedRoute = this.selectGui.getSaveRoute();
            URL input = this.getClass().getClassLoader().getResource("equirectangular.png");
            if (input == null) {
                System.out.println("Error loading the image");
                return;
            }
            Image image = new Image(input.toString(), this.imageView.getFitWidth(), this.imageView.getFitHeight(), true, true);
            WritableImage w = new WritableImage((int) image.getWidth(), (int) image.getHeight());
            PixelWriter writer = w.getPixelWriter();

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    //Retrieving the color of the pixel of the loaded image
                    Color color = image.getPixelReader().getColor(x, y);
                    //Setting the color to the writable image
                    writer.setColor(x, y, color.darker());
                }
            }
            this.db = new CreateDataBase(false);
            for (Route route : this.savedRoute.getList()) {
                AirportRepository airportRepository1 = new AirportRepository(this.db.getDb());
                airportRepository1.findById(route.getSourceAirportId());
                Airport airport1 = airportRepository1.getList().get(0);
                double xx1 = Math.cos(Math.toRadians(-1)) * airport1.getLongitude() / 180;
                double yy1 = (airport1.getLatitude() + 1) / 90;
                int x1 = (int) Math.round((xx1 * 0.997 + 1) * image.getWidth() / 2);
                int y1 = (int) Math.round((-1 * yy1 * 0.997 + 1) * image.getHeight() / 2);
                AirportRepository airportRepository2 = new AirportRepository(db.getDb());
                airportRepository2.findById(route.getDestinationAirportId());
                Airport airport2 = airportRepository2.getList().get(0);
                double xx2 = Math.cos(Math.toRadians(-1)) * airport2.getLongitude() / 180;
                double yy2 = (airport2.getLatitude() + 1) / 90;
                int x2 = (int) Math.round((xx2 * 0.997 + 1) * image.getWidth() / 2);
                int y2 = (int) Math.round((-1 * yy2 * 0.997 + 1) * image.getHeight() / 2);
                int d = 0;

                int dx = Math.abs(x2 - x1);
                int dy = Math.abs(y2 - y1);

                int dx2 = 2 * dx; // slope scaling factors to
                int dy2 = 2 * dy; // avoid floating point

                int ix = x1 < x2 ? 1 : -1; // increment direction
                int iy = y1 < y2 ? 1 : -1;

                int x = x1;
                int y = y1;

                if (dx >= dy) {
                    while (true) {
                        writer.setColor(x, y, Color.RED);
                        if (x == x2)
                            break;
                        x += ix;
                        d += dy2;
                        if (d > dx) {
                            y += iy;
                            d -= dx2;
                        }
                    }
                } else {
                    while (true) {
                        writer.setColor(x, y, Color.RED);
                        if (y == y2)
                            break;
                        y += iy;
                        d += dx2;
                        if (d > dy) {
                            x += ix;
                            d -= dy2;
                        }
                    }
                }


            }
            this.imageView.setImage(w);
            this.db.getDb().closeConnection();
            this.db = null;
        }

    }

}
