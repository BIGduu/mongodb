package com.bigduu.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Document
public class User {
    @Id
    private BigInteger id;

    private String firstName;
    private String lastName;

    @Version
    private Integer version;
}
