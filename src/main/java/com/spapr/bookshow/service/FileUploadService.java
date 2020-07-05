package com.spapr.bookshow.service;

import com.spapr.bookshow.controller.request.BookedMovieRequest;
import com.spapr.bookshow.controller.request.MovieRequest;
import com.spapr.bookshow.exception.FileStorageException;
import com.spapr.bookshow.exception.MyFileNotFoundException;
import com.spapr.bookshow.model.Booked;
import com.spapr.bookshow.model.FileUpload;
import com.spapr.bookshow.model.User;
import com.spapr.bookshow.repository.BookedMovieRepository;
import com.spapr.bookshow.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileUploadService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Autowired
    BookedMovieRepository bookedMovieRepository;
    @Autowired
    UserService userService;


    public FileUpload storeFile(MultipartFile file, MovieRequest movieRequest) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileUpload dbFile = new FileUpload(fileName, file.getContentType(), file.getBytes(),movieRequest.getName(),movieRequest.getDescription(),movieRequest.getCompany(),movieRequest.getDirector(),movieRequest.getProducer(),movieRequest.getInitialReleaseDate(),movieRequest.getActors());

            return fileUploadRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public List<FileUpload> getAllUser() {
        List<FileUpload> fileUpload = fileUploadRepository.findAll();
        return fileUpload;
    }

    public FileUpload storeFile1(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileUpload dbFile = new FileUpload(fileName, file.getContentType(), file.getBytes(),"","","","","","","");

            return fileUploadRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public FileUpload getFile(String fileId) {
        return fileUploadRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public Booked saveBookMovie(BookedMovieRequest bookedMovieRequest){
        User user = userService.getUserById(new Integer(bookedMovieRequest.getUser_id()));
        FileUpload fileUpload =  getFile(bookedMovieRequest.getMovie_id());
        Booked booked = new Booked();
       // booked.setId(new String(String.valueOf(user.getId())));
        booked.setUserId(user.getId().toString());
        booked.setCompany(fileUpload.getCompany());
        booked.setMovieName(fileUpload.getMovieName());
        booked.setDescription(fileUpload.getDescription());
        booked.setDirector(fileUpload.getDirector());
        booked.setProducer(fileUpload.getProducer());
        booked.setData(fileUpload.getData());
        booked.setMovieId(fileUpload.getId());
        booked.setInitialReleaseDate(fileUpload.getInitialReleaseDate());
        booked.setActors(fileUpload.getActors());
        Booked booked1 = bookedMovieRepository.save(booked);
        return booked1;
    }

    public List<Booked> getMovieById(String userId){
        List<Booked> booked = bookedMovieRepository.findTheBookedMoviesListWithUserId(userId);
        return booked;
    }
}
