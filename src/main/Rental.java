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

    protected int getFrequentRenterPoints(){
        if ((this.getMovie().getPrice() == Movie.NEW_RELEASE) && this.getDaysRented() > 1)
            return 2;

        return 1;
    }

    protected double getCharge(){
        return this.movie.getCharge(this.daysRented);
    }

}
