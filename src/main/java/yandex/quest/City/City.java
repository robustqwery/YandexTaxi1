package main.java.yandex.quest.City;

import main.java.yandex.quest.City.Objects.Area;
import main.java.yandex.quest.City.Objects.Driver;

import java.util.ArrayList;

/**
 * Класс описывает город. Включает необходимые функции для решения послевленной задачи.
 * @author Кочетов А.В., Трифонов Н.В.
 * @version 3.3
 */
public class City {
    /**Размер города M:N*/
    private int[] city_size;
    /**Коллекция зон повышенного спроса внутри города.*/
    private ArrayList<Area> areas;
    /**K - пользователи внутри зоны повышенного спроса.*/
    private int K;
    /**D - водители внутри зоны повышенного спроса.*/
    private int D;
    /**Водители вне зоны повышенного спроса.*/
    private ArrayList<Driver> drivers;


    public int[] getCity_size() {
        return city_size;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public int getK() {
        return K;
    }

    public int getD() {
        return D;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Основной конструктор города.
     * @param _matrix_size размер города M:N
     * @param _areas коллекция зон повышенного спроса внутри города.
     * @param _K пользователи внутри зоны повышенного спроса.
     * @param _D водители внутри зоны повышенного спроса.
     * @param _drivers водители вне зоны повышенного спроса.
     */
    public City(int[] _matrix_size, ArrayList<Area> _areas, int _K, int _D, ArrayList<Driver> _drivers) {
        this.city_size = _matrix_size;
        this.areas = _areas;
        this.K = _K;
        this.D = _D;
        this.drivers = _drivers;
    }

    /**
     * Функция считает кротчайший путь водителя({@link Driver}) к зоне повыщенного спроса({@link Area}).
     * @param driver водитель({@link Driver}).
     * @param area зона повышеного спроса({@link Area}).
     * @return кратчайший путь водителя({@link Driver}) к зоне повышенного спроса({@link Area}).
     */
    private int GetResultDriverToArea(Driver driver, Area area){
        int Dx = Math.min(Math.abs(driver.getX0() - area.getX0()),Math.abs(driver.getX0() - area.getX1()));
        int Dy = Math.min(Math.abs(driver.getY0() - area.getY0()),Math.abs(driver.getY0() - area.getY1()));

        boolean q1 = area.getX0() <= driver.getX0() && driver.getX0() <= area.getX1();
        boolean q2 = area.getY0() <= driver.getY0() && driver.getY0() <= area.getY1();
        if(q1 && q2){ return 0; }
        else if(q2){ return Dx; }
        else if(q1){ return Dy; }
        else{ return Dx + Dy; }
    }
    /**
     * Основной алгоритм:<br>
     *     Высчитывает кротчайщие пути к зоне повышенного спроса.<br>
     *     Суммирует длины путей пока коэфицент зоны повышенного спроса не станет менее единицы.
     * @return Сумма кротщайших путей необходимых для погашения зоны повышенного спроса.
     */
    public int FullResult(){
        ArrayList<Driver> tmp_drivers = new ArrayList<>(drivers);
        ArrayList<Integer> results = new ArrayList<>();
        boolean is_lacks_drivers = false;
        for(Area area : areas){
            while (area.getСoefficient() >= 1 && tmp_drivers.size() > 0) {
                int min = Integer.MAX_VALUE;
                Driver min_driver = new Driver(0,0);
                for (int nDriver = 0; nDriver < tmp_drivers.size(); nDriver++) {
                    if(GetResultDriverToArea(tmp_drivers.get(nDriver),area) < min){
                        min = GetResultDriverToArea(tmp_drivers.get(nDriver),area);
                        min_driver = tmp_drivers.get(nDriver);
                    }
                }
                results.add(min);
                area.addDriver(min_driver);
                tmp_drivers.remove(min_driver);
            }
            if(tmp_drivers.size() == 0 && area.getСoefficient() >= 1){
                is_lacks_drivers = true;
            }
        }
        int full_result = 0;
        if(!results.isEmpty()){
            for(int i:results){
                full_result += i;
            }
        }
        if (is_lacks_drivers){
            full_result = -1;
        }
        return full_result;
    }

    /**
     * Возвращает форматированную строку со всеми необходимыми данными о городе.
     * @return String.
     */
    @Override
    public String toString() {
        String areas_str = "";
        String drivers_str = "";
        for(Area a : areas){
            areas_str += a.toString() + "; ";
        }
        for(Driver d : drivers){
            drivers_str += d.toString() + "; ";
        }
        return city_size[0] + " " + city_size[1]
                + "\nAreas: " + areas_str
                + "\n" + K + " " + D
                + "\nDrivers: " + drivers_str;
    }
}
