package com.spring.web.dao;

import com.spring.web.model.request.ImportPhoto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImportPhotoMapper {


    void savePhoto(ImportPhoto importPhoto);

    List<ImportPhoto> selectPhoto(@Param("userId") Integer userId);

    Boolean deletePhoto(Integer id);

    List<ImportPhoto> selectOne(@Param("userId")Integer userId, @Param("url")String url);

    void updatePhoto(@Param("id")Integer id, @Param("url")String url);
}
