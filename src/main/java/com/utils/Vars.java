package com.utils;

import com.page.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Vars {

    @Getter
    private boolean inited;

    @Getter @Setter
    private String baseUrl;

    @Getter @Setter
    private String project;

    @Getter @Setter
    private Page currentPage;

    @Getter @Setter
    private String currentStepId;

    @Getter
    private List<Page> pages;

    @Getter @Setter
    private String scenarioId;

    @Getter @Setter
    private String runId;

    public Vars() {
        populate();
    }
    private void populate() {
        pages = new ArrayList<>();
        inited = true;
    }


}
