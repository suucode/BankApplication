package shop.mtcoding.bank.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.domain.AudingTime;
import shop.mtcoding.bank.domain.user.User;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "account") //첫글자가 대문자이길 바라지않아서 적어주는것..
@Entity
public class Account extends AudingTime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private Long number; //MyBatis는 이름을 number로 쓰면 안됨.. 정확하게 accountNumber 이런식으로 써야 안헷갈리고 매핑됨...

    @Column(nullable = false, length = 50)
    private String ownerName; //계좌주 실명

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false)
    private Long balance; //잔액

    //특별한 설정을 하지않는 이상 커멜표기법은 DB에 _로 들어간다
    @Column(nullable = false)
    private Boolean isActive; //계좌 활성화여부

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    //Account를 select할 때 자동으로 join이 일어남 -> fetch를 lazy로 줘서 원할때만 가져올 수 있게 한다

    @Builder
    public Account(Long id, Long number, String ownerName, String password, Long balance, Boolean isActive, User user) {
        this.id = id;
        this.number = number;
        this.ownerName = ownerName;
        this.password = password;
        this.balance = balance;
        this.isActive = isActive;
        this.user = user;
    }

    
}
