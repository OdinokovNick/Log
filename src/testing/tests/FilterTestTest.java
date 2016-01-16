package testing.tests;

import exception.WrongKeyException;
import filters.Filter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;


/**
 * Created by Шерхан on 05.08.2015.
 */

public class FilterTestTest extends Assert {

    private String info = "info";
    private String warning = "warning";
    private String error = "error";
    private String fatal = "fatal";
    private String badFormatMessage = "badFormatMessage";

    @Test
    public void testIsAllowedInfo() throws WrongKeyException {
        Filter filter = new Filter(info);
        assertTrue(filter.isAllowed(info));
        assertTrue(filter.isAllowed(warning));
        assertTrue(filter.isAllowed(error));
        assertTrue(filter.isAllowed(fatal));
    }

    @Test
    public void testIsNotAllowedInfo() throws WrongKeyException {
        Filter warningFilter = new Filter(warning);
        Filter errorFilter = new Filter(error);
        Filter fatalFilter = new Filter(fatal);

        assertFalse(warningFilter.isAllowed(info));
        assertFalse(errorFilter.isAllowed(info));
        assertFalse(fatalFilter.isAllowed(info));
    }

    @Test(expected = WrongKeyException.class)
    public void testIsAllowedThrownException() throws WrongKeyException {
        Filter filter = new Filter(info);
        filter.isAllowed(badFormatMessage);
    }

    @Test(expected = WrongKeyException.class)
    public void testCreatedFilterWithException() throws WrongKeyException {
        new Filter(badFormatMessage);
    }
}
