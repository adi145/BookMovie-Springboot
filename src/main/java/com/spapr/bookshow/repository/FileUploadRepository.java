package com.spapr.bookshow.repository;

import com.spapr.bookshow.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
}
