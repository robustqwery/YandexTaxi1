package main.java.yandex.quest.City;

import main.java.yandex.quest.City.Objects.Area;
import main.java.yandex.quest.City.Objects.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Посторитель города. Данные извлекает из специального файла.
 * @author Кочетов А.В., Трифонов Н.В.
 * @version 2.3
 */
public class Builder {
    /**
        collection.get(0) Уровень 1: Размер матрицы города "n m"<br>
        collection.get(1) Уровень 2: Геометрия области повышенного спроса "x0 y0 x1 y1 ..."<br>
        collection.get(2) Уровень 3: Пользователи и водители "K D"<br>
        collection.get(3) Уровень 4: Координаты водителей "Dx0 Dy0 ..."<br>
     */
    private ArrayList<ArrayList<Integer>> collection;

    /**
     * Конструктор - Строит {@link Builder#collection} из файла по указанному пути.
     * @param _puth Путь к файлу с данными.
     */
    public Builder(String _puth) {
        collection = new ArrayList<ArrayList<Integer>>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(_puth))) {
            String s;
            while((s = bufferedReader.readLine())!= null){
                collection.add(String2Int(s));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция возвращает готовый объект {@link City} на основе данных из {@link Builder#collection}.
     * @return {@link City}.
     */
    public City getCity(){
        return new City(ArrayList2Massive(collection.get(0)),BuildAreasCollection(),collection.get(2).get(0),collection.get(2).get(1),BuildDriversCollection());
    }

    /**
     * Функция конверитрует строку вида "3 4 2 5" в коллекцию чисел.
     * @param _input строка для конвертации вида "3 4 2 5".
     * @return Коллекция чисел.
     */
    private ArrayList<Integer> String2Int(String _input) {
        ArrayList<String> out_substr = new ArrayList<String>();
        String buffer = "";
        for(int i = 0; i < _input.length(); i++){
            if(_input.charAt(i) == ' '){
                out_substr.add(buffer);
                i++;
                buffer = "";
            }

            buffer += _input.charAt(i);

            if(i == _input.length() - 1){
                out_substr.add(buffer);
                buffer = "";
            }
        }
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (String s:out_substr) {
            try {
                nums.add(Integer.parseInt(s));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return nums;
    }
    /**
     * Функция Конвертирует коллекцию чисел в целочисленный массив.
     * @param _list коллекция целых чисел.
     * @return Целочисленный масив.
     */
    private int[] ArrayList2Massive(ArrayList<Integer> _list){
        int[] massive = new int[_list.size()];
        for(int i = 0; i <_list.size(); i++ )
            massive[i] = _list.get(i);
        return massive;
    }

    /**
     * Функция определяет находится ли объект в рамках города.
     * @param object объект {@link Driver} или {@link Area}.
     * @return true если объект не выходит за границы города.
     */
    private boolean IsEntering(Object object){
        int _max_x = collection.get(0).get(0), _max_y = collection.get(0).get(1);
        if(object.getClass() == Area.class){
            Area area = (Area)object;
            if(((area.getX0() <= _max_x && area.getX1() <= _max_x) && (area.getX0() >= 0 && area.getX1() >= 0)) && ((area.getY0() <= _max_y && area.getY1() <= _max_y) && (area.getY0() >= 0 && area.getY1() >= 0))){
                return true;
            }
            else {
                return false;
            }
        }
        else if(object.getClass() == Driver.class){
            Driver driver = (Driver)object;
            if(driver.getX0() <= _max_x && driver.getY0() <= _max_y && driver.getX0() >= 0 && driver.getY0() >= 0){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Функция создаёт коллекцию {@link Driver} на основе данных {@link Builder#collection}.
     * @return коллекция {@link Driver}.
     */
    private ArrayList<Driver> BuildDriversCollection(){
        ArrayList<Driver> collection_drivers = new ArrayList<Driver>();
        int need_size= collection.get(3).size();
        for (int i = 0; i < need_size; i += 2) {
            while (need_size%2 != 0){
                need_size--;
            }
            Driver driver = new Driver(
                    collection.get(3).get(i),
                    collection.get(3).get(i + 1)
            );
            if(IsEntering(driver)){
                collection_drivers.add(driver);
            }
        }
        return collection_drivers;
    }
    /**
     * Функция создаёт коллекцию {@link Area} на основе данных {@link Builder#collection}.
     * @return коллекция {@link Area}.
     */
    private ArrayList<Area> BuildAreasCollection(){
        ArrayList<Area> collection_areas = new ArrayList<>();
        int need_size= collection.get(1).size();
        for (int i = 0; i < need_size; i+=4) {
            while (need_size%4 != 0){
                need_size--;
            }
            Area area =  new Area(
                    collection.get(1).get(i),
                    collection.get(1).get(i + 1),
                    collection.get(1).get(i + 2),
                    collection.get(1).get(i + 3),
                    collection.get(2).get(0),
                    collection.get(2).get(1)
            );
            if(IsEntering(area)){
                collection_areas.add(area);
            }
        }
        return collection_areas;
    }

}
