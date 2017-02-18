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
    private final List<ControlSequence> postControlSequences;

    public RasterPrinterBuilder() {
        this.controlSequences = new ArrayList<ControlSequence>();
        this.postControlSequences = new ArrayList<ControlSequence>();
    }

    public static RasterPrinterBuilder aPrinterBuilder() {
        return new RasterPrinterBuilder();
    }

    /**
     * IF you are using this, be absolutely sure to <b>use existing commands</b> or you <b>know what you are doing</b>.
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

    public RasterPrinterBuilder postControlSequences(final ControlSequence controlSequence ) {
        this.postControlSequences.add( controlSequence );
        return this;
    }

    public RasterPrinterBuilder postGeneralControlSequence(final GeneralControlSequence controlSequence ) {
        this.postControlSequences.add( controlSequence );
        return this;
    }

    public RasterPrinterBuilder postRasterControlSequence(final RasterControlSequence controlSequence ) {
        this.postControlSequences.add( controlSequence );
        return this;
    }

    public Printer buildPrinter( final Bitmap bitmap, final int maxWidth ) {
        return RasterPrinter.create( controlSequences, postControlSequences, bitmap, maxWidth );
    }

    public Printer buildPrinter( final Bitmap bitmap, final int maxWidth, final int luminanceThreshold ) {
        return RasterPrinter.create( controlSequences, postControlSequences, bitmap, maxWidth, luminanceThreshold );
    }
}
