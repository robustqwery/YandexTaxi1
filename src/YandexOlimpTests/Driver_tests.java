package YandexOlimpTests;

import junit.framework.TestCase;
import main.java.yandex.quest.City.Objects.Driver;

public class Driver_tests extends TestCase {
    Driver driver;
    private final int x = 5, y = 5;
    private final String toString_actual = "[" + x + "," + y + "]";
    @Override
    protected void setUp() throws Exception {
        driver = new Driver(x,y);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

    }

    public void testDriverCoordinats(){
        int getX0_expect = driver.getX0();
        assertEquals(getX0_expect,x);
        int getY0_expect = driver.getY0();
        assertEquals(getX0_expect,y);
    }
    public void testDriverToString(){
        String toString_expect = driver.toString();
        assertEquals(toString_expect,toString_actual);
    }
}
