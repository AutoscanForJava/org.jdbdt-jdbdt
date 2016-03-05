package org.jdbdt.tcfg.h2;

import org.jdbdt.DBEngineTestSuite;
import org.junit.BeforeClass;

@SuppressWarnings("javadoc")
public class FileDBTest extends DBEngineTestSuite {
  @BeforeClass 
  public static void setup() { 
    System.setProperty(DB_URL_PROP, "jdbc:h2:./jdbdt-h2-test");
  }
}
