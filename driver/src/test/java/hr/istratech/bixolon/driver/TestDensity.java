package hr.istratech.bixolon.driver;

/**
 * @author ksaric
 */

public enum TestDensity {
    LOW( 10 ),
    DEFAULT( 100 ),
    HIGH( 1000 ),
    VERY_HIGH( 10000 ),
    EXTREME( 1000000 );

    private final int numberOfTests;

    TestDensity( final int numberOfTests ) {
        this.numberOfTests = numberOfTests;
    }

    public int getNumberOfTests() {
        return numberOfTests;
    }
}