package chapter_11.exercise_11_8_1;

import org.junit.Test;

import static org.junit.Assert.*;

public class FridgeTest {

    Fridge fridge = new Fridge();
    final String ANY_PRODUCT_1 = "ANY_PRODUCT_1";
    final String ANY_PRODUCT_2 = "ANY_PRODUCT_2";

    @Test
    public void shouldContainAddedProduct() {
        fridge.put(ANY_PRODUCT_1);
        assertTrue("Should contain added product.", fridge.contains(ANY_PRODUCT_1));
    }

    @Test
    public void shouldNotAllowToStoreMoreThanOneIdenticalProductAtTheSameTime() {
        fridge.put(ANY_PRODUCT_1);
        assertFalse("Disallow putting the same product.", fridge.put(ANY_PRODUCT_1));
    }

    @Test
    public void shouldStoreProductAfterTryingToAddTheSameOne() {
        fridge.put(ANY_PRODUCT_1);
        fridge.put(ANY_PRODUCT_1);
        assertTrue(fridge.contains(ANY_PRODUCT_1));
    }

    @Test
    public void shouldAllowToStoreMultipleProductsAtTheSameTime() {
        fridge.put(ANY_PRODUCT_1);
        fridge.put(ANY_PRODUCT_2);
        assertTrue(fridge.contains(ANY_PRODUCT_1));
        assertTrue(fridge.contains(ANY_PRODUCT_2));
    }

    @Test
    public void shouldAllowToPutTheSameProductMultipleTimesWhenItsNotThere() throws NoSuchItemException {
        fridge.put(ANY_PRODUCT_1);
        fridge.take(ANY_PRODUCT_1);
        fridge.put(ANY_PRODUCT_1);
        assertTrue("Should contain product added after removal.", fridge.contains(ANY_PRODUCT_1));
    }

    @Test
    public void shouldNotContainNotAddedProduct() {
        fridge.put(ANY_PRODUCT_1);
        assertFalse(fridge.contains(ANY_PRODUCT_2));
    }

    @Test(expected = NoSuchItemException.class)
    public void shouldThrowNSIEWhenTakingProductWhichWasNeverAdded() throws NoSuchItemException {
        fridge.take(ANY_PRODUCT_1);
    }

    @Test(expected = NoSuchItemException.class)
    public void shouldThrowNSIEWhenTakingProductWhichWasAlreadyTaken() throws NoSuchItemException {
        fridge.put(ANY_PRODUCT_1);
        fridge.take(ANY_PRODUCT_1);
        fridge.take(ANY_PRODUCT_1);
    }

    @Test
    public void shouldContainNoProductsWhenAllProductsWereTaken() throws NoSuchItemException {
        fridge.put(ANY_PRODUCT_1);
        fridge.put(ANY_PRODUCT_2);
        fridge.take(ANY_PRODUCT_1);
        fridge.take(ANY_PRODUCT_2);

        assertFalse(fridge.contains(ANY_PRODUCT_1));
        assertFalse(fridge.contains(ANY_PRODUCT_2));
    }
}
