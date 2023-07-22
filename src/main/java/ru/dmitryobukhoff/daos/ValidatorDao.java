package ru.dmitryobukhoff.daos;

import ru.dmitryobukhoff.models.Person;

public interface ValidatorDao {
    Person getPersonByLogin(String login);
    Person getPersonByEmail(String email);
}
