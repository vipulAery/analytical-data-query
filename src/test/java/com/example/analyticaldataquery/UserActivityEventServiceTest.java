package com.example.analyticaldataquery;

import com.example.analyticaldataquery.exception.InvalidQueryException;
import com.example.analyticaldataquery.model.Query;
import com.example.analyticaldataquery.model.UserActivityEvent;
import com.example.analyticaldataquery.service.UserActivityEventService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class UserActivityEventServiceTest {
    UserActivityEvent userActivityEvent1 = new UserActivityEvent("id100", "actor100", "action100", "resource100", LocalDateTime.of(2024, 5, 5, 22, 42));
    UserActivityEvent userActivityEvent2 = new UserActivityEvent("id101", "actor101", "action101", "resource101", LocalDateTime.of(2024, 5, 5, 22, 42));

    private final EntityManager entityManager;
    private final UserActivityEventService userActivityEventService;

    public UserActivityEventServiceTest(@Autowired EntityManager entityManager) {
        this.entityManager = entityManager;
        this.userActivityEventService = new UserActivityEventService(entityManager);
    }

    @BeforeEach
    public void setUp() {
        this.entityManager.persist(userActivityEvent1);
        this.entityManager.persist(userActivityEvent2);
    }

    @Test
    public void validateUserActivityEvent() {
        var userActivityEvents = userActivityEventService.searchByQuery(new Query("eventId==id100, action==action101", 0, 2));
        assertThatList(userActivityEvents).element(0).satisfies(userActivityEvent -> assertEquals("id101", userActivityEvent.eventId));
        assertThatList(userActivityEvents).element(1).satisfies(userActivityEvent -> assertEquals("id100", userActivityEvent.eventId));
    }

    @Test
    public void validateUserActivityEventQueryAndLogic() {
        assertThatList(userActivityEventService.searchByQuery(new Query("eventId==id100; action==action101", 0, 1))).size().isEqualTo(0);
    }

    @Test
    public void validateUserActivityEventPagination() {
        assertThatList(userActivityEventService.searchByQuery(new Query("eventId==id100, action==action101", 0, 1))).size().isEqualTo(1);
        assertThatList(userActivityEventService.searchByQuery(new Query("eventId==id100, action==action101", 0, 2))).size().isEqualTo(2);
    }


    @Test
    public void validateUserActivityInvalidQuery() {
        assertThrows(IllegalArgumentException.class, () -> userActivityEventService.searchByQuery(new Query("eventd==id100, action==action101", 0, 1)));
        assertThrowsExactly(InvalidQueryException.class, () -> userActivityEventService.searchByQuery(new Query("eventd==id100, action==action101", 0, 1)));
    }

    @AfterEach
    public void tearDown() {
        this.entityManager.remove(userActivityEvent1);
        this.entityManager.remove(userActivityEvent2);
    }
}
