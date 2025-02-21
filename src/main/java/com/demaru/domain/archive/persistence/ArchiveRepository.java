package com.demaru.domain.archive.persistence;

import com.demaru.domain.archive.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
