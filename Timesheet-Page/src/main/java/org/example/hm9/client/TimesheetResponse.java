package org.example.hm9.client;

import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
public class TimesheetResponse {

    private Long timesheetId;

    private Long timesheetProjectId;

    private Long timesheetEmployeeId;

    private Integer minutes;

    private LocalDate createdAt;
}
