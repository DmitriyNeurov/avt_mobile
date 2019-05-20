import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetClassString()
    {
        boolean result = getClassString().contains("hello") || getClassString().contains("Hello");
        if (result) {
            System.out.println("Строка содержит слово hello либо слово Hello");
        }

        Assert.assertTrue("Строка не содержит слова hello и Hello", result);
    }
}
