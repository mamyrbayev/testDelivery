package com.delivery.testDelivery.file.repositories;

import com.delivery.testDelivery.file.models.entities.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileResourceRepository extends JpaRepository<FileResource, Long> {

    Optional<FileResource> getByFileName(String fileName);

}
