package com.amazingco.companystructure.repositories;

import com.amazingco.companystructure.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query(value = "SELECT * FROM Member p WHERE p.parent = ?1 OR p.parent IN (SELECT p.id FROM Member p WHERE p.parent = ?1)", nativeQuery = true)
    List<MemberEntity> getAllDescendantByMemberId(Long memberId);

}
