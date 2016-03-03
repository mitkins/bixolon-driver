package hr.istratech.bixolon.driver.general;


import hr.istratech.bixolon.driver.command.print.CodePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ksaric
 */

public class TextPrinterBuilder {

    private final List<ControlSequence> controlSequences;
    private CodePage codePage = CodePage.CP_437_USA;

    public TextPrinterBuilder() {
        this.controlSequences = new ArrayList<ControlSequence>();
    }

    public static TextPrinterBuilder aPrinterBuilder() {
        return new TextPrinterBuilder();
    }

    public TextPrinterBuilder withCodePage( final CodePage codePage ) {
        this.codePage = codePage;
        this.controlSequences.add( codePage );
        return this;
    }

    /**
     * IF you are using this, be absolutly sure to <b>use existing commands</b> or you <b>know what you are doing</b>.
     *
     * @param controlSequence being composed as a printer
     * @return a builder for chaining
     */

    public TextPrinterBuilder withControlSequences( final ControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public TextPrinterBuilder withGeneralControlSequence( final GeneralControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public TextPrinterBuilder withTextControlSequence( final TextControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public Printer buildPrinter( final String data ) {
        return TextPrinter.create( controlSequences, codePage, data );
    }

}
