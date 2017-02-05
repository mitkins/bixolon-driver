package hr.istratech.bixolon.driver.general;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import hr.istratech.bixolon.driver.command.print.CodePage;

/**
 * @author ksaric
 */

public class RasterPrinterBuilder {

    private final List<ControlSequence> controlSequences;
    private CodePage codePage = CodePage.CP_437_USA;

    public RasterPrinterBuilder() {
        this.controlSequences = new ArrayList<ControlSequence>();
    }

    public static RasterPrinterBuilder aPrinterBuilder() {
        return new RasterPrinterBuilder();
    }

    public RasterPrinterBuilder withCodePage(final CodePage codePage ) {
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

    public RasterPrinterBuilder withControlSequences(final ControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public RasterPrinterBuilder withGeneralControlSequence(final GeneralControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public RasterPrinterBuilder withRasterControlSequence(final RasterControlSequence controlSequence ) {
        this.controlSequences.add( controlSequence );
        return this;
    }

    public Printer buildPrinter( final Bitmap bitmap, final boolean pageMode, final int printerWidth ) {
        return RasterPrinter.create( controlSequences, bitmap, pageMode, printerWidth );
    }

    public Printer buildPrinter( final Bitmap bitmap, final boolean pageMode, final int printerWidth, final int luminanceThreshold ) {
        return RasterPrinter.create( controlSequences, bitmap, pageMode, printerWidth, luminanceThreshold );
    }
}
