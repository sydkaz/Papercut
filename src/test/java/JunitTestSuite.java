import com.test.papercut.CorrectDataTest;
import com.test.papercut.InCorrectDataTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CorrectDataTest.class,InCorrectDataTest.class

})

public class JunitTestSuite {
}