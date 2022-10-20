package com.hanghae99chapter5homework.domain;

import com.hanghae99chapter5homework.global.Timestamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;


}
