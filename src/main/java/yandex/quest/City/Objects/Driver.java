package main.java.yandex.quest.City.Objects;

/**
 * Класс описывает водителя внутри города. Поолностью наследует {@link CityObject}
 * @author Кочетов А.В., Трифонов Н.В.
 * @version 1.0
 */
public class Driver extends CityObject {
    /**
     * Конструктор - создает новый объкет Driver(x,y)
     * @param x Координата Х
     * @param y Координата Y
     */
    public Driver(int x, int y) {
        super(x, y);
    }
}
