package com.pranav.brothers.batch.processor;

import java.util.Arrays;

import org.springframework.batch.item.ItemProcessor;

import com.pranav.brothers.beans.InputBean;
import com.pranav.brothers.beans.OutputBean;
import com.pranav.brothers.beans.OutputBean.Person;
import com.pranav.brothers.beans.OutputBean.Person.Address;
import com.pranav.brothers.beans.OutputBean.Person.Contact;

public class InputBeanProcessor implements ItemProcessor<InputBean, OutputBean> {

    @Override
    public OutputBean process(InputBean inputBean) throws Exception {
    	OutputBean outputBean = new OutputBean();
    	outputBean.setCompanyName(inputBean.getCompanyName());
    	
    	Person person = new Person();
    	person.setFirstName(inputBean.getFirstName());
    	person.setLastName(inputBean.getLastName());

    	Contact contact  = new Contact();
    	contact.setPhone(inputBean.getPhone());
    	contact.setPhone1(inputBean.getPhone1());
    	contact.setEmail(inputBean.getEmail());
    	person.setContacts(Arrays.asList(contact));
    	
    	Address address = new Address();
    	address.setAddress(inputBean.getAddress());
    	address.setCountry(inputBean.getCountry());
    	address.setCity(inputBean.getCity());
    	address.setState(inputBean.getState());
    	address.setZip(inputBean.getZip());
    	person.setAddresses(Arrays.asList(address));
    	
    	outputBean.setPerson(person);
    	 
        return outputBean;
    }

}
