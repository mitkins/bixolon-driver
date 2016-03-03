package hr.istratech.bixolon.driver.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public abstract class ByteCharset extends Charset {

    protected char[] lookupTable;
    protected static final char NONE = '�';

    ByteCharset( String canonicalName, String[] aliases ) {
        super( canonicalName, aliases );
    }

    public boolean contains( final Charset cs ) {
        return cs.getClass() == this.getClass();
    }

    public char[] getLookupTable() {
        return this.lookupTable;
    }

    public CharsetDecoder newDecoder() {
        return new ByteCharset.Decoder( this );
    }

    public CharsetEncoder newEncoder() {
        return new ByteCharset.Encoder( this );
    }

    private static final class Decoder extends CharsetDecoder {
        private char[] lookup;

        Decoder( ByteCharset cs ) {
            super( cs, 1.0F, 1.0F );
            this.lookup = cs.getLookupTable();
        }

        protected CoderResult decodeLoop( ByteBuffer in, CharBuffer out ) {
            char c;
            for (; in.hasRemaining(); out.put( c ) ) {
                byte b = in.get();
                if ( !out.hasRemaining() ) {
                    in.position( in.position() - 1 );
                    return CoderResult.OVERFLOW;
                }

                if ( ( c = this.lookup[ b & 255 ] ) == '�' ) {
                    ;
                }
            }

            return CoderResult.UNDERFLOW;
        }
    }

    private static final class Encoder extends CharsetEncoder {
        private byte[] lookup;

        Encoder( ByteCharset cs ) {
            super( cs, 1.0F, 1.0F );
            char[] lookup_table = cs.getLookupTable();
            char max = 0;

            int i;
            char c;
            for ( i = 0; i < lookup_table.length; ++i ) {
                c = lookup_table[ i ];
                max = c > max && c < '�' ? c : max;
            }

            this.lookup = new byte[ max + 1 ];

            for ( i = 0; i < lookup_table.length; ++i ) {
                c = lookup_table[ i ];
                if ( c != 0 && c < '�' ) {
                    this.lookup[ c ] = (byte) i;
                }
            }

        }

        protected CoderResult encodeLoop( CharBuffer in, ByteBuffer out ) {
            while ( in.hasRemaining() ) {
                char c = in.get();
                if ( !out.hasRemaining() ) {
                    in.position( in.position() - 1 );
                    return CoderResult.OVERFLOW;
                }

                byte b = c < this.lookup.length ? this.lookup[ c ] : 0;
                if ( b == 0 && c != 0 ) {
                    in.position( in.position() - 1 );
                    return CoderResult.unmappableForLength( 1 );
                }

                out.put( b );
            }

            return CoderResult.UNDERFLOW;
        }
    }
}
