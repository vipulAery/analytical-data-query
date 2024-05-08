package com.example.analyticaldataquery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

@Entity
public class UserActivityEvent {
    @Id
    public String eventId;
    public String actor;
    public String action;
    public String resource;
    public LocalDateTime localDateTime;

}

