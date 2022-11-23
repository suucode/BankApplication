package shop.mtcoding.bank.domain.transaction;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.config.enums.TransactionEnum;
import shop.mtcoding.bank.domain.AudingTime;
import shop.mtcoding.bank.domain.account.Account;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "transaction")
@Entity
public class Transaction extends AudingTime{ //Import할때 주의... 같은게 많아서..ㅠ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50) //붙이는 이유는 제약조건 주려고
    private Long number;

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) //걸어주면 연결은 되는데 foreignKey 제약조건을 없애줘서 null이 들어갈 수 있다
    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(nullable = false, length = 50)
    private Account withdrawAccount; //출금계좌 

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount; //입금계좌 

    @Column(nullable = false)
    private Long amount; //금액

    private Long withdrawAccountBalance; //거래 후 출금계좌의 잔액
    private Long depositAccountBalance; //거래 후 입금계좌의 잔액

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum gubun; //입금(ATM으로부터), 출금(ATM으로), 이체(다른 계좌로)

    @Builder
    public Transaction(Long id, Long number, Account withdrawAccount, Account depositAccount, Long amount,
            Long withdrawAccountBalance, Long depositAccountBalance, TransactionEnum gubun) {
        this.id = id;
        this.number = number;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
    }

    
}
