package com.spapr.bookshow.controller;

import com.spapr.bookshow.Payload.UploadFileResponse;
import com.spapr.bookshow.controller.request.BookedMovieRequest;
import com.spapr.bookshow.controller.request.MovieRequest;
import com.spapr.bookshow.exception.UserNotFoundException;
import com.spapr.bookshow.factory.ResponseFactory;
import com.spapr.bookshow.model.Booked;
import com.spapr.bookshow.model.FileUpload;
import com.spapr.bookshow.model.User;
import com.spapr.bookshow.service.FileUploadService;
import com.spapr.bookshow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    UserService userServicel;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@ModelAttribute MovieRequest movieRequest, @RequestParam("file") MultipartFile file) {
        FileUpload dbFile = fileUploadService.storeFile(file,movieRequest);

        log.info("========== Start uploadFile file with ==========");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();
        return new UploadFileResponse((String) dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(), (String) dbFile.getId(), dbFile);
    }

    @GetMapping(value = "/allMovies/{id}")
    public ResponseEntity getAllUsers(@PathVariable(value = "id") Integer id){
        log.info("========== Start get all movies ==========");
        User user  = userServicel.getUserById(id);
        if (user == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, "No user found");

        }
        List<FileUpload> fileUpload = fileUploadService.getAllUser();
        String message = "";
        int responseCounter = fileUpload.size();
        if (responseCounter == 0) {
            message = "No movies to display";
        }
        log.info("========== user count ========== {}", responseCounter);

        System.out.print(responseCounter);
        return responseFactory.success(fileUpload, message);
    }

    @PostMapping("/uploadFile1")
    public UploadFileResponse uploadFile1(@RequestParam("file") MultipartFile file) {
        FileUpload dbFile = fileUploadService.storeFile1(file);

        log.info("========== Start uploadFile file with ==========");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();
        FileUpload fileUpload = new FileUpload();
        return new UploadFileResponse((String) dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(), (String) dbFile.getId(),fileUpload);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        log.info("========== Start uploadFile multiple files with ==========");
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile1(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) {
        log.info("========== Start downloading files with ==========");
        log.info("========== Start downloading files fileid =========={}",fileId);

        // Load file from database
        FileUpload dbFile = fileUploadService.getFile(fileId);

        byte[] image = dbFile.getData();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType())).body(image);

////        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
////                .path("/downloadFile/")
////                .path(dbFile.getId())
////                .toUriString();
////        File img = new File(fileDownloadUri);
//        try {
//            return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType())).body(Files.readAllBytes(img.toPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // return ResponseEntity.ok(new ByteArrayResource(dbFile.getData()));
////                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
////                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
////                .body(new ByteArrayResource(dbFile.getData()));
 //       return  ResponseEntity.ok().body(null);
    }

//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
//        log.info("========== Start downloading files with ==========");
//
//        // Load file from database
//        FileUpload dbFile = fileUploadService.getFile(fileId);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));
//
//    }


    @PostMapping(value = "/booked")
    public ResponseEntity bookedMovie(@Valid @RequestBody BookedMovieRequest bookedMovieRequest){
        Booked booked = fileUploadService.saveBookMovie(bookedMovieRequest);
        return responseFactory.success(booked, "Ticket booked successfully");
    }

    // Get single user
    @GetMapping(value = "/booked/{id}")
    public ResponseEntity getBookedById(@PathVariable(value = "id") String userId){

        log.info("========== Start get booked movies list by user ========== {}", userId);

        List<Booked> booked = fileUploadService.getMovieById(userId);
        if (booked == null) {
            return responseFactory.error(HttpStatus.BAD_REQUEST, "Movie not with this user");
        }
        log.info("========== found booked movies list ========== {}", booked);

        return responseFactory.success(booked, "");
    }




}
