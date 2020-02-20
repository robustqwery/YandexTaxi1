package main.java.yandex.quest;

import main.java.yandex.quest.City.Builder;
import main.java.yandex.quest.City.City;
import main.java.yandex.quest.City.Objects.Area;
import main.java.yandex.quest.City.Objects.Driver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Класс описывает приложение JavaFX которое отображает макет текущего города.
 * @author Кочетов А.В., Трифонов Н.В.
 * @version 1.0
 */
public class CityMap extends Application {
    /**Определяет масштабирование объектов.*/
    private static final int SCALE_DEFAULT = 20;
    /**Город.*/
    City city;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        city = new Builder("/home/alexey/IdeaProjects/maven-hello-world/YandexQuest-2/test.txt").getCity();

        Canvas canvas = createCanvasGrid(city.getCity_size()[0]*SCALE_DEFAULT,city.getCity_size()[1]*SCALE_DEFAULT,false);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (Driver driver:city.getDrivers()) {
            drawDriver(gc,driver);
        }

        for (Area area:city.getAreas()) {
            drawArea(gc,area);
        }

        Pane root = new Pane();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.sizeToScene();

        stage.setResizable(false);

        stage.setTitle("JavaFX Yandex Quest");

        stage.show();
    }

    /**
     * Метод отображает на карте водителя.
     * @param gc графический контекст.
     * @param driver водитель.
     */
    private void drawDriver(GraphicsContext gc, Driver driver){
        gc.fillRect(SCALE_DEFAULT*driver.getX0(),SCALE_DEFAULT*driver.getY0(),SCALE_DEFAULT,SCALE_DEFAULT);
        gc.stroke();
    }

    /**
     * Метод отображает на карте зону повышенного спроса.
     * @param gc графический контекст.
     * @param area зона повышенного спроса.
     */
    private void drawArea(GraphicsContext gc, Area area){
        gc.setFill(Color.YELLOW);
        gc.fillRect(
                SCALE_DEFAULT*area.getX0(),
                SCALE_DEFAULT*area.getY0(),
                SCALE_DEFAULT*Math.abs(area.getX0() - area.getX1()),
                SCALE_DEFAULT*Math.abs(area.getY0() - area.getY1())
        );
        gc.stroke();
        gc.setFill(Color.BLACK);
    }

    /**
     * Метод создаёт сетку города.
     * @param width ширина.
     * @param height высота.
     * @param sharp углы.
     * @return объект Canvas.
     */
    private Canvas createCanvasGrid(int width, int height, boolean sharp) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        for (int x =0; x < width; x+=SCALE_DEFAULT) {
            double x1 ;
            if (sharp) {
                x1 = x + 0.5;
            } else {
                x1 = x ;
            }
            gc.moveTo(x1, 0);
            gc.lineTo(x1, height);
            gc.stroke();
        }

        for (int y = 0; y < height; y+=SCALE_DEFAULT) {
            double y1 ;
            if (sharp) {
                y1 = y + 0.5 ;
            } else {
                y1 = y ;
            }
            gc.moveTo(0, y1);
            gc.lineTo(width, y1);
            gc.stroke();
        }
        return canvas ;
    }
}
