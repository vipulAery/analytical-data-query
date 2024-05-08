//package com.example.analyticaldataquery;
//
//import com.example.analyticaldataquery.model.Query;
//import com.example.analyticaldataquery.model.UserActivityEvent;
//import com.example.analyticaldataquery.service.UserActivityEventService;
//import jakarta.annotation.PostConstruct;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.*;
//
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDateTime;
//
//
//@DataJpaTest
//public class UserActivityEventServiceTest {
//    @Autowired
//    private TestEntityManager entityManager;
//
//    private UserActivityEventService userActivityEventService;
//    @PostConstruct
//    public void init() {
//        userActivityEventService = new UserActivityEventService(entityManager.getEntityManager());
//    }
//
//    @Test
//    public void testExample() {
//        this.entityManager.persist(new UserActivityEvent("id100", "actor100", "action100", "resource100", LocalDateTime.of(2024, 5, 5, 22, 42)));
//        this.entityManager.persist(new UserActivityEvent("id101", "actor101", "action101", "resource101", LocalDateTime.of(2024, 5, 5, 22, 42)));
//
//        assertThatList(userActivityEventService.searchByQuery(new Query("eventId==id100, action==action101", 0, 1))).size().isEqualTo(1);
//        assertThatList(userActivityEventService.searchByQuery(new Query("eventId==id100, action==action101", 0, 2))).size().isEqualTo(2);
//        assertThatList(userActivityEventService.searchByQuery(new Query("eventId==id100, action==action101", 0, 2))).element(0).satisfies(userActivityEvent -> userActivityEvent.eventId.equals("id101"));
//    }
//}
