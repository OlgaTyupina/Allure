import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Test
    @DisplayName("Должны заполняться все данные, потом данные остаются, меняется только дата и перепланируется встреча")
    void shouldFillInTheForm() {
        open("http://localhost:9999");
        $("[placeholder ='Город']").setValue(DataGeneration.getCity());
        DataGeneration.cleanUp();
        $("[placeholder='Дата встречи']").setValue(DataGeneration.getNewDate());
        $("[data-test-id=name] input").setValue(DataGeneration.getName());
        $("[data-test-id=phone] input").setValue(DataGeneration.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        DataGeneration.cleanUp();
        $("[placeholder='Дата встречи']").setValue(DataGeneration.getFutureDate());
        $(".button__text").click();
        $("[data-test-id='replan-notification']").waitUntil(Condition.visible,
                5000);
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }
}