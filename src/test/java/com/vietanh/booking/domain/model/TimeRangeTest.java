package com.vietanh.booking.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeRangeTest {

    // Cố định 1 ngày để test chỉ phụ thuộc giờ:phút
    private static LocalDateTime at(int hour, int minute) {
        return LocalDateTime.of(2026, 10, 7, hour, minute);
    }

    @Test
    void start_after_end_throws() {
        assertThrows(IllegalArgumentException.class, () -> new TimeRange(at(10, 0), at(9, 0)));
    }

    @Test
    void start_equal_to_end_throws() {
        assertThrows(IllegalArgumentException.class, () -> new TimeRange(at(9, 0), at(9, 0)));
    }

    @Test
    void null_time_throws() {
        assertThrows(IllegalArgumentException.class, () -> new TimeRange(null, at(9, 0)));
        assertThrows(IllegalArgumentException.class, () -> new TimeRange(at(9, 0), null));
    }

    @Test
    void adjacent_ranges_do_not_overlap() {
        TimeRange nineToTen = new TimeRange(at(9, 0), at(10, 0));
        TimeRange tenToEleven = new TimeRange(at(10, 0), at(11, 0));

        assertFalse(nineToTen.overlaps(tenToEleven));
        assertFalse(tenToEleven.overlaps(nineToTen));
    }

    @Test
    void intersecting_ranges_overlap() {
        TimeRange nineToTen = new TimeRange(at(9, 0), at(10, 0));
        TimeRange nineThirtyToTenThirty = new TimeRange(at(9, 30), at(10, 30));

        assertTrue(nineToTen.overlaps(nineThirtyToTenThirty));
        assertTrue(nineThirtyToTenThirty.overlaps(nineToTen));
    }

    @Test
    void range_inside_another_overlaps() {
        TimeRange morning = new TimeRange(at(9, 0), at(12, 0));
        TimeRange tenToEleven = new TimeRange(at(10, 0), at(11, 0));

        assertTrue(morning.overlaps(tenToEleven));
        assertTrue(tenToEleven.overlaps(morning));
    }

    @Test
    void separate_ranges_do_not_overlap() {
        TimeRange nineToTen = new TimeRange(at(9, 0), at(10, 0));
        TimeRange twoToThreePm = new TimeRange(at(14, 0), at(15, 0));

        assertFalse(nineToTen.overlaps(twoToThreePm));
        assertFalse(twoToThreePm.overlaps(nineToTen));
    }
}