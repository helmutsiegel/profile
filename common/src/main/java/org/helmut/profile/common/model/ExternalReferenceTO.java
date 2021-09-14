package org.helmut.profile.common.model;

import org.helmut.profile.common.validation.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExternalReferenceTO {
    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @URL(protocol = "https")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
