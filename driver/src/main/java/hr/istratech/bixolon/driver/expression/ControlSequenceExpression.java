package hr.istratech.bixolon.driver.expression;

/**
 * Unfortunately, Java doesn't have enought type information to make the 'Interpreter' pattern type-safe.
 * However, one could switch to <b>Scala</b> and <b>FreeMonad</b>!
 *
 * @author ksaric
 */

public interface ControlSequenceExpression /*extends ControlSequence*/ {
    byte[] interpret();
}