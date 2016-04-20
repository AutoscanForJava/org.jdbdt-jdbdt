package org.jdbdt;

import static org.jdbdt.JDBDT.*;
import static org.jdbdt.TestUtil.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

@SuppressWarnings("javadoc")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CallInfoTest extends DBTestCase {

  @Rule public TestName testName = new TestName();

  private static class FakeClass {
    static CallInfo fake() { 
      return CallInfo.create(); 
    }
    static CallInfo fake(String msg) { 
      return CallInfo.create(msg); 
    }
  }
  static void assertMethodInfo(CallInfo.MethodInfo ci, String className, String methodName) {
    assertEquals("class name", className, ci.getClassName());
    assertEquals("method name", methodName, ci.getMethodName());
  }

  void assertCallInfo(CallInfo ci, String msg) {
    assertMethodInfo(ci.getCallerMethodInfo(),
        getClass().getName(),
        testName.getMethodName());
    assertMethodInfo(ci.getAPIMethodInfo(), 
        FakeClass.class.getName(),
        "fake");
    assertEquals("message", msg, ci.getMessage());
  }
  
  @Test
  public void test1() {
    assertCallInfo( FakeClass.fake(), "");
  }

  @Test
  public void test2() {
    String msg = "this is the message";
    assertCallInfo( FakeClass.fake(msg), msg);
  }
}
