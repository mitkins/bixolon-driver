package hr.istratech.bixolon.driver.general;


import hr.istratech.bixolon.driver.command.qr.QrCodePrint;
import hr.istratech.bixolon.driver.command.qr.QrCodeStore;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;

/**
 * @author ksaric
 */

class QrPrinter implements Printer {

    private final Collection<ControlSequence> controlSequences;
    private final String data;

    private QrPrinter( final Collection<ControlSequence> controlSequences, final String data ) {
        this.controlSequences = Collections.unmodifiableCollection( controlSequences );
        this.data = data;
    }

    public static QrPrinter create( final Collection<ControlSequence> controlSequences, final String data ) {
        return new QrPrinter( controlSequences, data );
    }

    @Override
    public byte[] getCommand() {
        return print( data );
    }

    protected byte[] print( final String data ) {

        final byte[] dataBytes = data.getBytes();

        final QrCodeStore qrCodeStore = QrCodeStore.createQrCodeStore( dataBytes );

        Integer messageSize = qrCodeStore.getCommand().length + dataBytes.length + QrCodePrint.PRINT.getCommand().length;

        for ( final ControlSequence controlSequence : controlSequences ) {
            messageSize += controlSequence.getCommand().length;
        }

        final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

        // put the instructions
        for ( final ControlSequence controlSequence : controlSequences ) {
            buffer.put( controlSequence.getCommand() );
        }

        buffer.put( qrCodeStore.getCommand() );
        buffer.put( dataBytes );
        buffer.put( QrCodePrint.PRINT.getCommand() );

        return buffer.array();
    }
}