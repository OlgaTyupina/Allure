import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class DataGeneration {
    private DataGeneration () {}

    private static Faker faker = new Faker(new Locale("ru"));
    private static String firstname = faker.name().firstName();
    private static String lastname = faker.name().lastName();
    private static String city = faker.address().city();
    private static String phone = faker.phoneNumber().phoneNumber();

    public static String getName() {

        return firstname + " " + lastname;
    }

    public static String getCity() {
        return "Барнаул";
    }

    public static String getPhone() {
        return phone;
    }

    static String getNewDate() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }
    public static void cleanUp() {
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }
    public static String getFutureDate() {
        LocalDate newDate = LocalDate.now().plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return newDate.format(formatter);
    }

}
