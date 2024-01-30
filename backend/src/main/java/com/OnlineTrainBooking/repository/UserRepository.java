package com.OnlineTrainBooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.OnlineTrainBooking.entity.Role;
import com.OnlineTrainBooking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByRole_RolenameIs(String roleName);
	
	@Query("select u from User u where u.email=?1")
	Optional<User> findByEmail(String email);
//	 @Modifying
//	    @Query("DELETE FROM User_Role ur WHERE ur.User_Id.username = :username")
//	    void deleteUserRoleByUsername(@Param("username") String username);

//	@Query("Select u from User u where u.name=?1")
//	public User getuserName(String name);
}
