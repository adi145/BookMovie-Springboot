package com.spapr.bookshow.repository;

import com.spapr.bookshow.model.Booked;
import com.spapr.bookshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookedMovieRepository extends JpaRepository<Booked, Integer> {
    @Query("select u from Booked u where u.userId = :userId")
    List<Booked> findTheBookedMoviesListWithUserId(@Param("userId") String userId);

}
