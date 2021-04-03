package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDelivery {


    @Test
    void shouldTestAllFields() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String dayVisit = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Вася Пупкин");
        $("[data-test-id=phone] input").setValue("+79876543210");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dayVisit));
    }
}
