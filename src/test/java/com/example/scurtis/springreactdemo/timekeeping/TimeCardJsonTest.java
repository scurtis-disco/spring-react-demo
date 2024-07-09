package com.example.scurtis.springreactdemo.timekeeping;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@JsonTest
class TimeCardJsonTest {

    @Autowired
    private JacksonTester<TimeCard> timeCardJson;

    @Value("classpath:data/timecard/expected-tc.json")
    Resource resourceFile;

    UUID timeCardUuid = UUID.fromString("afb46619-8354-42df-9250-28505920da3a");
    UUID timeCardEntryId = UUID.fromString("54292efb-750d-41dc-a521-ce1ea1f4242e");

    Employee testEmployee = Employee.builder()
            .withEmployeeId(1234567L)
            .withFirstName("Sean")
            .withLastName("Curtis")
            .withDescription("This is a description")
            .build();



    @Test
    void timeCardSerializationTest() throws IOException {
        TimeCardEntry timeCardEntry = TimeCardEntry.builder()
                .withTimeCard(
                    TimeCard.builder()
                        .withTimeCardUuid(timeCardUuid)
                        .build()
                )
                .withStartDayTime(1719403200L)
                .withEndDayTime(1719435600L)
                .withTimeCardEntryUuid(timeCardEntryId)
                .build();
        List<TimeCardEntry> tces = new ArrayList<>();
        tces.add(timeCardEntry);
        TimeCard timeCard = TimeCard.builder()
                .withEmployee(
                        Employee.builder()
                            .withEmployeeId(1234567L)
                            .build()
                )
                .withIsApproved(true)
                .withTimeCardUuid(timeCardUuid)
                .withTimeCardEntries(
                    tces
                )
                .build();
        // write object as JSON
        JsonContent<TimeCard> jsonContent = timeCardJson.write(timeCard);
         log.info("json --> {}", timeCardJson.write(timeCard).toString());
        // check values
        assertThat(jsonContent).hasJsonPathStringValue("timeCardUuid");
        assertThat(jsonContent).extractingJsonPathStringValue("timeCardUuid")
                .isEqualTo(timeCard.getTimeCardUuid().toString());
        assertThat(jsonContent).hasJsonPathMapValue("employee");
        assertThat(jsonContent).extractingJsonPathMapValue("employee")
                .extracting("employeeId").isEqualTo(timeCard.getEmployee().getEmployeeId().intValue());
        assertThat(jsonContent).hasJsonPathBooleanValue("approved");
        assertThat(jsonContent).extractingJsonPathBooleanValue("approved")
                .isEqualTo(timeCard.isApproved());
        assertThat(jsonContent).hasJsonPath("timeCardEntries");
        assertThat(jsonContent).extractingJsonPathArrayValue("timeCardEntries").hasSizeGreaterThan(0);
    }

    @Test
    void timeCardDeserializationTest() throws IOException {
        // get string content from sample
        String expected = resourceFile.getContentAsString(Charset.defaultCharset());
        List<TimeCardEntry> tces = new ArrayList<>();
        tces.add(TimeCardEntry.builder()
//            .withTimeCard(
//                    TimeCard.builder()
//                    .withTimeCardUuid(timeCardUuid)
//                    .withIsApproved(true)
//                    .build()
//            )
//                .withTimeCardUuid(timeCardUuid)
            .withStartDayTime(1719403200L)
            .withEndDayTime(1719435600L)
            .withTimeCardEntryUuid(timeCardEntryId)
            .build()
        );
        TimeCard timeCard = TimeCard.builder()
                .withEmployee(
                    testEmployee
                )
                .withIsApproved(true)
                .withTimeCardUuid(timeCardUuid)
                .withTimeCardEntries(
                        tces
                )
                .build();

        // raw comparison

        // object comparisons after parsing
        TimeCard parsedExpected = timeCardJson.parseObject(expected);
        assertThat(parsedExpected.getTimeCardUuid()).isEqualTo(timeCard.getTimeCardUuid());
        assertThat(parsedExpected.isApproved()).isEqualTo(timeCard.isApproved());
        assertThat(parsedExpected.getEmployee().getEmployeeId()).isEqualTo(timeCard.getEmployee().getEmployeeId());
        log.info("time card entries --> {}", parsedExpected.getTimeCardEntries().toString());
//        assertThat(parsedExpected.getTimeCardEntries()).containsExactlyInAnyOrderElementsOf(timeCard.getTimeCardEntries());
    }

    @Test
    void getTimeCardUuid() {
    }

    @Test
    void getEmployeeId() {
    }

    @Test
    void getTimeCardEntries() {
    }

    @Test
    void isApproved() {
    }

    @Test
    void setTimeCardUuid() {
    }

    @Test
    void setEmployeeId() {
    }

    @Test
    void setTimeCardEntries() {
    }

    @Test
    void setApproved() {
    }
}