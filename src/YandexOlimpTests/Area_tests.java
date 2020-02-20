package YandexOlimpTests;
import junit.framework.TestCase;
import main.java.yandex.quest.City.Objects.Area;
import main.java.yandex.quest.City.Objects.Driver;

public class Area_tests extends TestCase{
    private final int x0_actual = 1;
    private final int y0_actual = 1;
    private final int x1_actual = 2;
    private final int y1_actual = 2;
    private final int users_actual = 4;
    private final int cout_drivers_in_area_actual = 3;
    private final float сoefficient_actual = (float) users_actual/(float) cout_drivers_in_area_actual;
    private final String toString_actual = "[" + x0_actual + "," + y0_actual + "][" + x1_actual + "," + y1_actual + "]";
    Area area;

    @Override
    protected void setUp() throws Exception {
        area = new Area(x0_actual, y0_actual,x1_actual,y1_actual,users_actual,cout_drivers_in_area_actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAreaGetCoordinats(){
        assertEquals(area.getX0(),x0_actual);
        assertEquals(area.getY0(),y0_actual);
        assertEquals(area.getX1(),x1_actual);
        assertEquals(area.getY1(),y1_actual);
    }

    public void testAreaGetСoefficient(){
        assertEquals(area.getСoefficient(),сoefficient_actual);
    }

    public void testAreaDrivers(){
        assertEquals(area.drivers_in_area.size(),cout_drivers_in_area_actual);
    }

    public void testAreaAddDriver_CalcCoefficient(){
        area.addDriver(new Driver(0,0));
        assertEquals(area.drivers_in_area.size(),cout_drivers_in_area_actual + 1);
        float сoefficient_actual_new = (float) users_actual/(float) (cout_drivers_in_area_actual + 1);
        assertEquals(area.getСoefficient(),сoefficient_actual_new);
    }

    public void testToString(){
        assertEquals(area.toString(),toString_actual);
    }

}
