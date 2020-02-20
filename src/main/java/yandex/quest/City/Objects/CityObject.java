package main.java.yandex.quest.City.Objects;

/**
 * Общий класс для объектов внутри города.
 * @author Кочетов А.В., Трифонов Н.В.
 * @version 1.0
 */
public abstract class CityObject {
    /**Координата X*/
    private int x0;
    /**Координата Y*/
    private int y0;

    /**Конструктор - создание нового объекта
     * @param x Координата X.
     * @param y Координата Y.
     */
    public CityObject(int x, int y){
        this.x0 = x;
        this.y0 = y;
    }

    /**Функция возвращает {@link main.java.yandex.quest.City.Objects.CityObject#x0}
     * @return возвращает координату x0.
     */
    public int getX0() {
        return x0;
    }
    /**Функция возвращает {@link main.java.yandex.quest.City.Objects.CityObject#y0}
     * @return возвращает координату y0.
     */
    public int getY0() {
        return y0;
    }

    /**
     * Функция возвращает координаты объекта.
     * @return координаты объкета в виде строки.
     */
    @Override
    public String toString(){
        return "[" + x0 + "," + y0 + "]";
    }
}
