package main;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public Movie(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price.getPriceCode();
    }

    public void setPrice(int arg) {
        switch (arg) {
            case NEW_RELEASE:
                this.price = new NewReleasePrice();
                break;

            case CHILDRENS:
                this.price = new ChildrensPrice();
                break;

            case REGULAR:
                this.price = new RegularPrice();
                break;

            default:
                throw new IllegalArgumentException("Incorrect Pricecode");
        }
    }

    public String getTitle (){
        return title;
    };

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", priceCode=" + price +
                '}';
    }

    protected double getCharge(int daysRented) {
        if(this.price != null)
            return this.price.getCharge(daysRented);
        throw new NullPointerException();
    }
    
    protected int getFrequentRenterPoints(int daysRented){
        return this.price.getFrequentRenterPoints(daysRented);
    }
    
}
