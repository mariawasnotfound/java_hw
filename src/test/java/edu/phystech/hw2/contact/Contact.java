package edu.phystech.hw2.contact;

import java.util.regex.Pattern;

public record Contact(String username, String email) implements Comparable<Contact> {
    public static final String UNKNOWN_EMAIL = "UNKNOWN";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@gmail\\.com$");

    public Contact(String user) {
        this(user, UNKNOWN_EMAIL);
    }

    public Contact(String username, String email) {
        if (username == null || username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }

        if (!UNKNOWN_EMAIL.equals(email)) {
            if (email == null || email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
                throw new InvalidContactFieldException("email");
            }
        }

        this.username = username;
        this.email = email;
    }

    @Override
    public int compareTo(Contact other) {
        return Integer.compare(this.username.length(), other.username.length());
    }
}