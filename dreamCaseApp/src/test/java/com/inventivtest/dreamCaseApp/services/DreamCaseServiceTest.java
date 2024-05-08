package com.inventivtest.dreamCaseApp.services;

import com.inventivtest.dreamCaseApp.dto.DreamCaseDto;
import com.inventivtest.dreamCaseApp.entities.DreamCase;
import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseNotFoundException;
import com.inventivtest.dreamCaseApp.repositories.DreamCaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DreamCaseServiceTest {

    @Mock
    private DreamCaseRepository dreamCaseRepository;

    @InjectMocks
    private  DreamCaseService dreamCaseService;

    @Captor
    private ArgumentCaptor<DreamCase> argumentCaptor;

    @Captor
    private ArgumentCaptor<Long> caseIdargumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Nested
    class createDreamCase{


        // This test should create a dream case with success

        @Test
        @DisplayName("Test 1")
        void shouldCreateDreamCaseWithSuccess() {


            //Arrange
            var dCase = new DreamCase(
                    1L,
                    new Date("10/09/2022"),
                    new Date("10/09/2024"),
                    "Title1",
                    "Description 1"
            );

            doReturn(dCase).when(dreamCaseRepository).save(argumentCaptor.capture());

            var input = new DreamCaseDto(null,new Date("10/09/2022"), new Date("10/09/2024"),"Title1","Description 1");

            //Act
            var output = dreamCaseService.createDreamCase(input);

            //Assert
            assertNotNull(output);

            var caseCaptured = argumentCaptor.getValue();

            assertEquals(input.getCreationDate(), caseCaptured.getCreationDate());
            assertEquals(input.getLastUpdateDate(), caseCaptured.getLastUpdateDate());
            assertEquals(input.getTitle(), caseCaptured.getTitle());
            assertEquals(input.getDescription(), caseCaptured.getDescription());


        }



        //should throw exception when error occurs
        @Test
        @DisplayName("Test 1")
        void shouldThrowExceptionWhenErrorOccurs() {

            //Arrange

            doThrow(new RuntimeException()).when(dreamCaseRepository).save(argumentCaptor.capture());

            var input = new DreamCaseDto(null,new Date("10/09/2022"), new Date("10/09/2024"),"Title1","Description 1");

            //Act
            assertThrows(RuntimeException.class,()-> dreamCaseService.createDreamCase(input));


        }


    }

    @Nested
    class findCaseByCaseId{

        //This should get a DreamCase With CaseId
        @Test
        @DisplayName("Test 2")
        void shouldGetDreamCaseWithCaseIdOptionIsPresent() {
            // Arrange
            var dCase = new DreamCaseDto(
                    1L,
                    new Date("10/09/2022"),
                    new Date("10/09/2024"),
                    "Title1",
                    "Description 1"
            );

            // Mock do comportamento do repository
            doReturn(Optional.of(new DreamCase()))
                    .when(dreamCaseRepository)
                    .findById(caseIdargumentCaptor.capture());

            // Act
            dreamCaseService.getCaseByCaseId(dCase.getCaseId().longValue());

            // Assert
           // assertTrue(output.equals(dCase));
            assertEquals(dCase.getCaseId(), caseIdargumentCaptor.getValue());
        }


    }



    @Nested
    class deleteDreamCase{
        @Test
        @DisplayName("Test 3")
        void shouldDeleteDreamCaseWithSuccess() {
            Long id = 1L;
            DreamCase dreamCase = new DreamCase(); // Crie um caso de sonho fictício

            // Mock do comportamento do repository
            doReturn(Optional.of(dreamCase)).when(dreamCaseRepository).findCaseByCaseId(id);

            // Act
            dreamCaseService.deleteDreamCase(id);
            // Assert
            verify(dreamCaseRepository).findCaseByCaseId(id);
            verify(dreamCaseRepository).deleteById(id);
        }


        @Test
        @DisplayName("Test 3")
        void testDeleteDreamCaseNotExists() {
            // Arrange
            Long id = 1L;

            // Mock do comportamento do repository para retornar Optional.empty()
            doReturn(Optional.empty()).when(dreamCaseRepository).findCaseByCaseId(id);
            // Act & Assert
            assertThrows(DreamCaseNotFoundException.class, () -> dreamCaseService.deleteDreamCase(id));
        }
    }
}