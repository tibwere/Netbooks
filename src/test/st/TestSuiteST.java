package test.st;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite dei test prodotti dall'utente qui sotto riportato
 * @author Simone Tiberi (M. 0252795)
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestReader.class, TestNavbarSelenium.class, TestParser.class, TestBookEvaluationBean.class })
public class TestSuiteST {

}
