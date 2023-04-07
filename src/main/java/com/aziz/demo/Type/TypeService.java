package com.aziz.demo.Type;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

   final TypeRepository typeRepository;
    public Type createType(Type type)
    {
        return typeRepository.save(type);
    }
    public Page<Type> getAllTypesParPage(int page, int size) {
        return typeRepository.findAll(PageRequest.of(page, size));
    }


    public List<Type> findAll()
    {
        return typeRepository.findAll();
    }
    public void deleteTypeById( Integer id)
    {
        typeRepository.deleteById(id);
    }
}
