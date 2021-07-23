package com.example.mount_carmel_school.storage;

import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.util.GenerateRandomString;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@AllArgsConstructor
@Service
public class FileSystemStorageService {

    @Autowired
    private GenerateRandomString generateRandomString;

    private static final String IMAGES_USERS_FOLDER = "src/main/java/com/example/mount_carmel_school/assets/users/";


    public String saveUserFile(MultipartFile file){
        if (file.isEmpty()) {
           return  "Please select a file to upload";
        }
        try {
            String filename = IMAGES_USERS_FOLDER+"user-profile-"+generateRandomString.generateStringV2()+file.getOriginalFilename();
            File myFile = new File(filename);
            myFile.createNewFile();
            FileOutputStream fos =new FileOutputStream(myFile);
            fos.write(file.getBytes());
            fos.close();
            return filename;
        } catch (IOException e) {
            throw new ApiRequestException("Try again error occurred "+e.getMessage());
        }
    }

    public boolean deleteFile(String path)
    {
        File file = new File(path);
        return file.delete();
    }

}
