package com.aziz.demo.phone;


import com.aziz.demo.Type.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {

    final PhoneRepository phoneRepository;
    public Phone createPhone(Phone phone)
    {
        return phoneRepository.save(phone);
    }


    public Phone savePhone(Phone phone)

    {
        return phoneRepository.save(phone);
    }

    public void deletePhoneById( Integer id)
    {
        phoneRepository.deleteById(id);
    }

    public Page<Phone> getAllPhonesParPage(int page, int size) {
        return phoneRepository.findAll(PageRequest.of(page, size));
    }
    public Phone getPhone(Integer id) {
        return phoneRepository.findById(id).get();
    }
    public Phone updatePhone(Phone p) {
        return   phoneRepository.save(p);
    }

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public List<Phone> findByNamePhone(String nom) {
        return phoneRepository.findByPhoneName(nom);
    }
  public List<Phone> findByNamePhoneContains(String nom) {
        return phoneRepository.findByPhoneNameContains(nom);
    }

    public List<Phone> findByNamePrice(String nom, BigDecimal prix) {
        return phoneRepository.findByNamePrice(nom, prix);
    }

    public List<Phone> findByCategorie(Type type) {
        return phoneRepository.findByType(type);
    }

    public List<Phone> findByTypeIdCat(Integer id) {
        return phoneRepository.findByTypeIdType(id);
    }

    public List<Phone> findByOrderByNomProduitAsc() {
        return phoneRepository.findByOrderByPhoneNameAsc();
    }

    public List<Phone> trierPhonesNamePrice() {
        return phoneRepository.trierPhonesNamePrice();
    }


    }


