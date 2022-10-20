package com.hanghae99chapter5homework.domain;

import com.hanghae99chapter5homework.domain.request.AccountRequestDto;
import com.hanghae99chapter5homework.global.Timestamped;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Builder
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String password;

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Account account = (Account) o;
        return id != null && Objects.equals(id,account.id);
    }

    public void update(AccountRequestDto accountRequestDto){
        this.nickname = accountRequestDto.getNickname();
        this.password = accountRequestDto.getPassword();
    }


}
