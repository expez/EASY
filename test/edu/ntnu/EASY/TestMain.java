package edu.ntnu.EASY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class TestMain {

    private Main main;

    @Before
    public final void setUp() {
    }
    @Test
    public final void testMain() {
	assertEquals(true, Main.getTrue());
    }

}
