package hr.istratech.bixolon.driver.general;


import java.util.ArrayList;
import java.util.List;

/**
 * @author ksaric
 */

public class QrPrinterBuilder {

    private final List<ControlSequence> controlSequences;

    public QrPrinterBuilder() {
        this.controlSequences = new ArrayList<ControlSequence>();
    }

    public static QrPrinterBuilder aPrinterBuilder() {
        return new QrPrinterBuilder();
    }

    /**
     * IF you are using this, be absolutly sure to <b>use existing commands</b> or you <b>know what you are doing</b>.
     *
     * @param controlSequence being composed as a printer
     * @return a builder for chaining
     */

    public QrPrinterBuilder withControlSequences( final ControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public QrPrinterBuilder withGeneralControlSequence( final GeneralControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public QrPrinterBuilder withQrControlSequence( final QrControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public Printer buildPrinter( final String data ) {
        return QrPrinter.create( controlSequences, data );
    }

}
