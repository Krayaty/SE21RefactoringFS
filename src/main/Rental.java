package main;

public class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(Movie newmovie, int newdaysRented) {
        movie = newmovie;
        daysRented = newdaysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "movie=" + movie +
                ", daysRented=" + daysRented +
                '}';
    }

    protected double getCharge() {
        double result = 0;
        switch (this.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (this.getDaysRented() > 2)
                    result += (this.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                result += this.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (this.getDaysRented() > 3)
                    result += (this.getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }

}
