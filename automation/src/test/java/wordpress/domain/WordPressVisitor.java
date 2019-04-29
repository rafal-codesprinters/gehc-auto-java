package wordpress.domain;

import java.util.UUID;

public class WordPressVisitor {
    private String name;
    private String email;

    public WordPressVisitor() {
        this.name = UUID.randomUUID().toString();
        this.email = this.name + "@test.domain.com";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
