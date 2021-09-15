package org.helmut.profile.common.model;

import org.helmut.profile.common.ValidityTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateChapterTOTest extends ValidityTester {

    @Test
    @DisplayName("Validate empty CreateTransferTO, should return two violations")
    public void validateCreateChapter1(){
        assertEquals(validator.validate(new CreateChapterTO()).size(), 2);
    }

    @Test
    @DisplayName("Validate CreateTransferTO no title set, should return one violations")
    public void validateCreateChapter2(){
        CreateChapterTO createChapterTO = new CreateChapterTO();
        createChapterTO.setProjectName("Project");
        assertEquals(validator.validate(createChapterTO).size(), 1);
    }

    @Test
    @DisplayName("Validate CreateTransferTO no project set, should return one violations")
    public void validateCreateChapter3(){
        CreateChapterTO createChapterTO = new CreateChapterTO();
        createChapterTO.setTitle("Title");
        assertEquals(validator.validate(createChapterTO).size(), 1);
    }

    @Test
    @DisplayName("Validate CreateTransferTO short title, should return one violations")
    public void validateCreateChapter4(){
        CreateChapterTO createChapterTO = new CreateChapterTO();
        createChapterTO.setTitle("");
        createChapterTO.setProjectName("Project");
        assertEquals(validator.validate(createChapterTO).size(), 1);
    }
}