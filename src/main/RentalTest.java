package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalTest {

    private Movie newRelease;
    private Movie childrens;
    private Movie regular;

    private ArrayList<Rental> newReleaseRentals;
    private ArrayList<Rental> childrensRentals;
    private ArrayList<Rental> regularRentals;

    @BeforeEach
    public void setUp() throws Exception {

        newRelease = new Movie("Movie1");
        newRelease.setPrice(Movie.NEW_RELEASE);
        childrens = new Movie("Movie2");
        childrens.setPrice(Movie.CHILDRENS);
        regular = new Movie("Movie3");
        regular.setPrice(Movie.REGULAR);

        newReleaseRentals = new ArrayList<>();
        newReleaseRentals.add(new Rental(newRelease, 1));
        newReleaseRentals.add(new Rental(newRelease, 2));
        newReleaseRentals.add(new Rental(newRelease, 3));
        newReleaseRentals.add(new Rental(newRelease, 4));

        childrensRentals = new ArrayList<>();
        childrensRentals.add(new Rental(childrens, 1));
        childrensRentals.add(new Rental(childrens, 2));
        childrensRentals.add(new Rental(childrens, 3));
        childrensRentals.add(new Rental(childrens, 4));

        regularRentals = new ArrayList<>();
        regularRentals.add(new Rental(regular, 1));
        regularRentals.add(new Rental(regular, 2));
        regularRentals.add(new Rental(regular, 3));
        regularRentals.add(new Rental(regular, 4));
    }

    @Test
    @DisplayName("Tells correct amount of money owed for a given rental of type NEW_RELEASE.")
    public void testGetChargeNewRelease() {

        double actual;
        double expected = 3;
        assertEquals(expected, (actual = newReleaseRentals.get(0).getCharge()),
                "The amount of money owed for the rental: \"" + newReleaseRentals.get(0) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed for a given rental of type CHILDRENS.")
    public void testGetChargeChildrens() {

        double actual;
        double expected;

        /** Retail within the average-rental-time-bound */

        expected = 1.5;

        assertEquals(expected, (actual = childrensRentals.get(0).getCharge()),
                "The amount of money owed for the rental: \"" + childrensRentals.get(0) + "\" should be " + expected +
                        "The amount was: " + actual + ".");


        /** Retail outside of the average-rental-time-bound */

        expected = 3.0;

        assertEquals(expected, (actual = childrensRentals.get(3).getCharge()),
                "The amount of money owed for the rental: \"" + childrensRentals.get(3) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed for a given rental of type REGULAR.")
    public void testGetChargeRegular() {

        double actual;
        double expected;

        /** Retail within the average-rental-time-bound */

        expected = 2;

        assertEquals(expected, (actual = regularRentals.get(0).getCharge()),
                "The amount of money owed for the rental: \"" + regularRentals.get(0) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");


        /** Retail outside of the average-rental-time-bound */

        expected = 3.5;

        assertEquals(expected, (actual = regularRentals.get(2).getCharge()),
                "The amount of money owed for the rental: \"" + regularRentals.get(2) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

        expected = 5.0;

        assertEquals(expected, (actual = regularRentals.get(3).getCharge()),
                "The amount of money owed for the rental: \"" + regularRentals.get(3) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

    }

    @Test
    @DisplayName("Returns the number of points basend on the type of the rental object.")
    public void testGetFrequentRenterPoints() {

        int actual;
        int expected;

        /** Rental of type NEW_RELEASE */

        /** Rental-time < 1 */

        expected = 1;

        assertEquals(expected, (actual = newReleaseRentals.get(0).getFrequentRenterPoints()),
                "The number of points for the rental: \"" + newReleaseRentals.get(0) + "\" should be " + expected + "\n" +
                        "The number was: " + actual + ".");

        /** Rental-time >= 1 */

        expected = 2;

        assertEquals(expected, (actual = newReleaseRentals.get(1).getFrequentRenterPoints()),
                "The number of points for the rental: \"" + newReleaseRentals.get(1) + "\" should be " + expected + "\n" +
                        "The number was: " + actual + ".");


        /** Rental of other type */

        expected = 1;

        assertEquals(expected, (actual = regularRentals.get(0).getFrequentRenterPoints()),
                "The number of points for the rental: \"" + regularRentals.get(0) + "\" should be " + expected + "\n" +
                        "The number was: " + actual + ".");

    }

}
