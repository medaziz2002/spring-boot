package com.aziz.demo.phone;


import org.springframework.data.rest.core.config.Projection;

@Projection( name ="phoneName" ,types = {Phone.class})
public interface PhoneProjection {
   public String getPhoneName();
}
