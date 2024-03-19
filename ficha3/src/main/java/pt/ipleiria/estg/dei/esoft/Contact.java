package pt.ipleiria.estg.dei.esoft;

import java.util.Date;
import java.util.Objects;

public class Contact {
        private String firstName;
        private String lastName;
        private Date birthday;
        private String phone;
        private String email;
        // TODO Generate constructors
        // TODO Generate getters and setters


        public Contact(String firstName, String phone) {
            this.firstName = firstName;
            this.phone = phone;
        }

        public Contact(String firstName, String lastName, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
        }

        public Contact(String firstName, String lastName, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
        }

        public Contact(String firstName, String lastName, Date birthday, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthday = birthday;
            this.phone = phone;
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
/*
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contact)) return false;
        var another = (Contact) o;
        if (this.firstName != another.firstName) return false;
        if (this.lastName != another.lastName) return false;
        if (this.birthday != another.birthday) return false;
        if (this.phone != another.phone) return false;
        if (this.email != another.email) return false;
        return true;
    }
        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, birthday, phone, email);
        }*/
@Override
public boolean equals(Object o) {
    return o instanceof Contact
            && (
            Objects.equals(this.phone, ((Contact) o).phone)
                    || Objects.equals(this.email, ((Contact) o).email)
    );
}
    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
    }
