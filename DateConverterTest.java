package homeWork13;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("DateConverter Tests")
public class DateConverterTest {

    private static int testCounter = 0;

    @BeforeAll
    public static void init() {
        // set local date and time format in eng
        Locale.setDefault(Locale.ENGLISH);
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("Tests are done");
    }

    @BeforeEach
    public void setUp() {
        testCounter++;
        System.out.println(testCounter + " Test started");
    }

    @AfterEach
    public void tearDown() {
        System.out.println(testCounter + " Test is finished");
    }

    @ParameterizedTest
    @CsvSource({
            "25-10-2023, dd-MM-yyyy, dd-MM-yyyy, 25-10-2023",
            "25-10-2023, dd-MM-yyyy, MM/dd/yyyy, 10/25/2023",
            "25-10-2023, dd-MM-yyyy, yyyy-MM-dd, 2023-10-25",
            "25-10-2023, dd-MM-yyyy, dd MMM yyyy, 25 Oct 2023",
            "25-10-2023, dd-MM-yyyy, dd MMMM yyyy, 25 October 2023",

            "10/25/2023, MM/dd/yyyy, dd-MM-yyyy, 25-10-2023",
            "10/25/2023, MM/dd/yyyy, MM/dd/yyyy, 10/25/2023",
            "10/25/2023, MM/dd/yyyy, yyyy-MM-dd, 2023-10-25",
            "10/25/2023, MM/dd/yyyy, dd MMM yyyy, 25 Oct 2023",
            "10/25/2023, MM/dd/yyyy, dd MMMM yyyy, 25 October 2023",

            "2023-10-25, yyyy-MM-dd, dd-MM-yyyy, 25-10-2023",
            "2023-10-25, yyyy-MM-dd, MM/dd/yyyy, 10/25/2023",
            "2023-10-25, yyyy-MM-dd, yyyy-MM-dd, 2023-10-25",
            "2023-10-25, yyyy-MM-dd, dd MMM yyyy, 25 Oct 2023",
            "2023-10-25, yyyy-MM-dd, dd MMMM yyyy, 25 October 2023",

            "25 Oct 2023, dd MMM yyyy, dd-MM-yyyy, 25-10-2023",
            "25 Oct 2023, dd MMM yyyy, MM/dd/yyyy, 10/25/2023",
            "25 Oct 2023, dd MMM yyyy, yyyy-MM-dd, 2023-10-25",
            "25 Oct 2023, dd MMM yyyy, dd MMM yyyy, 25 Oct 2023",
            "25 Oct 2023, dd MMM yyyy, dd MMMM yyyy, 25 October 2023",

            "25 October 2023, dd MMMM yyyy, dd-MM-yyyy, 25-10-2023",
            "25 October 2023, dd MMMM yyyy, MM/dd/yyyy, 10/25/2023",
            "25 October 2023, dd MMMM yyyy, yyyy-MM-dd, 2023-10-25",
            "25 October 2023, dd MMMM yyyy, dd MMM yyyy, 25 Oct 2023",
            "25 October 2023, dd MMMM yyyy, dd MMMM yyyy, 25 October 2023",
    })
    @DisplayName("Positive Convert Date Test")
    public void testConvertDate_Positive(String inputDate, String inputFormat, String outputFormat, String expectedOutputDate) {
        String actualOutputDate = DateConverter.convertDate(inputDate, inputFormat, outputFormat);
        assertEquals(expectedOutputDate, actualOutputDate);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-10-25, 1000, yyyy-MM-dd",
            "2023-10-25, true, yyyy-MM-dd",
            "25/2023/10, MM/dd/yyyy, yyyy-MM-dd",
            "25/25/2023, MM/dd/yyyy, dd/MM/yyyy",
            "25 Oct 2023, dd MMMM yyyy, dd/MM/yyyy",
    })
    @DisplayName("Negative Convert Date Test")
    public void testConvertDate_Negative(String inputDate, String inputFormat, String outputFormat) {
        assertThrows(Exception.class, () -> DateConverter.convertDate(inputDate, inputFormat, outputFormat));
    }

    @ParameterizedTest
    @CsvSource({
            "25-10-2023, dd-MM-yyyy",
            "10/25/2023, MM/dd/yyyy",
            "2023-10-25, yyyy-MM-dd",
            "25 Oct 2023, dd MMM yyyy",
            "25 October 2023, dd MMMM yyyy",
    })
    @DisplayName("Positive Detect Date Format Test")
    public void testDetectDateFormat_Positive(String inputDate, String expectedFormat) {
        String detectedFormat = DateConverter.detectDateFormat(inputDate);
        assertEquals(expectedFormat, detectedFormat);
    }

    @ParameterizedTest
    @CsvSource({
            "string",
            "true",
            "25-10-23",
            "25/25/2023",
            "10-25-2023",
            "25 Oct",
    })
    @DisplayName("Negative Detect Date Format Test")
    public void testDetectDateFormat_Negative(String inputDate) {
        assertNull(DateConverter.detectDateFormat(inputDate));
    }
}

