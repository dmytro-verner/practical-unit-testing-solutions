package chapter_9.exercise_9_8_1;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFixtureTest {

    private static final Logger logger = LoggerFactory.getLogger(TestFixtureTest.class);

    @BeforeClass
    public static void setUpClass() {
        logger.info("Before class");
    }

    @Before
    public void setUp() {
        logger.info("Before test");
    }

    @Test
    public void testMethodA() {
        logger.info("Method A");
    }

    @Test
    public void testMethodB() {
        logger.info("Method B");
    }

    @After
    public void tearDown() {
        logger.info("After test");
    }

    @AfterClass
    public static void tearDownClass() {
        logger.info("After class");
    }
}
