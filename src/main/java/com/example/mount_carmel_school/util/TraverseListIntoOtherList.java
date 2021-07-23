package com.example.mount_carmel_school.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraverseListIntoOtherList {
    private List<Object> copyFromList;
    private List<Object> copyToList;

//    public  List<Object> copy(List<Object> copyFromList,List<Object> copyToList)
//    {
//        for(Object item:copyFromList)
//        {
//            copyToList.add(new Object(item));
//        }

}
