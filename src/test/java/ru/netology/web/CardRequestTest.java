package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardRequestTest {

    @Test
    void shouldTestHappyPath() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Бродовая Диана");
        $("[data-test-id=phone] input").setValue("+79231112233");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldValidateWithEmptyNameField() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79231112233");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldValidateWithEmptyPhoneField() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Бродовая Диана");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldValidateWithEmptyAllFields() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldValidateWithLatinLetters() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Diana");
        $("[data-test-id=phone] input").setValue("+79231112233");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldValidatePhoneFieldWithoutPlus() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Бродовая Диана");
        $("[data-test-id=phone] input").setValue("79231112233");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldValidatePhoneFieldWithTooManyNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Бродовая Диана");
        $("[data-test-id=phone] input").setValue("7923111223344");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldValidatePhoneFieldWithTooFewNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Бродовая Диана");
        $("[data-test-id=phone] input").setValue("7923111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldValidateAgreement() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Бродовая Диана");
        $("[data-test-id=phone] input").setValue("+79231112233");
        $("button").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}
