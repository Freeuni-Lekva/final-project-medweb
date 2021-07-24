package freeuni.edu.ge;

import java.util.Objects;

public class Request {

        private String name;
        private String surname;
        private String ID;

        public Request(String name, String surname, String ID){
            this.name = name;
            this.surname = surname;
            this.ID = ID;
        }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(name, request.name) && Objects.equals(surname, request.surname) && Objects.equals(ID, request.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, ID);
    }
}
