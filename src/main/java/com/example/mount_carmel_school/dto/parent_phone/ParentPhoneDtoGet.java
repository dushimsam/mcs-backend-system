package com.example.mount_carmel_school.dto.parent_phone;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.ParentPhone;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ParentPhoneDtoGet {
    private Long id;
    private String phone;
    public ParentPhoneDtoGet(ParentPhone parentPhone)
    {
        BeanUtils.copyProperties(parentPhone,this);
    }
}
