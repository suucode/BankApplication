package shop.mtcoding.bank.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> { //엔티티의 타입, PK(id)의 타입
    
    @Query("select u from User u where username = :username") //NamedQuery가 있어서 안써도되지만 일관적으로 쓰기위해서 쓴다
    Optional<User> findByUsername(@Param("username") String username);
    
}
