package Hibernate.dao;


import Hibernate.models.Note;
import Hibernate.models.User;

import java.util.List;

public interface DaoInterface {
    List<Note> select(User users);

    List<Note> delete(User users, int id);


    void registr(User users);

    List<Note> searchById(User users, int i);

    List<Note> searchNotId(User users, String name, String imp, String date);
}
