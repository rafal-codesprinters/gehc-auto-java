package testdata;

import java.util.UUID;

public class WpVisitor {
    private String NAME;
    private String EMAIL;

    public WpVisitor() {
        this.NAME = UUID.randomUUID().toString();
        this.EMAIL = this.NAME + "@test.domain.com";
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
}
