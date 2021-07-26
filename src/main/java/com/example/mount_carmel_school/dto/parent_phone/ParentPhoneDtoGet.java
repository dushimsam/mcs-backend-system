package com.example.mount_carmel_school.dto.parent_phone;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.ParentPhone;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ParentPhoneDtoGet {
    private Long id;
    private String phone;
    private Date createdAt;
    private ParentDtoGet parent;
    private Date lastModifiedAt;
    public ParentPhoneDtoGet(ParentPhone parentPhone)
    {
        BeanUtils.copyProperties(parentPhone,this);
        parent = new ParentDtoGet(parentPhone.getParent(),"PHONE");
    }

    public ParentPhoneDtoGet(ParentPhone parentPhone,String ignore)
    {
        BeanUtils.copyProperties(parentPhone,this);


        if(!ignore.equals("PARENT"))
        {
            parent = new ParentDtoGet(parentPhone.getParent(),"PHONE");

        }
    }
}
