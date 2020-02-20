package YandexOlimpTests;

import junit.framework.TestCase;
import main.java.yandex.quest.City.City;
import main.java.yandex.quest.City.Objects.Area;
import main.java.yandex.quest.City.Objects.Driver;

import java.util.ArrayList;

public class City_tests extends TestCase {
    private final int[] city_size_actual = {2,2};
    private int K_actual = 1, D_actual = 1;
    private ArrayList<Area> areas_actual;
    private ArrayList<Driver> drivers_actual;

    private final int full_result_actual = 2;

    City test_city;

    @Override
    protected void setUp() throws Exception {
        areas_actual = new ArrayList<>();
        drivers_actual = new ArrayList<>();
        areas_actual.add(new Area(1,1,2,2,K_actual,D_actual));
        drivers_actual.add(new Driver(0,0));
        test_city = new City(city_size_actual,areas_actual,K_actual,D_actual,drivers_actual);
    }

    @Override
    protected void tearDown() throws Exception {
        areas_actual.clear();
        drivers_actual.clear();
    }

    public void testFields(){
        assertEquals(test_city.getCity_size(),city_size_actual);
        assertEquals(test_city.getAreas(),areas_actual);
        assertEquals(test_city.getK(),K_actual);
        assertEquals(test_city.getD(),D_actual);
        assertEquals(test_city.getDrivers(),drivers_actual);
    }

    public void testFullResult(){
        assertEquals(test_city.FullResult(),full_result_actual);
    }

    public void testToString(){
        String areas_str = "";
        String drivers_str = "";
        for(Area a : areas_actual){
            areas_str += a.toString() + "; ";
        }
        for(Driver d : drivers_actual){
            drivers_str += d.toString() + "; ";
        }
        String actual =
                city_size_actual[0] + " " + city_size_actual[1]
                + "\nAreas: " + areas_str
                + "\n" + K_actual + " " + D_actual
                + "\nDrivers: " + drivers_str;

        assertEquals(test_city.toString(),actual);
    }

}
