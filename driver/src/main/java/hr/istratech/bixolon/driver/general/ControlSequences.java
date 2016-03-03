package hr.istratech.bixolon.driver.general;

/**
 * @author ksaric
 */

public class ControlSequences {

    ControlSequences() {
    }

    /**
     * A generally unsafe call. If you are using it, use the types to guide you, otherwise <b>know what you are doing</b>.
     *
     * @param controlSequences to be constructed as {@link ControlSequence}
     * @return control sequence to use/compose
     */

    public static ControlSequence createControlSequence( final byte[] controlSequences ) {
        return new ControlSequence() {
            @Override
            public byte[] getCommand() {
                return controlSequences;
            }
        };
    }

}
