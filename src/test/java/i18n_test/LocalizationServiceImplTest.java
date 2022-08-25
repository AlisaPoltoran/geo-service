package i18n_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {

    LocalizationServiceImpl sut = new LocalizationServiceImpl();

    @Test
    void localeRussiaTest() {
        Country country = Country.RUSSIA;

        String result = sut.locale(country);

        Assertions.assertEquals(result, "Добро пожаловать");

    }

    @ParameterizedTest
    @EnumSource(value = Country.class, mode = EXCLUDE, names = {"RUSSIA"})
    void localeNotRussiaTest(Country country) {
        String result = sut.locale(country);

        Assertions.assertEquals(result, "Welcome");
    }

}
