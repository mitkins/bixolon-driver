package hr.istratech.bixolon.driver.general;

/**
 * A general interface that return the <b>printer control sequence</b> for a command in bytes.
 *
 * @author ksaric
 */

public interface ControlSequence {
    byte[] getCommand();
}
