package shop.mtcoding.bank.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass //자식이 얘를 상속하는데 자식이 이 친구를 테이블의 Column으로 만들라는 것
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class AudingTime { //시간은 항상 써야해서 귀찮으므로 따로만들어주기, Extends 해서 사용할 예정 (자체를 new할 수 없도록 추상클래스로 생성)
    
    @LastModifiedDate // Insert, Update할 때 현재시간
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    @CreatedDate // Insert할 때 현재시간
    @Column(nullable = false)
    protected LocalDateTime createdAt;
}
