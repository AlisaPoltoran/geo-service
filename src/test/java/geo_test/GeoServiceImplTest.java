package geo_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {

    GeoServiceImpl sut = new GeoServiceImpl();

    @BeforeEach
    public void init() {
        sut = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("ipAndLocations")
    void byIpTestExclNullIp(String input, Location location) {

        Location result = sut.byIp(input);
        Assertions.assertAll("result",
                () -> assertEquals(result.getCountry(),location.getCountry()),
                () -> assertEquals(result.getCity(), location.getCity()),
                () -> assertEquals(result.getStreet(),location.getStreet()),
                () -> assertEquals(result.getBuiling(),location.getBuiling()));
    }

    private static Stream<Arguments> ipAndLocations() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.1.45.15", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.66.883.147", new Location("New York", Country.USA, null,  0)));
    }

    @Test
    void byCoordinatesThrowExceptionTest() {
        double latitude = 345.55;
        double longitude = 34.44;

        Exception exception = assertThrows(RuntimeException.class, () ->
                sut.byCoordinates(latitude, longitude));
        assertEquals("Not implemented", exception.getMessage());
    }

}
