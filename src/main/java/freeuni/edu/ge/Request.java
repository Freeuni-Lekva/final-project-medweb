package freeuni.edu.ge;

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
}
