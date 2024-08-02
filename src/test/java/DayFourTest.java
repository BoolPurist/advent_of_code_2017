// aa bb cc dd ee is valid.
// aa bb cc dd aa is not valid - the word aa appears more than once.
// aa bb cc dd aaa is valid - aa and aaa count as different words.
//


import org.junit.jupiter.api.Test;

import solutions.GivenTask;
import solutions.day_4.ParserDayFour;

public class DayFourTest {

    @Test
    public void detectValidityOfLetterSequence() {
        var input = "aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa";
        var expected = "2";
        Utils.assertCase(input, new ParserDayFour(),GivenTask.First,expected);
        
    }
}
