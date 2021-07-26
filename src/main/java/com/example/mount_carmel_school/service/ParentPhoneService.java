package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoGet;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.ParentPhone;
import com.example.mount_carmel_school.repository.ParentPhoneRepository;
import com.example.mount_carmel_school.repository.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ParentPhoneService {

    @Autowired
    private ParentPhoneRepository parentPhoneRepository;

    @Autowired
    private ParentRepository parentRepository;


    public ParentPhoneDtoGet add(ParentPhoneDtoPost parentPhonePost)
    {
        Boolean phoneExists = parentPhoneRepository.selectsPhoneExists(parentPhonePost.getPhone());
        if(phoneExists)
        {
            throw new ApiRequestException(" Phone Exists ");
        }else{
            ParentPhone parentPhone = new ParentPhone();
            BeanUtils.copyProperties(parentPhonePost,parentPhone);
           return new ParentPhoneDtoGet(parentPhoneRepository.save(parentPhone));
        }
    }


    public ParentPhoneDtoGet update(Long parentPhoneId,ParentPhoneDtoPost parentPhonePost)
    {
        ParentPhone parentPhone = parentPhoneRepository.findById(parentPhoneId).orElseThrow(()-> new NotFoundException("Parent phone"));
        BeanUtils.copyProperties(parentPhonePost,parentPhone);
        return new ParentPhoneDtoGet(parentPhoneRepository.save(parentPhone));

    }




    public ParentPhoneDtoGet get(Long phoneId)
    {
       ParentPhone parentPhone = parentPhoneRepository.findById(phoneId).orElseThrow(()-> new NotFoundException("Parent phone"));
       return new ParentPhoneDtoGet(parentPhone);
    }

    public DeleteResponseDto delete(Long phoneId)
    {
        ParentPhone parentPhone = parentPhoneRepository.findById(phoneId).orElseThrow(()-> new NotFoundException("Parent phone"));
        parentPhoneRepository.delete(parentPhone);
        return new DeleteResponseDto(parentPhone);
    }


    public List<ParentPhoneDtoGet> getAll()
    {
       List<ParentPhone> phones = parentPhoneRepository.findAll();
        List<ParentPhoneDtoGet> parentPhoneGets = new ArrayList<>();
        for(ParentPhone parentPhone:phones)
       {
           parentPhoneGets.add(new ParentPhoneDtoGet(parentPhone));
       }
        return parentPhoneGets;
    }

    public ParentPhoneDtoGet addPhoneToParent(ParentPhoneDtoPost parentPhonePost)
    {
        ParentPhone parentPhone = new ParentPhone();
        Parent parent = parentRepository.findById(parentPhonePost.getParentId()).orElseThrow(()->new NotFoundException("Parent"));
        ParentPhone duplicates = parentPhoneRepository.findParentPhoneByParentAndPhone(parent,parentPhonePost.getPhone());

        if(duplicates != null)
        {
            throw new ApiRequestException("The Parent-Phone is already registered");
        }
            BeanUtils.copyProperties(parentPhonePost,parentPhone);
            parentPhone.setParent(parent);
            return  new ParentPhoneDtoGet(parentPhoneRepository.save(parentPhone));

    }

}
