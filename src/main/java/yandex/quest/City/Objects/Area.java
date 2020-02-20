package main.java.yandex.quest.City.Objects;

import java.util.ArrayList;

/**
 * Класс описывает зону потребности города.
 * @author Кочетов А.В., Трифонов Н.В.
 * @version 1.4
 */
public class Area extends CityObject {
    /**Координата X1.*/
    private int x1;
    /**Координата Y1.*/
    private int y1;
    /**Колличество пользователей в зоне.*/
    private int users;
    /**Коэффицент зоны потребности.*/
    private float сoefficient;
    /**Водители внутри зоны.*/
    public ArrayList<Driver> drivers_in_area;

    /**
     * Конструтор.
     * @param x0 Координата X.
     * @param y0 Координата Y.
     * @param x1 Координата X1.
     * @param y1 Координата Y1.
     * @param users Колличество пользователей в зоне.
     * @param drivers Колличество водителей в зоне.
     */
    public Area(int x0, int y0, int x1,int y1,int users, int drivers) {
        super(x0, y0);
        if(users <= 0){
            this.users = 1;
        }
        else{
            this.users = users;
        }
        this.x1 = x1;
        this.y1 = y1;
        drivers_in_area = new ArrayList<Driver>();
        for (int i = 0; i < drivers;i++){
            drivers_in_area.add(new Driver(0,0));
        }
        CalcCoefficient();
    }

    /**
     * Функция возвращает {@link Area#x1}
     * @return {@link Area#x1}
     */
    public int getX1() {
        return x1;
    }
    /**
     * Функция возвращает {@link Area#y1}
     * @return {@link Area#y1}
     */
    public int getY1() {
        return y1;
    }
    /**
     * Функция возвращает {@link Area#сoefficient}
     * @return {@link Area#сoefficient}
     */
    public float getСoefficient() {
        return сoefficient;
    }

    /**
     * Функция добавляет водителя({@link Driver}) в водителей в зоне {@link Area#drivers_in_area}
     * @param driver Водитель {@link Driver}
     */
    public void addDriver(Driver driver){
        drivers_in_area.add(driver);
        CalcCoefficient();
    }
    /**
     * Функция возвращает координаты объекта.
     * @return координаты объкета в виде строки.
     */
    @Override
    public String toString() {
        return super.toString() + "[" + x1 + "," + y1 + "]";
    }

    /**
     * Функция считает коэффицент зоны потребности по формуле:
     * коэффицент = (количесвто_пользователей_зоны/количесвто_водителей_зоны)
     * результат выполнения записывается в {@link Area#сoefficient}
     */
    private void CalcCoefficient(){
        сoefficient = (float)users/(float)drivers_in_area.size();
    }
}