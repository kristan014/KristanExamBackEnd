package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByMobileNumber(String mobileNumber);

    // order by id
    List<User> findByOrderByIdAsc();

    // get by email where not equal to id
    @Query("SELECT u FROM User u WHERE u.email = :email AND (u.id <> :id OR :id IS NULL)")
    List<User> findByEmailWhereIdNotEqual(@Param("email") String email, @Param("id") Long id);

    // get by mobile number where not equal to id
    @Query("SELECT u FROM User u WHERE u.mobileNumber = :mobileNumber AND (u.id <> :id OR :id IS NULL)")
    List<User> findByMobileNumberWhereIdNotEqual(String mobileNumber,Long id);

    // get starting letter of firstname
    List<User> findByFirstNameStartingWithIgnoreCase(String keyword);

    // get starting letter of lastname
    List<User> findByLastNameStartingWithIgnoreCase(String keyword);


    }
    

