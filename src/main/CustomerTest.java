package main;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private Customer customer;

    private Movie newRelease;
    private Movie childrens;
    private Movie regular;

    private ArrayList<Rental> newReleaseRentals;
    private ArrayList<Rental> childrensRentals;
    private ArrayList<Rental> regularRentals;



    @BeforeEach
    public void setUp() throws Exception {
        customer = new Customer("Customer");

        newRelease = new Movie("Movie1", Movie.NEW_RELEASE);
        childrens = new Movie("Movie2", Movie.CHILDRENS);
        regular = new Movie("Movie3", Movie.REGULAR);

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
    @DisplayName("Add a given rental to the rentals vector.")
    public void testAddRental() {

        assertEquals(0, customer.getRentals().size(),
                "On startup the collection of rentals should be empty (size 0).\n" +
                        "The size was: " + customer.getRentals().size() + ".");


        /** Append a rental of type NEW_RELEASE to the collection. */
        customer.addRental(newReleaseRentals.get(0));

        assertEquals(1, customer.getRentals().size(),
                "The rentals collection should have size 1 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(newReleaseRentals.get(0), customer.getRentals().get(0),
                "The addRental method should append the specific value \"" + newReleaseRentals.get(0) + "\" to the collection.\n" +
                        "But value was: \"" + customer.getRentals().get(0) + "\".");


        /** Append a rental of type CHILDRENS to the collection. */
        customer.addRental(childrensRentals.get(0));

        assertEquals(2, customer.getRentals().size(),
                "The rentals collection should have size 2 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(childrensRentals.get(0), customer.getRentals().get(1),
                "The addRental method should append the specific value \"" + childrensRentals.get(0) + "\" to the collection.\n" +
                        "But value was: \"" + customer.getRentals().get(1) + "\".");


        /** Append a rental of type REGULAR to the collection. */
        customer.addRental(regularRentals.get(0));

        assertEquals(3, customer.getRentals().size(),
                "The rentals collection should have size 3 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(regularRentals.get(0), customer.getRentals().get(2),
                "The addRental method should append the specific value \"" + regularRentals.get(0) + "\" to the collection.\n" +
                        "But value was: \"" + customer.getRentals().get(2) + "\".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed and points collected for empty rentals collection.")
    public void testStatementWithNoRentals() {

        String expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";

        String statement;

        assertEquals(0, customer.getRentals().size(),
                "On startup the collection of rentals should be empty (size 0).\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed and points collected rentals collection with one rental of type NEW_RELEASE.")
    public void testStatementWithOneNewReleaseRental() {

        /** Append a rental of type NEW_RELEASE to the collection. */
        customer.addRental(newReleaseRentals.get(0));
        double totalamount = 3.0;
        int points = 1;
        String statement;
        String expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie1\t\t1\t3.0\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(1, customer.getRentals().size(),
                "The rentals collection should have size 1 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed and points collected rentals collection with one rental of type CHILDRENS.")
    public void testStatementWithOneChildrensRental() {

        double totalamount;
        int points;
        String expected;
        String statement;


        /** One retail within the average-rental-time-bound */

        customer.addRental(childrensRentals.get(0));
        totalamount = 1.5;
        points = 1;
        expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie2\t\t1\t1.5\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(1, customer.getRentals().size(),
                "The rentals collection should have size 1 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");


        /** Clear rentals collection */

        customer.getRentals().clear();

        assertEquals(0, customer.getRentals().size(),
                "On startup the collection of rentals should be empty (size 0).\n" +
                        "The size was: " + customer.getRentals().size() + ".");


        /** One retail outside of the average-rental-time-bound */

        customer.addRental(childrensRentals.get(3));
        totalamount = 3.0;
        points = 1;
        expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie2\t\t4\t3.0\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(1, customer.getRentals().size(),
                "The rentals collection should have size 1 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");
    }

    @Test
    @DisplayName("Tells correct amount of money owed and points collected rentals collection with one rental of type REGULAR.")
    public void testStatementWithOneRegularRental() {

        double totalamount;
        int points;
        String expected;
        String statement;


        /** One retail within the average-rental-time-bound */

        customer.addRental(regularRentals.get(0));
        totalamount = 2.0;
        points = 1;
        expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie3\t\t1\t2.0\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(1, customer.getRentals().size(),
                "The rentals collection should have size 1 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");


        /** Clear rentals collection */

        customer.getRentals().clear();

        assertEquals(0, customer.getRentals().size(),
                "On startup the collection of rentals should be empty (size 0).\n" +
                        "The size was: " + customer.getRentals().size() + ".");


        /** One retail outside of the average-rental-time-bound */

        customer.addRental(regularRentals.get(2));
        totalamount = 3.5;
        points = 1;
        expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie3\t\t3\t3.5\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(1, customer.getRentals().size(),
                "The rentals collection should have size 1 after appending a rental to it.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");
    }

    @Test
    @DisplayName("Tells correct amount of money owed and points collected rentals collection with one rental of type REGULAR.")
    public void testStatementWithManyRentalsOfAllTypes() {

        double totalamount;
        int points;
        String expected;
        String statement;


        /** Many retails of one type */

        newReleaseRentals.forEach(customer::addRental);
        totalamount = 30.0;
        points = 7;
        expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie1\t\t1\t3.0\n" +
                "\tMovie1\t\t2\t6.0\n" +
                "\tMovie1\t\t3\t9.0\n" +
                "\tMovie1\t\t4\t12.0\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(4, customer.getRentals().size(),
                "The rentals collection should have size 4.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");


        /** Many retails of many type */

        childrensRentals.forEach(customer::addRental);
        regularRentals.forEach(customer::addRental);
        totalamount = 50.0;
        points = 15;
        expected = "main.Rental Record for Customer\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tMovie1\t\t1\t3.0\n" +
                "\tMovie1\t\t2\t6.0\n" +
                "\tMovie1\t\t3\t9.0\n" +
                "\tMovie1\t\t4\t12.0\n" +
                "\tMovie2\t\t1\t1.5\n" +
                "\tMovie2\t\t2\t1.5\n" +
                "\tMovie2\t\t3\t1.5\n" +
                "\tMovie2\t\t4\t3.0\n" +
                "\tMovie3\t\t1\t2.0\n" +
                "\tMovie3\t\t2\t2.0\n" +
                "\tMovie3\t\t3\t3.5\n" +
                "\tMovie3\t\t4\t5.0\n" +
                "Amount owed is " + totalamount + "\n" +
                "You earned " + points + " frequent renter points";

        assertEquals(12, customer.getRentals().size(),
                "The rentals collection should have size 12.\n" +
                        "The size was: " + customer.getRentals().size() + ".");

        assertEquals(expected, (statement = customer.statement()),
                        "The value should be:\n\"" + expected + "\".\n" +
                        "The returned string was:\n\"" + statement + "\".");
    }

    @Test
    @DisplayName("Tells correct amount of money owed for a given rental of type NEW_RELEASE.")
    public void testAmountForNewRelease() {

        double actual;
        double expected = 3;
        assertEquals(expected, (actual = customer.amountFor(newReleaseRentals.get(0))),
                "The amount of money owed for the rental: \"" + newReleaseRentals.get(0) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed for a given rental of type CHILDRENS.")
    public void testAmountForChildrens() {

        double actual;
        double expected;

        /** Retail within the average-rental-time-bound */

        expected = 1.5;

        assertEquals(expected, (actual = customer.amountFor(childrensRentals.get(0))),
                "The amount of money owed for the rental: \"" + childrensRentals.get(0) + "\" should be " + expected +
                        "The amount was: " + actual + ".");


        /** Retail outside of the average-rental-time-bound */

        expected = 3.0;

        assertEquals(expected, (actual = customer.amountFor(childrensRentals.get(3))),
                "The amount of money owed for the rental: \"" + childrensRentals.get(3) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

    }

    @Test
    @DisplayName("Tells correct amount of money owed for a given rental of type REGULAR.")
    public void testAmountForRegular() {

        double actual;
        double expected;

        /** Retail within the average-rental-time-bound */

        expected = 2;

        assertEquals(expected, (actual = customer.amountFor(regularRentals.get(0))),
                "The amount of money owed for the rental: \"" + regularRentals.get(0) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");


        /** Retail outside of the average-rental-time-bound */

        expected = 3.5;

        assertEquals(expected, (actual = customer.amountFor(regularRentals.get(2))),
                "The amount of money owed for the rental: \"" + regularRentals.get(2) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

        expected = 5.0;

        assertEquals(expected, (actual = customer.amountFor(regularRentals.get(3))),
                "The amount of money owed for the rental: \"" + regularRentals.get(3) + "\" should be " + expected + "\n" +
                        "The amount was: " + actual + ".");

    }

}
