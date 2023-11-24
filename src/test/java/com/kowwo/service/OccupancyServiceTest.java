package com.kowwo.service;

import com.kowwo.model.BookingStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

@SpringBootTest
public class OccupancyServiceTest {

    @Autowired
    private OccupancyService occupancyService;

    @ParameterizedTest
    @MethodSource("shouldReturnCorrectBookingStatusTestData")
    void shouldReturnCorrectBookingStatus(int economyRooms, int premiumRooms, BookingStatus expected) {
        BookingStatus status = occupancyService.calculateRevenue(economyRooms, premiumRooms);

        assertEquals(expected, status);
    }

    private static Stream<Arguments> shouldReturnCorrectBookingStatusTestData() {
        return Stream.of(
                Arguments.of(3, 3, new BookingStatus(3, 167, 3, 738)),
                Arguments.of(5, 7, new BookingStatus(4, 189, 6, 1054)),
                Arguments.of(99, 99, new BookingStatus(4, 189, 6, 1054)),
                Arguments.of(7, 2, new BookingStatus(4, 189, 2, 583)),
                Arguments.of(1, 10, new BookingStatus(1, 22, 9, 1221)),
                Arguments.of(0, 0, new BookingStatus(0, 0, 0, 0)),
                Arguments.of(10, 0, new BookingStatus(4, 189, 0, 0)),
                Arguments.of(0, 10, new BookingStatus(0, 0, 10, 1243)),
                Arguments.of(-1, -1, new BookingStatus(0, 0, 0, 0)),
                Arguments.of(-1, 3, new BookingStatus(0, 0, 3, 738)),
                Arguments.of(3, -1, new BookingStatus(3, 167, 0, 0))
        );
    }
}
