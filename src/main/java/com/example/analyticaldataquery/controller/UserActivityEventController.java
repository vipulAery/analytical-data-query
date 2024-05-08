package com.example.analyticaldataquery.controller;

import com.example.analyticaldataquery.model.Query;
import com.example.analyticaldataquery.model.UserActivityEvent;
import com.example.analyticaldataquery.service.UserActivityEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserActivityEventController {
    private final UserActivityEventService userActivityEventService;

    @PostMapping("/user-activity-events")
    List<UserActivityEvent> userActivityEvents(@RequestBody Query query) {
        return userActivityEventService.searchByQuery(query);
    }

}
