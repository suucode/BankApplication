package shop.mtcoding.bank.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.domain.AudingTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // public으로 만들었지만 private로 인식됨 = new 못함!
//Builder로 생성자를 만들어둬서 빈생성자가 없으므로 JPA가 new를 할 수 없기때문에 걸어준다
@Getter
@Table(name = "users")
@Entity
public class User extends AudingTime { //시간을 안적어도 둘다 생성됨
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    //DB에는 EnumType이 없기때문에 DB에는 String으로 인식하게 할 수 있도록 하려고 사용
    @Column(unique = true, nullable = false)
    private UserEnum role; //ADMIN, CUSTOMER

    @Builder
    public User(Long id, String username, String password, String email, UserEnum role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

}
