package com.jonathan.ApiSpringBoot.Service;

import com.jonathan.ApiSpringBoot.domain.Person;
import java.util.List;

public interface PersonService {

    public List<Person> getPerson();

    public void savePerson(Person person);

    public void deletePerson(Person person);

    public Person findPerson(Person person);
}
