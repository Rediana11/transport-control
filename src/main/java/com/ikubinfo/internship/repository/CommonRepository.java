package com.ikubinfo.internship.repository;

import java.util.List;


public interface CommonRepository<T>  {

    List<T> findByIsDeleted(boolean deleted);

}
