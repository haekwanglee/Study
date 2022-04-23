# The Starting Point

The sample program is very simple. It is a program to calculate and print a statement of a
customer's charges at a video store. The program is told which movies a customer rented and for
how long. It then calculates the charges, which depend on how long the movie is rented, and
identifies the type movie. There are three kinds of movies: regular, children's, and new releases.
In addition to calculating charges, the statement also computes frequent renter points, which vary
depending on whether the film is a new release.

# Further Requirements

1. System owner wants to add a type of movie in order to vary rental charge and
 frequent renter points.
 <br>It requires "bestseller" which is charged for 5 dollars per a day for first
  2 days, after then 7.5 dollars per a day.
 <br>bestseller type is quite expensive, it rewards 5 points per a day.
 2. System owner would like to change a type of movie by certain policy.
 <br>For instance, a film can be 'bestseller' from 'new release' if it become popular.
 <br>The policy has not been determined so far, but we need to consider the possibility.
 3. System Owner has a plan to extend his business on web.
 <br>Not only printing customer statement on paper, but also printing it as a html format.
 <br>Please refer below code.
 
 ```java

    public String htmlStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        String result = "<table><tr><th colspan=3 align=left><b>Rentals for <b></th><th><em>" + getName() + "</em></th></tr>\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            //determine amounts for each line
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDREN:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1) frequentRenterPoints++;

            //show figures for this rental
            result += "<tr><td>&nbsp;</td><td>"+ each.getMovie().getTitle() + "</td><td>&nbsp;</td><td>" +
                    String.valueOf(thisAmount) + "</td></tr>\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "<tr><td colspan=4><b>Amount owed is <EM>" + String.valueOf(totalAmount) + "</b></td></tr>\n";
        result += "<tr><td colspan=4><b>You earned <EM>" +
                String.valueOf(frequentRenterPoints) +
                "</EM> frequent " + "renter points</b></td></tr></table>";
        return result;
    }
 
 ```
 
