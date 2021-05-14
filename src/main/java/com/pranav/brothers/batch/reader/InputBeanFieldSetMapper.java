package com.pranav.brothers.batch.reader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.pranav.brothers.beans.InputBean;

public class InputBeanFieldSetMapper implements FieldSetMapper<InputBean> {

    @Override
    public InputBean mapFieldSet(FieldSet fieldSet) throws BindException {
    	InputBean inputBean = new InputBean();
        inputBean.setFirstName(fieldSet.readString("first_name"));
        inputBean.setLastName(fieldSet.readString("last_name"));
        inputBean.setCompanyName(fieldSet.readString("company_name"));
        inputBean.setAddress(fieldSet.readString("address"));
        inputBean.setCity(fieldSet.readString("city"));
        inputBean.setCountry(fieldSet.readString("county"));
        inputBean.setState(fieldSet.readString("state"));
        inputBean.setZip(fieldSet.readString("zip"));
        inputBean.setPhone1(fieldSet.readString("phone1"));
        inputBean.setPhone(fieldSet.readString("phone"));
        inputBean.setEmail(fieldSet.readString("email"));
        return inputBean;
    }

}
