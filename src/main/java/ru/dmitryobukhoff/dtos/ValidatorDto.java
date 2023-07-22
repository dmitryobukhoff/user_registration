package ru.dmitryobukhoff.dtos;

import ru.dmitryobukhoff.connection.DatabaseConnection;
import ru.dmitryobukhoff.daos.ValidatorDao;
import ru.dmitryobukhoff.models.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValidatorDto implements ValidatorDao {

    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    private Connection connection = databaseConnection.getConnection();

    @Override
    public Person getPersonByLogin(String login) {
        Person person = null;
        String request = "select * from users where login='" + login + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            if(resultSet.next()){
                person = new Person(
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Override
    public Person getPersonByEmail(String email) {
        Person person = null;
        String request = "select * from users where email='" + email + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            if(resultSet.next()){
                person = new Person(
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
}
