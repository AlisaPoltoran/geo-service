package sender_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MessageSenderTest {

    @Test
    void sendRusMessage() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        String result = messageSender.send(headers);

        Assertions.assertEquals(result, "Добро пожаловать");
    }

    @Test
    void sendEngMessage() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String result = messageSender.send(headers);

        Assertions.assertEquals(result, "Welcome");
    }

}
